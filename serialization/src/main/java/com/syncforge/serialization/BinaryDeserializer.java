package com.syncforge.serialization;

import com.syncforge.core.exceptions.ParseException;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class BinaryDeserializer {
    private final DataInputStream dis;
    private final int totalLength;
    private int readBytesCount = 0;

    public BinaryDeserializer(byte[] data) {
        this.dis = new DataInputStream(new ByteArrayInputStream(data));
        this.totalLength = data.length;
    }

    public byte[] readBytes(int len) throws IOException {
        if (len < 0 || len > totalLength - readBytesCount) {
            throw new ParseException("Binary parse overflow error: requested " + len + " bytes", null);
        }
        byte[] bytes = new byte[len];
        dis.readFully(bytes);
        readBytesCount += len;
        return bytes;
    }

    public int readShort() throws IOException {
        if (2 > totalLength - readBytesCount) {
            throw new ParseException("Binary parse overflow reading short", null);
        }
        readBytesCount += 2;
        return dis.readShort() & 0xFFFF;
    }

    public int readInt() throws IOException {
        if (4 > totalLength - readBytesCount) {
            throw new ParseException("Binary parse overflow reading int", null);
        }
        readBytesCount += 4;
        return dis.readInt();
    }

    public long readLong() throws IOException {
        if (8 > totalLength - readBytesCount) {
            throw new ParseException("Binary parse overflow reading long", null);
        }
        readBytesCount += 8;
        return dis.readLong();
    }

    public String readString() throws IOException {
        int len = readInt();
        if (len < 0 || len > 10 * 1024 * 1024) {
            throw new ParseException("Invalid string length: " + len, null);
        }
        byte[] bytes = readBytes(len);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public int getReadBytesCount() {
        return readBytesCount;
    }
}
