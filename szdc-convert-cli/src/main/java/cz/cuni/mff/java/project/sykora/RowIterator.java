package cz.cuni.mff.java.project.sykora;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.Iterator;

/**
 Implementation of iterator accessing rows of Excel spreadsheet.
 @author Jakub Sykora
 @version 1.0.0
 @since 1.4.2023
 */
public class RowIterator implements Iterator<Row> {

    private final Iterator<Row> rowIterator;
    private Row currentRow;
    private int currentRowIndex;

    private RowIterator(Sheet sheet) {

        rowIterator = sheet.rowIterator();
        currentRowIndex = -1;
    }

    /**
     * Create iterator over sheet in workbook.
     * @return Iterator instance.
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

    public int getCurrentRowIndex() {
        return currentRowIndex;
    }
}
