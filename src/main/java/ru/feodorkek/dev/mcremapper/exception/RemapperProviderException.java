package ru.feodorkek.dev.mcremapper.exception;

import java.text.MessageFormat;

public class RemapperProviderException extends RuntimeException {
    
    public RemapperProviderException( final String message,
                                      final Object... messageParams ) {
        super( MessageFormat.format( message, messageParams ) );
    }
    
    public RemapperProviderException( final String message,
                                      final Throwable cause,
                                      final Object... messageParams ) {
        super( MessageFormat.format( message, messageParams ), cause );
    }
    
}