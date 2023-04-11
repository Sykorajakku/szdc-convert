package cz.cuni.mff.java.project.sykora;

/**
    Allows throwing an exception specifically for import-related errors that may occur in the CLI.
    @author Jakub Sykora
    @version 1.0.0
    @since 1.4.2023
*/
public class ImportException extends Exception {

    /**
        Constructs a new ImportException object with the specified detail message and cause.
        @param message the detail message (which is saved for later retrieval by the getMessage() method)
        @param cause the cause (which is saved for later retrieval by the getCause() method). A null value is permitted,
         and indicates that the cause is nonexistent or unknown.
    */
    public ImportException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
        Constructs a new ImportException object with the specified detail message.
        @param message the detail message (which is saved for later retrieval by the getMessage() method)
    */        
    public ImportException(String message) {
        super(message);
    }
}
