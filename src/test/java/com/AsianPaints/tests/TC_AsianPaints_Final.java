package com.AsianPaints.tests;

import com.AsianPaints.pages.Pages_AsianPaints;
import com.AsianPaints.utilities.ExcelUtil;
import com.AsianPaints.utilities.PropertyUtil;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.annotations.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class TC_AsianPaints_Final {
    String projectpath = System.getProperty("user.dir");
    ExtentReports extent;
    ExtentSparkReporter spark;
    protected WebDriver driver;
    protected Properties prob;

    @BeforeSuite
    public void startReport() {
        extent = new ExtentReports();
        String reportPath = projectpath + "/Reports/TC_AP_Project Final Report.html";
        new File(projectpath + "/Reports/Screenshots").mkdirs();
        spark = new ExtentSparkReporter(reportPath);
        extent.attachReporter(spark);
    }
    
    @AfterSuite
    public void endReport() {
        extent.flush();
    }
    @BeforeMethod
    public void setup() throws IOException {
        WebDriverManager.chromedriver().setup();
        driver = new org.openqa.selenium.chrome.ChromeDriver();
        prob = PropertyUtil.loadProperties(projectpath + "/Data/CalcBudgetPage.properties");
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
        driver.get(prob.getProperty("url"));
        driver.manage().window().maximize();
        handlePopups();
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) driver.quit();
    }

    
    //Test Case 1 : To Check Browser is Opening or Not
    @Test(priority = 1)
    public void OpenBrowser() {
        ExtentTest test = extent.createTest("Browser is Opening or Not");
        try {
            WebElement result = driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/div/header/div[3]/div[1]/a/picture/img"));
            if (result.isDisplayed()) {
                test.pass("Browser is Opening Successfully.");
            } else {
                throw new Exception("Browser is Not Opening.");
            }
        } catch (Exception e) {
            captureFailure(test, e.getMessage());
        }
    }

    //Test Case 2 : To Check Hover is Working or Not
    @Test(priority = 2)
    public void Check_Hover() {
        ExtentTest test = extent.createTest("Checking Hover is Working or Not");
        try {
            Pages_AsianPaints page = new Pages_AsianPaints(driver);
            page.hoverOverProducts();
            WebElement result = driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/div/header/div[4]/nav/ul/li[2]/ul/li[1]/ul/span"));
            if (result.isDisplayed()) {
                test.pass("Hover is Working.");
            } else {
                throw new Exception("Hover is Not Working.");
            }
        } catch (Exception e) {
            captureFailure(test, e.getMessage());
        }
    }

    //Test Case 3 : To Check Paint Section is Opening/Not
    @Test(priority = 3)
    public void Check_PaintSection() {
        ExtentTest test = extent.createTest("Paint Section is Opening/Not");
        try {
            Pages_AsianPaints page = new Pages_AsianPaints(driver);
            page.hoverOverProducts();
            page.clickInteriorWallPaints();
            handleSplash();
            WebElement result = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div/div[1]/div/div[1]/div/div[1]/div[1]/div[1]"));
            if (result.isDisplayed()) {
                test.pass("Paint Section is Opening.");
            } else {
                throw new Exception("Paint Section is Not Opening.");
            }
        } catch (Exception e) {
            captureFailure(test, e.getMessage());
        }
    }

    //Test Case 4 : To Check Calc. of Paint Budget
    @Test(dataProvider = "AreaDatas", priority = 4)
    public void Calc_Budget_Paint_Int(String AreaVal) {
        ExtentTest test = extent.createTest("Calc Paint Budget | Area: " + AreaVal);
        try {
            Pages_AsianPaints page = new Pages_AsianPaints(driver);
            page.hoverOverProducts();
            page.clickInteriorWallPaints();
            handleSplash();
            page.clickFreshPainting();
            page.clickInterior();
            page.enterArea(AreaVal);
            page.clickCalculateNow();
            handleSplash();
            WebElement result = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div/div[1]/div/div/div/div[1]/div[1]/div[3]/div[2]/div/div/ul/li[1]/div/h4"));
            if (result.isDisplayed()) {
                test.pass("Budget displayed successfully for area " + AreaVal);
            } else {
                throw new Exception("Budget result not visible.");
            }
        } catch (Exception e) {
            captureFailure(test, e.getMessage());
        }
    }

    
    //Test Case 5 : To Check WaterProofing Section is Opening/Not
    @Test(priority = 5)
    public void Check_WaterProofingSection() {
        ExtentTest test = extent.createTest("WaterProofing Section is Opening/Not");
        try {
            Pages_AsianPaints page = new Pages_AsianPaints(driver);
            page.hoverOverProducts();
            page.clickWaterProofingAllProduct();
            handleSplash();
            WebElement result = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div/div[2]/div/div/div/div[1]/div[1]"));
            if (result.isDisplayed()) {
                test.pass("WaterProofing Section is Opening.");
            } else {
                throw new Exception("WaterProofing Section is Not Opening.");
            }
        } catch (Exception e) {
            captureFailure(test, e.getMessage());
        }
    }

    
    //Test Case 6 : To Check Calc. WaterProofing Budget
    @Test(dataProvider = "WaterAreaDatas", priority = 6)
    public void Calc_Budget_WaterProofing(String WaterAreaVal) {
        ExtentTest test = extent.createTest("Calc WaterProofing Budget | Area: " + WaterAreaVal);
        try {
            Pages_AsianPaints page = new Pages_AsianPaints(driver);
            page.hoverOverProducts();
            page.clickWaterProofingAllProduct();
            handleSplash();
            page.clickRoof();
            page.clickFreshCons();
            page.EnterWaterArea(WaterAreaVal);
            page.Water_Sumbit();
            handleSplash();
            WebElement result = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div/div[1]/div/div[1]/div[1]/div[2]/div[1]/div[2]"));
            if (result.isDisplayed()) {
                test.pass("WaterProofing displayed successfully for area " + WaterAreaVal);
            } else {
                throw new Exception("WaterProofing result not visible.");
            }
        } catch (Exception e) {
            captureFailure(test, e.getMessage());
        }
    }
    
    //Test Case 7 : To Check "Checking Find Contractor Page is Opening or Not
    @Test(priority = 7)
    public void Check_FindContractor() {
        ExtentTest test = extent.createTest("Checking Find Contractor Page is Opening or Not");
        try {
            Pages_AsianPaints page = new Pages_AsianPaints(driver);
            page.clickFindContractor();
            handleSplash();
            WebElement result = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[2]/div/div[4]/div/div/p[1]/span/span"));
            if (result.isDisplayed()) {
                test.pass("Find Contractor Page is Opening.");
            } else {
                throw new Exception("Find Contractor Page is Not Opening");
            }
        } catch (Exception e) {
            captureFailure(test, e.getMessage());
        }
    }

    //Test Case 8 : To Check are Showing or not after Entering PinCode
    @Test(dataProvider = "PinCodeDatas", priority = 8)
    public void FindContractorUsingPinCode(String EntPin) {
        ExtentTest test = extent.createTest("Contractor Lookup | Pin: " + EntPin);
        try {
            Pages_AsianPaints page = new Pages_AsianPaints(driver);
            page.clickFindContractor();
            handleSplash();
            page.EnterPinCode(EntPin);
            page.clickEnterButton();
            handleSplash();

            WebElement result = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[2]/div/div[2]/div/div[1]/h2"));
            if (result.isDisplayed()) {
                test.pass("Contractors displayed successfully for PinCode " + EntPin);
            } else {
                throw new Exception("No contractors found.");
            }
        } catch (Exception e) {
            captureFailure(test, e.getMessage());
        }
    }
    
    // To handle Pop Up and Cookies
    private void handlePopups() {
        try {
            WebElement cookieClose = driver.findElement(By.id("onetrust-close-btn-container"));
            if (cookieClose.isDisplayed())
                cookieClose.click();
        } catch (NoSuchElementException ignored) {}
        handleSplash();
    }

    private void handleSplash() {
        try {
            WebElement splashClose = driver.findElement(By.className("splash-close-btn"));
            if (splashClose.isDisplayed())
                splashClose.click();
        } catch (NoSuchElementException ignored) {}
    }

    //To Capture Screenshot When Test Case Failed
    private void captureFailure(ExtentTest test, String message) {
        try {
            test.fail(message);
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String dest = projectpath + "/Reports/Screenshots/Fail_" + timestamp + ".png";
            FileUtils.copyFile(src, new File(dest));
            test.addScreenCaptureFromPath(dest);
        } catch (IOException e) {
            test.fail("Screenshot failed: " + e.getMessage());
        }
    }
    
    // Data Provider 1 for Paint Budget Section
    @DataProvider
    public String[] AreaDatas() throws IOException {
        return ExcelUtil.readColumn(projectpath + "/data/AsianPaintsData.xlsx", 0, 0);
    }
    
    // Data Provider 2 for WaterProofing Budget Section
    @DataProvider
    public String[] WaterAreaDatas() throws IOException 
    {
        return ExcelUtil.readColumn(projectpath + "/data/AsianPaintsData.xlsx", 1, 0);
    }
    
    // Data Provider 3 for Find Contractors
    @DataProvider
    public String[] PinCodeDatas() throws IOException 
    {
        return ExcelUtil.readColumn(projectpath + "/data/AsianPaintsData.xlsx", 2, 0);
    }
}
