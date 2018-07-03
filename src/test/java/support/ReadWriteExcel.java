package support;

import config.Constants;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Desc: This function is used to handle Read and Write Excel file.
 * Created by: DoaiTran on: 27-Oct-16.
 * Updated by: on:
 * http://toolsqa.wpengine.com/selenium-webdriver/keyword-driven-framework/test-suite-execution-engine/
 */
public class ReadWriteExcel {
    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;
    private static XSSFRow Row;
    //////////////////////////////////////////////////////////////////////////////////////
    // Des: This method is to set the File path and to open the Excel file
    // Created by DoaiTran. 27-Oct-2016
    // Update 04-Nov-2016. Ref: Try ... Catch ...
    //////////////////////////////////////////////////////////////////////////////////////
    public static void  setExcelFile(String Path) throws Exception{
        try {
            FileInputStream ExcelFile = new FileInputStream(Path);
            ExcelWBook = new XSSFWorkbook(ExcelFile);
        }catch (Exception e){
            Log.error("Class ReadWrite | Method setExcelFile | Exception desc: "+ e.getMessage());
            ExecuteTestcase.bResult = false;
        }
    }
    public static void  closeExcelfile(String Path)throws Exception{
        //ExcelWBook.close();
        FileInputStream ExcelFile = new FileInputStream(Path);
        ExcelFile.close();
    }
    //////////////////////////////////////////////////////////////////////////////////////
    // Des: This method is to set to get the number of row on a sheet
    // Created by DoaiTran. 27-Oct-2016
    // Update 04-Nov-2016. Ref: Try ... Catch ...
    //////////////////////////////////////////////////////////////////////////////////////
    public static int  getNumberofRow(String SheetName) throws  Exception{
        int number=0;
        try{
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
            number = ExcelWSheet.getLastRowNum()+1;
        }catch (Exception e) {
            Log.error("Class ReadWriteExcel| Method getNumberofRow | Exception desc: "+ e.getMessage());
            ExecuteTestcase.bResult = false;
        }
        return number;
    }
    //////////////////////////////////////////////////////////////////////////////////////
    // Des: This method is to set to get test data
    // Created by DoaiTran. 27-Oct-2016
    // Update: 01-Nov-2016 Source: http://izip.us/pQL
    // Update: 04-Nov-2016. Ref: Try ... Catch ...
    //////////////////////////////////////////////////////////////////////////////////////
    public static String getCellData(int RowNum, int ColNum, String SheetName) throws  Exception{
        try {
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
            String CellData = Cell.getStringCellValue();
            return CellData;
        }
        catch (Exception e){
            Log.error("Class ReadWriteExcel| Method getCellData | Exception desc: "+ e.getMessage());
            ExecuteTestcase.bResult = false;
            return "";
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////
    // Des: This method is to read the test data from the Excel cell
    // Created by DoaiTran. 01-Nov-2016
    // Update:
    // Source: http://izip.us/pQL
    //////////////////////////////////////////////////////////////////////////////////////
    public static int getRowContains(String sTestCaseName, int colNum,String SheetName) throws Exception{
        int iRowNum=0;
        try {
            //ExcelWSheet = ExcelWBook.getSheet(SheetName);
            int rowCount = ReadWriteExcel.getNumberofRow(SheetName);
            for (; iRowNum < rowCount; iRowNum++) {
                if (ReadWriteExcel.getCellData(iRowNum, colNum, SheetName).equalsIgnoreCase(sTestCaseName)) {
                    break;
                }
            }
        }catch (Exception e){
            Log.error("Class ReadWriteExcel| Method getRowContains | Exception desc: "+ e.getMessage());
        }
        return iRowNum;
    }
    //////////////////////////////////////////////////////////////////////////////////////
    //This method is to get the count of the test steps of test case
    //This method takes three arguments (Sheet name, Test Case Id & Test case row number
    //Created by DoaiTran. 01-Nov-2016 Source: http://izip.us/pQL
    //Updated: 04-Nov-2016
    //Ref: Try Catch
    //////////////////////////////////////////////////////////////////////////////////////
    public static int getTestStepsCount(String SheetName, String sTestCaseID, int iTestCaseStart) throws Exception{
        try {
            for (int i = iTestCaseStart; i <= ReadWriteExcel.getNumberofRow(SheetName); i++) {
                if (!sTestCaseID.equals(ReadWriteExcel.getCellData(i, Constants.Col_TestCaseID, SheetName))) {
                    int number = i;
                    return number;
                }
            }
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
            int number = ExcelWSheet.getLastRowNum() + 1;
            return number;
        }catch (Exception e){
            Log.error("Class ReadWriteExcel| Method getRowContains | Exception desc: "+ e.getMessage());
            ExecuteTestcase.bResult = false;
            return 0;
        }
    }
    @SuppressWarnings("static-access")
//This method is use to write value in the excel sheet
//This method accepts four arguments (Result, Row Number, Column Number & Sheet Name)
    public static void setCellData(String Result,  int RowNum, int ColNum, String SheetName, String ExcelSave) throws Exception    {
        try{
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
            Row  = ExcelWSheet.getRow(RowNum);
            //Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
            Cell = Row.getCell(ColNum);
            if (Cell == null) {
                Cell = Row.createCell(ColNum);
                Cell.setCellValue(Result);
            } else {
                Cell.setCellValue(Result);
            }
            //Cell.setCellValue(Result);
            // Constant variables Test Data path and Test Data file name
            FileOutputStream fileOut = new FileOutputStream(ExcelSave);
            ExcelWBook.write(fileOut);
            //fileOut.flush();
            fileOut.close();
            ExcelWBook = new XSSFWorkbook(new FileInputStream(ExcelSave));
        }catch(Exception e){
            ExecuteTestcase.bResult = false;
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////
    // Des: This method is to set the File path and to open the Excel file
    // Created by DoaiTran. 27-Oct-2016
    // Update 04-Nov-2016. Ref: Try ... Catch ...
    //////////////////////////////////////////////////////////////////////////////////////
    public static void  saveExcelFile(String Path) throws Exception{
        try {
            FileOutputStream fout = new FileOutputStream(Path);
            //FileInputStream ExcelFile = new FileInputStream(Path);
            //ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelWBook.write(fout);
        }catch (Exception e){
            Log.error("Class ReadWrite | Method saveExcelFile | Exception desc: "+ e.getMessage());
            ExecuteTestcase.bResult = false;
        }
    }
}
