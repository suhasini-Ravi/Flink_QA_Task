package com.flink.pages;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BasePage {
	public static WebDriver driver;
	public ExtentTest test;
	public Properties prop;

	public BasePage(WebDriver driver,ExtentTest test, Properties prop) {
		this.driver = driver;
		this.test=test;
		this.prop=prop;
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public void click(String eleLocator) {
		getWebElement(eleLocator).click();
	}

	public void type(String eleLocator, String data) {
		getWebElement(eleLocator).sendKeys(prop.getProperty(data));
	}

	public void clear(String eleLocator) {
		getWebElement(eleLocator).clear();
	}

	public void scrollToEnd() throws Exception {
		((JavascriptExecutor) driver)
		.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void scrollToElement(String eleLocator) throws Exception {
		WebElement element = getWebElement(eleLocator);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500); 
	}

	public String getText(String eleLocator) throws Exception {

		try {
			String text = getWebElement(eleLocator).getText();
			return text;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}		
	}

	public boolean verifyText(String eleLocator, String expectedText) {
		Boolean text = null;
		if(getWebElement(eleLocator).getText().equals(expectedText)) {
			text = true;
		}else {
			text = false;
		}
		return text;
	}

	public List<String> getAllDropDownValues(String eleLocator){

		List<String> values = new ArrayList<String>();
		List<WebElement> allDDElements = new Select(getWebElement(eleLocator)).getOptions();

		for(WebElement menuitem:allDDElements) {
			values.add(menuitem.getText());
		}
		return values;
	}

	public void selectDDByVisibleText(String eleLocator, String text){
		Select dd = new Select(getWebElement(eleLocator));
		dd.selectByVisibleText(prop.getProperty(text));
	}

	public int getActualProductListSize(String eleLocator){
		List<WebElement> productsList = driver.findElements(By.xpath(prop.getProperty(eleLocator)));
		int size = productsList.size();
		System.out.println("Actual product size: "+size);
		return size;
	}

	public int getIndexOfList(List list, int value){
		return list.indexOf(value);
	}

	public void clickWebTableData(String eleLocator, String product) throws Exception{
		List <WebElement> productsList = driver.findElements(By.xpath(prop.getProperty(eleLocator)));
		try {
			for(WebElement e : productsList){
				System.out.println("Actual Product: "+e.getText());
				if(e.getText().contains(prop.getProperty(product))) {
					waitForElementClickable(eleLocator);
					e.click();
					break;
				}
			}
		}catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed the action"+ e.getMessage());
			e.printStackTrace();
			Assert.fail("Failed the action"+ e.getMessage());
		}
	}

	public void waitForElementClickable(String eleLocator) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		if(eleLocator.endsWith("_xpath")) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty(eleLocator))));
		}else if(eleLocator.endsWith("_css")) {
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(prop.getProperty(eleLocator))));
		}else if(eleLocator.endsWith("_id")) {
			wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty(eleLocator))));
		}else if(eleLocator.endsWith("_name")) {
			wait.until(ExpectedConditions.elementToBeClickable(By.name(prop.getProperty(eleLocator))));
		}else if(eleLocator.endsWith("_linktext")) {
			wait.until(ExpectedConditions.elementToBeClickable(By.linkText(prop.getProperty(eleLocator))));
		}

	}

	public WebElement getWebElement(String eleLocator) {
		WebElement element = null;
		if(eleLocator.endsWith("_xpath")) {
			element = driver.findElement(By.xpath(prop.getProperty(eleLocator)));
		}else if(eleLocator.endsWith("_css")) {
			element = driver.findElement(By.cssSelector(prop.getProperty(eleLocator)));
		}else if(eleLocator.endsWith("id")) {
			element = driver.findElement(By.id(prop.getProperty(eleLocator)));
		}else if(eleLocator.endsWith("name")) {
			element = driver.findElement(By.name(prop.getProperty(eleLocator)));
		}else if(eleLocator.endsWith("linktext")) {
			element = driver.findElement(By.linkText(prop.getProperty(eleLocator)));
		}
		return element;
	}

	public List<String> getTableData(String eleLocator) {
		List<String> listOfItems = new ArrayList<String>();
		WebElement cartTable = getWebElement(eleLocator);
		List<WebElement> rowsTable = cartTable.findElements(By.tagName("tr"));
		int rowsCount = rowsTable.size();
		for (int row = 0; row < rowsCount; row++) {
			//To locate columns(cells) of that specific row.
			List<WebElement> ColumnsRow = rowsTable.get(row).findElements(By.tagName("td"));
			//To calculate no of columns (cells). In that specific row.
			int columnsCount = ColumnsRow.size();
			//Loop will execute till the last cell of that specific row.
			for (int column = 0; column < columnsCount; column++) {
				// To retrieve text from that specific cell.
				String cellVal = ColumnsRow.get(column).getText();
				listOfItems.add(cellVal);
			}
		}
		return listOfItems;
	}

	public boolean checkListAreEqual(List l1, List l2){
		boolean val;
		if(l1.equals(l2)){
			val = true;
		}else
			val = false;
		return val;
	}
}
