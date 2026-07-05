# SyncForge - Enterprise JVM File Sync & Manifest Engine

SyncForge is a production-quality, high-integrity file tree synchronization, manifest parsing, differential snapshot, transaction runtime, and conflict resolution engine built from scratch in Java 21.

## Architecture & Modules

SyncForge is strictly decoupled into 20 granular JVM modules:

1. **core**: Diagnostic reporter, Source spans, Result monads, internal logger.
2. **path**: Unix-style path normalization, traversal defense (`../`), glob includes/excludes.
3. **metadata**: Strongly-typed model for file, directory, and symlink entries, permissions, hashes.
4. **manifest**: Lexer, Parser, AST, and serialization for the custom manifest DSL.
5. **scanner**: Crawler that scans folders deterministically and extracts metadata.
6. **snapshot**: Reader and Writer for the custom binary snapshot format (`SFSN`).
7. **diff**: differential comparison engine (add, delete, modify, rename, permission update).
8. **conflict**: conflict detector (case collisions, parallel modifications) and resolver.
9. **patch**: Reader and Writer for the custom binary patch format (`SFPH`).
10. **planner**: Topological sorter scheduling safe file system operations order.
11. **runtime**: Transaction executor and state machine with transparent reverse-order rollback.
12. **checksum**: SHA-256 and CRC32 implementations.
13. **compression**: Zlib/Deflate compression wrapper.
14. **indexing**: Fast lookup indices (paths, checksums, timestamps, extensions).
15. **query**: Compiler and evaluator for textual queries (e.g. `type:file AND size > 1000`).
16. **storage**: Local workspace store for managing snapshot histories.
17. **validation**: Multi-stage semantic validations.
18. **serialization**: Safe primitive binary stream serializers.
19. **cli**: Command-line interface.
20. **fuzz**: Jazzer fuzzer harnesses.

---

## Custom Binary Specifications

### 1. Snapshot Format (`SFSN`)
```text
+-------------------+------------------+------------------+
| Magic (SFSN) [4B] | Version (1) [2B] | Flags [2B]       |
+-------------------+------------------+------------------+
| Entry Count [4B]  | Path Table Offset [8B]              |
+---------------------------------------------------------+
| Metadata Table Offset [8B] | Checksum Table Offset [8B] |
+---------------------------------------------------------+
| Payload Length [8B]                                     |
+---------------------------------------------------------+
| Payload Data (Optionally compressed via Deflate)        |
+---------------------------------------------------------+
| Footer Checksum (SHA-256 over all preceding bytes) [32B]|
+---------------------------------------------------------+
```

### 2. Patch Format (`SFPH`)
```text
+-------------------+------------------+------------------+
| Magic (SFPH) [4B] | Version (1) [2B] | Op Count [4B]    |
+-------------------+------------------+------------------+
| Op Table Offset [8B]                 | Payload Offset [8B] |
+--------------------------------------+------------------+
| Payload Length [8B]                                     |
+---------------------------------------------------------+
| Operations & File Deltas Payload Data                   |
+---------------------------------------------------------+
| Footer Checksum (SHA-256 over all preceding bytes) [32B]|
+---------------------------------------------------------+
```

---

## CLI Manual & Examples

### 1. Validate a DSL Manifest
```bash
./gradlew :cli:run --args="manifest validate samples/basic_manifest.sfm"
```

### 2. Scan a Directory into a Binary Snapshot
```bash
./gradlew :cli:run --args="scan src --out snapshot.sfs"
```

### 3. Compute Differences between Snapshots
```bash
./gradlew :cli:run --args="diff old.sfs new.sfs --out update.sfp"
```

### 4. Query a Snapshot
```bash
./gradlew :cli:run --args="query snapshot.sfs 'type:file AND size > 1000'"
```

---

## Fuzzing and Verification

The project includes 7 Jazzer-compatible fuzz targets located in the `fuzz` module:
- `ManifestParserFuzzer`
- `SnapshotParserFuzzer`
- `PatchParserFuzzer`
- `DiffPlanFuzzer`
- `PathNormalizerFuzzer`
- `ConflictResolverFuzzer`
- `QueryParserFuzzer`

Seed corpora files are maintained under `fuzz/corpus/`. The project conforms to the ClusterFuzzLite layout with configuration scripts inside `.clusterfuzzlite/`.
