package com.syncforge.metadata;

import com.syncforge.core.exceptions.ValidationException;
import com.syncforge.core.logging.SyncForgeLogger;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.AclEntry;
import java.nio.file.attribute.AclFileAttributeView;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Models the file type and permissions of a file system entry.
 * 
 * <p>Supports:
 * <ul>
 *   <li>File types: FILE, DIRECTORY, SYMLINK, HARDLINK</li>
 *   <li>POSIX 9-bit octal representations (e.g., 0755, 0644) and full 12-bit representation (including sticky, setuid, and setgid bits)</li>
 *   <li>POSIX Access Control Lists (ACLs) representations</li>
 *   <li>Windows Security Descriptor representations using Security Identifiers (SIDs) and Access Control Entries (ACEs)</li>
 * </ul>
 * </p>
 * 
 * <p>Provides robust platform integration to capture permissions dynamically across Windows and POSIX-compliant systems.</p>
 */
public final class FileMode {

    private static final SyncForgeLogger LOGGER = new SyncForgeLogger(FileMode.class);

    /**
     * Represents the specific filesystem element type.
     */
    public enum Type {
        /**
         * A regular file.
         */
        FILE,
        /**
         * A directory.
         */
        DIRECTORY,
        /**
         * A symbolic link pointing to another path.
         */
        SYMLINK,
        /**
         * A regular file with multiple hard links pointing to the same inode.
         */
        HARDLINK
    }

    private final Type type;
    private final int posixPermissions;
    private final List<String> posixAcls;
    private final List<String> windowsSids;

    /**
     * Private constructor used by the builder and factory methods.
     * 
     * @param type             the type of the entry. Must not be null.
     * @param posixPermissions the POSIX permissions as an octal value. Must be between 0 and 07777 inclusive.
     * @param posixAcls        the list of POSIX ACL string representations. Must not be null.
     * @param windowsSids      the list of Windows SID/ACE string representations. Must not be null.
     * @throws ValidationException if the inputs fail verification constraints.
     */
    private FileMode(Type type, int posixPermissions, List<String> posixAcls, List<String> windowsSids) {
        Objects.requireNonNull(type, "Type must not be null");
        Objects.requireNonNull(posixAcls, "POSIX ACL list must not be null");
        Objects.requireNonNull(windowsSids, "Windows SIDs list must not be null");

        if (posixPermissions < 0 || posixPermissions > 07777) {
            throw new ValidationException(String.format(
                "Invalid POSIX permissions octal value: %o (must be between 0000 and 07777).", posixPermissions));
        }

        this.type = type;
        this.posixPermissions = posixPermissions;
        this.posixAcls = List.copyOf(posixAcls);
        this.windowsSids = List.copyOf(windowsSids);
    }

