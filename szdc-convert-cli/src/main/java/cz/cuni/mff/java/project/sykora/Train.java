package cz.cuni.mff.java.project.sykora;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents parsed train.
 */
@Getter
public class Train {

    private final TrainType trainType;
    private final String trainNumber;
    private final List<String> descriptions = new ArrayList<>();
    private final List<StationDataRow> stationDataRows = new ArrayList<>();

    /**
     * Create
     * @param trainType
     * @param trainNumber
     */
    public Train(TrainType trainType, String trainNumber) {
        this.trainType = trainType;
        this.trainNumber = trainNumber;
    }
}
