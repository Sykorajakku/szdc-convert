package cz.cuni.mff.java.project.sykora;

/**
    The ParserLogger interface defines a contract for handling and logging the results of train parsing operations.
    Implementing classes should provide concrete methods for handling parsing errors and successes.
*/
public interface ParserLogger {

    /**
         Method called when a parsing error occurs.
         Implementing classes should provide appropriate error handling and logging.
         @param error A string describing the parsing error.
     */
    void logTrainParseError(String error);

    /**
        Method called when a train is parsed successfully.
        Implementing classes should provide appropriate success handling and logging.
        @param success A string describing the successful parsing operation.
    */
    void logTrainParseSuccess(String success);
}
