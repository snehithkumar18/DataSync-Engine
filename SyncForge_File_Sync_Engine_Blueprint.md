# SyncForge – Java/JVM File Sync & Manifest Engine Blueprint

## Purpose

Build **SyncForge**, an original Java/JVM file synchronization, manifest, snapshot, diff, patch, and conflict-resolution engine.

SyncForge should be a real software product, not a toy repository. It should provide reusable components for scanning file trees, generating manifests, computing differences between snapshots, applying patch files, resolving conflicts, validating sync plans, indexing file metadata, storing snapshots, and exposing CLI tooling.

It is not a clone of Syncthing, rsync, Git, Dropbox, Unison, or any public project. Public projects may be studied only for high-level architectural inspiration.

Do not copy public source code, package structure, class names, algorithms line-for-line, tests, documentation, or commit history.

---

## Why This Project Is Strong

File sync engines naturally contain:

- Structured manifest parsing
- Binary snapshot formats
- Diff/patch parsing
- Path normalization
- File metadata modeling
- Conflict detection
- Sync planning
- Stateful apply/rollback logic
- Hash/checksum validation
- Indexing
- Multi-stage validation
- Multiple parser and runtime entry points

---

## Recommended Project Name

Best choice: **SyncForge**

Other options:

- DeltaSync
- FileVault
- PatchForge
- MirrorCore
- SyncVault
- SnapshotForge
- SyncMesh
- FileDelta
- ReplicaForge
- ManifestSync

---

## Target Tech Stack

- Java 21 or Java 17 LTS
- Gradle Kotlin DSL
- Pure JVM library
- JUnit 5
- CLI module
- Jazzer-compatible fuzz harnesses
- ClusterFuzzLite-compatible layout
- Offline deterministic build

---

## High-Level Concept

SyncForge compares source and target file trees using manifests and snapshots. It creates a sync plan, detects conflicts, applies patches, and can restore or rollback state from snapshots.

Example manifest DSL:

```text
manifest "project_backup" {
  root = "/workspace/project"
  mode = "mirror"

  include "**/*.java"
  include "docs/**"
  exclude "build/**"
  exclude ".git/**"

  checksum = "sha256"
  preserve_permissions = true
}
```

Example CLI usage:

```bash
syncforge scan ./project --out project.sfm
syncforge diff old.sfs new.sfs --out update.sfp
syncforge plan source.sfm target.sfm
syncforge apply update.sfp --target ./backup
syncforge verify ./backup --manifest project.sfm
syncforge inspect snapshot.sfs
```

---

## Suggested Repository Layout

```text
syncforge/
├── settings.gradle.kts
├── build.gradle.kts
├── README.md
├── LICENSE
├── docs/
├── samples/
├── core/
├── path/
├── metadata/
├── manifest/
├── scanner/
├── snapshot/
├── diff/
├── patch/
├── conflict/
├── planner/
├── runtime/
├── checksum/
├── compression/
├── indexing/
├── storage/
├── validation/
├── query/
├── serialization/
├── cli/
├── fuzz/
│   ├── ManifestParserFuzzer.java
│   ├── SnapshotParserFuzzer.java
│   ├── PatchParserFuzzer.java
│   ├── DiffPlanFuzzer.java
│   ├── PathNormalizerFuzzer.java
│   ├── ConflictResolverFuzzer.java
│   └── QueryParserFuzzer.java
├── fuzz/corpus/
└── .clusterfuzzlite/
    ├── build.sh
    └── project.yaml
```

---

## Core Modules

### 1. core

Responsibilities:
- Common exceptions
- Diagnostics
- Source spans
- Error reporter
- Result types
- Configuration
- Internal logging abstraction
- Size/time utilities

### 2. path

Responsibilities:
- Path normalization
- Relative path handling
- Path rules
- Include/exclude matching
- Glob matching
- Case sensitivity modes
- Path safety validation

Important path cases:
```text
../ traversal
duplicate separators
case-insensitive collisions
Windows vs Unix separators
absolute vs relative paths
unicode normalization
reserved names
```

### 3. metadata

Responsibilities:
- File metadata model
- Directory metadata model
- Symlink metadata model
- Permissions model
- Timestamp model
- Size model
- Hash metadata
- Change reason model

