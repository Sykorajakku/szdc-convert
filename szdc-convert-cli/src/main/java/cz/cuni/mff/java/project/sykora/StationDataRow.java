package cz.cuni.mff.java.project.sykora;

import lombok.Data;

@Data
public class StationDataRow {

    private final String station;
    //private RegularTime regularTime;
    private MovementTime arrivalTime;
    private MovementTime departureTime;

    public StationDataRow(String station) {
        this.station = station;
    }

}
