/**
 * 
 */
package com.condos.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

/**
 * @author Swathi
 *
 *This class has all web element and methods related to Condos.ca home page
 */
public class HomePage {
	
	WebDriver driver;
	
	
	@FindBy(tagName = "input")
	WebElement locationSearchTextBox;
	
	@FindBy(xpath = "/html/body/div/div/main/div[1]/div[2]/div/div[1]/div/div/div/div/div[2]/div/div[2]/div/div/div[1]/ul/li[1]/div/div[1]/div[1]" )
	WebElement locationFromDropDown;

	
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		
	}
	
	public void searchLocation(String location) {
		//driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.MILLISECONDS);   
		locationSearchTextBox.click();
		locationSearchTextBox.sendKeys(location);
		//driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.MILLISECONDS); 
		Actions action = new Actions(driver);
		action.click(locationFromDropDown).perform();
		
		
		locationFromDropDown.click();
		
		
//		WebDriverWait wait = new WebDriverWait(driver, 20);
//		wait.until(ExpectedConditions.elementToBeClickable(locationSearchTextBox));
//	
//		locationFromDropDown.click();
//		locationFromDropDown.clear();
//		locationFromDropDown.sendKeys(location);
//		locationFromDropDown.sendKeys(Keys.ENTER);
		
//		Actions action = new Actions(driver);
//		locationSearchTextBox.sendKeys(location);
//		locationSearchTextBox.sendKeys(Keys.ENTER);
		
		
//		locationSearchTextBox.click();
//		locationSearchTextBox.sendKeys(location);
//		
//		JavascriptExecutor jExecutor =  (JavascriptExecutor)driver;
//		jExecutor.executeScript("$(arguments[0]).change();", locationFromDropDown);
//		
		//locationFromDropDown.click();
		//locationFromDropDown.sendKeys(location);
		
	
		
		
		//locationFromDropDown.click();
		
	}
	
	

}