Suggested classes:
```text
FileEntry
DirectoryEntry
SymlinkEntry
EntryMetadata
FileMode
FileTimestamp
FileHash
ChangeReason
```

### 4. manifest

Responsibilities:
- Manifest DSL lexer
- Manifest DSL parser
- Manifest AST
- Manifest model
- Include/exclude rules
- Sync options
- Manifest validation
- Manifest serializer

Example manifest DSL:
```text
manifest "backup" {
  root = "/data/project"
  include "**/*.java"
  exclude "build/**"
  checksum = "sha256"
  mode = "mirror"
}
```

### 5. scanner

Responsibilities:
- File tree scanner
- Directory walker
- Metadata collector
- Checksum collector
- Filter application
- Scan report generation
- Deterministic ordering

### 6. snapshot

Responsibilities:
- Snapshot model
- Snapshot reader
- Snapshot writer
- Binary snapshot format
- Snapshot manifest
- Entry table
- Path table
- Checksum table
- Snapshot validation

Custom snapshot format idea:
```text
SFSN
version: u16
flags: u16
entry_count: u32
path_table_offset: u64
metadata_table_offset: u64
checksum_table_offset: u64
payload_length: u64
footer_checksum
```

### 7. diff

Responsibilities:
- Snapshot comparison
- Added file detection
- Removed file detection
- Modified file detection
- Rename detection
- Permission change detection
- Conflict candidate detection
- Diff report generation

Diff result types:
```text
ADD
DELETE
MODIFY
RENAME
PERMISSION_CHANGE
TYPE_CHANGE
CONFLICT
UNCHANGED
```

### 8. patch

Responsibilities:
- Patch model
- Patch reader
- Patch writer
- Patch operation parser
- Patch validation
- Patch application
- Patch rollback
- Patch summary

Custom patch format idea:
```text
SFPH
version: u16
operation_count: u32
operation_table_offset: u64
payload_offset: u64
payload_length: u64
checksum
```

Patch operations:
```text
CREATE_FILE
DELETE_FILE
UPDATE_FILE
RENAME_PATH
CREATE_DIRECTORY
DELETE_DIRECTORY
UPDATE_PERMISSIONS
UPDATE_SYMLINK
```

### 9. conflict

Responsibilities:
- Conflict model
- Conflict detection
- Conflict classification
- Resolution strategy
- Merge policy
- Manual resolution report

Conflict types:
```text
BOTH_MODIFIED
DELETE_MODIFY
RENAME_RENAME
TYPE_CHANGE
CASE_COLLISION
PERMISSION_CONFLICT
SYMLINK_TARGET_CONFLICT
```

### 10. planner

Responsibilities:
- Sync plan builder
- Operation ordering
- Dependency ordering
- Safe apply ordering
- Conflict-aware planning
- Rollback plan creation
- Dry-run support

Recommended planning path:
```text
old snapshot
→ new snapshot
→ diff
→ conflict detection
→ operation graph
→ safe order
→ sync plan
```

### 11. runtime

Responsibilities:
- Patch executor
- Sync executor
- Operation lifecycle
- State transitions
- Error handling
- Rollback
- Partial failure recovery
- Execution report

Runtime states:
```text
PENDING
RUNNING
APPLIED
FAILED
ROLLED_BACK
SKIPPED
CONFLICTED
```

### 12. checksum

Responsibilities:
- SHA-256
- CRC32
- File hashing
- Streaming hashing
- Snapshot checksum validation
- Patch checksum validation

### 13. compression

Responsibilities:
- Compression abstraction
- Deflate-like wrapper
- Block compression metadata
- Compression validation
- Block reader/writer

### 14. indexing

Responsibilities:
- Path index
- Checksum index
- Timestamp index
- Extension index
- Snapshot index
- Patch index
- Index reader/writer

### 15. storage

Responsibilities:
- File-backed snapshot store
- In-memory snapshot store
- Patch store
- Temporary operation store
- Storage metadata
- Cleanup utilities

### 16. validation

Responsibilities:
- Manifest validation
- Snapshot validation
- Patch validation
- Path safety validation
- Operation ordering validation
- Conflict validation
- Checksum validation

### 17. query

Responsibilities:
- Query lexer
- Query parser
- Query AST
- Snapshot query evaluator
- Diff query evaluator
- Filter expressions

