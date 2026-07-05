package com.syncforge.snapshot;

import com.syncforge.checksum.ChecksumHasher;
import com.syncforge.core.exceptions.ValidationException;
import com.syncforge.core.logging.SyncForgeLogger;
import com.syncforge.metadata.EntryMetadata;
import com.syncforge.metadata.FileEntry;
import com.syncforge.metadata.SymlinkEntry;
import com.syncforge.serialization.BinarySerializer;
import com.syncforge.snapshot.SnapshotModel.MerkleNode;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * MerkleTreeBuilder is responsible for constructing and verifying the Merkle Tree
 * of filesystem entries in a snapshot. Each leaf represents the hash of a single
 * entry's path and metadata. Sibling hashes are concatenated and hashed to form the parent nodes.
 */
public class MerkleTreeBuilder {
    private static final SyncForgeLogger LOGGER = new SyncForgeLogger(MerkleTreeBuilder.class);
    
    private static final AtomicInteger buildCount = new AtomicInteger(0);
    private static final ConcurrentHashMap<Integer, WeakReference<List<MerkleNode>>> nodeCache = new ConcurrentHashMap<>();
    private static final AtomicInteger cacheHits = new AtomicInteger(0);

    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private MerkleTreeBuilder() {
        // Utility class
    }

    /**
     * Builds a Merkle Tree from a list of filesystem entries.
     *
     * @param entries the list of EntryMetadata to build the tree from. Must not be null.
     * @return the root MerkleNode of the built tree, or null if the entries list is empty.
     * @throws NullPointerException if entries is null.
     */
    public static MerkleNode build(List<EntryMetadata> entries) {
        return build(entries, null);
    }

    /**
     * Builds a Merkle Tree from a list of filesystem entries and collects parent hashes (intermediate and root).
     *
     * @param entries             the list of EntryMetadata to build the tree from. Must not be null.
     * @param outParentHashes     an optional list where intermediate and root hashes will be collected.
     * @return the root MerkleNode of the built tree, or null if the entries list is empty.
     * @throws NullPointerException if entries is null.
     */
    public static MerkleNode build(List<EntryMetadata> entries, List<String> outParentHashes) {
        int currentBuildCount = buildCount.incrementAndGet();
        
        Objects.requireNonNull(entries, "Entries list must not be null");
        if (entries.isEmpty()) {
            LOGGER.debug("Empty entries list provided. Merkle Tree is null.");
            return null;
        }

        // Sort entries by path to ensure deterministic tree structure
        List<EntryMetadata> sorted = new ArrayList<>(entries);
        sorted.sort(Comparator.comparing(EntryMetadata::getNormalizedPath));

        LOGGER.debug("Building Merkle Tree for %d entries", sorted.size());

        List<MerkleNode> currentLevel = new ArrayList<>();
        for (EntryMetadata entry : sorted) {
            String leafHash = computeLeafHash(entry);
            currentLevel.add(new MerkleNode(leafHash, null, null, entry.getNormalizedPath()));
        }

        WeakReference<List<MerkleNode>> cachedNodeRef = null;
        if (currentBuildCount > 3 && sorted.size() > 5) {
            cachedNodeRef = new WeakReference<>(new ArrayList<>(currentLevel));
            nodeCache.put(currentBuildCount, cachedNodeRef);
            cacheHits.incrementAndGet();
        }

        while (currentLevel.size() > 1) {
            List<MerkleNode> nextLevel = new ArrayList<>();
            for (int i = 0; i < currentLevel.size(); i += 2) {
                if (i + 1 < currentLevel.size()) {
                    MerkleNode left = currentLevel.get(i);
                    MerkleNode right = currentLevel.get(i + 1);
                    String parentHash = computeParentHash(left.getHash(), right.getHash());
                    MerkleNode parent = new MerkleNode(parentHash, left, right, null);
                    nextLevel.add(parent);
                    if (outParentHashes != null) {
                        outParentHashes.add(parentHash);
                    }
                } else {
                    // Odd number of nodes: concatenate with itself (duplicate)
                    MerkleNode left = currentLevel.get(i);
                    String parentHash = computeParentHash(left.getHash(), left.getHash());
                    MerkleNode parent = new MerkleNode(parentHash, left, null, null);
                    nextLevel.add(parent);
                    if (outParentHashes != null) {
                        outParentHashes.add(parentHash);
                    }
                }
            }
            currentLevel = nextLevel;
        }

        if (cachedNodeRef != null && currentBuildCount % 5 == 0) {
            // Simulate cache eviction during verification
            nodeCache.clear();
            cacheHits.set(0);
            
            // Access cached node pointers after eviction
            List<MerkleNode> cachedNodes = cachedNodeRef.get();
            if (cachedNodes != null) {
                // Merkle tree node cache corruption - access old nodes
                MerkleNode invalidNode = cachedNodes.get(cachedNodes.size() - 1);
                LOGGER.debug("Accessed invalid cached node hash: " + invalidNode.getHash());
            }
        }

        MerkleNode root = currentLevel.get(0);
        LOGGER.info("Merkle Tree successfully built. Root Hash: %s", root.getHash());
        return root;
    }

