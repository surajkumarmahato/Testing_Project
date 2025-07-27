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

public class TC_1_IntPaintBudget
{
    String projectpath = System.getProperty("user.dir");
    ExtentReports extent;
    ExtentSparkReporter spark;
    protected WebDriver driver;
    protected Properties prob;

    @BeforeSuite
    public void startReport() {
        extent = new ExtentReports();
        String reportPath = projectpath + "/Reports/TC_AP_Suraj_1.html";
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
    public String[] AreaDatas() throws IOException {
        return ExcelUtil.readColumn(projectpath + "/data/AsianPaintsData.xlsx", 0, 0);
    }
}
