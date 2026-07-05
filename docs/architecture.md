# SyncForge Architecture Guide

SyncForge is a modular JVM-based file tree replication and synchronization engine.

## Modular Decomposition

SyncForge is composed of distinct modules:

- **core**: Base exceptions, diagnostics, diagnostic reporter.
- **checksum**: SHA-256 and CRC32 implementations.
- **compression**: Deflate-based compression abstraction.
- **path**: Separator normalization, path rules, safety validation (traversal checks), glob include/exclude.
- **metadata**: Strong types representing file, directory, and symlink states, permissions, and modification times.
- **validation**: Ensures semantic coherence of manifests, paths, and operation sequences.
- **serialization**: Binary/text serialization helper tools.
- **manifest**: Custom Lexer, Parser, AST, and DSL evaluator for manifest files.
- **scanner**: High-performance recursive crawler that records snapshot structures deterministically.
- **snapshot**: Serialization and parsing logic for the custom binary snapshot format (`SFSN`).
- **diff**: Determines changes (add, delete, modify, rename, permission update) between snapshots.
- **conflict**: Detects race conditions and implements resolution strategies.
- **patch**: Formulates and records the binary patch format (`SFPH`).
- **planner**: Topological operation graph generator that sequences files/directories updates safely.
- **runtime**: State machine that applies patches and rolls back transactionally if a step fails.
- **indexing**: Speeds up queries and lookups.
- **query**: AST parser and evaluator for query filters.
- **storage**: Local workspace repository managing history and database files.
- **cli**: Command line entry points.
- **fuzz**: Jazzer harness integrations.
