package com.condos.testcases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.condos.pages.HomePage;

public class testcases {
	WebDriver driver;
	String location;
	
	@BeforeTest
	public void initialSetUp() {
		
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\Swathi\\Desktop\\Swathi\\Automation\\chromedriver_win32\\chromedriver.exe");
		//driver = new ChromeDriver();
		
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Swathi\\Desktop\\Swathi\\Automation\\geckodriver-v0.30.0-win64\\geckodriver.exe");
//		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
//		capabilities.setCapability("marionette",true);
		driver= new FirefoxDriver();
		driver.manage().window().maximize();
		Properties prop = new Properties();
		try {
			InputStream input =  new FileInputStream("C:\\Users\\Swathi\\eclipse-workspace\\UI_selenium\\src\\test\\java\\com\\condos\\propertyFile\\projectProperties.properties");
			prop.load(input);
			driver.get(prop.getProperty("url"));
			location = prop.getProperty("location");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	@Test(description = "Printing of price in sorted order ")
	public void printingPrice() {
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		HomePage home_page =  PageFactory.initElements(driver,HomePage.class);
//		home_page.searchLocation(location);
		//driver.navigate().refresh();
		driver.findElement(By.id("search-input")).sendKeys("Toronto");
//		WebDriverWait wait = new WebDriverWait(driver, 30);
//		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/div/main/div[1]/div[2]/div/div[1]/div/div/div/div/div[2]/div/div[2]/div/div/div[1]/ul/li[1]/div/div[1]/div[1]"))));
//		driver.findElement(By.xpath("/html/body/div[1]/div/main/div[1]/div[2]/div/div[1]/div/div/div/div/div[2]/div/div[2]/div/div/div[1]/ul/li[1]/div/div[1]/div[1]")).click();
//		driver.quit();
	}

}