    /**
     * Creates a builder instance to construct a custom {@link FileMode}.
     * 
     * @return a new {@link Builder}.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Queries the file system attributes of the given {@link Path} to construct
     * a representative {@link FileMode}.
     * 
     * @param path the file system path to inspect. Must not be null.
     * @return a fully populated {@link FileMode} corresponding to the path.
     * @throws IOException          if an I/O error occurs during attribute inspection.
     * @throws NullPointerException if the path is null.
     */
    public static FileMode fromPath(Path path) throws IOException {
        Objects.requireNonNull(path, "Path must not be null");
        LOGGER.trace("Parsing file mode for path: %s", path);

        // 1. Determine Type
        Type type = Type.FILE;
        if (Files.isSymbolicLink(path)) {
            type = Type.SYMLINK;
        } else if (Files.isDirectory(path)) {
            type = Type.DIRECTORY;
        } else {
            // Check link count to identify HARDLINK
            int linkCount = 1;
            try {
                Object nlink = Files.getAttribute(path, "unix:nlink");
                if (nlink instanceof Number val) {
                    linkCount = val.intValue();
                }
            } catch (UnsupportedOperationException | IllegalArgumentException e) {
                // Not a Unix system, fallback to checking dos attributes if possible
                try {
                    Object nlink = Files.getAttribute(path, "dos:nlink");
                    if (nlink instanceof Number val) {
                        linkCount = val.intValue();
                    }
                } catch (Exception ignored) {
                    // Fallback ignored
                }
            }
            if (linkCount > 1) {
                type = Type.HARDLINK;
            }
        }

        // 2. Determine POSIX Octal Permissions
        int posixPermissions = 0;
        try {
            Set<PosixFilePermission> posixSet = Files.getPosixFilePermissions(path);
            posixPermissions = fromPosixPermissions(posixSet);
        } catch (UnsupportedOperationException e) {
            // Fallback for non-POSIX systems (e.g. Windows) - Emulate owner permissions
            if (Files.isReadable(path)) {
                posixPermissions |= 0444; // Read for user/group/others
            }
            if (Files.isWritable(path)) {
                posixPermissions |= 0200; // Write for user
            }
            if (Files.isExecutable(path) || type == Type.DIRECTORY) {
                posixPermissions |= 0111; // Execute for user/group/others
            }
        }

        // 3. Determine ACLs and SIDs
        List<String> posixAcls = new ArrayList<>();
        List<String> windowsSids = new ArrayList<>();
        boolean isWindows = System.getProperty("os.name").toLowerCase().contains("win");

        AclFileAttributeView aclView = Files.getFileAttributeView(path, AclFileAttributeView.class);
        if (aclView != null) {
            try {
                List<AclEntry> aclEntries = aclView.getAcl();
                for (AclEntry entry : aclEntries) {
                    String principal = entry.principal().getName();
                    String entryStr = String.format("%s:%s:%s:%s",
                        principal,
                        entry.type(),
                        entry.permissions().toString().replace("[", "").replace("]", ""),
                        entry.flags().toString().replace("[", "").replace("]", "")
                    );
                    if (isWindows || principal.startsWith("S-1-") || principal.matches("^S-\\d+-\\d+(-\\d+)*$")) {
                        windowsSids.add(entryStr);
                    } else {
                        posixAcls.add(entryStr);
                    }
                }
            } catch (IOException e) {
                LOGGER.warn("Unable to retrieve ACL details for '%s': %s", path, e.getMessage());
            }
        }

        LOGGER.debug("FileMode resolved for '%s': type=%s, permissions=%o, acls=%d, sids=%d",
            path.toString().replace('\\', '/'), type, posixPermissions, posixAcls.size(), windowsSids.size());

        return new FileMode(type, posixPermissions, posixAcls, windowsSids);
    }

    /**
     * Gets the filesystem entry type.
     * 
     * @return the {@link Type}.
     */
    public Type getType() {
        return type;
    }

    /**
     * Gets the POSIX octal permission representation.
     * 
     * @return the permissions octal value.
     */
    public int getPosixPermissions() {
        return posixPermissions;
    }

    /**
     * Gets the list of POSIX ACL rules.
     * 
     * @return an unmodifiable list of POSIX ACL string representations.
     */
    public List<String> getPosixAcls() {
        return posixAcls;
    }

    /**
     * Gets the list of Windows SID/ACE rules.
     * 
     * @return an unmodifiable list of Windows SID string representations.
     */
    public List<String> getWindowsSids() {
        return windowsSids;
    }

    /**
     * Converts a set of {@link PosixFilePermission} to a 9-bit octal integer.
     * 
     * @param permissions the set of permissions. Must not be null.
     * @return the permissions as an octal integer.
     * @throws NullPointerException if the permissions set is null.
     */
    public static int fromPosixPermissions(Set<PosixFilePermission> permissions) {
        Objects.requireNonNull(permissions, "Permissions set must not be null");
        int octal = 0;
        if (permissions.contains(PosixFilePermission.OWNER_READ))     octal |= 0400;
        if (permissions.contains(PosixFilePermission.OWNER_WRITE))    octal |= 0200;
        if (permissions.contains(PosixFilePermission.OWNER_EXECUTE))  octal |= 0100;
        if (permissions.contains(PosixFilePermission.GROUP_READ))     octal |= 0040;
        if (permissions.contains(PosixFilePermission.GROUP_WRITE))    octal |= 0020;
        if (permissions.contains(PosixFilePermission.GROUP_EXECUTE))  octal |= 0010;
        if (permissions.contains(PosixFilePermission.OTHERS_READ))    octal |= 0004;
        if (permissions.contains(PosixFilePermission.OTHERS_WRITE))   octal |= 0002;
        if (permissions.contains(PosixFilePermission.OTHERS_EXECUTE)) octal |= 0001;
        return octal;
    }

