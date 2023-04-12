package cz.cuni.mff.java.project.sykora;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 Writes trains into JSON object.
 @author Jakub Sykora
 @version 1.0.0
 @since 1.4.2023
 */
public class JsonTrainWriter implements TrainWriter {

    private final JSONObject trains;

    /**
     * Internally creates JSON array which each train being appended to the array.
     */
    public JsonTrainWriter() {
        trains = new JSONObject();
    }

    /**
     * Attach train to JSON array.
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
     * Attaches current JSON array to returned JSON object.
     */
    public JSONObject createRootJsonObject() {
        var root = new JSONObject();
        root.put("trains", trains);
        return root;
    }
}
