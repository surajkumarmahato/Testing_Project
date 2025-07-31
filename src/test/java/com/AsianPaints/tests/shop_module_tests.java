//package com.tests;
//
//import org.testng.annotations.*;
//import org.openqa.selenium.WebDriver;
//
//import com.aventstack.extentreports.*;
//import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.util.Assert;
//import com.base.Asain_paints_base;
//import com.pages.shop_module_pages;
//import com.utilities.Asain_paints_utilities;
//
//public class shop_module_tests extends Asain_paints_base {
//
//    ExtentReports report;
//    ExtentTest test;
//    shop_module_pages page;
//
//    @BeforeTest
//    public void setupReport() {
//        report = Asain_paints_utilities.getReportInstance("Shop");
//    }
//
//   /* @BeforeMethod
//    public void setup() {
//        initializeDriver();
//        page = new shop_module_pages(driver);
//    }*/
//
//    @Test(priority = 2)
//    public void testMechanisedToolsModule() {
//        test = report.createTest("Mechanised Tools Test");
//        try {
//            page.handleCookiesAndPopups();
//            page.navigateToMechanisedTools();
//            page.applyFilter();
//            test.log(Status.PASS, "Navigated to Mechanised Tools and applied filter successfully");
//        } catch (Exception e) {
//        	String screenshotPath = Asain_paints_utilities.getScreenshotPath(driver, "Mechained Tools Module");
//            test.log(Status.FAIL, "Failed Mechanised Tools Test: " + e.getMessage());
//            test.addScreenCaptureFromPath(screenshotPath);
//        }
//    }
//
//    @Test(priority = 1)
//    public void testShopProductAddToCart() {
//        test = report.createTest("Shop Product Add to Cart Test");
//        try {
//            page.handleCookiesAndPopups();
//            page.navigateToToolsFromShop();
//            String parentWindow = driver.getWindowHandle();
//            page.clickProductImageInShop();
//            page.switchToChildWindowAndAddToCart(parentWindow);
//            test.log(Status.PASS, "Product added to cart successfully");
//        } catch (Exception e) {
//        	String screenshotPath = Asain_paints_utilities.getScreenshotPath(driver, "Add To Cart Module");
//            test.log(Status.FAIL, "Failed to add product to cart: " + e.getMessage());
//            test.addScreenCaptureFromPath(screenshotPath);
//        }
//    }
//    
//    @Test(priority = 1)
//    public void ColorBooks() {
//        test = report.createTest("Navigating To color Books");
//        try {
//        	page.handleCookiesAndPopups();
//            page.navigateToMechanisedTools();
//            page.colorbook();
//            test.log(Status.PASS, "Navigated to color books successfully");
//        } catch (Exception e) {
//        	String screenshotPath = Asain_paints_utilities.getScreenshotPath(driver, "color books");
//            test.log(Status.FAIL, "can't navigated to color books: " + e.getMessage());
//            test.addScreenCaptureFromPath(screenshotPath);
//        }
//    }
//
//    /*@AfterMethod
//    public void tearDown() {
//        closeDriver();
//    }*/
//
//    @AfterTest
//    public void flushReport() {
//        report.flush();
//    }
//}
