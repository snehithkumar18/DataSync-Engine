package com.syncforge.metadata;

import com.syncforge.core.exceptions.ValidationException;
import com.syncforge.core.logging.SyncForgeLogger;
import java.util.Objects;

/**
 * Represents a cryptographic or non-cryptographic file hash, capturing the algorithm type
 * and the hash value itself. Performs validation to ensure that the hash value matches
 * a strict hexadecimal format.
 * 
 * <p>Both the algorithm and value are normalized during construction:
 * the algorithm is converted to uppercase, and the value is converted to lowercase.
 * This guarantees consistent equality checks across different case configurations.</p>
 * 
 * @param algorithm the hash algorithm name (e.g., "SHA-256", "CRC32", "MD5"). Must not be null or blank.
 * @param value     the hexadecimal hash value. Must not be null or blank, and must contain only hex characters.
 */
public record FileHash(String algorithm, String value) {

    private static final SyncForgeLogger LOGGER = new SyncForgeLogger(FileHash.class);

    /**
     * Compact constructor to validate and normalize hash properties.
     * 
     * @throws ValidationException if the algorithm is null or blank, or if the value is null,
     *                             blank, or not a valid hexadecimal string.
     */
    public FileHash {
        // Validate and normalize algorithm
        Objects.requireNonNull(algorithm, "Algorithm must not be null");
        if (algorithm.isBlank()) {
            throw new ValidationException("Hash algorithm must not be empty or blank.");
        }
        algorithm = algorithm.trim().toUpperCase();

        // Validate and normalize value
        Objects.requireNonNull(value, "Hash value must not be null");
        if (value.isBlank()) {
            throw new ValidationException("Hash value must not be empty or blank.");
        }
        value = value.trim().toLowerCase();

        // Validate hexadecimal format
        if (!value.matches("^[0-9a-f]{1,}$")) {
            LOGGER.error("Invalid hexadecimal format provided for hash: algorithm='%s', value='%s'", algorithm, value);
            throw new ValidationException("Hash value must be a valid hexadecimal string: '" + value + "'");
        }

        LOGGER.trace("FileHash successfully constructed: %s:%s", algorithm, value);
    }

    /**
     * Creates a {@link FileHash} instance specifically using the SHA-256 algorithm.
     * 
     * @param value the hexadecimal hash value. Must not be null, blank, or non-hex.
     * @return a new {@link FileHash} instance representing the SHA-256 hash.
     * @throws ValidationException if the value is invalid.
     */
    public static FileHash sha256(String value) {
        return new FileHash("SHA-256", value);
    }

    /**
     * Creates a {@link FileHash} instance specifically using the CRC32 algorithm.
     * 
     * @param value the hexadecimal hash value representing the CRC32 checksum. Must not be null, blank, or non-hex.
     * @return a new {@link FileHash} instance representing the CRC32 checksum.
     * @throws ValidationException if the value is invalid.
     */
    public static FileHash crc32(String value) {
        return new FileHash("CRC32", value);
    }

    /**
     * Creates a {@link FileHash} instance specifically using the MD5 algorithm.
     * 
     * @param value the hexadecimal hash value. Must not be null, blank, or non-hex.
     * @return a new {@link FileHash} instance representing the MD5 hash.
     * @throws ValidationException if the value is invalid.
     */
    public static FileHash md5(String value) {
        return new FileHash("MD5", value);
    }

    /**
     * Returns a formatted representation of the hash.
     * 
     * @return a string in the format "ALGORITHM:value"
     */
    @Override
    public String toString() {
        return algorithm + ":" + value;
    }
}
