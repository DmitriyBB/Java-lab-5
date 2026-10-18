package task06;

import java.util.Objects;

public final class Result<T> {

    private final T value;
    private final String error;

    private Result(T value, String error) {
        this.value = value;
        this.error = error;
    }

    public static <T> Result<T> ok(T value) {
        return new Result<>(Objects.requireNonNull(value, "value"), null);
    }

    public static <T> Result<T> error(String error) {
        Objects.requireNonNull(error, "error");
        if (error.isBlank()) {
            throw new IllegalArgumentException("error message must not be blank");
        }
        return new Result<>(null, error);
    }

    public boolean isOk() {
        return error == null;
    }

    public T get() {
        if (!isOk()) {
            throw new IllegalStateException("Result is an error: " + error);
        }
        return value;
    }

    public String error() {
        if (isOk()) {
            throw new IllegalStateException("Result is ok, there is no error");
        }
        return error;
    }

    @Override
    public String toString() {
        return isOk() ? "Ok(" + value + ")" : "Error(" + error + ")";
    }
}
