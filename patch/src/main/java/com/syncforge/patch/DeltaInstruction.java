package com.syncforge.patch;

import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a single binary delta instruction.
 */
public record DeltaInstruction(
    Type type,
    long offset,
    int length,
    byte[] data
) {
    /**
     * Delta instruction types.
     */
    public enum Type {
        /**
         * Appends new literal bytes.
         */
        ADD,

        /**
         * Copies a byte range from the source or target stream.
         */
        COPY,

        /**
         * Appends repeating byte values.
         */
        RUN
    }

    /**
     * Constructs a DeltaInstruction.
     */
    public DeltaInstruction {
        Objects.requireNonNull(type, "Instruction type must not be null");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeltaInstruction other)) return false;
        return offset == other.offset &&
               length == other.length &&
               type == other.type &&
               Arrays.equals(data, other.data);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(type, offset, length);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        return String.format("DeltaInstruction[type=%s, offset=%d, length=%d, dataLen=%s]",
            type, offset, length, data != null ? data.length : "null");
    }
}
