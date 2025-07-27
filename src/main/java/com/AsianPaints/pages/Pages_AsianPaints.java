package com.AsianPaints.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Pages_AsianPaints {
    WebDriver driver;

    // Constructor
    public Pages_AsianPaints(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private By prodMenu = By.id("box-2");
    private By interiorWallPaints = By.xpath("//*[@id='box-2']/ul/li[1]/ul/li[1]/a");
    private By freshPaintingOption = By.xpath("//img[@alt='Fresh Painting']");
    private By interiorOption = By.xpath("//img[@alt='Interior']");
    private By areaInput = By.id("area");
    private By calculateBtn = By.id("calculate-now");

    private By FindContractor = By.xpath("/html/body/div[3]/div/div[1]/div/div/header/div[2]/div[4]");
    private By EnterPin = By.xpath("/html/body/div[3]/div[1]/div[2]/div/div[6]/div/div/div/form/div/div[2]/div/input");
    private By EnterButton = By.xpath("/html/body/div[3]/div[1]/div[2]/div/div[6]/div/div/div/form/div/div[2]/button");
    private By AllProductClk = By.xpath("//*[@id=\"box-2\"]/ul/li[3]/ul/li[1]/a");
    private By RoofClick = By.xpath("//img[@alt='Terrace/Roof']");
    private By FreshClick = By.id("fresh-painting-wp");
    private By WaterArea = By.id("area");
    private By WaterSubmit = By.id("calculate-now");

    // Actions
    public void hoverOverProducts() {
        WebElement prod = driver.findElement(prodMenu);
        Actions act = new Actions(driver);
        act.moveToElement(prod).perform();
    }
    public void clickInteriorWallPaints() {
        driver.findElement(interiorWallPaints).click();
    }
    public void clickFreshPainting() {
        driver.findElement(freshPaintingOption).click();
    }
    public void clickInterior() {
        driver.findElement(interiorOption).click();
    }
    public void enterArea(String sqft) {
        driver.findElement(areaInput).sendKeys(sqft);
    }
    public void clickCalculateNow() {
        driver.findElement(calculateBtn).click();
    }
    public void clickWaterProofingAllProduct() {
        driver.findElement(AllProductClk).click();
    }
    public void clickRoof() {
        driver.findElement(RoofClick).click();
    }
    public void clickFreshCons() {
        driver.findElement(FreshClick).click();
    }
    public void EnterWaterArea(String val) {
        driver.findElement(WaterArea).sendKeys(val);
    }
    public void Water_Sumbit() {
        driver.findElement(WaterSubmit).click();
    }
    public void clickFindContractor() {
        driver.findElement(FindContractor).click();
    }
    public void EnterPinCode(String pin) {
        driver.findElement(EnterPin).sendKeys(pin);
    }
    public void clickEnterButton() {
        driver.findElement(EnterButton).click();
    }
}
