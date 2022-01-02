package com.condos.testcases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
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
	
	//Method to initialize and set browser
	@BeforeTest
	public void initialSetUp() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Swathi\\Desktop\\Swathi\\Automation\\chromedriver_win32\\chromedriver.exe");
		driver =  new ChromeDriver();
		driver.manage().window().maximize();
		Properties prop = new Properties();
		try {
			InputStream input =  new FileInputStream("C:\\Users\\Swathi\\eclipse-workspace\\UI_selenium\\src\\test\\java\\com\\condos\\propertyFile\\projectProperties.properties");
			prop.load(input);
			driver.get(prop.getProperty("url"));
			Thread.sleep(3000);
			location = prop.getProperty("location");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Test(description = "Printing of price in sorted order and getting 5th property details in json ")
	public void printingPriceAndFetchingPropertyDetail() throws InterruptedException, FileNotFoundException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		HomePage home_page =  PageFactory.initElements(driver,HomePage.class);
		home_page.searchLocation(location);
		List<String> priceList = home_page.getListOfProperties();
		home_page.sortAndPrint(priceList);
		home_page.select5thProperty();
		home_page.getRoomDetails();
		home_page.quitDriver();
		System.out.println("json file is creatd on this workspace location");
	}


}
