package com.flink.tests;

import com.flink.pages.CartPage;
import com.flink.pages.HomePage;
import com.flink.pages.PaymentsPage;
import com.flink.pages.ProductsPage;
import com.flink.util.RetryAnalyzer;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class EndtoEnd_TC1 extends BaseTest {
	String testCaseName="EndtoEnd_TC1";

	@Test(retryAnalyzer = RetryAnalyzer.class)
	@Parameters({"browser"})
	public void endtoEnd_TC1(String browser) throws Exception {
		test = extent.startTest(testCaseName+"_"+browser+ " - Iteration "+iterator, "Starting the "+testCaseName+" on browser -  "+browser+ " - Iteration "+iterator);
		System.out.println(testCaseName);

		launchApp(browser);

		HomePage home = new HomePage(driver, test, prop);
		ProductsPage products = new ProductsPage(driver, test, prop);
		CartPage cart = new CartPage(driver, test, prop);
		PaymentsPage pay = new PaymentsPage(driver, test, prop);

		home.getCurrentTemperature("currentTemp_id");
		//Logging to extent reports
		test.log(LogStatus.INFO, "Current Temperature: "+home.getCurrentTemperature("currentTemp_id"));
		try{
			String productType = home.checkTempAndShopProduct();
			//Logging to extent reports
			test.log(LogStatus.INFO, "Shop for the product type: "+ productType);

			//Add lower price items to cart
			if(productType.equals("moisturizers")){
				products.shopMoisturizers();
			}else if (productType.equals("sunscreens")){
				products.shopSunscreens();
			}
			//Logging to extent reports
			test.log(LogStatus.INFO, "Added minimum priced items to cart.");
			test.log(LogStatus.INFO, "Expected cart list with Product names: "+ products.expectedProductList);
			test.log(LogStatus.INFO, "Expected cart list with Price details: "+ products.expectedPriceList);

			// Go to Cart by clicking on Cart button
			home.click("cartButton_id");
			//Logging to extent reports
			test.log(LogStatus.INFO, "Clicked on cart button on top right");

			cart.getCartList("itemList_xpath");
			//Logging to extent reports
			test.log(LogStatus.INFO, "Actual cart list with Product names: "+ cart.actualProductList);
			test.log(LogStatus.INFO, "Actual cart list with Price details: "+ cart.actualPriceList);
			test.log(LogStatus.INFO, "Verifying the shopping cart");

			//Verify shopping cart with the product names
			Assert.assertTrue(products.expectedProductList.equals(cart.actualProductList),"The products name are not same");

			//Verify shopping cart with the product prices
			Assert.assertTrue(products.expectedPriceList.equals(cart.actualPriceList),"The products prices are not same");
			test.log(LogStatus.PASS, "The expected and actual cart list is verified and as expected");

			//Proceed for payment
			home.click("payWithCard_css");
			test.log(LogStatus.INFO, "Clicked on Pay with card");
		}catch (Exception e){
			e.printStackTrace();
			test.log(LogStatus.FAIL, "The expected and actual cart list is verified and as expected");
		}
		pay.checkoutToPay();
	}
}