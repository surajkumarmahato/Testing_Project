//
//package com.tests;
//
//import com.base.*;
//import com.pages.LoginPage;
//import com.pages.ProductPage;
//import com.pages.PromoCode;
//import com.pages.SignOut;
//import com.utilities.*;
//import com.pages.AddressPage;
//import com.aventstack.extentreports.ExtentTest;
//import org.openqa.selenium.TimeoutException;
//import java.io.IOException;
//import com.utilities.Asain_paints_utilities;
//import org.openqa.selenium.By;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//public class LoginToCartAddressFlowTest extends Asain_paints_base {
//
//    LoginPage loginPage;
//    ProductPage productPage;
//    AddressPage addressPage;
//
//    @Test(priority = 1)
//    public void testLogin() throws InterruptedException {
//        ExtentTest test = extent.createTest("Asian Paints - Login Test");
//
//        test.info("Navigated to Asian Paints website");
//        Asain_paints_utilities.handlePopups(driver);
//
//        loginPage = new LoginPage(driver, wait);
//        loginPage.login(Asain_paints_utilities.getProperty("phone"));
//
//        test.info("Clicked on profile icon");
//        test.info("Entered phone number");
//        test.info("Entered OTP manually");
//
//        // ðŸ”½ Add wait for profile icon after login
//        try {
//            wait.until(ExpectedConditions.visibilityOfElementLocated(
//                By.xpath("/html/body/div[3]/div/div[1]/div/div/header/div[2]/div[5]")));
//            test.pass("Login successful");
//        } catch (TimeoutException e) {
//            test.fail("Login failed: Profile icon not visible after login");
//            throw e;
//        }
//    }
//
//    
//    @Test(priority = 2)
//    public void testAddToCart() throws InterruptedException {
//        ExtentTest test = extent.createTest("Asian Paints - Add to Cart Test");
//
//        productPage = new ProductPage(driver, wait);
//        productPage.addToCart();
//        test.info("Go to shop");
//        test.info("Click on DIY tools");
//        test.info("Select the item");
//        test.info("Click on Add to Cart");
//        test.pass("Product added to cart successfully");
//    }
//    
//    @Test(priority = 3)
//    public void testAddAddress() {
//        ExtentTest test = extent.createTest("Asian Paints - Add Address Test");
//
//        try {
//            addressPage = new AddressPage(driver, wait);
//            addressPage.addAddress();
//            
//            test.info("Go to cart");
//            test.info("Click on checkout");
//            test.info("Select Address");
//            test.info("Fill the address");
//            
//            test.pass("Address added successfully");
//
//        } catch (Exception e) {
//            // Capture failure details in report
//            test.fail("Failed to add address: " + e.getMessage());
//            e.printStackTrace();
//           
//        }
//    }
//    
//    @Test(priority = 4)
//    public void testPromoCodeInputField() {
//        ExtentTest test = extent.createTest("Asian Paints - Promo Code Text Entry Test");
//
//        PromoCode cartPage = new PromoCode(driver, wait);
//
//        // Step 1: Navigate to Checkout
//        test.info("Clicking cart and proceeding to checkout...");
//        cartPage.clickCartAndCheckout();
//
//        // Step 2: Verify Promo Code Section
//        test.info("Checking if promo code section is visible");
//
//        if (cartPage.isPromoSectionPresent()) {
//            test.pass("Promo code heading and input section found");
//
//            String promoCode = "TESTCODE123";
//            boolean canEnter = cartPage.canEnterPromoText(promoCode);
//
//            Assert.assertTrue(canEnter, "Promo code input field is not accepting text");
//            test.pass("Promo code entered successfully: " + promoCode);
//        } else {
//            test.fail("Promo code section is not present on the page");
//            Assert.fail("Promo code section missing");
//        }
//    }
//
//    
//    @Test(priority = 5, alwaysRun = true)
//    public void testSignOut() {
//        ExtentTest test = extent.createTest("Asian Paints - Sign Out Test");
//
//        try {
//            SignOut profilePage = new SignOut(driver, wait);
//            profilePage.signOut();
//            test.pass("âœ… User signed out successfully.");
//        } catch (Exception e) {
//            test.fail("â�Œ Sign out failed. Error: " + e.getMessage());
//            
//            
//        }
//    }
//
//
//}
//
//
//
