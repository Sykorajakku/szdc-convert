package cz.cuni.mff.java.project.sykora;

import java.util.List;

/**

 @author Jakub Sykora
 @version 1.0.0
 @since 1.4.2023
 */
public class ScheduleCategorize {

    public static void categorizeSchedule(List<StationDataRow> stationDataRows, List<MovementTime> arrivalTimeValues, List<MovementTime> departureTimeValues) {

        var arrivalIndex = 0;
        var departureIndex = 0;
        var stationIndex = 0;

        // regular times are always equal to station count or station count - 1, if second option, skip first station

        for ( ; stationIndex < stationDataRows.size(); ++stationIndex) {

            var stationDataRow = stationDataRows.get(stationIndex);

            if (arrivalIndex < arrivalTimeValues.size()) {

                var nextArrival = arrivalTimeValues.get(arrivalIndex);

                if (departureIndex == departureTimeValues.size()) {
                    stationDataRow.setArrivalTime(nextArrival);
                    arrivalIndex++;
                }
                else {
                    var nextDeparture = departureTimeValues.get(departureIndex);

                        if (nextArrival.isBeforeOrSame(nextDeparture) || (nextArrival.getHour() >= 23 && nextDeparture.getHour() < nextArrival.getHour())) {
                        stationDataRow.setArrivalTime(arrivalTimeValues.get(arrivalIndex));
                        arrivalIndex++;
                    }
                }
            }

            if (departureIndex < departureTimeValues.size()) {
                stationDataRow.setDepartureTime(departureTimeValues.get(departureIndex));
                ++departureIndex;
            }
        }
    }
}
