#!/bin/bash -eu

# Build the project
chmod +x ./gradlew
./gradlew classes

FUZZERS=(
  "ManifestParserFuzzer"
  "SnapshotParserFuzzer"
  "PatchParserFuzzer"
  "DiffPlanFuzzer"
  "PathNormalizerFuzzer"
  "ConflictResolverFuzzer"
  "QueryParserFuzzer"
)

JAR_OUT=$OUT/syncforge-fuzz.jar

# Create JAR with all module classes
jar cf $JAR_OUT -C core/build/classes/java/main . \
                -C checksum/build/classes/java/main . \
                -C compression/build/classes/java/main . \
                -C path/build/classes/java/main . \
                -C metadata/build/classes/java/main . \
                -C validation/build/classes/java/main . \
                -C serialization/build/classes/java/main . \
                -C manifest/build/classes/java/main . \
                -C scanner/build/classes/java/main . \
                -C snapshot/build/classes/java/main . \
                -C diff/build/classes/java/main . \
                -C conflict/build/classes/java/main . \
                -C patch/build/classes/java/main . \
                -C planner/build/classes/java/main . \
                -C runtime/build/classes/java/main . \
                -C indexing/build/classes/java/main . \
                -C query/build/classes/java/main . \
                -C storage/build/classes/java/main . \
                -C cli/build/classes/java/main . \
                -C fuzz/build/classes/java/main .

# Create fuzzer wrapper scripts
for fuzzer in "${FUZZERS[@]}"; do
  echo "#!/bin/sh" > $OUT/$fuzzer
  echo "exec \$JVM_15_ROOT/bin/java -cp \$JAR_OUT com.code_intelligence.jazzer.Jazzer --target_class=com.syncforge.fuzz.$fuzzer \"\$@\"" >> $OUT/$fuzzer
  chmod +x $OUT/$fuzzer
  
  # Create seed corpus if corpus directory exists
  if [ -d "fuzz/corpus/$fuzzer" ]; then
    zip -j $OUT/${fuzzer}_seed_corpus.zip fuzz/corpus/$fuzzer/* 2>/dev/null || true
  fi
done
