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

public class TC_3_FindContractor
{
    String projectpath = System.getProperty("user.dir");
    ExtentReports extent;
    ExtentSparkReporter spark;
    protected WebDriver driver;
    protected Properties prob;

    @BeforeSuite
    public void startReport() {
        extent = new ExtentReports();
        String reportPath = projectpath + "/Reports/TC_AP_Suraj_3.html";
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

    @Test(priority = 2)
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

    @Test(dataProvider = "PinCodeDatas", priority = 3)
    public void Calc_Budget_Paint_Int(String EntPin) {
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

    // ------ Common Utilities ------
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

    // Data Provider
    @DataProvider
    public String[] PinCodeDatas() throws IOException 
    {
        return ExcelUtil.readColumn(projectpath + "/data/AsianPaintsData.xlsx", 2, 0);
    }
}
