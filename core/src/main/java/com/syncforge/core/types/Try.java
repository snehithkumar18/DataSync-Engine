package com.syncforge.core.types;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Try is a monad that wraps computations that may throw exceptions. It represents either
 * a {@code Success} holding the result of a successful computation, or a {@code Failure}
 * holding the thrown exception.
 * 
 * <p>It allows propagating and chaining computations fluently without writing verbose
 * try-catch blocks.</p>
 *
 * @param <T> the type of the value held by a successful Try.
 */
public final class Try<T> {

    /**
     * Functional interface representing a supplier that may throw a checked exception.
     *
     * @param <T> the type of result.
     */
    @FunctionalInterface
    public interface CheckedSupplier<T> {
        /**
         * Computes a result, or throws a checked exception.
         *
         * @return computed result
         * @throws Throwable if unable to compute a result
         */
        T get() throws Throwable;
    }

    /**
     * Functional interface representing a function that may throw a checked exception.
     *
     * @param <T> the input type.
     * @param <R> the result type.
     */
    @FunctionalInterface
    public interface CheckedFunction<T, R> {
        /**
         * Applies this function to the given argument.
         *
         * @param t the function argument
         * @return the function result
         * @throws Throwable if unable to apply
         */
        R apply(T t) throws Throwable;
    }

    /**
     * Functional interface representing a runnable that may throw a checked exception.
     */
    @FunctionalInterface
    public interface CheckedRunnable {
        /**
         * Runs the action, or throws a checked exception.
         *
         * @throws Throwable if execution fails
         */
        void run() throws Throwable;
    }

    private final T value;
    private final Throwable failure;

    private Try(T value, Throwable failure) {
        this.value = value;
        this.failure = failure;
    }

    /**
     * Executes the throwing supplier computation and wraps the result in a Try.
     * If the supplier succeeds, returns a successful Try; if it throws, returns a failed Try.
     *
     * @param <T>      the computation result type.
     * @param supplier the throwing supplier. Must not be null.
     * @return a Try wrapping the outcome.
     * @throws NullPointerException if supplier is null.
     */
    public static <T> Try<T> of(CheckedSupplier<? extends T> supplier) {
        Objects.requireNonNull(supplier, "Supplier must not be null");
        try {
            return new Try<>(supplier.get(), null);
        } catch (Throwable t) {
            return new Try<>(null, t);
        }
    }

    /**
     * Executes the throwing runnable action and wraps the result as a Try of Void.
     *
     * @param runnable the throwing runnable. Must not be null.
     * @return a Try representing completion or failure.
     * @throws NullPointerException if runnable is null.
     */
    public static Try<Void> run(CheckedRunnable runnable) {
        Objects.requireNonNull(runnable, "Runnable must not be null");
        try {
            runnable.run();
            return new Try<>(null, null);
        } catch (Throwable t) {
            return new Try<>(null, t);
        }
    }

    /**
     * Creates a successful Try wrapping the specified value.
     *
     * @param <T>   the value type.
     * @param value the successful value. May be null (though successful non-null is preferred).
     * @return a successful Try.
     */
    public static <T> Try<T> success(T value) {
        return new Try<>(value, null);
    }

    /**
     * Creates a failed Try wrapping the specified throwable.
     *
     * @param <T>     the value type.
     * @param failure the failure cause. Must not be null.
     * @return a failed Try.
     * @throws NullPointerException if failure is null.
     */
    public static <T> Try<T> failure(Throwable failure) {
        Objects.requireNonNull(failure, "Failure exception must not be null");
        return new Try<>(null, failure);
    }

    /**
     * Checks if this Try represents a success.
     *
     * @return true if successful, false otherwise.
     */
    public boolean isSuccess() {
        return failure == null;
    }

    /**
     * Checks if this Try represents a failure.
     *
     * @return true if failed, false otherwise.
     */
    public boolean isFailure() {
        return failure != null;
    }

    /**
     * Returns the success value, or throws the underlying exception wrapped in a RuntimeException.
     *
     * @return the success value.
     * @throws RuntimeException if this Try represents a failure.
     */
    public T get() {
        if (isFailure()) {
            if (failure instanceof RuntimeException runtimeException) {
                throw runtimeException;
            }
            throw new RuntimeException("Try failed with a checked exception", failure);
        }
        return value;
    }

    /**
     * Returns the underlying failure cause.
     *
     * @return the {@link Throwable} if failed, or null if successful.
     */
    public Throwable getCause() {
        return failure;
    }

    /**
     * Maps the successful value of this Try using the throwing mapper.
     * If this Try is a failure, the mapper is bypassed, and the failure is propagated.
     *
     * @param <U>    the mapped type.
     * @param mapper the mapping function. Must not be null.
     * @return a new Try containing the mapped result or failure.
     * @throws NullPointerException if mapper is null.
     */
    @SuppressWarnings("unchecked")
    public <U> Try<U> map(CheckedFunction<? super T, ? extends U> mapper) {
        Objects.requireNonNull(mapper, "Mapper must not be null");
        if (isFailure()) {
            return (Try<U>) this;
        }
        try {
            return Try.success(mapper.apply(value));
        } catch (Throwable t) {
            return Try.failure(t);
        }
    }

