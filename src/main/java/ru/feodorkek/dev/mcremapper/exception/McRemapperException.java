package ru.feodorkek.dev.mcremapper.exception;

public class McRemapperException extends RuntimeException {

    public McRemapperException(final String message) {
        super(message);
    }

    public McRemapperException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
