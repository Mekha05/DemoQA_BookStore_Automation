
package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {

    private static Workbook workbook;
    private static Sheet sheet;
    private static String filePath;

    // Load Excel file and select the required sheet
    public static void loadExcel(String path, String sheetName) {
        try {
            filePath = path;

            try (FileInputStream fis = new FileInputStream(filePath)) {
                workbook = WorkbookFactory.create(fis);
            }

            sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new IllegalArgumentException(
                    "Sheet not found: " + sheetName
                );
            }

        } catch (IOException e) {
            throw new RuntimeException(
                "Unable to load Excel file.", e
            );
        }
    }

    // Get the number of columns containing header text
    public static int getColCount() {
        Row headerRow = sheet.getRow(0);

        if (headerRow == null) {
            return 0;
        }

        int lastColumn = headerRow.getLastCellNum();

        for (int i = lastColumn - 1; i >= 0; i--) {
            Cell cell = headerRow.getCell(i);

            if (cell != null &&
                !new DataFormatter()
                    .formatCellValue(cell).trim().isEmpty()) {
                return i + 1;
            }
        }

        return 0;
    }

    // Read a single cell value
    public static String getCellData(int rowNum, int colNum) {
        Row row = sheet.getRow(rowNum);

        if (row == null) {
            return "";
        }

        Cell cell = row.getCell(colNum);

        if (cell == null) {
            return "";
        }

        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell);
    }

    // Get the last row that contains data
    public static int getRowCount() {
        int lastRow = sheet.getLastRowNum();
        int colCount = getColCount();

        while (lastRow > 0) {
            Row row = sheet.getRow(lastRow);

            if (row != null) {
                boolean hasData = false;

                for (int i = 0; i < colCount; i++) {
                    String value = getCellData(lastRow, i);

                    if (!value.trim().isEmpty()) {
                        hasData = true;
                        break;
                    }
                }

                if (hasData) {
                    return lastRow;
                }
            }

            lastRow--;
        }

        return 0;
    }

    // Write or update a cell and save the workbook
    public static void setCellData(
            int rowNum, int colNum, String value) {

        Row row = sheet.getRow(rowNum);

        if (row == null) {
            row = sheet.createRow(rowNum);
        }

        Cell cell = row.getCell(colNum);

        if (cell == null) {
            cell = row.createCell(colNum);
        }

        cell.setCellValue(value);

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            workbook.write(fos);
        } catch (IOException e) {
            throw new RuntimeException(
                "Unable to save Excel file.", e
            );
        }
    }

    // Read non-empty data rows, skipping the header
    public static Object[][] getData(
            String path, String sheetName) {

        loadExcel(path, sheetName);

        int colCount = getColCount();
        List<Object[]> rows = new ArrayList<>();

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);

            if (row == null) {
                continue;
            }

            Object[] rowData = new Object[colCount];
            boolean hasData = false;

            for (int j = 0; j < colCount; j++) {
                String value = getCellData(i, j);
                rowData[j] = value;

                if (!value.trim().isEmpty()) {
                    hasData = true;
                }
            }

            if (hasData) {
                rows.add(rowData);
            }
        }

        System.out.println(
            "Sheet: " + sheetName
            + " | Column count: " + colCount
            + " | Data rows: " + rows.size()
        );

        return rows.toArray(new Object[0][]);
    }
}
