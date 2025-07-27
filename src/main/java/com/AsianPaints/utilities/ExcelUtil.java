package com.AsianPaints.utilities;

import org.apache.poi.xssf.usermodel.*;
import java.io.*;

public class ExcelUtil {
    public static String[] readColumn(String filePath, int sheetIndex, int colIndex) throws IOException {
        FileInputStream fs = new FileInputStream(new File(filePath));
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
        XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
        int rows = sheet.getPhysicalNumberOfRows();
        String[] data = new String[rows];
        for (int i = 0; i < rows; i++) {
            data[i] = Integer.toString((int) (sheet.getRow(i).getCell(colIndex).getNumericCellValue()));
        }
        workbook.close();
        return data;
    }
}
