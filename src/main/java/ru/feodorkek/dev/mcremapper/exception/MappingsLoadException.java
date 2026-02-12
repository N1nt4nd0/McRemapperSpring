package ru.feodorkek.dev.mcremapper.exception;

import java.text.MessageFormat;

public class MappingsLoadException extends RuntimeException {
    
    public MappingsLoadException( final String message,
                                  final Object... messageParams ) {
        super( MessageFormat.format( message, messageParams ) );
    }
    
    public MappingsLoadException( final String message,
                                  final Throwable cause,
                                  final Object... messageParams ) {
        super( MessageFormat.format( message, messageParams ), cause );
    }
    
}