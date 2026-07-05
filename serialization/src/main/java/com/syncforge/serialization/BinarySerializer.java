package com.syncforge.serialization;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class BinarySerializer {
    private final ByteArrayOutputStream bos;
    private final DataOutputStream dos;

    public BinarySerializer() {
        this.bos = new ByteArrayOutputStream();
        this.dos = new DataOutputStream(bos);
    }

    public void writeBytes(byte[] bytes) throws IOException {
        dos.write(bytes);
    }

    public void writeShort(int v) throws IOException {
        dos.writeShort(v);
    }

    public void writeInt(int v) throws IOException {
        dos.writeInt(v);
    }

    public void writeLong(long v) throws IOException {
        dos.writeLong(v);
    }

    public void writeString(String str) throws IOException {
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        dos.writeInt(bytes.length);
        dos.write(bytes);
    }

    public byte[] toByteArray() {
        return bos.toByteArray();
    }
}