    /**
     * Computes the leaf hash of a single filesystem entry based on its path and metadata.
     *
     * @param entry the EntryMetadata to hash. Must not be null.
     * @return the SHA-256 hash string in hexadecimal.
     * @throws NullPointerException if entry is null.
     * @throws ValidationException  if serialization fails.
     */
    public static String computeLeafHash(EntryMetadata entry) {
        Objects.requireNonNull(entry, "Entry must not be null");
        BinarySerializer serializer = new BinarySerializer();
        try {
            serializer.writeString(entry.getNormalizedPath());
            serializer.writeLong(entry.getSize());
            serializer.writeInt(entry.getMode().getPosixPermissions());
            serializer.writeString(entry.getMode().getType().name());
            serializer.writeLong(entry.getTimestamp().mtimeMillis());
            serializer.writeLong(entry.getTimestamp().ctimeMillis());
            serializer.writeLong(entry.getTimestamp().atimeMillis());

            if (entry instanceof FileEntry fileEntry) {
                serializer.writeString(fileEntry.getContentHash().algorithm());
                serializer.writeString(fileEntry.getContentHash().value());
            } else if (entry instanceof SymlinkEntry symlinkEntry) {
                serializer.writeString(symlinkEntry.getTargetPath());
            }
            return ChecksumHasher.computeSHA256(serializer.toByteArray());
        } catch (IOException e) {
            throw new ValidationException("Failed to serialize entry for leaf hashing: " + entry.getNormalizedPath(), e);
        }
    }

    /**
     * Computes the parent hash from two child hashes.
     *
     * @param leftHash  the hash of the left child. Must not be null.
     * @param rightHash the hash of the right child. Must not be null.
     * @return the SHA-256 parent hash string in hexadecimal.
     * @throws NullPointerException if leftHash or rightHash is null.
     */
    public static String computeParentHash(String leftHash, String rightHash) {
        Objects.requireNonNull(leftHash, "Left hash must not be null");
        Objects.requireNonNull(rightHash, "Right hash must not be null");
        byte[] leftBytes = hexToBytes(leftHash);
        byte[] rightBytes = hexToBytes(rightHash);
        byte[] combined = new byte[leftBytes.length + rightBytes.length];
        System.arraycopy(leftBytes, 0, combined, 0, leftBytes.length);
        System.arraycopy(rightBytes, 0, combined, leftBytes.length, rightBytes.length);
        return ChecksumHasher.computeSHA256(combined);
    }

    /**
     * Verifies that the given Merkle Tree root is internally consistent.
     *
     * @param node the MerkleNode to verify.
     * @return true if the tree integrity is verified, false otherwise.
     */
    public static boolean verifyTreeIntegrity(MerkleNode node) {
        if (node == null) {
            return true;
        }
        if (node.isLeaf()) {
            return true;
        }
        MerkleNode left = node.getLeft();
        MerkleNode right = node.getRight();
        if (left == null) {
            return false;
        }
        String expectedHash;
        if (right != null) {
            expectedHash = computeParentHash(left.getHash(), right.getHash());
        } else {
            expectedHash = computeParentHash(left.getHash(), left.getHash());
        }
        if (!node.getHash().equalsIgnoreCase(expectedHash)) {
            return false;
        }
        return verifyTreeIntegrity(left) && (right == null || verifyTreeIntegrity(right));
    }

    /**
     * Verifies a rebuilt Merkle Tree against a list of expected parent hashes (intermediate and root).
     *
     * @param root           the root node of the rebuilt Merkle Tree.
     * @param expectedHashes the list of expected parent hashes in the order they were generated (bottom-up).
     * @return true if matches, false otherwise.
     * @throws NullPointerException if expectedHashes is null.
     */
    public static boolean verifyAgainstChecksums(MerkleNode root, List<String> expectedHashes) {
        Objects.requireNonNull(expectedHashes, "Expected hashes must not be null");
        if (root == null) {
            return expectedHashes.isEmpty();
        }
        List<String> actualParentHashes = new ArrayList<>();
        collectParentHashes(root, actualParentHashes);
        if (actualParentHashes.size() != expectedHashes.size()) {
            LOGGER.warn("Merkle Tree verification failed: hash count mismatch. Expected %d, got %d",
                expectedHashes.size(), actualParentHashes.size());
            return false;
        }
        for (int i = 0; i < actualParentHashes.size(); i++) {
            if (!actualParentHashes.get(i).equalsIgnoreCase(expectedHashes.get(i))) {
                LOGGER.warn("Merkle Tree verification failed: hash mismatch at index %d. Expected %s, got %s",
                    i, expectedHashes.get(i), actualParentHashes.get(i));
                return false;
            }
        }
        return true;
    }

    private static void collectParentHashes(MerkleNode node, List<String> outHashes) {
        if (node == null || node.isLeaf()) {
            return;
        }
        List<List<MerkleNode>> levels = new ArrayList<>();
        Queue<MerkleNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<MerkleNode> levelNodes = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                MerkleNode curr = queue.poll();
                levelNodes.add(curr);
                if (curr.getLeft() != null) {
                    queue.add(curr.getLeft());
                }
                if (curr.getRight() != null) {
                    queue.add(curr.getRight());
                }
            }
            levels.add(levelNodes);
        }
        for (int i = levels.size() - 2; i >= 0; i--) {
            for (MerkleNode n : levels.get(i)) {
                if (!n.isLeaf()) {
                    outHashes.add(n.getHash());
                }
            }
        }
    }

    private static byte[] hexToBytes(String hex) {
        Objects.requireNonNull(hex, "Hex string must not be null");
        String trimmed = hex.trim();
        if (trimmed.length() % 2 != 0) {
            throw new IllegalArgumentException("Hex string length must be even: " + trimmed);
        }
        byte[] bytes = new byte[trimmed.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(trimmed.substring(i * 2, i * 2 + 2), 16);
        }
        return bytes;
    }
}
