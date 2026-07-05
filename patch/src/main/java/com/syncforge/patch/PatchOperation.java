package com.syncforge.patch;

import com.syncforge.metadata.FileHash;

public class PatchOperation {
    public enum Type {
        CREATE_FILE(1),
        DELETE_FILE(2),
        UPDATE_FILE(3),
        RENAME_PATH(4),
        CREATE_DIRECTORY(5),
        DELETE_DIRECTORY(6),
        UPDATE_PERMISSIONS(7),
        UPDATE_SYMLINK(8);

        private final int code;
        Type(int code) {
            this.code = code;
        }
        public int getCode() {
            return code;
        }
        public static Type fromCode(int code) {
            for (Type t : values()) {
                if (t.code == code) return t;
            }
            throw new IllegalArgumentException("Unknown op code: " + code);
        }
    }

    private final Type type;
    private final String path;
    private final String targetPath;
    private final int permissions;
    private final long lastModified;
    private final FileHash hash;
    private final long payloadOffset;
    private final long payloadLength;
    private final byte[] rawData;

    public PatchOperation(Type type, String path, String targetPath, int permissions, long lastModified, FileHash hash, long payloadOffset, long payloadLength, byte[] rawData) {
        this.type = type;
        this.path = path;
        this.targetPath = targetPath != null ? targetPath : "";
        this.permissions = permissions;
        this.lastModified = lastModified;
        this.hash = hash != null ? hash : new FileHash("", "");
        this.payloadOffset = payloadOffset;
        this.payloadLength = payloadLength;
        this.rawData = rawData;
    }

    public Type getType() {
        return type;
    }

    public String getPath() {
        return path;
    }

    public String getTargetPath() {
        return targetPath;
    }

    public int getPermissions() {
        return permissions;
    }

    public long getLastModified() {
        return lastModified;
    }

    public FileHash getHash() {
        return hash;
    }

    public long getPayloadOffset() {
        return payloadOffset;
    }

    public long getPayloadLength() {
        return payloadLength;
    }

    public byte[] getRawData() {
        return rawData;
    }
}
