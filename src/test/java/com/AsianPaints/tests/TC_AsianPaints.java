package com.AsianPaints.tests;

import com.AsianPaints.pages.Pages_AsianPaints;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class TC_AsianPaints 
{
    String projectpath = System.getProperty("user.dir");
    ExtentReports extent;
    ExtentSparkReporter spark;

    //Before Suite
    @BeforeSuite
    public void startReport() 
    {
        extent = new ExtentReports();
        String reportPath = projectpath + "\\Reports\\TC_AP_Suraj_1.html";
        new File(projectpath + "\\Reports\\Screenshots").mkdirs(); // Ensure folders exist
        spark = new ExtentSparkReporter(reportPath);
        extent.attachReporter(spark);
    }


    //After Suite
    @AfterSuite
    public void endReport() 
    {
        extent.flush();
    }

    //Calc Cost Of Interior Painting
    @Test(dataProvider = "AreaDatas")
    public void Calc_Budget_Paint_Int(String AreaVal) throws IOException 
    {
        ExtentTest test = extent.createTest("Paint Budget | Area: " + AreaVal);

        WebDriver driver = setupDriver(test);
        try 
        {
            Properties prob = loadProperties();
            driver.get(prob.getProperty("url"));
            driver.manage().window().maximize();
            handlePopups(driver);

            Pages_AsianPaints page = new Pages_AsianPaints(driver);
            page.hoverOverProducts();
            page.clickInteriorWallPaints();
            handleSplash(driver);
            page.clickFreshPainting();
            page.clickInterior();
            page.enterArea(AreaVal);
            page.clickCalculateNow();

            WebElement result = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div/div[1]/div/div/div/div[1]/div[1]/div[3]/div[2]/div/div/ul/li[1]/div/h4"));
            if (result.isDisplayed()) 
            {
                test.pass("Budget displayed successfully for area " + AreaVal);
            } 
            else 
            {
                throw new Exception("Budget result not visible.");
            }
        } 
        catch (Exception e) 
        {
            captureFailure(driver, test, e.getMessage());
        } 
        finally 
        {
            driver.quit();
        }
    }

    //Calc Cost Of WaterProofing
    @Test(dataProvider = "WaterAreaDatas")
    public void Calc_WaterProofing(String WaterAreaVal) throws IOException, InterruptedException 
    {
        ExtentTest test = extent.createTest("Waterproof Budget | Area: " + WaterAreaVal);

        WebDriver driver = setupDriver(test);
        try 
        {
            Properties prob = loadProperties();
            driver.get(prob.getProperty("url"));
            driver.manage().window().maximize();
            handlePopups(driver);

            Pages_AsianPaints page = new Pages_AsianPaints(driver);
            page.hoverOverProducts();
            page.clickWaterProofingAllProduct();
            Thread.sleep(3000);
            handleSplash(driver);
            page.clickRoof();
            page.clickFreshCons();
            page.EnterWaterArea(WaterAreaVal);
            page.Water_Sumbit();

            WebElement result = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div/div[1]/div/div[1]/div[1]/div[2]/div[1]/div[1]"));
            if (result.isDisplayed()) 
            {
                test.pass("Waterproofing budget displayed for area " + WaterAreaVal);
            } 
            else 
            {
                throw new Exception("Waterproofing result not visible.");
            }
        } catch (Exception e) 
        {
            captureFailure(driver, test, e.getMessage());
        } 
        finally 
        {
            driver.quit();
        }
    }

    
    //Find Contractor By Pincode
    @Test(dataProvider = "PinCodeDatas")
    public void Find_Contractor(String EntPin) throws IOException, InterruptedException 
    {
        ExtentTest test = extent.createTest("Contractor Lookup | Pin: " + EntPin);

        WebDriver driver = setupDriver(test);
        try 
        {
            Properties prob = loadProperties();
            driver.get(prob.getProperty("url"));
            driver.manage().window().maximize();
            handlePopups(driver);

            Pages_AsianPaints page = new Pages_AsianPaints(driver);
            page.clickFindContractor();
            handleSplash(driver);
            page.EnterPinCode(EntPin);
            page.clickEnterButton();

            WebElement result = driver.findElement(By.xpath("//h2[contains(text(),'Contractors')]"));
            if (result.isDisplayed()) 
            {
                test.pass("Contractors displayed successfully for PinCode " + EntPin);
            } 
            else 
            {
                throw new Exception("No contractors found.");
            }
        } 
        catch (Exception e) 
        {
            captureFailure(driver, test, e.getMessage());
        } 
        finally 
        {
            driver.quit();
        }
    }

    // Setup & Utility Methods
    private WebDriver setupDriver(ExtentTest test) 
    {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    private Properties loadProperties() throws IOException 
    {
        Properties prob = new Properties();
        FileInputStream fs2 = new FileInputStream(projectpath + "\\Data\\CalcBudgetPage.properties");
        prob.load(fs2);
        return prob;
    }

    //Cookies Closer
    private void handlePopups(WebDriver driver) 
    {
        try 
        {
            WebElement cookieClose = driver.findElement(By.id("onetrust-close-btn-container"));
            if (cookieClose.isDisplayed()) 
            {
                cookieClose.click();
            }
        } 
        catch (NoSuchElementException ignored) 
        {}
        handleSplash(driver);
    }

    //PopUp Closer
    private void handleSplash(WebDriver driver) 
    {
        try 
        {
            WebElement splashClose = driver.findElement(By.className("splash-close-btn"));
            if (splashClose.isDisplayed()) 
            {
                splashClose.click();
            }
        } 
        catch (NoSuchElementException ignored) 
        {
        }
    }

    //Capture ScreenShot
    private void captureFailure(WebDriver driver, ExtentTest test, String message) 
    {
        try 
        {
            test.fail(message);
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String dest = projectpath + "/Reports/Screenshots/Fail_" + timestamp + ".png";
            File destFile = new File(dest);
            FileUtils.copyFile(src, destFile);
            test.addScreenCaptureFromPath(dest);
        } 
        catch (IOException e) 
        {
            test.fail("Screenshot capture failed: " + e.getMessage());
        }
    }

    // Data for Int Painting
    @DataProvider
    public String[] AreaDatas() throws IOException 
    {
        return readExcelColumn(0, 0);
    }

    // Data for Waterpoofing
    @DataProvider
    public String[] WaterAreaDatas() throws IOException 
    {
        return readExcelColumn(1, 0);
    }

    // Data for Contractors
    @DataProvider
    public String[] PinCodeDatas() throws IOException 
    {
        return readExcelColumn(2, 0);
    }

    private String[] readExcelColumn(int sheetIndex, int colIndex) throws IOException 
    {
        FileInputStream fs = new FileInputStream(new File(projectpath + "\\data\\AsianPaintsData.xlsx"));
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
        XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
        int rows = sheet.getPhysicalNumberOfRows();
        String[] data = new String[rows];
        for (int i = 0; i < rows; i++) 
        {
            data[i] = Integer.toString((int) (sheet.getRow(i).getCell(colIndex).getNumericCellValue()));
        }
        workbook.close();
        return data;
    }
}