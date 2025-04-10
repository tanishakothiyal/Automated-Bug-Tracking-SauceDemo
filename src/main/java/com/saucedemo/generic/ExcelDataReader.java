package com.saucedemo.generic;

import java.io.InputStream;
import org.apache.poi.ss.usermodel.*;

public class ExcelDataReader {

    private static final String SHEET_NAME ="Sheet1";

    private static String getCellValue(int row, int col) {
        String data = "";

        try (InputStream fis = ExcelDataReader.class
                .getClassLoader()
                .getResourceAsStream("resources/SauceDemoTestdata.xlsx")) {

            if (fis == null) {
                throw new RuntimeException("Excel file not found in classpath at: resources/SauceDemoTestdata.xlsx");
            }

            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheet(SHEET_NAME);

            if (sheet == null) throw new RuntimeException("Sheet not found: " + SHEET_NAME);
            Row dataRow = sheet.getRow(row);
            if (dataRow == null) throw new RuntimeException("Row not found: " + row);
            Cell cell = dataRow.getCell(col);
            if (cell == null) throw new RuntimeException("Cell not found at (" + row + ", " + col + ")");

            data = cell.toString();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error reading Excel file: " + e.getMessage(), e);
        }

        return data;
    }

    public static String getUsername() {
        return getCellValue(1, 1); // B2
    }

    public static String getPassword() {
        return getCellValue(2, 1); // B3
    }

    public static String getURL() {
        return getCellValue(3, 1); // B4
    }
}