Example queries:
```text
type:file AND size > 1000000
ext:"java" AND changed:true
path contains "src/main"
checksum:"abc123"
```

### 18. serialization

Responsibilities:
- Serialize manifests
- Serialize snapshots
- Serialize diffs
- Serialize patches
- Serialize reports
- Serialize diagnostics

### 19. cli

Commands:
```text
syncforge scan <directory> --out snapshot.sfs
syncforge manifest validate sync.sfm
syncforge diff old.sfs new.sfs --out update.sfp
syncforge plan old.sfs new.sfs
syncforge apply update.sfp --target <directory>
syncforge rollback rollback.sfp --target <directory>
syncforge verify <directory> --snapshot snapshot.sfs
syncforge inspect snapshot.sfs
syncforge query snapshot.sfs 'type:file AND size > 1000'
```

---

## Fuzz Harnesses

Add these Jazzer-compatible fuzz harnesses:

```text
ManifestParserFuzzer.java
SnapshotParserFuzzer.java
PatchParserFuzzer.java
DiffPlanFuzzer.java
PathNormalizerFuzzer.java
ConflictResolverFuzzer.java
QueryParserFuzzer.java
```

Each harness should reach real project logic.

Example harness:

```java
public class SnapshotParserFuzzer {
    public static void fuzzerTestOneInput(byte[] data) {
        try {
            SnapshotReader reader = new SnapshotReader();
            reader.read(data);
        } catch (SyncForgeParseException | SyncForgeValidationException expected) {
            // Invalid user input is expected.
        }
    }
}
```

---

## Seed Corpus

Create small valid inputs for each harness.

```text
fuzz/corpus/ManifestParserFuzzer/basic.sfm
fuzz/corpus/ManifestParserFuzzer/include-exclude.sfm
fuzz/corpus/SnapshotParserFuzzer/minimal.sfs
fuzz/corpus/SnapshotParserFuzzer/nested-tree.sfs
fuzz/corpus/PatchParserFuzzer/minimal.sfp
fuzz/corpus/DiffPlanFuzzer/basic-diff.txt
fuzz/corpus/PathNormalizerFuzzer/paths.txt
fuzz/corpus/ConflictResolverFuzzer/conflict.json
fuzz/corpus/QueryParserFuzzer/basic.query
```

Example seed manifest:

```text
manifest "project" {
  root = "."
  include "src/**"
  include "docs/**"
  exclude "build/**"
  checksum = "sha256"
  mode = "mirror"
}
```

---

## Strong Engineering Areas

Focus on real complexity:

- Manifest DSL parsing
- Path normalization
- Glob include/exclude matching
- Snapshot binary format
- Patch binary format
- Diff generation
- Conflict detection
- Operation graph ordering
- Runtime rollback
- Index/query interaction
- Checksum validation

Recommended execution path:

```text
read manifest
→ scan file tree
→ normalize paths
→ collect metadata
→ build snapshot
→ compare snapshots
→ detect conflicts
→ build patch
→ validate operation graph
→ apply patch
→ update indexes
→ verify final state
```

---

## Good Natural Bug-Prone Areas To Test

Do **not** intentionally plant fake bugs.

Instead, build real functionality and use testing/fuzzing to discover genuine issues.

| Area | Natural issue to test |
|---|---|
| Manifest parser | Nested include/exclude blocks and invalid escapes |
| Path normalizer | `../`, mixed separators, unicode/case normalization |
| Glob matcher | Recursive `**` matching and empty segments |
| Snapshot reader | Length/offset inconsistencies |
| Patch reader | Operation table mismatch |
| Diff engine | Rename/delete/modify ambiguity |
| Conflict resolver | Both-modified vs delete-modify confusion |
| Planner | Invalid operation ordering |
| Runtime | Partial failure rollback state bug |
| Indexing | Snapshot/index mismatch |
| Query parser | Boolean precedence and quoted path terms |

Likely JVM failure modes during testing:
```text
IndexOutOfBoundsException
IllegalStateException
StackOverflowError
OutOfMemoryError
NegativeArraySizeException
ArithmeticException
ClassCastException
```

Avoid:
```text
Hardcoded crash triggers
Magic-byte-only crashes
Simple null pointer bugs
One-function bugs
Bugs only inside fuzz harnesses
Filtering crashing input instead of fixing root cause
```

