package cz.cuni.mff.java.project.sykora;

import lombok.Data;

/**
 Intermediate parsed data grouping raw data from Excel sheet about arrival and departure to station.
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
