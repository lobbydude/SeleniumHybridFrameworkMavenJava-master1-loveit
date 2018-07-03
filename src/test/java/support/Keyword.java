package support;

import config.Constants;
import executionEngine.RunTestNG;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static support.ExecuteTestcase.*;


/**
 * Created by DoaiTran on 26-Oct-16.
 * Create all definitions for actions on Selenium
 * Lasted Upgrade: 12-Dec-2016. For Maven and TestNG:  RunTestNG
 */
public class Keyword {
    public static WebDriver DRIVER;
    public static void openBrowser(String object, String data){
        /**************************************************************************************************
        /** Des: This function is used to define the browser
        /** Created by DoaiTran. Date: 26-Oct-2016
        /** Modification History:
        /**        Modify by: DoaiTran           Date: 04-Nov-2016       Note: https://goo.gl/qRMJtv
        /**************************************************************************************************/
        try {
            Log.info("*Try to Open Browser*");
            if (data.equals("FF")) {
                DRIVER = new FirefoxDriver();
                DRIVER.manage().window().maximize();

            } else if (data.equals("Chrome")) {
                DRIVER = new ChromeDriver();
                DRIVER.manage().window().maximize();

            } else if (data.equals("IE")) {
                DRIVER = new InternetExplorerDriver();
                DRIVER.manage().window().maximize();

            } else if (data.equals("")) {
                DRIVER = new FirefoxDriver();
                DRIVER.manage().window().maximize();

            }
            Log.info("Opened Browser: "+data);
        }catch (Exception e){
            Log.info("-------- Unable to open Browser --------" + e.getMessage());
            ExecuteTestcase.bResult = false;
        }
    }
    public static void navigateToURL (String object, String data){
        /***************************************************************************************************
        /** Des: This function is used to navigate to BASE_URL. BASE_URL is define on CONSTANTS.JAVA
        /** Created by DoaiTran. 26-Oct-2016
        /** Modification History:
        /**        Modify by: DoaiTran           Date: 27-Oct-2016       Note:
        /***************************************************************************************************/
        try {
            Log.info("*Try to navigate to URL*");
            DRIVER.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            DRIVER.navigate().to(Constants.URL);
            Log.info("Navigated to BASE_URL");
        }catch (Exception e){
            Log.info("-------- Unable to navigate to BASE_URL -------- "+ e.getMessage());
            ExecuteTestcase.bResult = false;
        }
    }

