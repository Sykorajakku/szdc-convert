package cz.cuni.mff.java.project.sykora;

import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.IOException;

/**
    The SzdcTrainParser class is responsible for converting train timetables from an Excel sheet into a JSON
    representation. The class reads the Excel file and processes the train schedule data using the provided
    TrainWriter and ParserLogger implementations.
    @author Jakub Sykora
    @version 1.0.0
    @since 1.4.2023
 */
public class SzdcTrainParser {

    private final TrainWriter trainWriter;

    private final ParserLogger parserLogger;

    /**
         Constructs a SzdcTrainParser instance with the provided TrainWriter and ParserLogger implementations.
         @param trainWriter The TrainWriter to be used for converting train data to JSON.
         @param parserLogger The ParserLogger to be used for logging parsing results.
     */
    public SzdcTrainParser(TrainWriter trainWriter, ParserLogger parserLogger) {
        this.trainWriter = trainWriter;
        this.parserLogger = parserLogger;
    }

    /**
         Parses train schedules from the given Excel file and writes the parsed data to the TrainWriter.
         @param trainRailwaySchedulesFile The Excel file containing train schedules.
         @throws ImportException If an error occurs while processing the Excel file.
     */
    public void parseTrains(File trainRailwaySchedulesFile) throws ImportException {

        try(Workbook workbook = StreamingReader.builder().open(trainRailwaySchedulesFile)) {

            var sheet = workbook.getSheet(ImportConstants.ScheduleSheetName);
            var rowIterator = RowIterator.create(workbook, ImportConstants.ScheduleSheetName);

            if (rowIterator == null) {
                throw new ImportException("Sheet " + ImportConstants.ScheduleSheetName + " was not found.");
            }

            var mergedRegions = new MergedRegions(trainRailwaySchedulesFile);
            var trainProcessor = new TrainProcessor(mergedRegions.getMergedRegions(ImportConstants.ScheduleSheetName), parserLogger);

            for (var row : sheet) {
                trainProcessor.processRow(row);
            }

            for (var train : trainProcessor.getParsedTrains()) {
                trainWriter.writeTrain(train);
            }
        }
        catch (IOException ex) {
            throw new ImportException("Unable to process file with train schedule: ", ex);
        }
    }
}