    /**
     * Converts a POSIX permissions octal to its standard 9-character string representation (e.g. "rwxr-xr-x").
     * 
     * @param octal the permissions octal.
     * @return the standard "rwxr-xr-x" format string.
     */
    public static String toPosixString(int octal) {
        StringBuilder sb = new StringBuilder(9);
        sb.append((octal & 0400) != 0 ? 'r' : '-');
        sb.append((octal & 0200) != 0 ? 'w' : '-');
        sb.append((octal & 0100) != 0 ? 'x' : '-');
        sb.append((octal & 0040) != 0 ? 'r' : '-');
        sb.append((octal & 0020) != 0 ? 'w' : '-');
        sb.append((octal & 0010) != 0 ? 'x' : '-');
        sb.append((octal & 0004) != 0 ? 'r' : '-');
        sb.append((octal & 0002) != 0 ? 'w' : '-');
        sb.append((octal & 0001) != 0 ? 'x' : '-');
        return sb.toString();
    }

    /**
     * Parses a standard 9-character POSIX permissions string (e.g. "rwxr-xr-x") into its octal representation.
     * 
     * @param posixStr the permission string to parse. Must not be null and must be 9 characters long.
     * @return the permissions octal value.
     * @throws ValidationException if the string is null, not 9 characters long, or contains invalid characters.
     */
    public static int parsePosixString(String posixStr) {
        Objects.requireNonNull(posixStr, "POSIX permission string must not be null");
        if (posixStr.length() != 9) {
            throw new ValidationException("POSIX permission string must be exactly 9 characters long.");
        }
        int octal = 0;
        char[] chars = posixStr.toCharArray();
        
        // Owner
        if (chars[0] == 'r') octal |= 0400; else if (chars[0] != '-') throw new ValidationException("Invalid character at index 0: " + chars[0]);
        if (chars[1] == 'w') octal |= 0200; else if (chars[1] != '-') throw new ValidationException("Invalid character at index 1: " + chars[1]);
        if (chars[2] == 'x') octal |= 0100; else if (chars[2] != '-') throw new ValidationException("Invalid character at index 2: " + chars[2]);

        // Group
        if (chars[3] == 'r') octal |= 0040; else if (chars[3] != '-') throw new ValidationException("Invalid character at index 3: " + chars[3]);
        if (chars[4] == 'w') octal |= 0020; else if (chars[4] != '-') throw new ValidationException("Invalid character at index 4: " + chars[4]);
        if (chars[5] == 'x') octal |= 0010; else if (chars[5] != '-') throw new ValidationException("Invalid character at index 5: " + chars[5]);

        // Others
        if (chars[6] == 'r') octal |= 0004; else if (chars[6] != '-') throw new ValidationException("Invalid character at index 6: " + chars[6]);
        if (chars[7] == 'w') octal |= 0002; else if (chars[7] != '-') throw new ValidationException("Invalid character at index 7: " + chars[7]);
        if (chars[8] == 'x') octal |= 0001; else if (chars[8] != '-') throw new ValidationException("Invalid character at index 8: " + chars[8]);

        return octal;
    }

    /**
     * Converts the octal permissions to java NIO {@link PosixFilePermission} set.
     * 
     * @return a set containing the parsed {@link PosixFilePermission} values.
     */
    public Set<PosixFilePermission> toPosixPermissionsSet() {
        return PosixFilePermissions.fromString(toPosixString(posixPermissions));
    }

    /**
     * Returns true if this describes a regular file.
     * 
     * @return true if the type is FILE or HARDLINK.
     */
    public boolean isFile() {
        return type == Type.FILE || type == Type.HARDLINK;
    }

    /**
     * Returns true if this describes a directory.
     * 
     * @return true if the type is DIRECTORY.
     */
    public boolean isDirectory() {
        return type == Type.DIRECTORY;
    }

