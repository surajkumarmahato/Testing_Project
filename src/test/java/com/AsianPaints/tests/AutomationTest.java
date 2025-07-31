//package com.tests;
//
//import com.base.Asain_paints_base ;
//import com.pages.*;
//import com.utilities.Asain_paints_utilities;
//
//import org.testng.annotations.*;
//import com.aventstack.extentreports.*;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
//public class AutomationTest extends Asain_paints_base  {
//
//    ExtentReports extent;
//    ExtentTest test;
//    HomePage home;
//    ColourCombinationPage combinationsPage;
//    CelebrityHomesage celebrityPage;
//    ColourOfTheYearPage colourPage;
//
//    @Parameters("browser")
//    @BeforeClass
//    public void setUp(@Optional("chrome") String browser) {
//        initializeBrowser(browser);
//        extent = Asain_paints_utilities.getReportInstance("Inspiration");  // â†� FIXED LINE
//        home = new HomePage();
//        combinationsPage = new ColourCombinationPage();
//        celebrityPage = new CelebrityHomesage();
//        colourPage = new ColourOfTheYearPage();
//    }
//
//    @Test(priority = 1)
//    public void clickInspirationTabOnly() throws InterruptedException {
//        test = extent.createTest("Click on 'Inspiration' Tab");
//        driver.get("https://www.asianpaints.com/");
//        home.clickInspirationTab();
//        test.pass("Clicked on 'Inspiration' tab successfully.");
//    }
//
//    @Test(priority = 2)
//    public void testColourCombinationsPage() {
//        test = extent.createTest("Verify Colour Combinations Page");
//        driver.get("https://www.asianpaints.com/inspiration/ideas/colour-inspiration.html");
//        combinationsPage.verifyImages();
//        test.pass("Verified all images by alt text.");
//    }
//
//    @Test(priority = 3)
//    public void testCelebrityHomesPage() {
//        test = extent.createTest("Verify Celebrity Homes Page");
//        driver.get("https://www.asianpaints.com/where-the-heart-is/season-7.html");
//        celebrityPage.verifyCelebrityContent();
//        test.pass("Verified button and video thumbnail.");
//    }
//
//    @Test(priority = 4)
//    public void testColourOfTheYearPage() {
//        test = extent.createTest("Verify Colour of the Year 2025 Page");
//        driver.get("https://www.asianpaints.com/colour-next.html");
//        colourPage.verifyColourOfYear();
//        test.pass("Verified heading for Colour of the Year 2025.");
//    }
//
//    @Test(priority = 5)
//    public void testInspirationSubModulesFailing() {
//        test = extent.createTest("(Failing) Validate Submodules Under Inspiration Tab");
//
//        driver.get("https://www.asianpaints.com/");
//        try {
//            home.clickInspirationTab();
//
//            // Intentionally failing element
//            driver.findElement(By.xpath("//a[text()='Colour Combinations']"));
//            test.pass("Submodule 'Colour Combinations' is visible");
//        } catch (Exception e) {
//            takeScreenshot("submodule_failure.png");
//            test.fail("Submodule 'Colour Combinations' not found or visible");
//        }
//    }
//
//    public void takeScreenshot(String fileName) {
//        try {
//            // âœ… Ensure screenshots/ folder exists
//            File screenshotDir = new File("screenshots");
//            if (!screenshotDir.exists()) {
//                screenshotDir.mkdirs(); // Create directory if it doesn't exist
//            }
//
//            // âœ… Capture screenshot
//            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//
//            // âœ… Set destination path
//            String fullPath = "screenshots/" + fileName;
//
//            // âœ… Copy screenshot to path
//            Files.copy(src.toPath(), Paths.get(fullPath));
//
//            // âœ… Attach to extent report
//            test.addScreenCaptureFromPath(fullPath);
//
//        } catch (IOException e) {
//            test.warning("Screenshot failed: " + e.getMessage());
//        }
//    }
//}
