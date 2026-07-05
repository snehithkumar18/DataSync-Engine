package com.syncforge.core.types;

import com.syncforge.core.diagnostics.Diagnostic;
import com.syncforge.core.exceptions.ValidationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Validation is a data type that represents either a success holding a valid value
 * or a failure accumulating one or more {@link Diagnostic} records.
 * 
 * <p>Unlike monadic types (like Result or Try) which short-circuit at the first failure,
 * Validation is designed to accumulate multiple error diagnostics across independent validation steps.</p>
 *
 * @param <T> the type of the successful value.
 */
public final class Validation<T> {

    private final T value;
    private final List<Diagnostic> diagnostics;

    private Validation(T value, List<Diagnostic> diagnostics) {
        this.value = value;
        this.diagnostics = diagnostics != null ? List.copyOf(diagnostics) : Collections.emptyList();
    }

    /**
     * Creates a valid Validation instance containing the specified value.
     *
     * @param <T>   the value type.
     * @param value the valid value. Must not be null.
     * @return a valid Validation instance.
     * @throws NullPointerException if value is null.
     */
    public static <T> Validation<T> valid(T value) {
        Objects.requireNonNull(value, "Value must not be null");
        return new Validation<>(value, null);
    }

    /**
     * Creates an invalid Validation instance containing a single diagnostic.
     *
     * @param <T>        the value type.
     * @param diagnostic the validation diagnostic. Must not be null.
     * @return an invalid Validation instance.
     * @throws NullPointerException if diagnostic is null.
     */
    public static <T> Validation<T> invalid(Diagnostic diagnostic) {
        Objects.requireNonNull(diagnostic, "Diagnostic must not be null");
        return new Validation<>(null, List.of(diagnostic));
    }

    /**
     * Creates an invalid Validation instance containing a list of diagnostics.
     *
     * @param <T>         the value type.
     * @param diagnostics the list of diagnostics. Must not be null or empty.
     * @return an invalid Validation instance.
     * @throws NullPointerException     if diagnostics is null.
     * @throws IllegalArgumentException if diagnostics is empty.
     */
    public static <T> Validation<T> invalid(List<Diagnostic> diagnostics) {
        Objects.requireNonNull(diagnostics, "Diagnostics must not be null");
        if (diagnostics.isEmpty()) {
            throw new IllegalArgumentException("Diagnostics list must not be empty");
        }
        return new Validation<>(null, diagnostics);
    }

    /**
     * Checks if this validation is valid.
     *
     * @return true if valid, false otherwise.
     */
    public boolean isValid() {
        return diagnostics.isEmpty();
    }

    /**
     * Checks if this validation is invalid.
     *
     * @return true if invalid, false otherwise.
     */
    public boolean isInvalid() {
        return !diagnostics.isEmpty();
    }

    /**
     * Returns the valid value, or throws a {@link ValidationException} containing all accumulated diagnostics.
     *
     * @return the valid value.
     * @throws ValidationException if this validation is invalid.
     */
    public T get() {
        if (isInvalid()) {
            throw new ValidationException("Validation failed", diagnostics);
        }
        return value;
    }

    /**
     * Returns the accumulated diagnostics.
     *
     * @return an unmodifiable list of diagnostics. May be empty if valid, but never null.
     */
    public List<Diagnostic> getDiagnostics() {
        return diagnostics;
    }

    /**
     * Maps the valid value using the provided function.
     * If this validation is invalid, the mapper is bypassed, and the diagnostics are propagated.
     *
     * @param <U>    the mapped value type.
     * @param mapper the mapping function. Must not be null.
     * @return a new Validation containing the mapped value or the diagnostics.
     * @throws NullPointerException if mapper is null.
     */
    @SuppressWarnings("unchecked")
    public <U> Validation<U> map(Function<? super T, ? extends U> mapper) {
        Objects.requireNonNull(mapper, "Mapper must not be null");
        if (isInvalid()) {
            return (Validation<U>) this;
        }
        return Validation.valid(mapper.apply(value));
    }

    /**
     * Flat-maps the valid value using the provided function.
     * If this validation is invalid, the mapper is bypassed.
     *
     * @param <U>    the mapped Validation type.
     * @param mapper the mapping function. Must not be null.
     * @return the resulting Validation from the mapper, or the original diagnostics.
     * @throws NullPointerException if mapper is null.
     */
    @SuppressWarnings("unchecked")
    public <U> Validation<U> flatMap(Function<? super T, ? extends Validation<U>> mapper) {
        Objects.requireNonNull(mapper, "Mapper must not be null");
        if (isInvalid()) {
            return (Validation<U>) this;
        }
        return Objects.requireNonNull(mapper.apply(value), "FlatMap mapper result must not be null");
    }

    /**
     * Combines (zips) this Validation with another Validation using the combiner function.
     * If both are valid, returns a valid Validation containing the combined result.
     * If either or both are invalid, returns an invalid Validation containing the accumulated diagnostics of both.
     *
     * @param <U>      the value type of the other Validation.
     * @param <V>      the value type of the combined Validation.
     * @param other    the other Validation. Must not be null.
     * @param combiner the function to combine the values. Must not be null.
     * @return the combined Validation.
     * @throws NullPointerException if other or combiner is null.
     */
    public <U, V> Validation<V> zip(Validation<U> other, BiFunction<? super T, ? super U, ? extends V> combiner) {
        Objects.requireNonNull(other, "Other Validation must not be null");
        Objects.requireNonNull(combiner, "Combiner function must not be null");

        if (this.isValid() && other.isValid()) {
            return Validation.valid(combiner.apply(this.value, other.value));
        }

        List<Diagnostic> combinedDiagnostics = new ArrayList<>();
        combinedDiagnostics.addAll(this.diagnostics);
        combinedDiagnostics.addAll(other.diagnostics);
        return Validation.invalid(combinedDiagnostics);
    }

    /**
     * Converts this Validation into a {@link Result}.
     *
     * @return a successful Result if valid, or a failed Result containing a {@link ValidationException} on invalid.
     */
    public Result<T, ValidationException> toResult() {
        if (isValid()) {
            return Result.success(value);
        }
        return Result.failure(new ValidationException("Validation failed", diagnostics));
    }

    /**
     * Converts this Validation into an {@link Optional}.
     *
     * @return an Optional containing the value if valid, or empty if invalid.
     */
    public Optional<T> toOptional() {
        return isValid() ? Optional.of(value) : Optional.empty();
    }

    /**
     * Invokes the consumer with the valid value if this validation is valid.
     *
     * @param consumer the consumer. Must not be null.
     * @throws NullPointerException if consumer is null.
     */
    public void ifValid(Consumer<? super T> consumer) {
        Objects.requireNonNull(consumer, "Consumer must not be null");
        if (isValid()) {
            consumer.accept(value);
        }
    }

    /**
     * Invokes the consumer with the accumulated diagnostics if this validation is invalid.
     *
     * @param consumer the consumer. Must not be null.
     * @throws NullPointerException if consumer is null.
     */
    public void ifInvalid(Consumer<? super List<Diagnostic>> consumer) {
        Objects.requireNonNull(consumer, "Consumer must not be null");
        if (isInvalid()) {
            consumer.accept(diagnostics);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Validation<?> other)) return false;
        return Objects.equals(value, other.value) && Objects.equals(diagnostics, other.diagnostics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, diagnostics);
    }

    @Override
    public String toString() {
        return isValid() ? "Valid(" + value + ")" : "Invalid(" + diagnostics + ")";
    }
}
