package com.AsianPaints.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Pages_AsianPaints {
    WebDriver driver;
    WebDriverWait wait;

    public Pages_AsianPaints(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Locators...
    private By prodMenu = By.id("box-2");
    private By moreMenu = By.xpath("/html/body/div[3]/div[1]/div[1]/div/div/header/div[3]/nav/ul[2]/li[6]/a");
    private By interiorWallPaints = By.xpath("//*[@id='box-2']/ul/li[1]/ul/li[1]/a");
    private By freshPaintingOption = By.xpath("//img[@alt='Fresh Painting']");
    private By interiorOption = By.xpath("//img[@alt='Interior']");
    private By areaInput = By.id("area");
    private By calculateBtn = By.id("calculate-now");
    private By FindContractor = By.xpath("/html/body/div[3]/div[1]/div[1]/div/div/header/div[3]/nav/ul[2]/li[6]/ul/li/ul/li[1]/a");
    private By EnterPin = By.xpath("/html/body/div[3]/div[1]/div[2]/div/div[6]/div/div/div/form/div/div[2]/div/input");
    private By EnterButton = By.xpath("/html/body/div[3]/div[1]/div[2]/div/div[6]/div/div/div/form/div/div[2]/button");
    private By AllProductClk = By.xpath("//*[@id=\"box-2\"]/ul/li[3]/ul/li[1]/a");
    private By RoofClick = By.xpath("//img[@alt='Terrace/Roof']");
    private By FreshClick = By.id("fresh-painting-wp");
    private By WaterArea = By.id("area");
    private By WaterSubmit = By.id("calculate-now");
    private By profileButton = By.xpath("/html/body/div[3]/div/div[1]/div/div/header/div[2]/div[5]/div[1]");
    private By signOut = By.xpath("/html/body/div[3]/div/div[1]/div/div/header/div[2]/div[5]/div[2]/div[2]");

    // Login-specific locators:
    private By loginIcon = By.xpath("/html/body/div[3]/div/div[1]/div/div/header/div[2]/div[5]");
    private By phoneInput = By.id("phoneInput");
    private By otpButton = By.xpath("//*[@id='gigya-otp-send-code-form']/div[4]/div[1]");
    private By nameInput = By.xpath("/html/body/div[3]/div/div/div[3]/div[2]/div/form/div[1]/div[2]/input");          
    private By emailInput = By.xpath("/html/body/div[3]/div/div/div[3]/div[2]/div/form/div[1]/div[3]/input");  
    private By pincodeInput = By.xpath("/html/body/div[3]/div/div/div[3]/div[2]/div/form/div[1]/div[4]/input");
    private By continueButton = By.xpath("/html/body/div[3]/div/div/div[3]/div[2]/div/form/div[3]/div[1]/input");    

    // Actions (unchanged)
    public void hoverOverProducts() {
        WebElement prod = driver.findElement(prodMenu);
        Actions act = new Actions(driver);
        act.moveToElement(prod).perform();
    }
    public void hoverOverMore() {
        WebElement more = driver.findElement(moreMenu);
        Actions act = new Actions(driver);
        act.moveToElement(more).perform();
    }
    
    public void clickInteriorWallPaints() { driver.findElement(interiorWallPaints).click(); }
    public void clickFreshPainting() { driver.findElement(freshPaintingOption).click(); }
    public void clickInterior() { driver.findElement(interiorOption).click(); }
    public void enterArea(String sqft) { driver.findElement(areaInput).sendKeys(sqft); }
    public void clickCalculateNow() { driver.findElement(calculateBtn).click(); }
    public void clickWaterProofingAllProduct() { driver.findElement(AllProductClk).click(); }
    public void clickRoof() { driver.findElement(RoofClick).click(); }
    public void clickFreshCons() { driver.findElement(FreshClick).click(); }
    public void EnterWaterArea(String val) { driver.findElement(WaterArea).sendKeys(val); }
    public void Water_Sumbit() { driver.findElement(WaterSubmit).click(); }
    public void clickFindContractor() { driver.findElement(FindContractor).click(); }
    public void EnterPinCode(String pin) { driver.findElement(EnterPin).sendKeys(pin); }
    public void clickEnterButton() { driver.findElement(EnterButton).click(); }
    public void clickProfileButton() { driver.findElement(profileButton).click(); }
    public void clickSignoutButton() { driver.findElement(signOut).click(); }
    
    // Optionally, a direct clickLogin.
    public void clickLogin() { driver.findElement(loginIcon).click(); }

    // --- Login Logic (one method for all steps) ---
    public void login(String phoneNumber) {
        closeSplashPopupIfPresent();

        try {
            if (!isLoginPopupVisible()) {
                WebElement profileIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(loginIcon));
                profileIcon.click();
            }
            WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneInput));
            inputField.clear();
            inputField.sendKeys(phoneNumber);

            WebElement otpBtn = wait.until(ExpectedConditions.elementToBeClickable(otpButton));
            otpBtn.click();
            System.out.println("Please enter OTP manually within 30 seconds...");
            Thread.sleep(20000);
        } 
        catch (Exception e) {
            System.out.println("LOGIN ERROR: " + e.getMessage());
        }
    }
    
    public void fillNameAndEmail(String name,String pin) {
        try {
            WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput));
//          WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
            WebElement pincodeField = wait.until(ExpectedConditions.visibilityOfElementLocated(pincodeInput));

            nameField.clear();
            nameField.sendKeys(name);
//          emailField.clear();
//          emailField.sendKeys(email);
            Thread.sleep(20000);
            pincodeField.clear();
            pincodeField.sendKeys(pin);

            WebElement contBtn = wait.until(ExpectedConditions.elementToBeClickable(continueButton));
            contBtn.click();
            Thread.sleep(9000);
        } catch (Exception e) {
            System.out.println("NAME/EMAIL/PinCode FILL ERROR: " + e.getMessage());
        }
    }

    private boolean isLoginPopupVisible() {
        try {
            WebElement loginInput = wait.until(ExpectedConditions.presenceOfElementLocated(phoneInput));
            return loginInput.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    private void closeSplashPopupIfPresent() {
        try {
            WebElement splash = driver.findElement(By.id("splash-popup"));
            if (splash.isDisplayed()) {
                splash.findElement(By.className("splash-close-btn")).click();
                wait.until(ExpectedConditions.invisibilityOf(splash));
            }
        } catch (Exception ignored) {}
    }
}
