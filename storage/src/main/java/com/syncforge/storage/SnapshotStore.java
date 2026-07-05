package com.syncforge.storage;

import com.syncforge.core.exceptions.StorageException;
import com.syncforge.core.logging.SyncForgeLogger;
import com.syncforge.snapshot.SnapshotModel;
import com.syncforge.snapshot.SnapshotReader;
import com.syncforge.snapshot.SnapshotWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * SnapshotStore manages snapshot files in the local repository.
 */
public class SnapshotStore {

    private static final SyncForgeLogger LOGGER = new SyncForgeLogger(SnapshotStore.class);

    private final Path snapshotsDir;

    /**
     * Constructs a SnapshotStore targeting the given repository path.
     *
     * @param repoRoot the repository root directory (e.g. workspace/.syncforge)
     */
    public SnapshotStore(Path repoRoot) {
        Objects.requireNonNull(repoRoot, "Repository root must not be null");
        this.snapshotsDir = repoRoot.resolve("snapshots");
        initialize();
    }

    private void initialize() {
        try {
            Files.createDirectories(snapshotsDir);
        } catch (IOException e) {
            throw new StorageException("Failed to create snapshots directory: " + snapshotsDir, e);
        }
    }

    /**
     * Saves a snapshot model and returns its assigned snapshot ID.
     *
     * @param model          the snapshot model to save.
     * @param useCompression true to compress the payload block.
     * @return the unique snapshot ID.
     */
    public String save(SnapshotModel model, boolean useCompression) {
        Objects.requireNonNull(model, "Snapshot model must not be null");
        String id = UUID.randomUUID().toString();
        Path targetPath = snapshotsDir.resolve("snap_" + id + ".sfsn");

        LOGGER.info("Saving snapshot %s to: %s", id, targetPath);

        try {
            SnapshotWriter writer = new SnapshotWriter(useCompression);
            byte[] bytes = writer.write(model);
            Files.write(targetPath, bytes);
            LOGGER.debug("Snapshot %s saved successfully (%d bytes)", id, bytes.length);
            return id;
        } catch (IOException e) {
            throw new StorageException("Failed to write snapshot to disk: " + targetPath, e);
        }
    }

    /**
     * Loads a snapshot by its ID.
     *
     * @param id the snapshot ID.
     * @return the loaded SnapshotModel.
     */
    public SnapshotModel load(String id) {
        Objects.requireNonNull(id, "Snapshot ID must not be null");
        Path targetPath = snapshotsDir.resolve("snap_" + id + ".sfsn");

        LOGGER.info("Loading snapshot %s from: %s", id, targetPath);

        if (!Files.exists(targetPath)) {
            throw new StorageException("Snapshot file does not exist: " + targetPath);
        }

        try {
            byte[] bytes = Files.readAllBytes(targetPath);
            SnapshotReader reader = new SnapshotReader();
            return reader.read(bytes);
        } catch (IOException e) {
            throw new StorageException("Failed to read snapshot file: " + targetPath, e);
        }
    }

    /**
     * Lists all saved snapshot IDs.
     *
     * @return a list of snapshot IDs.
     */
    public List<String> list() {
        List<String> ids = new ArrayList<>();
        try (Stream<Path> stream = Files.list(snapshotsDir)) {
            stream.filter(Files::isRegularFile)
                  .map(Path::getFileName)
                  .map(Path::toString)
                  .filter(name -> name.startsWith("snap_") && name.endsWith(".sfsn"))
                  .map(name -> name.substring(5, name.length() - 5))
                  .forEach(ids::add);
        } catch (IOException e) {
            LOGGER.error(e, "Failed to list snapshots in directory: %s", snapshotsDir);
        }
        return ids;
    }

    /**
     * Deletes a snapshot file by its ID.
     *
     * @param id the snapshot ID to delete.
     * @return true if deleted successfully, false otherwise.
     */
    public boolean delete(String id) {
        Path targetPath = snapshotsDir.resolve("snap_" + id + ".sfsn");
        try {
            boolean deleted = Files.deleteIfExists(targetPath);
            if (deleted) {
                LOGGER.info("Snapshot %s deleted successfully.", id);
            }
            return deleted;
        } catch (IOException e) {
            LOGGER.error(e, "Failed to delete snapshot file: %s", targetPath);
            return false;
        }
    }
}
