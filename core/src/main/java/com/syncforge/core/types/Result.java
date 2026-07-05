package com.syncforge.core.types;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Result represents the outcome of a computation that can either succeed with a value of type {@code T}
 * or fail with an exception of type {@code E}.
 * 
 * <p>It provides monadic operators like {@code map}, {@code flatMap}, {@code recover}, {@code filter},
 * and {@code zip} to enable fluent functional programming patterns without explicit try-catch blocks.</p>
 *
 * @param <T> the type of the success value.
 * @param <E> the type of the failure exception.
 */
public final class Result<T, E extends Throwable> {

    private final T value;
    private final E error;

    private Result(T value, E error) {
        this.value = value;
        this.error = error;
    }

    /**
     * Creates a successful Result wrapping the specified value.
     *
     * @param <T>   the value type.
     * @param <E>   the error type.
     * @param value the successful result value. Must not be null.
     * @return a successful Result.
     * @throws NullPointerException if value is null.
     */
    public static <T, E extends Throwable> Result<T, E> success(T value) {
        Objects.requireNonNull(value, "Success value must not be null");
        return new Result<>(value, null);
    }

    /**
     * Creates a failed Result wrapping the specified exception.
     *
     * @param <T>   the value type.
     * @param <E>   the error type.
     * @param error the exception representing the failure. Must not be null.
     * @return a failed Result.
     * @throws NullPointerException if error is null.
     */
    public static <T, E extends Throwable> Result<T, E> failure(E error) {
        Objects.requireNonNull(error, "Failure exception must not be null");
        return new Result<>(null, error);
    }

    /**
     * Checks if this Result represents a success.
     *
     * @return true if successful, false otherwise.
     */
    public boolean isSuccess() {
        return error == null;
    }

    /**
     * Checks if this Result represents a failure.
     *
     * @return true if failed, false otherwise.
     */
    public boolean isFailure() {
        return error != null;
    }

    /**
     * Returns the success value wrapped in an {@link Optional}.
     *
     * @return an {@link Optional} containing the value if successful, or empty if failed.
     */
    public Optional<T> getValue() {
        return Optional.ofNullable(value);
    }

    /**
     * Returns the failure exception wrapped in an {@link Optional}.
     *
     * @return an {@link Optional} containing the exception if failed, or empty if successful.
     */
    public Optional<E> getError() {
        return Optional.ofNullable(error);
    }

    /**
     * Gets the success value, or throws the underlying exception if it is a failure.
     *
     * @return the success value.
     * @throws E the underlying failure exception.
     */
    public T orElseThrow() throws E {
        if (isFailure()) {
            throw error;
        }
        return value;
    }

    /**
     * Gets the success value, or returns the specified fallback value if it is a failure.
     *
     * @param fallback the fallback value to return on failure. Must not be null.
     * @return the success value or the fallback.
     * @throws NullPointerException if fallback is null.
     */
    public T orElse(T fallback) {
        Objects.requireNonNull(fallback, "Fallback value must not be null");
        return isSuccess() ? value : fallback;
    }

    /**
     * Maps the success value using the provided function. If this Result is a failure,
     * the mapper is bypassed, and the failure is propagated.
     *
     * @param <U>    the mapped value type.
     * @param mapper the mapping function. Must not be null.
     * @return a new Result containing the mapped value or the original failure.
     * @throws NullPointerException if mapper is null.
     */
    @SuppressWarnings("unchecked")
    public <U> Result<U, E> map(Function<? super T, ? extends U> mapper) {
        Objects.requireNonNull(mapper, "Mapper must not be null");
        if (isFailure()) {
            return (Result<U, E>) this;
        }
        try {
            return Result.success(mapper.apply(value));
        } catch (Throwable t) {
            // If the mapper itself throws an exception, propagate it as failure if compatible
            // otherwise wrap/rethrow
            return Result.failure((E) t);
        }
    }

    /**
     * Flat-maps the success value using the provided function yielding another Result.
     * If this Result is a failure, the mapper is bypassed and the failure is propagated.
     *
     * @param <U>    the mapped value type.
     * @param mapper the mapping function. Must not be null.
     * @return the resulting Result of the mapper call, or the original failure.
     * @throws NullPointerException if mapper is null.
     */
    @SuppressWarnings("unchecked")
    public <U> Result<U, E> flatMap(Function<? super T, ? extends Result<U, E>> mapper) {
        Objects.requireNonNull(mapper, "Mapper must not be null");
        if (isFailure()) {
            return (Result<U, E>) this;
        }
        return Objects.requireNonNull(mapper.apply(value), "FlatMap mapper result must not be null");
    }

