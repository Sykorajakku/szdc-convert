package cz.cuni.mff.java.project.sykora;

/**
 * Interface representing a writer responsible for processing and storing parsed train data.
 */
public interface TrainWriter {

    /**
     * Processes and stores the parsed train data.
     *
     * This method is called when a train is successfully parsed from the input data source.
     * Implementations of this interface should define how the parsed train data is processed and stored.
     *
     * @param train The Train object containing parsed train data.
     */
    void writeTrain(Train train);
}