Strongest direction:
```text
snapshot reader
→ diff engine
→ conflict resolver
→ patch planner
→ runtime rollback validation
```

---

## Development Phases

### Phase 1 – Project Setup
- Create Gradle multi-module project.
- Add README.
- Add docs folder.
- Add samples folder.
- Add basic test structure.

### Phase 2 – Core Infrastructure
- Error model
- Diagnostics
- Source spans
- Configuration
- Result types
- Utility classes

### Phase 3 – Path Module
- Path normalization
- Include/exclude matching
- Glob parser
- Path safety validation
- Tests

### Phase 4 – Metadata Model
- File entry model
- Directory entry model
- Symlink entry model
- Permissions model
- Timestamp model
- Hash model
- Tests

### Phase 5 – Manifest Engine
- Manifest lexer
- Manifest parser
- Manifest AST
- Manifest model
- Manifest validation
- Tests

### Phase 6 – Scanner
- Directory walker
- Metadata collector
- Checksum collector
- Filter application
- Deterministic ordering
- Tests

### Phase 7 – Snapshot Format
- Snapshot model
- Snapshot writer
- Snapshot reader
- Entry table
- Path table
- Checksum table
- Tests

### Phase 8 – Diff Engine
- Snapshot comparison
- Add/delete/modify detection
- Rename detection
- Permission changes
- Diff report
- Tests

### Phase 9 – Conflict Engine
- Conflict detection
- Conflict classification
- Conflict resolution strategy
- Merge policy
- Tests

### Phase 10 – Patch Format
- Patch model
- Patch reader
- Patch writer
- Operation parser
- Patch validation
- Tests

### Phase 11 – Planner
- Sync plan builder
- Operation graph
- Safe ordering
- Rollback plan
- Dry-run plan
- Tests

### Phase 12 – Runtime
- Patch executor
- Operation lifecycle
- Failure handling
- Rollback
- Partial failure recovery
- Execution reports
- Tests

### Phase 13 – Indexing
- Path index
- Checksum index
- Timestamp index
- Extension index
- Index reader/writer
- Tests

### Phase 14 – Query Engine
- Query lexer
- Query parser
- Query AST
- Snapshot query evaluator
- Diff query evaluator
- Tests

### Phase 15 – Storage
- File-backed snapshot store
- In-memory snapshot store
- Patch store
- Temporary operation store
- Tests

### Phase 16 – CLI
- scan
- manifest validate
- diff
- plan
- apply
- rollback
- verify
- inspect
- query

### Phase 17 – Fuzzing Integration
- Add fuzz harnesses
- Add seed corpus
- Add ClusterFuzzLite files

### Phase 18 – Documentation
- Architecture guide
- Manifest DSL spec
- Snapshot binary format spec
- Patch binary format spec
- Diff algorithm guide
- Conflict resolution guide
- CLI manual
- Testing guide
- Fuzzing guide

---

## How To Reach 50k LOC Properly

Do not add filler. Reach 50k by building real modules.

Estimated LOC target:

| Area | LOC target |
|---|---:|
| Core diagnostics/errors | 3k |
| Path normalization/glob matching | 5k |
| Metadata model | 4k |
| Manifest parser | 5k |
| Scanner | 4k |
| Snapshot format | 6k |
| Diff engine | 6k |
| Conflict engine | 5k |
| Patch format | 6k |
| Planner | 5k |
| Runtime/rollback | 6k |
| Checksum/compression | 3k |
| Indexing | 4k |
| Query engine | 5k |
| Storage | 3k |
| CLI | 2k |
| Fuzz harnesses/samples | 1k |
| Tests | 15k–30k |

Estimated first-party source excluding tests: **65k–75k LOC**  
Estimated including tests: **80k–105k LOC**

Fastest meaningful path:
```text
Path module
→ Metadata model
→ Manifest parser
→ Scanner
→ Snapshot format
→ Diff engine
→ Patch format
→ Conflict resolver
→ Planner
→ Runtime rollback
→ Query/indexing
→ CLI
→ Tests
→ Fuzz harnesses
```

Aim for:
```text
180–280 Java files
200–350 meaningful lines per file average
50k+ meaningful first-party LOC
multiple parser entry points
multiple test suites
```

---