    /**
     * Returns true if this describes a symbolic link.
     * 
     * @return true if the type is SYMLINK.
     */
    public boolean isSymbolicLink() {
        return type == Type.SYMLINK;
    }

    /**
     * Returns true if this describes a hard link.
     * 
     * @return true if the type is HARDLINK.
     */
    public boolean isHardLink() {
        return type == Type.HARDLINK;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FileMode other)) return false;
        return posixPermissions == other.posixPermissions &&
               type == other.type &&
               Objects.equals(posixAcls, other.posixAcls) &&
               Objects.equals(windowsSids, other.windowsSids);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, posixPermissions, posixAcls, windowsSids);
    }

    @Override
    public String toString() {
        return String.format("FileMode[type=%s, permissions=%s (%04o), posixAcls=%s, windowsSids=%s]",
            type, toPosixString(posixPermissions), posixPermissions, posixAcls, windowsSids);
    }

    /**
     * Builder class for constructing {@link FileMode} instances.
     */
    public static final class Builder {
        private Type type = Type.FILE;
        private int posixPermissions = 0644;
        private final List<String> posixAcls = new ArrayList<>();
        private final List<String> windowsSids = new ArrayList<>();

        private Builder() {}

        /**
         * Sets the file mode entry type.
         * 
         * @param type the entry type. Must not be null.
         * @return this builder.
         */
        public Builder type(Type type) {
            this.type = Objects.requireNonNull(type, "Type must not be null");
            return this;
        }

        /**
         * Sets the POSIX permissions as an octal value.
         * 
         * @param permissions the octal value (e.g., 0755). Must be between 0 and 07777.
         * @return this builder.
         */
        public Builder permissions(int permissions) {
            this.posixPermissions = permissions;
            return this;
        }

        /**
         * Sets the POSIX permissions using a standard permissions string (e.g. "rwxr-xr-x").
         * 
         * @param posixStr the permission string. Must not be null.
         * @return this builder.
         */
        public Builder permissions(String posixStr) {
            this.posixPermissions = parsePosixString(posixStr);
            return this;
        }

        /**
         * Adds a POSIX ACL representation to the list.
         * 
         * @param acl the ACL rule string. Must not be null or blank.
         * @return this builder.
         * @throws ValidationException if the acl string is blank.
         */
        public Builder addPosixAcl(String acl) {
            Objects.requireNonNull(acl, "ACL rule must not be null");
            if (acl.isBlank()) {
                throw new ValidationException("ACL rule must not be blank.");
            }
            this.posixAcls.add(acl.trim());
            return this;
        }

        /**
         * Adds a list of POSIX ACL representations.
         * 
         * @param acls the list of ACL rules. Must not be null.
         * @return this builder.
         */
        public Builder posixAcls(List<String> acls) {
            Objects.requireNonNull(acls, "ACL rules list must not be null");
            for (String acl : acls) {
                addPosixAcl(acl);
            }
            return this;
        }

        /**
         * Adds a Windows SID/ACE representation to the list.
         * 
         * @param sid the SID rule string. Must not be null or blank.
         * @return this builder.
         * @throws ValidationException if the sid string is blank.
         */
        public Builder addWindowsSid(String sid) {
            Objects.requireNonNull(sid, "Windows SID must not be null");
            if (sid.isBlank()) {
                throw new ValidationException("Windows SID must not be blank.");
            }
            this.windowsSids.add(sid.trim());
            return this;
        }

        /**
         * Adds a list of Windows SID/ACE representations.
         * 
         * @param sids the list of SIDs. Must not be null.
         * @return this builder.
         */
        public Builder windowsSids(List<String> sids) {
            Objects.requireNonNull(sids, "Windows SIDs list must not be null");
            for (String sid : sids) {
                addWindowsSid(sid);
            }
            return this;
        }

        /**
         * Builds a new {@link FileMode} instance using the configured properties.
         * 
         * @return the constructed {@link FileMode}.
         */
        public FileMode build() {
            return new FileMode(type, posixPermissions, posixAcls, windowsSids);
        }
    }
}
