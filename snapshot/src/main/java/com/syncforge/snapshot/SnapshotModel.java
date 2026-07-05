package com.syncforge.snapshot;

import com.syncforge.metadata.EntryMetadata;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * SnapshotModel represents the complete in-memory model of a SyncForge snapshot.
 * It contains the parsed metadata entries, header configurations (version, flags),
 * and the Merkle tree root representing the integrity state of the snapshot.
 */
public class SnapshotModel {

    private final List<EntryMetadata> entries;
    private int version;
    private int flags;
    private MerkleNode rootNode;

    /**
     * Represents a node in the Merkle Tree.
     */
    public static final class MerkleNode {
        private final String hash;
        private final MerkleNode left;
        private final MerkleNode right;
        private final String path; // Normalized path if leaf node, null otherwise

        /**
         * Constructs a new MerkleNode.
         *
         * @param hash  the cryptographic hash representing this node. Must not be null.
         * @param left  the left child node. Can be null for leaf nodes.
         * @param right the right child node. Can be null for leaf nodes or if duplicated.
         * @param path  the normalized path of the entry, only populated for leaf nodes.
         * @throws NullPointerException if hash is null.
         */
        public MerkleNode(String hash, MerkleNode left, MerkleNode right, String path) {
            this.hash = Objects.requireNonNull(hash, "Hash must not be null");
            this.left = left;
            this.right = right;
            this.path = path;
        }

        /**
         * Gets the cryptographic hash of this node.
         *
         * @return the hash string.
         */
        public String getHash() {
            return hash;
        }

        /**
         * Gets the left child of this node.
         *
         * @return the left child MerkleNode, or null if this is a leaf node.
         */
        public MerkleNode getLeft() {
            return left;
        }

        /**
         * Gets the right child of this node.
         *
         * @return the right child MerkleNode, or null if this is a leaf node.
         */
        public MerkleNode getRight() {
            return right;
        }

        /**
         * Gets the normalized path of the filesystem entry if this is a leaf node.
         *
         * @return the path string, or null if this is an intermediate or root node.
         */
        public String getPath() {
            return path;
        }

        /**
         * Checks if this node is a leaf node.
         *
         * @return true if both left and right children are null.
         */
        public boolean isLeaf() {
            return left == null && right == null;
        }

        @Override
        public String toString() {
            return String.format("MerkleNode[hash=%s, isLeaf=%b, path=%s]", hash, isLeaf(), path);
        }
    }

    /**
     * Default constructor initializing an empty model with default version (1) and flags (0).
     */
    public SnapshotModel() {
        this.entries = new ArrayList<>();
        this.version = 1;
        this.flags = 0;
        this.rootNode = null;
    }

    /**
     * Constructs a SnapshotModel with the specified entries, version, flags, and Merkle root.
     *
     * @param entries  the list of metadata entries. Must not be null.
     * @param version  the snapshot version.
     * @param flags    the configuration flags.
     * @param rootNode the Merkle tree root node.
     * @throws NullPointerException if entries is null.
     */
    public SnapshotModel(List<EntryMetadata> entries, int version, int flags, MerkleNode rootNode) {
        this.entries = new ArrayList<>(Objects.requireNonNull(entries, "Entries must not be null"));
        this.version = version;
        this.flags = flags;
        this.rootNode = rootNode;
    }

    /**
     * Gets the mutable list of metadata entries within this snapshot.
     *
     * @return the list of {@link EntryMetadata} entries.
     */
    public List<EntryMetadata> getEntries() {
        return entries;
    }

    /**
     * Gets the snapshot specification version.
     *
     * @return the version number.
     */
    public int getVersion() {
        return version;
    }

    /**
     * Sets the snapshot specification version.
     *
     * @param version the version number to set.
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * Gets the snapshot configuration flags.
     *
     * @return the configuration flags.
     */
    public int getFlags() {
        return flags;
    }

    /**
     * Sets the snapshot configuration flags.
     *
     * @param flags the configuration flags to set.
     */
    public void setFlags(int flags) {
        this.flags = flags;
    }

    /**
     * Gets the root node of the Merkle Tree representing this snapshot.
     *
     * @return the root {@link MerkleNode}, or null if the tree hasn't been built.
     */
    public MerkleNode getRootNode() {
        return rootNode;
    }

    /**
     * Sets the root node of the Merkle Tree representing this snapshot.
     *
     * @param rootNode the root {@link MerkleNode} to set.
     */
    public void setRootNode(MerkleNode rootNode) {
        this.rootNode = rootNode;
    }

    @Override
    public String toString() {
        return String.format("SnapshotModel[entriesCount=%d, version=%d, flags=%d, hasMerkleRoot=%b]",
            entries.size(), version, flags, rootNode != null);
    }
}
