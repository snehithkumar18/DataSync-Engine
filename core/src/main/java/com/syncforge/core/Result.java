package com.syncforge.core;

import java.util.Optional;
import java.util.Objects;

/**
 * Legacy wrapper for {@link com.syncforge.core.types.Result}.
 * 
 * @deprecated Use {@link com.syncforge.core.types.Result} under the com.syncforge.core.types package.
 * 
 * @param <T> the type of the success value.
 * @param <E> the type of the failure exception.
 */
@Deprecated(since = "1.0.0", forRemoval = true)
public class Result<T, E extends Throwable> {

    private final com.syncforge.core.types.Result<T, E> delegate;

    private Result(com.syncforge.core.types.Result<T, E> delegate) {
        this.delegate = Objects.requireNonNull(delegate);
    }

    /**
     * Constructs a success Result wrapper.
     *
     * @param <T>   the success type
     * @param <E>   the error type
     * @param value the successful value
     * @return successful legacy result
     */
    public static <T, E extends Throwable> Result<T, E> success(T value) {
        return new Result<>(com.syncforge.core.types.Result.success(value));
    }

    /**
     * Constructs a failure Result wrapper.
     *
     * @param <T>   the success type
     * @param <E>   the error type
     * @param error the underlying error
     * @return failed legacy result
     */
    public static <T, E extends Throwable> Result<T, E> failure(E error) {
        return new Result<>(com.syncforge.core.types.Result.failure(error));
    }

    /**
     * Checks success state.
     *
     * @return true if successful
     */
    public boolean isSuccess() {
        return delegate.isSuccess();
    }

    /**
     * Checks failure state.
     *
     * @return true if failed
     */
    public boolean isFailure() {
        return delegate.isFailure();
    }

    /**
     * Gets the optional success value.
     *
     * @return optional value
     */
    public Optional<T> getValue() {
        return delegate.getValue();
    }

    /**
     * Gets the optional failure error.
     *
     * @return optional error
     */
    public Optional<E> getError() {
        return delegate.getError();
    }

    /**
     * Returns the success value or throws the exception.
     *
     * @return success value
     * @throws E underlying error
     */
    public T orElseThrow() throws E {
        return delegate.orElseThrow();
    }

    /**
     * Returns the underlying delegate result.
     *
     * @return the result monad.
     */
    public com.syncforge.core.types.Result<T, E> toModernResult() {
        return delegate;
    }
}
