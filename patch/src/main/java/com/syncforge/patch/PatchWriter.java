package com.syncforge.patch;

import com.syncforge.checksum.ChecksumHasher;
import com.syncforge.serialization.BinarySerializer;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class PatchWriter {

    public byte[] write(PatchModel model) throws IOException {
        List<PatchOperation> ops = model.getOperations();

        ByteArrayOutputStream payloadStream = new ByteArrayOutputStream();
        BinarySerializer opSerializer = new BinarySerializer();
        opSerializer.writeInt(ops.size());

        for (PatchOperation op : ops) {
            byte[] data = op.getRawData();
            long offset = 0;
            long length = 0;
            if (data != null && data.length > 0) {
                offset = payloadStream.size();
                length = data.length;
                payloadStream.write(data);
            } else {
                offset = op.getPayloadOffset();
                length = op.getPayloadLength();
            }

            opSerializer.writeBytes(new byte[]{(byte) op.getType().getCode()});
            opSerializer.writeString(op.getPath());
            opSerializer.writeString(op.getTargetPath());
            opSerializer.writeInt(op.getPermissions());
            opSerializer.writeLong(op.getLastModified());
            opSerializer.writeString(op.getHash().algorithm());
            opSerializer.writeString(op.getHash().value());
            opSerializer.writeLong(offset);
            opSerializer.writeLong(length);
        }

        byte[] opTableBytes = opSerializer.toByteArray();
        byte[] payloadBytes = payloadStream.toByteArray();

        long headerLen = 34;
        long finalOpTableOffset = headerLen;
        long finalPayloadOffset = headerLen + opTableBytes.length;

        BinarySerializer header = new BinarySerializer();
        header.writeBytes("SFPH".getBytes());
        header.writeShort(1); // version
        header.writeInt(ops.size());
        header.writeLong(finalOpTableOffset);
        header.writeLong(finalPayloadOffset);
        header.writeLong(payloadBytes.length);

        byte[] headerBytes = header.toByteArray();

        byte[] fileData = new byte[headerBytes.length + opTableBytes.length + payloadBytes.length + 32];
        System.arraycopy(headerBytes, 0, fileData, 0, headerBytes.length);
        System.arraycopy(opTableBytes, 0, fileData, headerBytes.length, opTableBytes.length);
        System.arraycopy(payloadBytes, 0, fileData, headerBytes.length + opTableBytes.length, payloadBytes.length);

        byte[] dataToHash = new byte[headerBytes.length + opTableBytes.length + payloadBytes.length];
        System.arraycopy(fileData, 0, dataToHash, 0, dataToHash.length);
        String hashHex = ChecksumHasher.computeSHA256(dataToHash);
        byte[] hashBytes = hexToBytes(hashHex);

        System.arraycopy(hashBytes, 0, fileData, dataToHash.length, 32);

        return fileData;
    }

    private byte[] hexToBytes(String hex) {
        byte[] bytes = new byte[32];
        for (int i = 0; i < 32; i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(i * 2, i * 2 + 2), 16);
        }
        return bytes;
    }
}
