package cz.cuni.mff.java.project.sykora;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**

 The JsonTrainWriter class is responsible for converting train schedules into a JSON object.
 The class implements the TrainWriter interface.
 @author Jakub Sykora
 @version 1.0.0
 @since 1.4.2023
 */
public class JsonTrainWriter implements TrainWriter {

    private final JSONObject trains;

    /**
     Constructor for the JsonTrainWriter class.
     Initializes an empty JSON object to store train information.
     */
    public JsonTrainWriter() {
        trains = new JSONObject();
    }

    /**
     Adds a Train object to the internal JSON object.
     The method creates a JSON representation of the train and appends it to the internal JSON object.
     @param train The Train object to be written into the JSON object.
     */
    @Override
    public void writeTrain(Train train) {
        int x = Integer.parseInt(train.getTrainNumber());

        var trainJSON = new JSONObject();
        trainJSON.put("trainType", train.getTrainType().toString());

        var scheduleJSON = new JSONArray();

        List<StationDataRow> dataRows = train.getStationDataRows();
        for (StationDataRow dataRow : dataRows) {

            var stationStopJSON = new JSONObject();
            stationStopJSON.put("stationName", dataRow.getStation().trim().replaceAll("\\.+?$", ""));

            JSONObject arrivalTimeJSON = null;
            var arrivalTime = dataRow.getArrivalTime();
            if (arrivalTime != null) {
                arrivalTimeJSON = new JSONObject();
                arrivalTimeJSON.put("hours", arrivalTime.getHour());
                arrivalTimeJSON.put("minutes", arrivalTime.getMinute());
                arrivalTimeJSON.put("isAfter30seconds", arrivalTime.isMoreThan30Seconds());
            }
            stationStopJSON.put("arrivalTime", arrivalTimeJSON);

            JSONObject departureTimeJSON = null;
            var departureTime = dataRow.getDepartureTime();
            if (departureTime != null) {
                departureTimeJSON = new JSONObject();
                departureTimeJSON.put("hours", departureTime.getHour());
                departureTimeJSON.put("minutes", departureTime.getMinute());
                departureTimeJSON.put("isAfter30Seconds", departureTime.isMoreThan30Seconds());
            }
            stationStopJSON.put("departureTime", departureTimeJSON);

            scheduleJSON.put(stationStopJSON);
        }

        trainJSON.put("schedule", scheduleJSON);
        trains.put(train.getTrainNumber(), trainJSON);
    }

    /**
     Constructs a root JSON object containing the JSON object representing all the trains.
     @return A JSON object containing the "trains" key with the JSON object representing all the trains.
     */
    public JSONObject createRootJsonObject() {
        var root = new JSONObject();
        root.put("trains", trains);
        return root;
    }
}
