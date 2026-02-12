package ru.feodorkek.dev.mcremapper.exception;

import java.text.MessageFormat;

public class RemappingException extends RuntimeException {
    
    public RemappingException( final String message,
                               final Object... messageParams ) {
        super( MessageFormat.format( message, messageParams ) );
    }
    
    public RemappingException( final String message,
                               final Throwable cause,
                               final Object... messageParams ) {
        super( MessageFormat.format( message, messageParams ), cause );
    }
    
}