    public static void navigateTo (String object, String data){
        /****************************************************************************************************
        /** Des: This function is used to navigate to BASE_URL
        /** Created by DoaiTran. 26-Oct-2016
        /** Modification History:
        /**        Modify by: DoaiTran           Date: 27-Oct-2016       Note:
        /****************************************************************************************************/
        try {
            Log.info("*Try to navigate to specify URL*");
            DRIVER.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            DRIVER.navigate().to(data);
            Log.info("Navigated to specify URL: "+data);
        }catch (Exception e){
            Log.info("-------- Unable to navigate to URL --------"+ e.getMessage());
            ExecuteTestcase.bResult = false;
        }
    }
    public static void closeBrowser(String object,String data){
        /*****************************************************************************************************
        /** Des: This function is used to close browser
        /** Created by DoaiTran. On: 26-Oct-2016
        /** Modification History:
        /**        Modify by: DoaiTran           Date: 27-Oct-2016       Note:
        /*****************************************************************************************************/
        try {
            Log.info("*Try to close the browser*");
            DRIVER.quit();
            Log.info("*Closed the browser*");
        }catch (Exception e){
            Log.info("-------- Unable to close Browser --------"+ e.getMessage());
            ExecuteTestcase.bResult = false;
        }
    }
    public static void clickElement(String object, String data){
        /*******************************************************************************************************/
        /** Des: This function is used to click on Elements
        /** Created by DoaiTran. 26-Oct-2016
        /** Modification History:
        /**        Modify by: DoaiTran            Date: 12-Dec-2016       Note: For Maven and TestNG:  RunTestNG
        /*******************************************************************************************************/
        try {
            Log.info("*Try to Click on WebElement*");
            DRIVER.findElement(By.xpath(RunTestNG.OR.getProperty(object))).click();
            Log.info("Clicked on WebElement "+ object);
        }catch (Exception e){
            Log.info("-------- Unable to click Element: "+object+" --------"+e.getMessage());
            ExecuteTestcase.bResult = false;
        }
    }
    public static void clickElementByLinkText(String object, String data){
         /****************************************************************************************************
        /** Des: This function is used to clickElementByLinkText
        /** Created by DoaiTran. 26-Oct-2016
        /** Modification History:
        /**        Modify by: DoaiTran            Date: 12-Dec-2016       Note: For Maven and TestNG:  RunTestNG
        /****************************************************************************************************/
        try {
            Log.info("*Try to Click on WebElement ByLinkText*");
            DRIVER.findElement(By.linkText(RunTestNG.OR.getProperty(object))).click();
            Log.info("Clicked on WebElement ByLinkText "+ object);
        }catch (Exception e){
            Log.info("-------- Unable to click Element By LinkText: "+object+" --------"+e.getMessage());
            ExecuteTestcase.bResult = false;
        }
    }
    public static void waitAndPause(String object,String data) throws InterruptedException {
        /****************************************************************************************************
        /** Des: This function is used to pause all actions with time (MilSecond).
        /** Created by DoaiTran. On: 26-Oct-2016
        /** Modification History:
        /**        Modify by:            Date:       Note:
        /****************************************************************************************************/
        try{
            Log.info("*Try to wait for 5 seconds*");
            //int ndata = Integer.parseInt(data);
            Thread.sleep(5000);
            Log.info("Waited for 5 seconds");
        }
        catch (Exception e){
            Log.info("-------- Unable to wait -------- "+ e.getMessage());
            ExecuteTestcase.bResult = false;
        }
    }
    public static void inputValue(String object, String data){
        /****************************************************************************************************
        /** Des: This function is used to clear and input data to text box.
        /** Created by DoaiTran. On: 07-Nov-2016
        /** Modification History:
        /**        Modify by: DoaiTran            Date: 12-Dec-2016       Note: For Maven and TestNG:  RunTestNG
        /****************************************************************************************************/
        try{
            Log.info("*Try to input value*");
            WebDriverWait wait = new WebDriverWait(DRIVER, 15);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(RunTestNG.OR.getProperty(object)))).clear();
            DRIVER.findElement(By.xpath(RunTestNG.OR.getProperty(object))).sendKeys(data);
            Log.info("Inputed value: "+ data + " to element " + object);
        }catch (Exception e){
            Log.info("-------- Unable to input value: "+ data + " to element " + object+" --------"+ e.getMessage());
            ExecuteTestcase.bResult = false;
        }
    }
    public static void clearTextBox(String object, String data){
        /****************************************************************************************************
        /** Des: This function is used to clear data on text box
        /** Created by DoaiTran. On: 07-Nov-2016
        /** Modification History:
        /**        Modify by: DoaiTran            Date: 12-Dec-2016       Note: For Maven and TestNG:  RunTestNG
        /****************************************************************************************************/
        try{
            Log.info("*Try clear data on TextBox*");
            WebDriverWait wait = new WebDriverWait(DRIVER, 15);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(RunTestNG.OR.getProperty(object)))).clear();
            Log.info("Cleared data on TextBox: "+object);
        }catch (Exception e){
            Log.info("-------- Unable to clear TextBox: "+object+" --------"+e.getMessage());
            ExecuteTestcase.bResult = false;
        }
    }
    public static void submit(String object, String data){
        /****************************************************************************************************
        /** Des: This function is used to submit button
        /** Created by DoaiTran. On: 13-Nov-2016
        /** Modification History:
        /**        Modify by: DoaiTran            Date: 12-Dec-2016       Note: For Maven and TestNG:  RunTestNG
        /****************************************************************************************************/
        try{
            Log.info("*Try to submit the button*");
            WebDriverWait wait = new WebDriverWait(DRIVER, 15);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(RunTestNG.OR.getProperty(object)))).submit();
            Log.info("Submitted the button: "+object);
        }catch (Exception e){
            Log.info("-------- Unable to submit button: "+object+" --------"+e.getMessage());
            ExecuteTestcase.bResult = false;
        }
    }
    public static void navigateToBack(String object, String data){
        /****************************************************************************************************
        /** Des: This function is used to navigate to Back page
        /** Created by DoaiTran. On: 13-Nov-2016
        /** Modification History:
        /**        Modify by:            Date:       Note:
        /****************************************************************************************************/
        try{
            Log.info("*Try to navigate to back page*");
            DRIVER.navigate().back();
            Thread.sleep(5000);
            Log.info("Navigated to back page");
        }catch (Exception e){
            Log.info("-------- Unable to Navigate to Back --------"+e.getMessage());
            ExecuteTestcase.bResult = false;
        }
    }
    public static void  navigatetoForward(String object, String data){
        /****************************************************************************************************
        ** Des: This function is used to navigate to Forward
        /** Created by DoaiTran. On: 13-Nov-2016
        /** Modification History:
        /**        Modify by:            Date:       Note:
        /****************************************************************************************************/
        try{
            Log.info("*Try to Navigate to Forward page*");
            DRIVER.navigate().forward();
            Thread.sleep(5000);
            Log.info("Navigated to Forward page");
        }catch (Exception e){
            Log.info("-------- Unable to Navigate to Forward --------"+e.getMessage());
            ExecuteTestcase.bResult = false;
        }
    }
    public static void refreshPage(String object, String data){
        /****************************************************************************************************
        /** Des: This function is used to refresh page.
        /** Created by DoaiTran. On: 13-Nov-2016
        /** Modification History:
        /**        Modify by:            Date:       Note:
        /****************************************************************************************************/
        try{
            Log.info("*Try to Refresh page*");
            DRIVER.navigate().refresh();
            Thread.sleep(5000);
            Log.info("Refreshed page");
        }catch (Exception e){
            Log.info("-------- Unable to refresh page --------"+e.getMessage());
            ExecuteTestcase.bResult = false;
        }
    }
    public static void  moveToElement(String object, String data){
        /****************************************************************************************************
        /** Des: This function is used to movetoElement
        /** Created by DoaiTran. On: 13-Nov-2016
        /** Modification History:
        /**        Modify by: DoaiTran            Date: 12-Dec-2016       Note: For Maven and TestNG:  RunTestNG
        /****************************************************************************************************/
        try{
            Log.info("*Try to hover to move To Element*");
            Actions actions = new Actions(DRIVER);
            WebElement Element = DRIVER.findElement(By.xpath(RunTestNG.OR.getProperty(object)));
            actions.moveToElement(Element).perform();
            Log.info("Hovered to move To Element: "+object);
        }catch (Exception e){
            Log.info("-------- Unble to move to Element: "+object+ " --------"+e.getMessage());
            ExecuteTestcase.bResult = false;
        }
    }
    public static void doubleClick(String object, String data){
        /****************************************************************************************************
        /** Des: This function is used to doubleClick
        /** Created by DoaiTran. On: 20-Nov-2016
        /** Modification History:
        /**        Modify by: DoaiTran            Date: 12-Dec-2016       Note: For Maven and TestNG:  RunTestNG
        /****************************************************************************************************/
        try{
            Log.info("*Try to DoubleClick on Element*");
            Actions actions = new Actions(DRIVER);
            WebElement Element = DRIVER.findElement(By.xpath(RunTestNG.OR.getProperty(object)));
            actions.doubleClick().perform();
            Log.info("DoubleClicked on Element: "+ data);
        }catch (Exception e){
            Log.info("-------- Unable to doubleClick on Element: " + data+ " --------"+e.getMessage());
            ExecuteTestcase.bResult = false;
        }
    }
    public static void  waitForElementPresent(String object, String data){
        /****************************************************************************************************
        /** Des: This function is used to waitForElementPresent
        /** Created by DoaiTran. On: 20-Nov-2016
        /** Modification History:
        /**        Modify by: DoaiTran            Date: 12-Dec-2016       Note: For Maven and TestNG:  RunTestNG
        /****************************************************************************************************/
        try{
            Log.info("*Try to Wait for element present*");
            WebDriverWait wait = new WebDriverWait(DRIVER, 15);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RunTestNG.OR.getProperty(object))));
            Log.info("Element: "+object+" is presented.");
        }catch (Exception e){
            Log.info("-------- Element: "+object+" is not presented --------"+e.getMessage());
            ExecuteTestcase.bResult = false;
        }
    }
    public static void verifyElementIsExisted(String object, String data){
        /****************************************************************************************************
        /** Des: This function is used to Verify element is existedt
        /** Created by DoaiTran. On: 20-Nov-2016
        /** Modification History:
        /**        Modify by: DoaiTran            Date: 12-Dec-2016       Note: For Maven and TestNG:  RunTestNG
        /****************************************************************************************************/
        try{
            Log.info("*Try toVerify Element is existed*");
            WebDriverWait wait = new WebDriverWait(DRIVER, 15);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(RunTestNG.OR.getProperty(object))));
            Log.info("Element: "+object+" is existed");
        }catch (Exception e) {
            Log.info("-------- Element: "+object+" is not existed --------"+e.getMessage());
            ExecuteTestcase.bResult = false;
        }
    }
    public static void verifyText(String object, String data){
        /****************************************************************************************************
        /** Des: This function is used to verify actual text and expected text.
        /** Created by DoaiTran. On: 21-Nov-2016
        /** Modification History:
        /**        Modify by: DoaiTran            Date: 12-Dec-2016       Note: For Maven and TestNG:  RunTestNG
        /****************************************************************************************************/
        try{
            Log.info("*Try to verify text between get from element and expected text*");
            String actualText = DRIVER.findElement(By.xpath(RunTestNG.OR.getProperty(object))).getText();
            if(actualText.equals(data)){
                ExecuteTestcase.bResult = true;
                Log.info("Expected text on "+object+"and actual text: "+data+" are the same.");
            }else {
                ExecuteTestcase.bResult = false;
                Log.info("Actual text on "+object+"and actual text: "+data+" are different.");
            }
        }catch(Exception e){
            Log.info("-------- Unable to verify Actual text on "+object+"and actual text: "+data+" --------"+e.getMessage());
            ExecuteTestcase.bResult = false;
        }
    }
    public static void closeAllBrowsers(String object, String data){
        /****************************************************************************************************
        /** Des: This function is used to kill all browser processes.
        /** Created by DoaiTran. On: 21-Nov-2016
        /** Modification History:
        /**        Modify by:            Date:       Note:
        /****************************************************************************************************/
        try{
            Log.info("*Try to kill all browser processes*");
            taskkill("Chrome");
            taskkill("IE");
            taskkill("FF");
            Log.info("Killed all browser processes. Close all browsers.");
        }catch (Exception e){
            Log.info("-------- Unable to close all browser. --------"+e.getMessage());
            ExecuteTestcase.bResult = false;
        }
    }
    // Support: https://technet.microsoft.com/en-us/library/bb491009.aspx
    private static void taskkill(String strProcessName){
        String strCmdLine = null;
        //Runtime rt = Runtime.getRuntime();
        if (strProcessName.equals("Chrome")) {
            strCmdLine = String.format("taskkill /im chrome.exe /f /t >nul 2>&1");
        } else if (strProcessName.equals("IE")) {
            strCmdLine = String.format("taskkill /im iexplore.exe /f /t >nul 2>&1");
        } else if (strProcessName.equals("FF")) {
            strCmdLine = String.format("taskkill /im firefox.exe /f /t >nul 2>&1");
        }
        try {
            Runtime.getRuntime().exec(strCmdLine);
        }catch (Exception e){
            e.printStackTrace();
            Log.info("-------- Not able to kill all processes. --------"+e.getMessage());
            ExecuteTestcase.bResult = false;
        }
    }
    public static void waitForAjax(String object, String data){
        /****************************************************************************************************
        /** Des: This function is used to waitForAjax.
        /** Created by DoaiTran. On: 21-Nov-2016
        /** Modification History:
        /**        Modify by:            Date:       Note:
        /****************************************************************************************************/
        try {
            Log.info("*Wait for Ajax script be executed*");
            new WebDriverWait(DRIVER, 180).until(new ExpectedCondition<Boolean>()
            {
                public Boolean apply(WebDriver driver) {
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    return (Boolean)js.executeScript("return jQuery.active == 0");
                }
            });
            Log.info("Ajax script was executed");
        }catch (Exception e){
            Log.info("-------- Error Ajax script waiting --------"+e.getMessage());
            ExecuteTestcase.bResult = false;
        }
    }
    public static void selectByVisibleText(String object, String data){
        /****************************************************************************************************
        /** Des: This function is used to select By Visible Text
        /** Created by DoaiTran. On: 21-Nov-2016
        /** Modification History:
        /**        Modify by: DoaiTran            Date: 12-Dec-2016       Note: For Maven and TestNG:  RunTestNG
        /****************************************************************************************************/
        try{
            Log.info("*Try to select by visible text*");
            Select selectAction =  new Select(DRIVER.findElement(By.xpath(RunTestNG.OR.getProperty(object))));
            selectAction.selectByVisibleText(data);
            Log.info("Selected "+object+ " by visible text: "+data);
        }catch (Exception e){
            Log.info("-------- Unable to select "+object+" by visible text: "+data+" --------"+e.getMessage());
            ExecuteTestcase.bResult = false;
        }
    }
    public static void selectByValue(String object, String data){
        /****************************************************************************************************
        /** Des: This function is used to select By Value
        /** Created by DoaiTran. On: 21-Nov-2016
        /** Modification History:
        /**        Modify by: DoaiTran            Date: 12-Dec-2016       Note: For Maven and TestNG:  RunTestNG
        /****************************************************************************************************/
        try{
            Log.info("*Try to select by value*");
            Select selectAction =  new Select(DRIVER.findElement(By.xpath(RunTestNG.OR.getProperty(object))));
            selectAction.selectByValue(data);
            Log.info("Selected "+object+ " by value. "+data);
        }catch (Exception e){
            Log.info("-------- Unable to select "+object+" by value: "+data+" --------"+e.getMessage());
            ExecuteTestcase.bResult = false;
        }
    }
    public static void selectByIndex(String object, int data){
        /****************************************************************************************************
        /** Des: This function is used to select By Index
        /** Created by DoaiTran. On: 21-Nov-2016
        /** Modification History:
        /**        Modify by: DoaiTran            Date: 12-Dec-2016       Note: For Maven and TestNG:  RunTestNG
        /****************************************************************************************************/
        try{
            Log.info("*Try to select by index*");
            Select selectAction =  new Select(DRIVER.findElement(By.xpath(RunTestNG.OR.getProperty(object))));
            selectAction.selectByIndex(data);
            Log.info("Selected "+object+ " by index: "+data);
        }catch (Exception e){
            Log.info("-------- Unable to select "+object+" by index: "+data+" --------"+e.getMessage());
            ExecuteTestcase.bResult = false;
        }
    }
    public static void  switchToIFrameWithName(String object, String data){
        /****************************************************************************************************
        /**  Desc: this function use to switch frame on page. It's used before hover_on_menu, and click_element functions
        /**        Three functions use to click on submenu on page
        /**  Currently, "switch_to.frame(str_frame_name)" work well on latest IE and chrome. Not work on new FF version (Verified:NOT YET)
        /**  On FF should add more wait time.
        /**  Created By: DoaiTran
        /**  Created Date: 21-Nov-2016
        /**  Modification History:
        /**         Modify by:            Date:       Note:
        /*****************************************************************************************************/
        Boolean valueResult = true;
        try{
            Log.info("*Try to switch to iFrame with name*");
            List<WebElement> iframes = DRIVER.findElements(By.tagName("iframe")); // (By.xpath("//iframe"));
            for (WebElement iframe : iframes) {
                System.out.println(iframe);
                if(iframe.getAttribute("name").equals(data)){
                    DRIVER.switchTo().frame(data);
                    valueResult = true;
                    break;
                }else {
                    valueResult = false;
                }
            }
            if(valueResult){
                Log.info("We are in iFrame with Name: "+data);
            }else {
                Log.info("Unable to switch to iFrame with iFrame Name: "+data+" --------");
                ExecuteTestcase.bResult =false;
            }
        }catch (Exception e){
            Log.info("--------  Unable to switch to iFrame with name: "+data+" --------"+e.getMessage());
            ExecuteTestcase.bResult = false;
        }
    }
    public static void  switchToIFrameWithID(String object, String data){
        /***************************************************************************************************
        /** Desc: this function use to switch To IFrame With ID
        /** Created By: DoaiTran
        /** Modification History:
        /**         Modify by:            Date:       Note:
        /***************************************************************************************************/
        Boolean valueResult = true;
        try{
            Log.info("*Try to switch to iFrame with iFrameID*");
            List<WebElement> iframes = DRIVER.findElements(By.id("iframe"));
            for (WebElement iframe : iframes) {
                System.out.println(iframe);
                if(iframe.getAttribute("id").equals(data)){
                    DRIVER.switchTo().frame(data);
                    valueResult = true;
                    break;
                }else {
                    valueResult =false;
                }
            }
            if(valueResult){
                Log.info("We are in iFrame with ID: "+data);
            }else {
                Log.info("Unable to switch to iFrame with iFrame ID: "+data);
                ExecuteTestcase.bResult =false;
            }
        }catch (Exception e){
            Log.info("-------- Unable to switch to iFrame with iFrame ID: "+data+" --------"+e.getMessage());
            ExecuteTestcase.bResult = false;
        }
    }
    public static void switchToMainPage(String object, String data){
        /****************************************************************************************************
        /**  Desc: this function use to switch frame to main page.
        /**  Currently, "switch_to.frame(str_frame_name)" work well on latest IE and chrome. Not work on new FF version (Verified:NOT YET)
        /**  Created By: DoaiTran
        /**  Created Date: 21-Nov-2016
        /**  Modification History:
        /**         Modify by:            Date:       Note:
        /*****************************************************************************************************/
        try{
            Log.info("*Try to switch to Main Page layout*");
            DRIVER.switchTo().defaultContent();
            Log.info("We are in Main Page layout.");
        }catch (Exception e){
            Log.info("-------- Unable to switch to Main Page layout --------"+e.getMessage());
            ExecuteTestcase.bResult = false;
        }
    }
    public static void  verifyTextInTable(String object, String data){
        /***************************************************************************************************
        /**  Desc: this function use to verifyTextInTable
        /**  Currently,
        /**  Created By: DoaiTran
        /**  Created Date: 22-Nov-2016
        /**  Modification History:
        /**         Modify by: DoaiTran            Date: 12-Dec-2016       Note: For Maven and TestNG:  RunTestNG
        /****************************************************************************************************/
        boolean valueResult = false;
        try{
            Log.info("*Try to verify Text In Table*");
            List<WebElement> listCell = DRIVER.findElements(By.xpath(RunTestNG.OR.getProperty(object)));
            for (WebElement iCell: listCell){
                if(iCell.getText().equals(data)){
                    Log.info("Value: "+data+" on table: "+object+" is existing.");
                    valueResult = true;
                    break;
                }else {
                    valueResult = false;
                }
            }
            if(valueResult == true){
                ExecuteTestcase.bResult = true;
            }else {
                ExecuteTestcase.bResult = false;
                Log.info("Value: "+data+" on table: "+object+"is not existing.");
            }
        }catch (Exception e){
            Log.info("-------- Unable to verify data: "+data+" in table: "+object+" --------"+e.getMessage());
            ExecuteTestcase.bResult = false;
        }
    }
    public static void verifyCheckboxIsChecked(String object, String data){
        /****************************************************************************************************
        /** Des: This function is used to verify Checkbox Is Checked
        /** Created by DoaiTran. On: 23-Nov-2016
        /** Modification History:
        /**        Modify by: DoaiTran            Date: 12-Dec-2016       Note: For Maven and TestNG:  RunTestNG
        /****************************************************************************************************/
        try{
            Log.info("*Try to verify Checkbox Is Checked*");
            WebElement checkbox = DRIVER.findElement(By.xpath(RunTestNG.OR.getProperty(object)));
            if(checkbox.isSelected() == true){
                Log.info("CheckBox: "+object+" is checked.");
            }else{
                Log.info("CheckBox: "+object+" is NOT checked.");
                ExecuteTestcase.bResult = false;
            }
        }catch (Exception e) {
            Log.info("-------- Unable to verify checkbox "+object+" is checked or not --------"+e.getMessage());
            ExecuteTestcase.bResult = false;
        }
    }
    public static void verifyCheckboxIsNotChecked(String object, String data){
        /****************************************************************************************************
        /** Des: This function is used to verify Checkbox Is NOT Checked
        /** Created by DoaiTran. On: 23-Nov-2016
        /**
        /** Modification History:
        /**       Modify by: DoaiTran            Date: 12-Dec-2016       Note: For Maven and TestNG:  RunTestNG
        /****************************************************************************************************/
        try{
            Log.info("*Try to verify Checkbox Is Not Checked*");
            WebElement checkbox = DRIVER.findElement(By.xpath(RunTestNG.OR.getProperty(object)));
            if(checkbox.isSelected() == false){
                Log.info("CheckBox: "+object+" is NOT checked.");
            }else{
                Log.info("CheckBox: "+object+" is checked.");
                ExecuteTestcase.bResult = false;
            }
        }catch (Exception e) {
            Log.info("-------- Unable to verify checkbox: "+object+" is available  or not --------"+e.getMessage());
            ExecuteTestcase.bResult = false;
        }
    }
    public static void verifyRadioIsChecked(String object, String data){
        /**************************************************************************************************
        /** Des: This function is used to verify Radio Is Checked
        /** Created by DoaiTran. On: 23-Nov-2016
        /** Modification History:
        /**         Modify by: DoaiTran            Date: 12-Dec-2016       Note: For Maven and TestNG:  RunTestNG
        /**************************************************************************************************/
        try{
            Log.info("*Try to verify Radio Is Checked*");
            WebElement radio = DRIVER.findElement(By.xpath(RunTestNG.OR.getProperty(object)));
            if(radio.isSelected() == true){
                Log.info("Radio: "+object+" is checked.");
            }else{
                Log.info("Radio: "+object+" is NOT checked.");
                ExecuteTestcase.bResult = false;
            }
        }catch (Exception e) {
            Log.info("-------- Unable to verify radio: "+object+" is checked or not --------"+e.getMessage());
            ExecuteTestcase.bResult = false;
        }
    }
    public static void verifyRadioIsNotChecked(String object, String data){
        /**************************************************************************************************
        /** Des: This function is used to verify Radio Is Not Checked
        /** Created by DoaiTran. On: 23-Nov-2016
        /** Modification History:
        /**         Modify by: DoaiTran            Date: 12-Dec-2016       Note: For Maven and TestNG:  RunTestNG
        /**************************************************************************************************/
        try{
            Log.info("*Try to verify Radio Is Not Checked*");
            WebElement radio = DRIVER.findElement(By.xpath(RunTestNG.OR.getProperty(object)));
            if(radio.isSelected() == false){
                Log.info("Radio: "+object+" is NOT checked.");
            }else{
                Log.info("Radio: "+object+" is checked.");
                ExecuteTestcase.bResult = false;
            }
        }catch (Exception e) {
            Log.info("-------- Unable to verify radio: "+object+" is available or not --------"+e.getMessage());
            ExecuteTestcase.bResult = false;
        }
    }
    public static void checkCheckBox(String object, String data){
        /**************************************************************************************************
        /** Des: This function is used to check Checkbox
        /** Created by DoaiTran. On: 24-Nov-2016
        /** Modification History:
        /**         Modify by: DoaiTran            Date: 12-Dec-2016       Note: For Maven and TestNG:  RunTestNG
        /**************************************************************************************************/
        try{
            Log.info("*Try to Check CheckBox*");
            WebElement objCheckbox = DRIVER.findElement(By.xpath(RunTestNG.OR.getProperty(object)));
            if(objCheckbox.isSelected()== false){
                objCheckbox.click();
                Log.info("Check CheckBox: "+object);
            }else {
                Log.info("CheckBox: "+object+ " was checked. Unable to check CheckBox.");
                ExecuteTestcase.bResult = false;
            }
        }catch (Exception e){
            Log.info("-------- Unable to check CheckBox: "+object+" --------"+e.getMessage());
            ExecuteTestcase.bResult = false;
        }
    }
    public static void  unCheckCheckBox (String object, String data){
        /**************************************************************************************************
        /** Des: This function is used to uncheck Checkbox
        /** Created by DoaiTran. On: 24-Nov-2016
        /** Modification History:
        /**         Modify by: DoaiTran            Date: 12-Dec-2016       Note: For Maven and TestNG:  RunTestNG
        /**************************************************************************************************/
        try{
            Log.info("*Try to unCheck CheckBox*");
            WebElement objCheckbox = DRIVER.findElement(By.xpath(RunTestNG.OR.getProperty(object)));
            if(objCheckbox.isSelected()== true){
                objCheckbox.click();
                Log.info("Uncheck CheckBox: "+object);
            }else {
                Log.info("CheckBox: "+object+ " was unchecked. Unable to uncheck CheckBox.");
                ExecuteTestcase.bResult = false;
            }
        }catch (Exception e){
            Log.info("-------- Unable to uncheck CheckBox: "+object+" --------"+e.getMessage());
            ExecuteTestcase.bResult = false;
        }
    }
    public static void  clickTreeViewItem (String object, String data){
        /*************************************************************************************************
        /** Des: This function is used to click TreeView Item
        /** Created by DoaiTran. On: 25-Nov-2016
        /** Modification History:
        /**         Modify by: DoaiTran            Date: 12-Dec-2016       Note: For Maven and TestNG:  RunTestNG
        /************************************************************************************************/
        boolean valueResult = false;
        try{
            Log.info("*Try to click TreeView Item*");
            WebElement treeView = DRIVER.findElement(By.xpath(RunTestNG.OR.getProperty(object)));
            List<WebElement> listChild = treeView.findElements(By.tagName("span"));
            for (WebElement child : listChild)
                if (child.getText() == data) {
                    child.click();
                    valueResult = true;
                    break;
                }
            if(valueResult = true){
                Log.info("Item "+ data +" is existed in TreeView: "+object);
            }else {
                Log.info("Item "+ data +" is NOT existed in TreeView: "+object);
                ExecuteTestcase.bResult = false;
            }
        }catch (Exception e){
            Log.info("-------- Unable to click TreeView Item: "+object+" --------"+e.getMessage());
            ExecuteTestcase.bResult = false;
        }
    }
    public static void  verifyTreeViewItemExist (String object, String data){
        /*************************************************************************************************
        /** Des: This function is used to verify Treeview Item Exist
        /** Created by DoaiTran. On: 25-Nov-2016
        /** Modification History:
        /**         Modify by: DoaiTran            Date: 12-Dec-2016       Note: For Maven and TestNG:  RunTestNG
        /************************************************************************************************/
        boolean valueResult = false;
        try{
            Log.info("*Try to verify TreeView Item Exist*");
            WebElement treeView = DRIVER.findElement(By.xpath(RunTestNG.OR.getProperty(object)));
            List<WebElement> listChild = treeView.findElements(By.tagName("span"));
            for (WebElement child : listChild)
                if (child.getText() == data) {
                     valueResult = true;
                    break;
                }
            if(valueResult = true){
                Log.info("Item "+ data +"is existed in tree view: "+object);
            }else {
                Log.info("Item "+ data +"is NOT existed in tree view: "+object);
                ExecuteTestcase.bResult = false;
            }
        }catch (Exception e){
            Log.info("-------- Unable to click TreeView Item: "+object+" --------"+e.getMessage());
            ExecuteTestcase.bResult = false;
        }
    }
    public static void  verifyTreeViewItemNotExist (String object, String data){
        /*************************************************************************************************
         /** Des: This function is used to verify Tree view Item Not Exist
         /** Created by DoaiTran. On: 25-Nov-2016
         /** Modification History:
         /**         Modify by: DoaiTran            Date: 12-Dec-2016       Note: For Maven and TestNG:  RunTestNG
         /************************************************************************************************/
        boolean valueResult = false;
        try{
            Log.info("*Try to verify TreeView Item Not Exist*");
            WebElement treeView = DRIVER.findElement(By.xpath(RunTestNG.OR.getProperty(object)));
            List<WebElement> listChild = treeView.findElements(By.tagName("span"));
            for (WebElement child : listChild)
                if (child.getText() == data) {
                    valueResult = false;
                    break;
                }
            if(valueResult = true){
                Log.info("Item "+ data +"is existed in TreeView: "+object);
            }else {
                Log.info("Item "+ data +"is NOT existed in TreeView: "+object);
                ExecuteTestcase.bResult = false;
            }
        }catch (Exception e){
            Log.info("-------- Unable to click TreeView Item: "+object+" --------"+e.getMessage());
            ExecuteTestcase.bResult = false;
        }
    }
    public static void closeAlertOfBrowser(String object, String data){
        /************************************************************************************************
        /** Des: This function is used to close Alert Of Browser
        /** Created by DoaiTran. On: 26-Nov-2016
        /** Modification History:
        /**         Modify by:            Date:       Note:
        /************************************************************************************************/
        try{
            Log.info("*Try to close alert of browser*");
            DRIVER.switchTo().alert().dismiss();
            Log.info("Closed alert of browser successfully.");
        }catch (Exception e){
            Log.info("-------- Unable to close alert of browser --------");
            ExecuteTestcase.bResult = false;
        }
    }
    public static void acceptAlertOfBrowser(String object, String data){
        /***********************************************************************************************
        /** Des: This function is used to accept Alert Of Browser
        /** Created by DoaiTran. On: 26-Nov-2016
        /** Modification History:
        /**         Modify by:            Date:       Note:
        /***********************************************************************************************/
        try{
            Log.info("*Try to accept alert of browser*");
            DRIVER.switchTo().alert().accept();
            Log.info("Accepted alert of browser successfully.");
        }catch (Exception e){
            Log.info("-------- Unable to accept alert of browser --------");
            ExecuteTestcase.bResult = false;
        }
    }
    public static void verifyAlertText(String object, String data){
        /**********************************************************************************************
        /** Des: This function is used to accept Alert Of Browser
        /** Created by DoaiTran. On: 26-Nov-2016
        /** Modification History:
        /**         Modify by:            Date:       Note:
        /**********************************************************************************************/
        try{
            Log.info("*Try to verify AlertText*");
            String getText = DRIVER.switchTo().alert().getText();
            if(getText == data){
                Log.info("AlertText is  the same with "+ data);
            }else {
                Log.info("AlertText is  different with "+ data);
                ExecuteTestcase.bResult = false;
            }
        }catch (Exception e){
            Log.info("-------- Unable to accept alert of browser --------");
            ExecuteTestcase.bResult = false;
        }
    }
    public static void sendValueToAlert(String object, String data){
        /*********************************************************************************************
        /** Des: This function is used to send Value To Alert
        /** Created by DoaiTran. On: 26-Nov-2016
        /** Modification History:
        /**         Modify by:            Date:       Note:
        /*********************************************************************************************/
        try{
            Log.info("*Try to send value to Alert*");
            DRIVER.switchTo().alert().sendKeys(data);
            Log.info("Sent value: "+data+" to alert successfully.");
        }catch (Exception e){
            Log.info("-------- Unable to send value to Alert --------");
            ExecuteTestcase.bResult = false;
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //  Exception handling
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void getscreenshot() throws Exception {
        /***************************************************************************************************
        /** Created by DoaiTran. On : 26-Oct-2016
        /**  Updated on 08-Nov-2016 for error steps.
        /**  Format of screenshot file name: Error__"TestCaseID"__"TestStepName"__"TimeStampValue".png
        /** Modification:
        /**         Modify by:            Date:       Note:
        /*************************************************************************************************/
        File scrFile = ((TakesScreenshot) DRIVER).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("src\\test\\java\\testing\\reports\\imagesLog\\" + "Error__" + testsuiteName+"__"+ sTestCaseID + "__" + TestStepName + "__" + GetTimeStampValue() + ".png"));
    }
    public  static String GetTimeStampValue()throws IOException {
        Calendar cal = Calendar.getInstance();
        java.util.Date time = cal.getTime();
        String timestamp = time.toString();
        System.out.println(timestamp);
        String systime = timestamp.replace(":", "-");
        System.out.println(systime);
        return systime;
    }
        /************************************/
}
