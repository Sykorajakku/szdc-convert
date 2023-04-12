package cz.cuni.mff.java.project.sykora;

import lombok.Data;

/**
    The StationDataRow class represents intermediate parsed data from an Excel sheet,
    containing information about a train's arrival and departure times at a specific station.
    This class is used to group raw data for further processing.
    @author Jakub Sykora
    @version 1.0.0
    @since 1.4.2023
 */
@Data
public class StationDataRow {

    private final String station;
    private MovementTime arrivalTime;
    private MovementTime departureTime;

    public StationDataRow(String station) {
        this.station = station;
    }

}
