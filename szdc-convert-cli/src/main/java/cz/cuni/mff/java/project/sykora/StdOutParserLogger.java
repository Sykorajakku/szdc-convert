package cz.cuni.mff.java.project.sykora;

/**
    The StdOutParserLogger class implements the ParserLogger interface for logging train parsing results
    to the standard console output. It provides methods for logging errors and successes related to train parsing.
 */
public class StdOutParserLogger implements ParserLogger {

    /**
         Logs the parsing error to the standard console output.
         @param error A string describing the parsing error.
     */
    @Override
    public void logTrainParseError(String error) {
        System.out.println(error);
    }

    /**
         Logs the successful parsing of a train to the standard console output.
         @param success A string describing the successful parsing operation.
     */
    @Override
    public void logTrainParseSuccess(String success) {
        System.out.println(success);
    }
}
