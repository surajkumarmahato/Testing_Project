//package com.tests;
//
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//import com.aventstack.extentreports.Status;
//import com.base.Asain_paints_base;
//import com.pages.home_page_asain_paints_pages;
//import com.utilities.Asain_paints_utilities;
//
//public class home_page_tests extends Asain_paints_base {
//
//    @Test(priority = 1)
//    public void verifyAddressNavigation() throws InterruptedException {
//        test = extent.createTest("Address Click Test");
//        home_page_asain_paints_pages home = new home_page_asain_paints_pages(driver);
//        test.log(Status.INFO, "Launching the Asian Paints website.");
//        home.handleCookiesAndPopups();
//        try {
//            home.clickAddress();
//            test.log(Status.INFO, "Clicked on the address icon in the header.");
//            test.log(Status.PASS, "Clicked Address Icon Successfully");
//        } catch (Exception e) {
//            String screenshotPath = Asain_paints_utilities.getScreenshotPath(driver, "verifyAddressNavigation");
//            test.log(Status.FAIL, "Failed to click on Address Icon: " + e.getMessage());
//            test.addScreenCaptureFromPath(screenshotPath);
//            Assert.fail();
//        }
//    }
//
//    @Test(priority = 2)
//    public void verifyColorSelection() throws InterruptedException {
//        test = extent.createTest("Color Selection Test");
//        home_page_asain_paints_pages home = new home_page_asain_paints_pages(driver);
//        test.log(Status.INFO, "Launching the Asian Paints website.");
//        home.handleCookiesAndPopups();
//        try {
//            home.hoverAndClickColors();
//            test.log(Status.INFO, "Hovered over 'Colours' and clicked 'All Colours'.");
//            test.log(Status.PASS, "Hovered and clicked on first color category");
//        } catch (Exception e) {
//            String screenshotPath = Asain_paints_utilities.getScreenshotPath(driver, "verifyColorSelection");
//            test.log(Status.FAIL, "Failed to hover/click color: " + e.getMessage());
//            test.addScreenCaptureFromPath(screenshotPath);
//            Assert.fail();
//        }
//    }
//
//    @Test(priority = 3)
//    public void verifyInteriorWallModule() throws InterruptedException {
//        test = extent.createTest("Interior Wall Navigation Test");
//        home_page_asain_paints_pages home = new home_page_asain_paints_pages(driver);
//        test.log(Status.INFO, "Launching the Asian Paints website.");
//        home.handleCookiesAndPopups();
//        try {
//            home.navigateInteriorWall();
//            test.log(Status.INFO, "Clicked on 'Interior Wall Paints' and its sub-option.");
//            test.log(Status.PASS, "Navigated to Interior Wall module successfully");
//        } catch (Exception e) {
//            String screenshotPath = Asain_paints_utilities.getScreenshotPath(driver, "verifyInteriorWallModule");
//            test.log(Status.FAIL, "Failed to navigate Interior Wall: " + e.getMessage());
//            test.addScreenCaptureFromPath(screenshotPath);
//            Assert.fail();
//        }
//    }
//
//    @Test(priority = 4)
//    public void verifyPageScroll() throws InterruptedException {
//        test = extent.createTest("Scroll Down Test");
//        home_page_asain_paints_pages home = new home_page_asain_paints_pages(driver);
//        test.log(Status.INFO, "Launching the Asian Paints website.");
//        home.handleCookiesAndPopups();
//        try {
//            home.scrollToBottom();
//            test.log(Status.INFO, "Checking the scrolling operation");
//            test.log(Status.PASS, "Scrolled to bottom of the page successfully");
//        } catch (Exception e) {
//            String screenshotPath = Asain_paints_utilities.getScreenshotPath(driver, "verifyPageScroll");
//            test.log(Status.FAIL, "Failed to scroll page: " + e.getMessage());
//            test.addScreenCaptureFromPath(screenshotPath);
//            Assert.fail();
//        }
//    }
//
//    @Test(priority = 5)
//    public void verifybeautifulhomes() throws InterruptedException {
//        test = extent.createTest("Beautiful Homes Module");
//        home_page_asain_paints_pages home = new home_page_asain_paints_pages(driver);
//        test.log(Status.INFO, "Launching the Asian Paints website.");
//        home.handleCookiesAndPopups();
//        try {
//            home.beautifulhomes();
//            test.log(Status.INFO, "Clicking on the Beautiful Homes");
//            test.log(Status.PASS, "Navigated to Beautiful Homes module");
//        } catch (Exception e) {
//            String screenshotPath = Asain_paints_utilities.getScreenshotPath(driver, "verifybeautifulhomes");
//            test.log(Status.FAIL, "Failed to navigate Beautiful Homes: " + e.getMessage());
//            test.addScreenCaptureFromPath(screenshotPath);
//            Assert.fail();
//        }
//    }
//}