    /**
     * Flat-maps the successful value of this Try using the throwing mapper yielding another Try.
     * If this Try is a failure, the mapper is bypassed, and the failure is propagated.
     *
     * @param <U>    the mapped Try type.
     * @param mapper the mapping function. Must not be null.
     * @return the result of the mapper, or the original failure.
     * @throws NullPointerException if mapper is null.
     */
    @SuppressWarnings("unchecked")
    public <U> Try<U> flatMap(CheckedFunction<? super T, ? extends Try<U>> mapper) {
        Objects.requireNonNull(mapper, "Mapper must not be null");
        if (isFailure()) {
            return (Try<U>) this;
        }
        try {
            return Objects.requireNonNull(mapper.apply(value), "FlatMap mapper result must not be null");
        } catch (Throwable t) {
            return Try.failure(t);
        }
    }

    /**
     * Recovers from a failure by applying the throwing recovery function.
     * If this Try is a success, the recovery function is bypassed.
     *
     * @param recovery the recovery function. Must not be null.
     * @return a successful Try containing the recovered value, or a failed Try if recovery throws.
     * @throws NullPointerException if recovery is null.
     */
    public Try<T> recover(CheckedFunction<? super Throwable, ? extends T> recovery) {
        Objects.requireNonNull(recovery, "Recovery function must not be null");
        if (isSuccess()) {
            return this;
        }
        try {
            return Try.success(recovery.apply(failure));
        } catch (Throwable t) {
            return Try.failure(t);
        }
    }

    /**
     * Recovers from a failure by applying a function returning another Try.
     * If this Try is a success, the recovery function is bypassed.
     *
     * @param recovery the recovery function. Must not be null.
     * @return the recovered Try, or a failed Try if recovery throws.
     * @throws NullPointerException if recovery is null.
     */
    public Try<T> recoverWith(CheckedFunction<? super Throwable, ? extends Try<T>> recovery) {
        Objects.requireNonNull(recovery, "Recovery function must not be null");
        if (isSuccess()) {
            return this;
        }
        try {
            return Objects.requireNonNull(recovery.apply(failure), "Recovery result must not be null");
        } catch (Throwable t) {
            return Try.failure(t);
        }
    }

    /**
     * Returns the successful value, or the fallback value if this is a failure.
     *
     * @param fallback the fallback value. Must not be null.
     * @return the value or the fallback.
     * @throws NullPointerException if fallback is null.
     */
    public T orElse(T fallback) {
        Objects.requireNonNull(fallback, "Fallback must not be null");
        return isSuccess() ? value : fallback;
    }

    /**
     * Returns the successful value, or invokes the throwing supplier for a fallback value.
     * If the supplier throws, a RuntimeException wraps it.
     *
     * @param fallbackSupplier the throwing fallback supplier. Must not be null.
     * @return the value or supplier's result.
     * @throws NullPointerException if fallbackSupplier is null.
     */
    public T orElseGet(CheckedSupplier<? extends T> fallbackSupplier) {
        Objects.requireNonNull(fallbackSupplier, "Fallback supplier must not be null");
        if (isSuccess()) {
            return value;
        }
        try {
            return fallbackSupplier.get();
        } catch (Throwable t) {
            throw new RuntimeException("Fallback supplier failed", t);
        }
    }

    /**
     * Converts this Try into a {@link Result}.
     *
     * @return a successful Result containing T, or a failed Result containing the Throwable.
     */
    public Result<T, Throwable> toResult() {
        return isSuccess() ? Result.success(value) : Result.failure(failure);
    }

    /**
     * Converts this Try into an {@link Optional}.
     *
     * @return an {@link Optional} containing the value if successful, or empty if failed.
     */
    public Optional<T> toOptional() {
        return isSuccess() ? Optional.ofNullable(value) : Optional.empty();
    }

    /**
     * Invokes the consumer with the success value if this is a success.
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
     * Invokes the consumer with the failure exception if this is a failure.
     *
     * @param consumer the consumer. Must not be null.
     * @throws NullPointerException if consumer is null.
     */
    public void ifFailure(Consumer<? super Throwable> consumer) {
        Objects.requireNonNull(consumer, "Consumer must not be null");
        if (isFailure()) {
            consumer.accept(failure);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Try<?> other)) return false;
        return Objects.equals(value, other.value) && Objects.equals(failure, other.failure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, failure);
    }

    @Override
    public String toString() {
        return isSuccess() ? "Success(" + value + ")" : "Failure(" + failure + ")";
    }
}
