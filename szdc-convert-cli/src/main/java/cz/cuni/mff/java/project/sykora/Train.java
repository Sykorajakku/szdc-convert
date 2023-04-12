package cz.cuni.mff.java.project.sykora;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
    The Train class represents a parsed train with its associated details, such as train type, train number,
    descriptions, and a list of station data rows. This class is used to store and manage train data after
    parsing from an Excel file.
 */
@Getter
public class Train {

    private final TrainType trainType;
    private final String trainNumber;
    private final List<String> descriptions = new ArrayList<>();
    private final List<StationDataRow> stationDataRows = new ArrayList<>();

    /**
         Constructs a Train instance with the specified train type and train number.
         @param trainType The type of the train (e.g., regional, express, etc.).
         @param trainNumber The unique number identifying the train.
     */
    public Train(TrainType trainType, String trainNumber) {
        this.trainType = trainType;
        this.trainNumber = trainNumber;
    }
}
