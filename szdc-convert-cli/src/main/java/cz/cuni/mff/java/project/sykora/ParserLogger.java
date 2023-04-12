package cz.cuni.mff.java.project.sykora;

/**
 * Represents callback consumer of train parser results.
 */
public interface ParserLogger {

    /**
     * Parser reports error.
     */
    void logTrainParseError(String error);

    /**
     * Parser reports parsed train success.
     */
    void logTrainParseSuccess(String success);
}
