package cz.cuni.mff.java.project.sykora;

/*
    Implements logger for standard console.
 */
public class StdOutParserLogger implements ParserLogger {
    @Override
    public void logTrainParseError(String error) {
        System.out.println(error);
    }

    @Override
    public void logTrainParseSuccess(String success) {
        System.out.println(success);
    }
}
