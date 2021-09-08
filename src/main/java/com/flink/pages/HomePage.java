package com.flink.pages;

import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Properties;

public class HomePage extends BasePage{

	@FindBy(xpath="//*[text()='Buy moisturizers']")
	private WebElement moisturizers;

	@FindBy(xpath="//*[text()='Buy sunscreens']")
	private WebElement sunscreens;

	int currentTemperature;

	public HomePage(WebDriver driver, ExtentTest test, Properties prop) {
		super(driver, test, prop);
	}

	public int getCurrentTemperature(String eleLocator) throws Exception {
		String text = getText(eleLocator);
		String[] val = text.split("\\s+");
		currentTemperature = Integer.parseInt(val[0]);
		System.out.println("currentTemperature: "+currentTemperature);
		return currentTemperature;
	}

	public String checkTempAndShopProduct() throws Exception {
		String product = null;
		if(currentTemperature<19){
			product = "moisturizers";
			moisturizers.click();
		}else if (currentTemperature>34){
			product = "sunscreens";
			sunscreens.click();
		}else
			System.out.println("Hu hu! good weather and no need to shop");
		return product;
	}
}
