package cz.cuni.mff.java.project.sykora;

/**
 * Represents writer to write parsed train data.
 */
public interface TrainWriter {

    /**
     * Callback to process parsed train data.
     */
    void writeTrain(Train train);
}
