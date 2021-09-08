package com.flink.pages;

import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class CartPage extends BasePage{

	public List<Integer> actualPriceList = new LinkedList<Integer>();
	public List<String> actualProductList = new LinkedList<String>();

	public CartPage(WebDriver driver, ExtentTest test, Properties prop) {
		super(driver, test, prop);
	}

	public void getCartList(String eleLocator) throws Exception {
		List<String> productList = new ArrayList<String>();
		List<Integer> priceList = new ArrayList<Integer>();
		List<String> temp = new ArrayList<String>(getTableData(eleLocator));

			for(int i = 0; i < temp.size(); i++) {
				if(temp.get(i).length()>3){
					actualProductList.add(temp.get(i));
				}else
					actualPriceList.add(Integer.parseInt(temp.get(i)));
			}
	}
}
