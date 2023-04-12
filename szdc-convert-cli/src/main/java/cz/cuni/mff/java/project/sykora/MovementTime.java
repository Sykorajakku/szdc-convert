package cz.cuni.mff.java.project.sykora;

import lombok.Getter;

/**
    Represents a time consisting of an hour and minute, and a flag indicating whether the time is greater than or equal to 30 seconds.
    @author Jakub Sykora
    @version 1.0.0
    @since 1.4.2023
*/
@Getter
public class MovementTime {

    private final boolean moreThan30Seconds;
    private final int hour;
    private final int minute;

    /**
        Constructs a new MovementTime object with the specified hour, minute, and flag indicating whether the time is greater than or equal to 30 seconds.
        @param hour the hour component of the movement time
        @param minute the minute component of the movement time
        @param moreThan30Seconds a boolean flag indicating whether the time is greater than or equal to 30 seconds
    */
    public MovementTime(int hour, int minute, boolean moreThan30Seconds) {
        this.hour = hour;
        this.minute = minute;
        this.moreThan30Seconds = moreThan30Seconds;
    }

    /**
        Determines if this MovementTime object is before or the same time as the specified MovementTime object.
        If this MovementTime object is before or the same time, the method returns true, otherwise it returns false.
        @param other the MovementTime object to compare with
        @return true if this MovementTime object is before or the same time as the specified MovementTime object, otherwise false
    */
    public boolean isBeforeOrSame(MovementTime other) {

        if (hour < other.hour) return true;
        else if (hour > other.hour) return false;
        else {
            if (minute < other.minute) return true;
            else if (minute > other.minute) return false;
            else {
                return other.isMoreThan30Seconds() || !isMoreThan30Seconds();
            }
        }
    }

    @Override
    public String toString() {
        return hour + ":" + minute + " + 30 sec? -> " + moreThan30Seconds;
    }
}
