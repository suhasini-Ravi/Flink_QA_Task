package com.flink.pages;

import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class PaymentsPage extends BasePage{

	public PaymentsPage(WebDriver driver, ExtentTest test, Properties prop) {
		super(driver, test, prop);
	}

	public void checkoutToPay() throws Exception {

		driver.switchTo().frame("stripe_checkout_app");
		type("payment_email_id", "email");
		type("payment_cardNumber_id", "cardNumber");
		type("payment_card_exp_id","card_exp");
		type("payment_cvv_id","cvc");
		click("pay_button_css");
	}
}
