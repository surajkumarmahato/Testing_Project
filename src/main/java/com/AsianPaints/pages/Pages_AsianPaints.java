package com.AsianPaints.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Pages_AsianPaints 
{
    WebDriver driver;

    // Constructor
    public Pages_AsianPaints(WebDriver driver)
    {
        this.driver = driver;
    }

    // Locators
    By prodMenu = By.id("box-2");
    By interiorWallPaints = By.xpath("//*[@id='box-2']/ul/li[1]/ul/li[1]/a");
    By freshPaintingOption = By.xpath("//img[@alt='Fresh Painting']");
    By interiorOption = By.xpath("//img[@alt='Interior']");
    By areaInput = By.id("area");
    By calculateBtn = By.id("calculate-now");
    By resultCard = By.className("cardHeading");
    By FindContractor = By.xpath("/html/body/div[3]/div/div[1]/div/div/header/div[2]/div[4]");
    By EnterPin = By.xpath("/html/body/div[3]/div[1]/div[2]/div/div[6]/div/div/div/form/div/div[2]/div/input");
    By EnterButton = By.xpath("/html/body/div[3]/div[1]/div[2]/div/div[6]/div/div/div/form/div/div[2]/button");
    By AllProductClk = By.xpath("//*[@id=\"box-2\"]/ul/li[3]/ul/li[1]/a");
    By RoofClick = By.xpath("//img[@alt='Terrace/Roof']");
    By FreshClick = By.id("fresh-painting-wp");
    By WaterArea = By.id("area");
    By WaterSubmit = By.id("calculate-now");
//    By PaintOutput = By.className("main-card-1 result-card ");

    // Test Case 1
    
    public void hoverOverProducts()
    {
        WebElement prod = driver.findElement(prodMenu);
        Actions act = new Actions(driver);
        act.moveToElement(prod).perform();
    }
    
    public void clickInteriorWallPaints()
    {
        driver.findElement(interiorWallPaints).click();
    }
    
    public void clickFreshPainting()
    {
        driver.findElement(freshPaintingOption).click();
    }

    public void clickInterior()
    {
        driver.findElement(interiorOption).click();
    }

    public void enterArea(String sqft)
    {
        driver.findElement(areaInput).sendKeys(sqft);
    }

    public void clickCalculateNow()
    {
        driver.findElement(calculateBtn).click();
    }
    
//    public boolean CheckPaintResult()
//    {
//    	WebElement result = driver.findElement(PaintOutput);
//    	if(result.isDisplayed())
//    	{
//    		return true;
//    	}
//    	else
//    	{
//    		return false;
//    	}
//    }

    // Test Case 2
    
    public void clickWaterProofingAllProduct()
    {
        driver.findElement(AllProductClk).click();
    }
    
    public void clickRoof()
    {
        driver.findElement(RoofClick).click();
    }
    
    public void clickFreshCons()
    {
        driver.findElement(FreshClick).click();
    }
    
    public void EnterWaterArea(String WaterAreaVal)
    {
        driver.findElement(WaterArea).sendKeys(WaterAreaVal);
    }
    
    public void Water_Sumbit()
    {
        driver.findElement(WaterSubmit).click();
    }

    // Test Case 3
    
    // Test Case 4
    
    public void clickFindContractor()
    {
        driver.findElement(FindContractor).click();
    }
    
    public void EnterPinCode(String pincode)
    {
        driver.findElement(EnterPin).sendKeys(pincode);
    }
    
    public void clickEnterButton()
    {
        driver.findElement(EnterButton).click();
    }
    
}