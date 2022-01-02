/**
 * 
 */
package com.condos.pages;


import org.openqa.selenium.By;
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

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * @author Swathi
 *
 *This class has all web element and methods related to Condos.ca home page
 */
public class HomePage {
	
	WebDriver driver;
	public List<Element> elementList;
	
	//Web elements 
	@FindBy(id = "search-input")
	WebElement locationSearchTextBox;
	
	@FindBy(className = "react-autosuggest__input" )
	WebElement locationSearchEntry;
	
	@FindBy(id = "listRow")
	WebElement priceValue;
	
	@FindBy(xpath = "//div[@id=\"listRow\"]/div[5]")
	WebElement fifthProperty;

	@FindBy(xpath = "//article" )
	WebElement roomDetail;
	
	@FindBy(xpath = "//*/table/tbody/tr" )
	WebElement roomDetailTable;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		
	}
	
	//Methods to search and select the location
	public void searchLocation(String location) {
	  
		locationSearchTextBox.click();
		try {
			Thread.sleep(200);
			locationSearchEntry.sendKeys(location);
			Thread.sleep(200);
			
			for (int i = 0; i < 4; ++i) {
                Document doc = Jsoup.parse(this.driver.findElement(By.id("react-autowhatever-1-section-0-item-" + i)).getAttribute("innerHTML"));
                if (doc.getElementsByAttributeValueContaining("class", "ResultItem").text().equalsIgnoreCase("Toronto")) {
                    this.driver.findElement(By.id("react-autowhatever-1-section-0-item-" + i)).click();
                    Thread.sleep(200);
                    break;
                }
                
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
		
		// Method to  get the list of properties on that page
	    public List<String> getListOfProperties() {
	        Document doc = Jsoup.parse(priceValue.getAttribute("innerHTML"));
	        this.elementList = doc.getElementsByAttributeValueContaining("class", "AskingPrice");
	        List<String> priceList = new ArrayList();
	        this.elementList.forEach((element) -> {
	            if (element.text().contains("$")) {
	                priceList.add(element.text());
	            } else {
	                priceList.add(element.getElementsByAttributeValueContaining("class", "BlurCont").text());
	            }

	        });
	        return priceList;
	    }	
		
	    // Method to print all the price values in sorted order (descending order) on the console from the first page
	    public void sortAndPrint(List<String> priceList) {
	        priceList.sort(Comparator.comparingInt(a -> Integer.parseInt(a.replace("$", "").replaceAll(",", ""))));
	        priceList.forEach(System.out::println);
	    }	
		

	 // Method to select the 5th Property on that list (it's changing every minute, so it's dynamic) and go to details
	    public void select5thProperty() throws InterruptedException {
	    	fifthProperty.click();
	        Thread.sleep(3000);
	    }
	    
	 // Method to get the  property details page, get all rooms info (name, size and features), store them on a json file
	    //  and set the filename with the property address(street name);
	    public void getRoomDetails() throws InterruptedException, FileNotFoundException {
	        JSONArray jsonArray = new JSONArray();
	        //Switch to current selected tab's content.
	        ArrayList<String> newTb = new ArrayList<>(driver.getWindowHandles());
	        this.driver.switchTo().window(newTb.get(1));

	        // Get the HTML parser
	        elementList.clear();
	        Document doc = Jsoup.parse(roomDetail.getAttribute("innerHTML"));
	        this.driver.findElement(By.className(doc.getElementsByAttributeValueContaining("class", "ShowMore").attr("class").split(" ")[0])).click();
	        Thread.sleep(2000);
	        doc = Jsoup.parse(roomDetail.getAttribute("innerHTML"));
	        doc.selectXpath("//*/table/tbody/tr").forEach(element -> {
	            JSONObject jsonObject = new JSONObject();
	            jsonObject.put("name", element.selectXpath("//tr/td[1]/div").text());
	            jsonObject.put("size", element.selectXpath("//tr/td[2]/div/div/span").text());
	            jsonObject.put("features", element.selectXpath("//tr/td[3]/div").text());
	            jsonArray.put(jsonObject);
	        });
	        PrintWriter printWriter = new PrintWriter(this.driver.getTitle().split("[|]")[0].trim()+".json");

	        printWriter.write(jsonArray.toString());
	        printWriter.close();
	    }
	    
	 // close and quit
	    public void quitDriver() {
	        this.driver.close();
	        this.driver.quit();
	    }

		
	}

	
	
	


