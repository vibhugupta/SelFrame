package helper;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Iterator;

/**
 * Created by vibhu on 3/8/2018.
 */
public class ExcelReader {
    static String workBookName;
    static File filePath;

    public ExcelReader() {
        File fileDir = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\testSheets");
        File[] files = fileDir.listFiles();
        workBookName = files[0].getName();
        filePath = new File(fileDir, workBookName);
    }

    private int getRowNumber(String columnName, Sheet sheetToRead) {
        for (int currentRow = sheetToRead.getFirstRowNum() + 1; currentRow <= sheetToRead.getLastRowNum(); currentRow++) {
            Row row = sheetToRead.getRow(currentRow);
            for (int currentColumn = row.getFirstCellNum(); currentColumn < row.getLastCellNum(); currentColumn++) {
                if (row.getCell(currentColumn).getStringCellValue().equalsIgnoreCase(columnName)) {
                    return currentRow;
                }
            }
        }
        return 0;
    }

    private Workbook getWorkBook(FileInputStream inputStream) {
        String sheetExtension = workBookName.replaceAll("^\\w*.", "");
        Workbook testCaseWorkBook = null;
        try {
            if (sheetExtension.equals("xlsx")) {
                testCaseWorkBook = new XSSFWorkbook(inputStream);
            } else if (sheetExtension.equals("xls")) {
                testCaseWorkBook = new HSSFWorkbook(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return testCaseWorkBook;
    }

    public boolean isTestPresent(String testName) {
        Sheet configurationSheet = null;
        Sheet sheetToRead = null;
        String testCategory = null;
        int testCategoryColumn = 3;
        String portalName = null;

        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            Workbook testCaseWorkBook = getWorkBook(inputStream);
            configurationSheet = testCaseWorkBook.getSheet("Sheet1");
            //get data from configuration file
            for (int currentRow = configurationSheet.getFirstRowNum() + 1; currentRow <= configurationSheet.getLastRowNum(); currentRow++) {
                portalName = configurationSheet.getRow(currentRow).getCell(1).getStringCellValue();
                testCategory = configurationSheet.getRow(currentRow).getCell(2).getStringCellValue();
                sheetToRead = testCaseWorkBook.getSheet(portalName);
                for (int currentPortalRow = sheetToRead.getFirstRowNum() + 1; currentPortalRow <= sheetToRead.getLastRowNum(); currentPortalRow++) {
                    final Row row = sheetToRead.getRow(currentPortalRow);
                    if (row.getCell(testCategoryColumn).getStringCellValue().contains(testCategory)) {
                        if (row.getCell(1).getStringCellValue().equalsIgnoreCase(testName)) {
                            return true;
                        }
                    }
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
        return false;
    }

    public static String readExcelData(String testDataFile, String sheetName, String ParmName) throws Exception {
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(testDataFile);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet worksheet = workbook.getSheet(sheetName);
            Iterator<Row> rowIterator = worksheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getCell(0).getStringCellValue().equalsIgnoreCase(ParmName)) {
                    if (row.getCell(1).getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        return String.valueOf(row.getCell(1).getNumericCellValue());
                    } else {
                        return row.getCell(1).getStringCellValue();
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception("Failed to retrieve value from test data xlsx \n" + e.getMessage());
        } finally {
            fileInputStream.close();
        }
        return null;
    }

    public static String writeExcelData(String testDataFile, String sheetName,int rowNumber, String ParmName,int columnNumber) throws Exception {
        FileInputStream fileInputStream = null;
        try {
            FileInputStream file = new FileInputStream(new File(testDataFile));

            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet(sheetName);
            Cell cell = null;

          //  cell = sheet.getRow(1).createCell(4);
            cell = sheet.getRow(rowNumber).createCell(columnNumber);

            cell.setCellValue(ParmName);
            file.close();

            FileOutputStream outFile = new FileOutputStream(new File(testDataFile));
            workbook.write(outFile);
            outFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static int getRowNumberForSpecificTest(String sheetPath,String SheetName, String stringToCompare)throws Exception{
        FileInputStream fileInputStream = null;
        int rowNum=0;
        try{
            fileInputStream = new FileInputStream(sheetPath);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet worksheet = workbook.getSheet(SheetName);

            Iterator<Row> rowIterator = worksheet.iterator();
            int rowNumber=0;
            while (rowIterator.hasNext()) {
                rowNumber++;
                Row row = rowIterator.next();
                if (row.getCell(0).getStringCellValue().equalsIgnoreCase(stringToCompare)) {
                    rowNum=rowNumber-1;
                    break;
                }
            }

        }catch (Exception e){
            throw new Exception("Failed to retrieve value from test data xlsx");
        }
        return rowNum;
    }
    public static int getColumnNumberForSpecificTest(String sheetPath,String SheetName)throws Exception{
        FileInputStream fileInputStream = null;
        int rowNum=0;
        int noOfColumns=0;

        try{
            fileInputStream = new FileInputStream(sheetPath);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet worksheet = workbook.getSheet(SheetName);

            noOfColumns = worksheet.getRow(0).getLastCellNum()-1;

        }catch (Exception e){
            throw new Exception("Failed to retrieve value from test data xlsx");
        }
        return noOfColumns;
    }
}
