package cz.cuni.mff.java.project.sykora;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.Iterator;

/**
    The RowIterator class provides an iterator for accessing rows in an Excel spreadsheet.
    The class implements the Iterator<Row> interface for iterating over rows in a given sheet.
    @author Jakub Sykora
    @version 1.0.0
    @since 1.4.2023
 */
public class RowIterator implements Iterator<Row> {

    private final Iterator<Row> rowIterator;
    private Row currentRow;
    private int currentRowIndex;

    /**
         Private constructor for the RowIterator class.
         Initializes the iterator with the given sheet.
         @param sheet The Sheet object to be iterated over.
     */
    private RowIterator(Sheet sheet) {
        rowIterator = sheet.rowIterator();
        currentRowIndex = -1;
    }

    /**
         Creates a new RowIterator instance for a specified sheet in the provided workbook.
         @param workbook The Workbook containing the sheet to be iterated over.
         @param sheetName The name of the sheet in the workbook.
         @return A RowIterator instance for the specified sheet, or null if the sheet is not found.
     */
    public static RowIterator create(Workbook workbook, String sheetName) {
        int sheetIndex = workbook.getSheetIndex(sheetName);
        return (sheetIndex == -1) ? null : new RowIterator(workbook.getSheet(sheetName));
    }

    @Override
    public boolean hasNext() {
        return rowIterator.hasNext();
    }

    @Override
    public Row next() {
        ++currentRowIndex;
        currentRow = rowIterator.next();
        return currentRow;
    }
}
