package cz.cuni.mff.java.project.sykora;

import lombok.Getter;
import java.util.Optional;

/**
 * Enum representing various train types.
 */
@Getter
public enum TrainType {

    OS("Os"),
    Ex("Ex"),
    R("R"),
    Sp("Sp"),
    Nex("Nex"),
    Pn("Pn"),
    Mn("Mn"),
    Vl("Vl"),
    Sl("Sl");

    private final String description;

    /**
     * Constructor for TrainType enum.
     *
     * @param description A string representing the train type description.
     */
    TrainType(String description) {
        this.description = description;
    }

    /**
     * Attempts to parse the TrainType enum given a textual description.
     *
     * @param string A string representing the train type description.
     * @return An Optional<TrainType> object containing the train type if found, or an empty Optional if not found.
     */
    public static Optional<TrainType> tryGetTrainType(String string) {

        for (var value : TrainType.values()) {
            if (string.startsWith(value.description + ' ')) {
                return Optional.of(value);
            }
        }

        if (string.contains("Vleč ")) {
            return Optional.of(Vl);
        }

        return Optional.empty();
    }

    @Override
    public String toString() {
        return description;
    }
}
