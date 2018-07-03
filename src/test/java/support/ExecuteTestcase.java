package support;

import config.Constants;

import java.lang.reflect.Method;


/**
 * Created by josdo on 19-Nov-16.
 */
public class ExecuteTestcase {
    public static boolean bResult;
    public static String sRunMode;
    public static String SheetName;
    public static int iTestStep;
    public static int iTestLastStep;
    public static String sTestCaseID;
    public static String sActionKeyword;
    public static String sPageObject;
    public static String TestStepName;
    public static String sData;
    public static boolean sTestCaseResult;
    public static Method method[];
    public static Keyword actionKeywords;
    public static String testsuiteName;
    public ExecuteTestcase() throws NoSuchMethodException, SecurityException{
        actionKeywords = new Keyword();
        method = actionKeywords.getClass().getMethods();
    }
    public static void execute_TestCase(String TestSuite, String TestReport, String TestsuiteName) throws Exception {
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //  Desc: This function will be used to read TestSuite ~ SheetTestCases. And Read each specify TestSteps on each Test Casse
        //  Created by DoaiTran. 31-Oct-2016
        //  Updated: DoaiTran. 13-Nov-2016
        //  => Read TestCases that will be executed (Runmode: Yes).
        //  => Read and execute TestCase (Sheets) that named as TestSuite sheet.
        //  => TestCases will be Passed only when all steps be executed and passed.
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        testsuiteName = TestsuiteName;
        new ExecuteTestcase();
        ReadWriteExcel.setExcelFile(TestSuite);      //This is to start the Log4j logging in the test case
        int iTotalTestCases = ReadWriteExcel.getNumberofRow(Constants.Sheet_TestCases);
        for (int iTestcase = 1; iTestcase <= iTotalTestCases; iTestcase++) {
            bResult = true;
            sTestCaseID = ReadWriteExcel.getCellData(iTestcase, Constants.Col_TestCaseID, Constants.Sheet_TestCases);
            sRunMode = ReadWriteExcel.getCellData(iTestcase, Constants.Col_RunMode, Constants.Sheet_TestCases);
            System.out.println(sTestCaseID);
            if (sRunMode.equals("Yes")) {
                System.out.println(sTestCaseID+sRunMode);
                SheetName = sTestCaseID;
                iTestStep = ReadWriteExcel.getRowContains(sTestCaseID, Constants.Col_TestCaseID, SheetName);
                iTestLastStep = ReadWriteExcel.getTestStepsCount(SheetName, sTestCaseID, iTestStep);
                Log.startTestCase(sTestCaseID);
                bResult = true;
                for (; iTestStep <= iTestLastStep; iTestStep++) {
                    sActionKeyword = ReadWriteExcel.getCellData(iTestStep, Constants.Col_ActionKeyword, SheetName);
                    sPageObject = ReadWriteExcel.getCellData(iTestStep, Constants.Col_PageObject, SheetName);
                    TestStepName = ReadWriteExcel.getCellData(iTestStep, Constants.Col_TestStepID, SheetName);
                    System.out.println(TestStepName);
                    sData = ReadWriteExcel.getCellData(iTestStep, Constants.Col_DataSet, SheetName);
                    execute_Actions(TestReport);
                    if (sTestCaseResult == false) {
                        ReadWriteExcel.setCellData(Constants.KEYWORD_FAIL, iTestcase, Constants.Col_Result, Constants.Sheet_TestCases,TestReport );
                        Log.endTestCase(sTestCaseID);
                        System.out.println("TestCase: Failed");
                        break;
                    }
                }
                if (sTestCaseResult == true) {
                    ReadWriteExcel.setCellData(Constants.KEYWORD_PASS, iTestcase, Constants.Col_Result, Constants.Sheet_TestCases,TestReport);
                    Log.endTestCase(sTestCaseID);
                    System.out.println("TestCase: Passed");
                }
            }
        }
    }
    public static void execute_Actions(String TestReport) throws Exception{
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Method to control execution of all actions.
        // And will set value "PASSED" / "FAILED" for each TestSteps.
        // Improvement: More data. Source: https://goo.gl/TB0wmO (07-Nov-16)
        // Updated on 08-Nov-16 => testing.reports
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        for(int i=0;i<method.length;i++){
            if(method[i].getName().equals(sActionKeyword)){
                method[i].invoke(actionKeywords,sPageObject,sData);
                //This code block will execute after every test step
                if(bResult == true){
                    //If the executed test step value is true, Pass the test step in Excel sheet
                    ReadWriteExcel.setCellData(Constants.KEYWORD_PASS, iTestStep, Constants.Col_TestStepResult, SheetName, TestReport);
                    sTestCaseResult = true;
                    break;
                }else{
                    //If the executed test step value is false, Fail the test step in Excel sheet
                    ReadWriteExcel.setCellData(Constants.KEYWORD_FAIL, iTestStep, Constants.Col_TestStepResult, SheetName, TestReport);
                    //In case of false, the test execution will not reach to last step of closing browser
                    //So it make sense to close the browser before moving on to next test case
                    Keyword.getscreenshot();
                    Keyword.closeBrowser("","");
                    sTestCaseResult = false;
                    break;
                }
            }
        }
    }
}
