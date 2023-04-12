package cz.cuni.mff.java.project.sykora;

import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.IOException;

/**
 Converts train timetables data in Excel sheet to its JSON representation.
 @author Jakub Sykora
 @version 1.0.0
 @since 1.4.2023
 */
public class SzdcTrainParser {

    private final TrainWriter trainWriter;

    private final ParserLogger parserLogger;

    /**
     * Creates train parser with provided output implementations.
     */
    public SzdcTrainParser(TrainWriter trainWriter, ParserLogger parserLogger) {
        this.trainWriter = trainWriter;
        this.parserLogger = parserLogger;
    }

    /**
     * Parse trains from given excel file.
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
