package com.flink.pages;

import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

public class ProductsPage extends BasePage{

	public List<Integer> expectedPriceList = new LinkedList<Integer>();
	public List<String> expectedProductList = new LinkedList<String>();
	int currentTemperature;

	public ProductsPage(WebDriver driver, ExtentTest test, Properties prop) {
		super(driver, test, prop);
	}

	public void shopMoisturizers() throws Exception {
		int minPriceAloe = 0;
		int minPriceAlmonds = 0;
		int indexAloe = 0;
		int indexAlmonds = 0;
		List<WebElement> products = new LinkedList<WebElement>();
		products = driver.findElements(By.xpath("//*[contains(@class,'ext-center col-4')]"));

		List<WebElement> almonds = new LinkedList<WebElement>();
		List<WebElement> aloe = new LinkedList<WebElement>();
		List<String> productAlmonds = new ArrayList<String>();
		List<String> productAloe = new ArrayList<String>();
		List<Integer> priceAlmonds = new ArrayList<Integer>();
		List<Integer> priceAloe = new ArrayList<Integer>();
		List<Integer> sortedPriceAlmonds = new ArrayList<Integer>();
		List<Integer> sortedPriceAloe = new ArrayList<Integer>();

		for(WebElement product:products) {
			if (product.getText().contains("Aloe" ) || product.getText().contains("aloe" )){
				aloe.add(product);
			}else if(product.getText().contains("Almond" ) || product.getText().contains("almond" )){
				almonds.add(product);
			}
		}
		for(WebElement alo:aloe) {
			String actProduct = alo.getText().substring(0, alo.getText().indexOf("\n")).trim();
			String price = alo.getText().substring(alo.getText().length()-7, alo.getText().length()-3).trim();
			productAloe.add(actProduct);
			priceAloe.add(Integer.parseInt(price));
		}
		sortedPriceAloe = new ArrayList<Integer>(priceAloe);
		Collections.sort(priceAloe);
		for(int i = 0; i < priceAloe.size(); i++) {
			minPriceAloe = priceAloe.get(i);
			expectedPriceList.add(priceAloe.get(i));
			driver.findElement(By.xpath("//*[contains(text(),"+priceAloe.get(i)+")]//following-sibling::button")).click();
			break;
		}
		indexAloe = getIndexOfList(sortedPriceAloe, minPriceAloe);
		String str = productAloe.get(indexAloe);
		expectedProductList.add(str);
		for(WebElement almond:almonds) {
			String actProduct = almond.getText().substring(0, almond.getText().indexOf("\n")).trim();
			String price = almond.getText().substring(almond.getText().length()-7, almond.getText().length()-3).trim();
			productAlmonds.add(actProduct);
			priceAlmonds.add(Integer.parseInt(price));
		}
		sortedPriceAlmonds = new ArrayList<Integer>(priceAlmonds);
		Collections.sort(priceAlmonds);
		for(int i = 0; i < priceAlmonds.size(); i++) {
			minPriceAlmonds = priceAlmonds.get(i);
			expectedPriceList.add(priceAlmonds.get(i));
			driver.findElement(By.xpath("//*[contains(text(),"+priceAlmonds.get(i)+")]//following-sibling::button")).click();
			break;
		}
		indexAlmonds = getIndexOfList(sortedPriceAlmonds, minPriceAlmonds);
		String str1 = productAlmonds.get(indexAlmonds);
		expectedProductList.add(str1);
	}

	public void shopSunscreens() throws Exception {
		int minPriceSPF50 = 0;
		int minPriceSPF30 = 0;
		int indexSPF50 = 0;
		int indexSPF30 = 0;
		List<WebElement> products = new LinkedList<WebElement>();
		products = driver.findElements(By.xpath("//*[contains(@class,'ext-center col-4')]"));

		List<WebElement> spf50 = new LinkedList<WebElement>();
		List<WebElement> spf30 = new LinkedList<WebElement>();
		List<String> productSPF50 = new ArrayList<String>();
		List<String> productSPF30 = new ArrayList<String>();
		List<Integer> priceSPF50 = new ArrayList<Integer>();
		List<Integer> priceSPF30 = new ArrayList<Integer>();
		List<Integer> sortedPriceSPF50 = new ArrayList<Integer>();
		List<Integer> sortedPriceSPF30 = new ArrayList<Integer>();

		for(WebElement product:products) {
			if (product.getText().contains("-50" )){
				spf50.add(product);
			}else if(product.getText().contains("-30" )){
				spf30.add(product);
			}
		}
		for(WebElement spf:spf50) {
			String actProduct = spf.getText().substring(0, spf.getText().indexOf("\n")).trim();
			String price = spf.getText().substring(spf.getText().length()-7, spf.getText().length()-3).trim();
			productSPF50.add(actProduct);
			priceSPF50.add(Integer.parseInt(price));
		}
		for(WebElement spf:spf30) {
			String actProduct = spf.getText().substring(0, spf.getText().indexOf("\n")).trim();
			String price = spf.getText().substring(spf.getText().length()-7, spf.getText().length()-3).trim();
			productSPF30.add(actProduct);
			priceSPF30.add(Integer.parseInt(price));
		}
		sortedPriceSPF50 = new ArrayList<Integer>(priceSPF50);
		Collections.sort(priceSPF50);
		for(int i = 0; i < priceSPF50.size(); i++) {
			minPriceSPF50 = priceSPF50.get(i);
			expectedPriceList.add(priceSPF50.get(i));
			driver.findElement(By.xpath("//*[contains(text(),"+priceSPF50.get(i)+")]//following-sibling::button")).click();
			break;
		}
		indexSPF50 = getIndexOfList(sortedPriceSPF50, minPriceSPF50);
		String str = productSPF50.get(indexSPF50);
		expectedProductList.add(str);
		sortedPriceSPF30 = new ArrayList<Integer>(priceSPF30);
		Collections.sort(priceSPF30);
		for(int i = 0; i < priceSPF30.size(); i++) {
			minPriceSPF30 = priceSPF30.get(i);
			expectedPriceList.add(priceSPF30.get(i));
			driver.findElement(By.xpath("//*[contains(text(),"+priceSPF30.get(i)+")]//following-sibling::button")).click();
			break;
		}
		indexSPF30 = getIndexOfList(sortedPriceSPF30, minPriceSPF30);
		String str1 = productSPF30.get(indexSPF30);
		expectedProductList.add(str1);
	}
}