    /**
     * Recovers from a failure by applying the recovery function to the exception.
     * If this Result is a success, the recovery is bypassed and this Result is returned.
     *
     * @param recovery the recovery function. Must not be null.
     * @return a successful Result containing either the original value or the recovered value.
     * @throws NullPointerException if recovery is null.
     */
    public Result<T, E> recover(Function<? super E, ? extends T> recovery) {
        Objects.requireNonNull(recovery, "Recovery function must not be null");
        if (isSuccess()) {
            return this;
        }
        return Result.success(recovery.apply(error));
    }

    /**
     * Recovers from a failure by applying a function that returns a new Result.
     * If this Result is a success, it is bypassed and this Result is returned.
     *
     * @param recovery the recovery function. Must not be null.
     * @return the recovered Result.
     * @throws NullPointerException if recovery is null.
     */
    public Result<T, E> recoverWith(Function<? super E, ? extends Result<T, E>> recovery) {
        Objects.requireNonNull(recovery, "Recovery function must not be null");
        if (isSuccess()) {
            return this;
        }
        return Objects.requireNonNull(recovery.apply(error), "Recovery result must not be null");
    }

    /**
     * Filters a successful Result. If the predicate holds, the Result is returned unchanged.
     * If the predicate is false, it returns a failed Result containing the exception generated
     * by the {@code errorGenerator}.
     *
     * @param predicate      the filtering predicate. Must not be null.
     * @param errorGenerator the generator for the exception if filter fails. Must not be null.
     * @return this Result if successful and matches predicate, or a failure.
     * @throws NullPointerException if predicate or errorGenerator is null.
     */
    public Result<T, E> filter(Predicate<? super T> predicate, Function<? super T, ? extends E> errorGenerator) {
        Objects.requireNonNull(predicate, "Predicate must not be null");
        Objects.requireNonNull(errorGenerator, "Error generator must not be null");
        if (isFailure()) {
            return this;
        }
        if (predicate.test(value)) {
            return this;
        }
        return Result.failure(errorGenerator.apply(value));
    }

    /**
     * Zips this successful Result with another successful Result using the combiner function.
     * If either Result is a failure, the first encountered failure is returned.
     *
     * @param <U>      the value type of the other Result.
     * @param <V>      the value type of the zipped result.
     * @param other    the other Result to combine with. Must not be null.
     * @param combiner the combiner function. Must not be null.
     * @return a zipped Result.
     * @throws NullPointerException if other or combiner is null.
     */
    @SuppressWarnings("unchecked")
    public <U, V> Result<V, E> zip(Result<U, E> other, BiFunction<? super T, ? super U, ? extends V> combiner) {
        Objects.requireNonNull(other, "Other Result must not be null");
        Objects.requireNonNull(combiner, "Combiner must not be null");
        if (this.isFailure()) {
            return (Result<V, E>) this;
        }
        if (other.isFailure()) {
            return (Result<V, E>) other;
        }
        return Result.success(combiner.apply(this.value, other.value));
    }

    /**
     * Invokes the consumer with the success value if this represents a success.
     *
     * @param consumer the consumer. Must not be null.
     * @throws NullPointerException if consumer is null.
     */
    public void ifSuccess(Consumer<? super T> consumer) {
        Objects.requireNonNull(consumer, "Consumer must not be null");
        if (isSuccess()) {
            consumer.accept(value);
        }
    }

    /**
     * Invokes the consumer with the failure exception if this represents a failure.
     *
     * @param consumer the consumer. Must not be null.
     * @throws NullPointerException if consumer is null.
     */
    public void ifFailure(Consumer<? super E> consumer) {
        Objects.requireNonNull(consumer, "Consumer must not be null");
        if (isFailure()) {
            consumer.accept(error);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Result<?, ?> other)) return false;
        return Objects.equals(value, other.value) && Objects.equals(error, other.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, error);
    }

    @Override
    public String toString() {
        return isSuccess() ? "Success(" + value + ")" : "Failure(" + error + ")";
    }
}
