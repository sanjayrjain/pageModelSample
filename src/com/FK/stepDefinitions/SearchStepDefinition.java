package com.FK.stepDefinitions;

import java.util.ArrayList;

import com.FK.base.ScriptHelper;
import com.FK.util.LoadConfigFile;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.FK.Listeners.ExtentReportListeners;
import com.FK.pages.HomePage;
import com.FK.pages.Search;
import com.FK.util.WebUIUtilty;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class SearchStepDefinition extends  ExtentReportListeners {
	HomePage landingPage;
	Search searchPage;
	private static WebDriver driver;
	String browser="Chrome";
	String email="2prodtest@FK.com";
	String password="Tester";
	String Keyword ="Chinos";
	String price, productID, productTitle=null;


	@Given("^guest/registered user is in home page for checkout case$")
	public void guest_registered_user_is_in_home_page_checkout_case()  {
		ExtentTest logInfo=null;
		try {
			test = extent.createTest(Feature.class, "Sanity Functionality");							
			test=test.createNode(Scenario.class, "Verify User is able to do a checkout as a guest");						
			logInfo=test.createNode(new GherkinKeyword("Given"), "guest_registered_user_is_in_home_page_for_checkout_case");
			driver = WebUIUtilty.launchBrowser(browser);
			driver.get(ScriptHelper.getURL());
			driver.findElement(By.xpath("//button[@aria-label='Close']")).click();
			landingPage = new HomePage(driver);
			
			logInfo.pass("User is in Home Page");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));			
			
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}
			
	}	

	
	@When("^the user searches a product and adds to cart$")
	public void the_user_searches_a_product_and_adds_to_cart() {
		ExtentTest logInfo=null;
		try {
			logInfo=test.createNode(new GherkinKeyword("When"), "the_user_searches_a_product_and_adds_to_cart");
		WebUIUtilty.timeOUt(10);
		driver.findElement(By.xpath("//span[text()=' Search ']")).click();
		Actions searchProduct = new Actions(driver);
		searchProduct.sendKeys(Keyword, Keys.ENTER).build().perform();
		logInfo.pass("Searching for a product");
		logInfo.addScreenCaptureFromPath(captureScreenShot(driver));		
	
		WebUIUtilty.timeOUt(10);
		driver.findElement(By.xpath("//*[contains(@class,'styles__StyledImage')]")).click();
		logInfo.pass("First product clicked");
		logInfo.addScreenCaptureFromPath(captureScreenShot(driver));		
	
	
		driver.findElement(By.xpath("//*[@class='size_option_wrapper']")).click();
		
		driver.findElement(By.xpath("//button[contains(@class,'add_to_cart')]")).click();
		logInfo.pass("Product added to cart");
		logInfo.addScreenCaptureFromPath(captureScreenShot(driver));		
		
		driver.findElement(By.className("button-link")).click();
		logInfo.addScreenCaptureFromPath(captureScreenShot(driver));		
		price=driver.findElement(By.xpath("//span[contains(@class,'bag-order-amount right')]")).getText();
		if(driver.getCurrentUrl().contains("com"))
		{
			if(price.contains("$"))
			{
				logInfo.pass("Price is in USD");
			}
			else {
				logInfo.fail("Incorrect price is displayed");
			}
		}
		if(driver.getCurrentUrl().contains("ca"))
		{
			if(price.contains("CAD"))
			{
				logInfo.pass("Price is in CAD");
			}
			else {
				logInfo.fail("Incorrect price is displayed");
			}
			
			logInfo.pass("Amount validations");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));		
		}
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}
	}
	
	@When("^user should be able to checkout as a guest$")
	public void user_should_be_able_to_checkout_as_a_guest() {
		ExtentTest logInfo=null;
		try {
			logInfo=test.createNode(new GherkinKeyword("When"), "user_should_be_able_to_checkout_as_a_guest");
		driver.findElement(By.xpath("//*[@alt='Proceed to Checkout']")).click();
		logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
		driver.findElement(By.xpath("//*[@alt='GUEST CHECKOUT']")).click();
		logInfo.pass("Guest checkout button is clicked");
		logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
		
		
	} catch (AssertionError | Exception e) {
		testStepHandle("FAIL",driver,logInfo,e);			
	}
	}




	@Then("shipping address, shipping method and payments section should be displayed")
	public void shipping_address_shipping_method_and_payments_section_should_be_displayed() {
	
	ExtentTest logInfo=null;
	try {
		logInfo=test.createNode(new GherkinKeyword("Then"), "shipping_address_shipping_method_and_payments_section_should_be_displayed");
	if(driver.getCurrentUrl().contains(".com")) {
		
		Actions address = new Actions(driver);
		address.sendKeys(driver.findElement(By.xpath("//*[@id='addNewFName']")),"Arjun").build().perform();
		address.sendKeys(driver.findElement(By.xpath("//*[@id='addNewLName']")),"kumar").build().perform();
		address.sendKeys(driver.findElement(By.xpath("//*[@id='addNewStreetAddr']")),"100 S. Murphy Ave").build().perform();
		address.sendKeys(driver.findElement(By.xpath("//*[@id='addNewZip']")),"94086").build().perform();
		address.sendKeys(driver.findElement(By.xpath("//*[@id='addNewCity']")),"sunnyvale").build().perform();
		address.sendKeys(driver.findElement(By.xpath("//*[@id='addNewState']")),"california").build().perform();
		address.sendKeys(Keys.TAB).build().perform();
		logInfo.pass("Address entered successfully");
		logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
		
		
}

else
{
	Actions caaddress = new Actions(driver);
	caaddress.sendKeys(driver.findElement(By.xpath("//*[@id='addNewFName']")),"Arjun").build().perform();
	caaddress.sendKeys(driver.findElement(By.xpath("//*[@id='addNewLName']")),"kumar").build().perform();
	caaddress.sendKeys(driver.findElement(By.xpath("//*[@id='addNewStreetAddr']")),"1010 east st").build().perform();
	caaddress.sendKeys(driver.findElement(By.xpath("//*[@id='addNewZip']")),"K1A 0B1").build().perform();
	caaddress.sendKeys(driver.findElement(By.xpath("//*[@id='addNewCity']")),"Ottawa").build().perform();
	caaddress.sendKeys(driver.findElement(By.xpath("//*[@id='addNewState']")),"ontorio").build().perform();
	caaddress.sendKeys(Keys.TAB).build().perform();
	logInfo.pass("Address entered successfully");
	logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
	
}

	Actions se = new Actions(driver);
se.sendKeys(driver.findElement(By.xpath("//*[@id='addEmail']")),"2prodtest@FK.com").build().perform();
se.sendKeys(driver.findElement(By.xpath("//*[@id='editAddressDaytime']")),"7845269020").build().perform();
logInfo.pass("Phone number and email address entered successfully");
logInfo.addScreenCaptureFromPath(captureScreenShot(driver));

boolean shipping=driver.findElement(By.xpath("//*[text()='SHIPPING METHOD']")).isDisplayed();
if(shipping) {
	logInfo.pass("Shipping methos is displayed");
}
else {
	logInfo.fail("Shipping methos is not displayed");
}

driver.findElement(By.xpath("//*[@id='checkoutEditAddressFormSubmit']")).click();



boolean payments=	driver.findElement(By.xpath("//*[text()='PAYMENT']")).isDisplayed();
if(payments) {
	logInfo.pass("Payment section is displayed");
}

else {
	logInfo.fail("payment section is not displayed");
}
logInfo.addScreenCaptureFromPath(captureScreenShot(driver));

driver.close();			

} catch (AssertionError | Exception e) {
	testStepHandle("FAIL",driver,logInfo,e);			
}
}
	
	@Given("^registered user is in home page to check order history$")
	public void registered_user_is_in_home_page_to_check_order_history() {
		ExtentTest logInfo=null;
		try {
			test = extent.createTest(Feature.class, "Sanity Functionality");							
			test=test.createNode(Scenario.class, "Verify User is able to check the order history");						
			logInfo=test.createNode(new GherkinKeyword("Given"), "guest_registered_user_is_in_home_page_to_check_order_history");
		driver = WebUIUtilty.launchBrowser(browser);
		driver.get(ScriptHelper.getURL());
		driver.findElement(By.xpath("//button[@aria-label='Close']")).click();
		landingPage = new HomePage(driver);	
		WebUIUtilty.timeOUt(5);
		driver.findElement(By.xpath("//*[@class='sign_in']")).click();
		Actions login = new Actions(driver);
		login.sendKeys(driver.findElement(By.xpath("//*[@id='loginEmail']")),email).build().perform();
		login.sendKeys(driver.findElement(By.xpath("//*[@id='loginPwd']")),password).build().perform();
		driver.findElement(By.id("signinFormSubmit")).click();
		logInfo.pass("User logged in");
		logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
	
	} catch (AssertionError | Exception e) {
		testStepHandle("FAIL",driver,logInfo,e);			
	}

	
	}
	
	
	@When("^click my account and navigate to order history$")
	public void click_my_account_and_navite_to_order_history() {
		ExtentTest logInfo=null;
		try {
								
			logInfo=test.createNode(new GherkinKeyword("When"), "click_my_account_and_navigate_to_order_history");
		
		WebUIUtilty.timeOUt(10);
		driver.findElement(By.xpath("//*[text()='Order Status & History']")).click();
		logInfo.pass("Order history link is clicked");
		logInfo.addScreenCaptureFromPath(captureScreenShot(driver));

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}
	}
	
	@Then("^order related details should be displayed$")
	public void order_related_details_should_be_displayed() {
		ExtentTest logInfo=null;
		try {
								
			logInfo=test.createNode(new GherkinKeyword("Then"), "order_related_details_should_be_displayed");
		
		
		boolean flag=driver.findElement(By.xpath("//*[text()='Order Status & History']")).isDisplayed();
		if(flag) {
			logInfo.pass("User navigated to order history page");
		}
			else {
				logInfo.fail("Unable to navigate to order history page");
			}
		logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
		
		boolean ordernumberFild=driver.findElement(By.xpath("//*[@id='searchOrderNumber']")).isDisplayed();
		if(ordernumberFild) {
			logInfo.pass("Enter order number field is displayed");
			
		}
		
		else {
			
			logInfo.fail("Enter order number field is not displayed");
		}
		logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
		driver.close();
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}
	}

	@Given("^guest/registered user is in home page to reset password$")
	public void guest_registered_user_is_in_home_page_to_reset_password()  {
		ExtentTest logInfo=null;
		try {
			test = extent.createTest(Feature.class, "Sanity Functionality");							
			test=test.createNode(Scenario.class, "Verify User is able to reset the password");						
			logInfo=test.createNode(new GherkinKeyword("Given"), "guest_registered_user_is_in_home_page_to_reset_password");
			driver = WebUIUtilty.launchBrowser(browser);
			driver.get(ScriptHelper.getURL());
			driver.findElement(By.xpath("//button[@aria-label='Close']")).click();
			landingPage = new HomePage(driver);
			
			logInfo.pass("User is in Home Page");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));			
			
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}
			
	}	
	
	@When("^the user navigates to forgot password page and enters email$")
	public void the_user_navigates_to_forgot_password_page_and_enters_email() {
		
		ExtentTest logInfo=null;
		try {
								
			logInfo=test.createNode(new GherkinKeyword("When"), "the_user_navigates_to_forgot_password_page_and_enters_email");
		
		driver.findElement(By.xpath("//*[@class='sign_in']")).click();
		driver.findElement(By.xpath("//*[text()='Forgot your password?']")).click();
		logInfo.pass("Forgot password link is clicked");
		logInfo.addScreenCaptureFromPath(captureScreenShot(driver));	
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1)); //Tab number


		driver.switchTo().window(tabs2.get(1));
		Actions forgotpwd=new Actions(driver);
		forgotpwd.sendKeys(driver.findElement(By.xpath("//*[@id='loginEmail']")), "2prodtest@FK.com").build().perform();
		logInfo.pass("Email address is entered");
		logInfo.addScreenCaptureFromPath(captureScreenShot(driver));	
		driver.findElement(By.xpath("//*[@id='forgotPwdSubmit']")).click();
		logInfo.addScreenCaptureFromPath(captureScreenShot(driver));	
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}
		
	
		
	}
	
	@Then("^forgot password mail sent confirmation message should be displayed$")
	public void forgot_password_mail_sent_confirmation_message_should_be_displayed() {
		ExtentTest logInfo=null;
		try {
								
			logInfo=test.createNode(new GherkinKeyword("Then"), "forgot_password_mail_sent_confirmation_message_should_be_displayed");
		
		WebUIUtilty.timeOUt(10);
		
		boolean flag=driver.findElement(By.className("response-text")).isDisplayed();
		
		if(flag)
		{
			logInfo.pass("Forgot password email has been sent");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));	
		}
		else {
			logInfo.fail("Unable to send mail");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));	
		}
		driver.quit();
	} catch (AssertionError | Exception e) {
		testStepHandle("FAIL",driver,logInfo,e);			
	}
		
	}
	@Given("^guest/registered user is in home page to sign in my Account$")
	public void guest_registered_user_is_in_home_page_to_sign_in_my_Account() throws Throwable {
		ExtentTest logInfo=null;
		try {
									
			logInfo=test.createNode(new GherkinKeyword("Given"), "guest_registered_user_is_in_home_page_to_sign_in_my_Account");
		driver = WebUIUtilty.launchBrowser(browser);
		driver.get(ScriptHelper.getURL());
		driver.findElement(By.xpath("//button[@aria-label='Close']")).click();
		landingPage = new HomePage(driver);	
		WebUIUtilty.timeOUt(5);
		driver.findElement(By.xpath("//*[@class='sign_in']")).click();
		
		logInfo.pass("User clicked on sign in");
		logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
	
	} catch (AssertionError | Exception e) {
		testStepHandle("FAIL",driver,logInfo,e);			
	}
	}

	@When("^user  enters email address and password to login account$")
	public void user_enters_email_address_and_password_to_login_account() throws Throwable {
		ExtentTest logInfo=null;
		try {
									
			logInfo=test.createNode(new GherkinKeyword("When"), "user_enters_email_address_and_password_to_login_account");
		WebUIUtilty.timeOUt(10);
		Actions login = new Actions(driver);
		login.sendKeys(driver.findElement(By.xpath("//*[@id='loginEmail']")),email).build().perform();
		login.sendKeys(driver.findElement(By.xpath("//*[@id='loginPwd']")),password).build().perform();
		logInfo.pass("User entered email ID and password to login account");
		logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
	
	} catch (AssertionError | Exception e) {
		testStepHandle("FAIL",driver,logInfo,e);			
	}
	}

	@Then("^user clicks on sign in button$")
	public void user_clicks_on_sign_in_button() throws Throwable {
		ExtentTest logInfo=null;
		try {
					WebUIUtilty.timeOUt(10);				
			logInfo=test.createNode(new GherkinKeyword("Then"), "user_clicks_on_sign_in_button");
		
		driver.findElement(By.xpath("//input[@id='signinFormSubmit']")).click();
		logInfo.pass("User clicked on sign in button");
		logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
	
	} catch (AssertionError | Exception e) {
		testStepHandle("FAIL",driver,logInfo,e);			
	}
	}

	@Then("^user clicks on Account Profile link$")
	public void user_clicks_on_Account_Profile_link() throws Throwable {
		ExtentTest logInfo=null;
		try {
					WebUIUtilty.timeOUt(15);				
			logInfo=test.createNode(new GherkinKeyword("Then"), "user_clicks_on_Account_Profile_link");
		
		driver.findElement(By.xpath("//a[text()='Account Profile']")).click();
		logInfo.pass("user_clicks_on_Account_Profile_link");
		logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
	
	} catch (AssertionError | Exception e) {
		testStepHandle("FAIL",driver,logInfo,e);			
	}
	}

	@Then("^user clicks on Address Book link$")
	public void user_clicks_on_Address_Book_link() throws Throwable {
		ExtentTest logInfo=null;
		try {
					WebUIUtilty.timeOUt(15);				
			logInfo=test.createNode(new GherkinKeyword("Then"), "user_clicks_on_Address_Book_link");
		
		driver.findElement(By.xpath("//a[text()='Address Book']")).click();
		logInfo.pass("User clicked on sign Address Book Link");
		logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
	
	} catch (AssertionError | Exception e) {
		testStepHandle("FAIL",driver,logInfo,e);			
	}
	}

	@Then("^user clicks on order status & history link$")
	public void user_clicks_on_order_status_history_link() throws Throwable {
		ExtentTest logInfo=null;
		try {
					WebUIUtilty.timeOUt(15);				
			logInfo=test.createNode(new GherkinKeyword("Then"), "user_clicks_on_order_status_history_link");
		
		driver.findElement(By.xpath("//a[text()='Order Status & History']")).click();
		logInfo.pass("user_clicks_on_order_status_history_link");
		logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
	
	} catch (AssertionError | Exception e) {
		testStepHandle("FAIL",driver,logInfo,e);			
	}
	}

	@Then("^user clicks on Reward History link$")
	public void user_clicks_on_Reward_History_link() throws Throwable {
		ExtentTest logInfo=null;
		try {
					WebUIUtilty.timeOUt(15);				
			logInfo=test.createNode(new GherkinKeyword("Then"), "user_clicks_on_Reward_History_link");
		
		driver.findElement(By.xpath("//a[text()='Rewards History']")).click();
		logInfo.pass("user_clicks_on_Reward_History_link");
		logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
	
	} catch (AssertionError | Exception e) {
		testStepHandle("FAIL",driver,logInfo,e);			
	}
	}

	@Then("^user clicks on Adventure Rewards FAQs link$")
	public void user_clicks_on_Adventure_Rewards_FAQs_link() throws Throwable {
		ExtentTest logInfo=null;
		try {
					WebUIUtilty.timeOUt(15);				
			logInfo=test.createNode(new GherkinKeyword("Then"), "user_clicks_on_Adventure_Rewards_FAQs_link");
		
		driver.findElement(By.xpath("//a[text()='Adventure Rewards FAQs']")).click();
		logInfo.pass("user_clicks_on_Adventure_Rewards_FAQs_link");
		logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
	
	} catch (AssertionError | Exception e) {
		testStepHandle("FAIL",driver,logInfo,e);			
	}
	}

	@Then("^user clicks on Adventure Rewards T&Cs link$")
	public void user_clicks_on_Adventure_Rewards_T_Cs_link() throws Throwable {
		ExtentTest logInfo=null;
		try {
					WebUIUtilty.timeOUt(15);				
			logInfo=test.createNode(new GherkinKeyword("Then"), "user_clicks_on_Adventure_Rewards_T_Cs_link");
		
		driver.findElement(By.xpath("//a[text()='Adventure Rewards T&Cs']")).click();
		logInfo.pass("user_clicks_on_Adventure_Rewards_T_Cs_link");
		logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
	
	} catch (AssertionError | Exception e) {
		testStepHandle("FAIL",driver,logInfo,e);			
	}
	}

	@Then("^user clicks on Wish List link$")
	public void user_clicks_on_Wish_List_link() throws Throwable {
		ExtentTest logInfo=null;
		try {
					WebUIUtilty.timeOUt(15);				
			logInfo=test.createNode(new GherkinKeyword("Then"), "user_clicks_on_Wish_List_link");
		
		driver.findElement(By.xpath("//a[text()='Wish List']")).click();
		logInfo.pass("user_clicks_on_Wish_List_link");
		logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
	
	} catch (AssertionError | Exception e) {
		testStepHandle("FAIL",driver,logInfo,e);			
	}
	}

	@Then("^user clicks on Subscriptions link$")
	public void user_clicks_on_Subscriptions_link() throws Throwable {
		ExtentTest logInfo=null;
		try {
					WebUIUtilty.timeOUt(15);				
			logInfo=test.createNode(new GherkinKeyword("Then"), "user_clicks_on_Subscriptions_link");
		
		driver.findElement(By.xpath("//a[text()='Subscriptions']")).click();
		logInfo.pass("user_clicks_on_Subscriptions_link");
		logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
	
	} catch (AssertionError | Exception e) {
		testStepHandle("FAIL",driver,logInfo,e);			
	}
	}

	@Then("^user clicks on Eddie Bauer Credit Card link$")
	public void user_clicks_on_Eddie_Bauer_Credit_Card_link() throws Throwable {
		ExtentTest logInfo=null;
		try {
					WebUIUtilty.timeOUt(15);				
			logInfo=test.createNode(new GherkinKeyword("Then"), "user_clicks_on_Eddie_Bauer_Credit_Card_link");
		
		driver.findElement(By.xpath("//a[text()='Eddie Bauer Credit Card']")).click();
		logInfo.pass("user_clicks_on_Eddie_Bauer_Credit_Card_link");
		logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
		driver.close();
	
	} catch (AssertionError | Exception e) {
		testStepHandle("FAIL",driver,logInfo,e);			
	}
	}


	@Given("guest\\/registered user is in home page to check wishlist")
	public void guest_registered_user_is_in_home_page_to_check_wishlist() {
		ExtentTest logInfo=null;
		try {
			test = extent.createTest(Feature.class, "Sanity Functionality");							
			test=test.createNode(Scenario.class, "Verify User is able to add,edit and delete wishlist");						
			logInfo=test.createNode(new GherkinKeyword("Given"), "guest_registered_user_is_in_home_page_to_check_wishlist");
			driver = WebUIUtilty.launchBrowser(browser);
			driver.get(ScriptHelper.getURL());
			driver.findElement(By.xpath("//button[@aria-label='Close']")).click();
			landingPage = new HomePage(driver);
			
			logInfo.pass("User is in Home Page");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));			
			
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}
			
	}	
	
	
	@When("^the user navigates to wishlist section$")
	public void the_user_navigates_to_wishlist_section() {
		
		ExtentTest logInfo=null;
		try {
								
			logInfo=test.createNode(new GherkinKeyword("When"), "the_user_navigates_to_wishlist_section");
		
		driver.findElement(By.xpath("//*[@class='sign_in']")).click();
		Actions login = new Actions(driver);
		login.sendKeys(driver.findElement(By.xpath("//*[@id='loginEmail']")),email).build().perform();
		login.sendKeys(driver.findElement(By.xpath("//*[@id='loginPwd']")),password).build().perform();
		driver.findElement(By.id("signinFormSubmit")).click();
		
		logInfo.pass("User logged in");
		logInfo.addScreenCaptureFromPath(captureScreenShot(driver));	
		
		
		Actions se = new Actions(driver);
		se.moveToElement(driver.findElement(By.xpath("//*[contains(@aria-label,'Eddie Bauer')]"))).click().build().perform();
		WebUIUtilty.timeOUt(10);
		driver.findElement(By.xpath("//span[text()=' Search ']")).click();
		Actions searchProduct = new Actions(driver);
		searchProduct.sendKeys(Keyword, Keys.ENTER).build().perform();
		logInfo.pass("User searches for a product");
		logInfo.addScreenCaptureFromPath(captureScreenShot(driver));	

		WebUIUtilty.timeOUt(10);
		driver.findElement(By.xpath("//*[contains(@class,'styles__StyledImage')]")).click();
		logInfo.pass("First product is clicked");
		logInfo.addScreenCaptureFromPath(captureScreenShot(driver));	

		driver.findElement(By.xpath("//*[@class='size_option_wrapper']")).click();
		
		driver.findElement(By.xpath("//button[contains(@class,'add_to_cart')]")).click();
		driver.findElement(By.className("button-link")).click();
		logInfo.pass("Product added to bag");
		logInfo.addScreenCaptureFromPath(captureScreenShot(driver));	
		
	} catch (AssertionError | Exception e) {
		testStepHandle("FAIL",driver,logInfo,e);			
	}
	}
	
	@Then("^the add, edit and delete should work properly$")
	public void the_add_edit_and_delete_should_work_properly() {
		ExtentTest logInfo=null;
		try {
								
			logInfo=test.createNode(new GherkinKeyword("Then"), "the_add_edit_and_delete_should_work_properly");
		WebUIUtilty.timeOUt(20);
		
		driver.findElement(By.xpath("(//li[@class='left wl-prod-link'])[1]")).click();
		logInfo.pass("Item moved to wishlist");
		logInfo.addScreenCaptureFromPath(captureScreenShot(driver));	
		
		WebUIUtilty.timeOUt(5);
		boolean addWishlist=driver.findElement(By.xpath("//*[contains(text(),'WISH LIST')]")).isDisplayed();
		if(addWishlist) {
		
			logInfo.pass("Product moved to wishlist");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));	
		}
		else {
			
			
			logInfo.fail("Unable to move the product to wishlist");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));	
		}
//		Actions se = new Actions(driver);
//		se.sendKeys(driver.findElement(By.xpath("//*[@class='wl-item-quantity']")),"2").build().perform();
//		se.sendKeys(Keys.TAB).build().perform();
		WebUIUtilty.timeOUt(10);
		//logInfo.pass("Edited the wishlist");
		//logInfo.addScreenCaptureFromPath(captureScreenShot(driver));	
		
		Select editwishlist = new Select(driver.findElement(By.xpath("//select[@id='item-quantity-0']")));
		editwishlist.deselectByIndex(2);
		System.out.println("wishlist is edited successfully");
		
//		//boolean editwishlist=driver.findElement(By.xpath("//*[contains(@data-formid,'edit-quantity')]")).isDisplayed();
//		if(editwishlist) {
//			
//			logInfo.pass("Wish list edited successfully");
//		}
//		else {
//			
//			logInfo.fail("Unable to edit wish list");
//		}
//		
		WebElement element = driver.findElement(By.xpath("//*[text()='Delete entire Wish List']"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;;
		       executor.executeScript("arguments[0].click();", new Object[]{element});
		
	;
		WebUIUtilty.timeOUt(8);
		driver.findElement(By.xpath("//*[@src='/static/img/btn-ok.png']")).click();
		
		logInfo.pass("Deleted the wishlist");
		logInfo.addScreenCaptureFromPath(captureScreenShot(driver));	
		driver.close();
	
		
	} catch (AssertionError | Exception e) {
		testStepHandle("FAIL",driver,logInfo,e);			
	}
	}
	
	@Given("^guest/registered user is in home page to verify shopping cart$")
	public void guest_registered_user_is_in_home_page_to_verify_shopping_cart()  {
		ExtentTest logInfo=null;
		try {
			test = extent.createTest(Feature.class, "Sanity Functionality");							
			test=test.createNode(Scenario.class, "Shopping cart verification");						
			logInfo=test.createNode(new GherkinKeyword("Given"), "guest_registered_user_is_in_home_page_to_verify_shopping_cart");
			driver = WebUIUtilty.launchBrowser(browser);
			driver.get(ScriptHelper.getURL());
			driver.findElement(By.xpath("//button[@aria-label='Close']")).click();
			landingPage = new HomePage(driver);
			
			logInfo.pass("User is in Home Page");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));			
			
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}
			
	}	
	
	
	@When("^the user adds a product to bag$")
	public void the_user_adds_a_product_to_bag() {
		ExtentTest logInfo=null;
		try {
			logInfo=test.createNode(new GherkinKeyword("When"), "the_user_adds_a_product_to_bag");
		
		WebUIUtilty.timeOUt(10);
		driver.findElement(By.xpath("//span[text()=' Search ']")).click();
		Actions searchProduct = new Actions(driver);
		searchProduct.sendKeys(Keyword, Keys.ENTER).build().perform();
		logInfo.pass("User searches a product");
		logInfo.addScreenCaptureFromPath(captureScreenShot(driver));			

		
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}
		
	}

	@Then("^verify the order summary and line items$")
	public void verify_the_order_summary_and_line_items() {
		ExtentTest logInfo=null;
		try {
			logInfo=test.createNode(new GherkinKeyword("Then"), "verify_the_order_summary_and_line_items");
		
			WebUIUtilty.timeOUt(10);
			driver.findElement(By.xpath("//*[contains(@class,'styles__StyledImage')]")).click();

			logInfo.pass("First product is clicked");
			
			String sizefromPLP=driver.findElement(By.xpath("//*[@class='size_option_wrapper']")).getText();
			String colorfromPLP=driver.findElement(By.xpath("//div[@class='label']/span[2]")).getText();
			String itemIDfromplp=driver.findElement(By.xpath("//*[@class='style_number']")).getText();
		
			
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));			
			driver.findElement(By.xpath("//*[@class='size_option_wrapper']")).click();
			
			driver.findElement(By.xpath("//button[contains(@class,'add_to_cart')]")).click();
			logInfo.pass("Product added to bag");
			WebUIUtilty.timeOUt(5);
		
			
		driver.findElement(By.className("button-link")).click();
		
		String sizefromcart=driver.findElement(By.xpath("//*[@id='prod-size']")).getText();
		String colorfromcart=driver.findElement(By.xpath("//*[@id='prod-color']")).getText();
		String itemIDfromcart=driver.findElement(By.className("checkout-textstyle-6")).getText();
		
		
	
		if(itemIDfromcart.contains(itemIDfromplp)) {
			logInfo.pass("Item ID is displayed");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));	
		}
		else {
			logInfo.fail("Item ID is not displayed");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));	
		}
		
		
		if(sizefromcart.contains(sizefromPLP)) {
			logInfo.pass("Size is displayed");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));	
		}
		else {
			logInfo.fail("Size is not displayed");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));	
		}
		
		
		if(colorfromcart.contains(colorfromPLP)) {
			logInfo.pass("Color is displayed");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));	
		}
		else {
			logInfo.fail("Color is not displayed");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));	
		}
		boolean orderSummary=driver.findElement(By.xpath("//*[@class='order-summary ']")).isDisplayed();
		if(orderSummary)
		{
			logInfo.pass("Order summary is displayed");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));	
		}
		else {
			logInfo.fail("Order summary is not displayed");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));	
		}
		
		driver.close();
				
	
	} catch (AssertionError | Exception e) {
		testStepHandle("FAIL",driver,logInfo,e);			
	}
	}
	
	@Given("^guest/registered user is in home page to verify minicart$")
	public void guest_registered_user_is_in_home_page_to_verifu_minicart()  {
		ExtentTest logInfo=null;
		try {
			test = extent.createTest(Feature.class, "Sanity Functionality");							
			test=test.createNode(Scenario.class, "Mini cart verification");						
			logInfo=test.createNode(new GherkinKeyword("Given"), "guest_registered_user_is_in_home_page_to_verify_mini_cart");
			driver = WebUIUtilty.launchBrowser(browser);
			driver.get(ScriptHelper.getURL());
			driver.findElement(By.xpath("//button[@aria-label='Close']")).click();
			landingPage = new HomePage(driver);
			
			logInfo.pass("User is in Home Page");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));			
			
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}
			
	}	
	
	@When("^the user adds a product to cart$")
	public void the_user_adds_a_product_to_cart() {
		ExtentTest logInfo=null;
		try {
			logInfo=test.createNode(new GherkinKeyword("When"), "the_user_adds_a_product_to_cart");
		
		WebUIUtilty.timeOUt(10);
		driver.findElement(By.xpath("//span[text()=' Search ']")).click();
		Actions searchProduct = new Actions(driver);
		searchProduct.sendKeys(Keyword, Keys.ENTER).build().perform();
		logInfo.pass("User searches a product");
		logInfo.addScreenCaptureFromPath(captureScreenShot(driver));			

			
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}
		
	}
	
	@Then("^verify the items displayed in mini cart$")
	public void verify_the_items_displayed_in_mini_cart() {
		ExtentTest logInfo=null;
		try {
							
			logInfo=test.createNode(new GherkinKeyword("Then"),"verify_the_items_displayed_in_mini_cart");
		
			WebUIUtilty.timeOUt(10);
			driver.findElement(By.xpath("//*[contains(@class,'styles__StyledImage')]")).click();

			logInfo.pass("First product is clicked");
			
			String sizefromPLP=driver.findElement(By.xpath("//*[@class='size_option_wrapper']")).getText();
			String colorfromPLP=driver.findElement(By.xpath("//div[@class='label']/span[2]")).getText();
			String itemIDfromplp=driver.findElement(By.xpath("//*[@class='style_number']")).getText();
		
			
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));			
			driver.findElement(By.xpath("//*[@class='size_option_wrapper']")).click();
			
			driver.findElement(By.xpath("//button[contains(@class,'add_to_cart')]")).click();
			logInfo.pass("Product added to bag");
			WebUIUtilty.timeOUt(5);
		
			
			String sizefromcart=driver.findElement(By.xpath("//div[contains(@class,'left col-2')]//div[1]")).getText();
			String colorfromcart=driver.findElement(By.xpath("//div[contains(@class,'left col-2')]//div[2]")).getText();
			String status=driver.findElement(By.xpath("//div[contains(@class,'left col-2')]//div[3]")).getText();
			
		
		
			if(sizefromcart.contains(sizefromPLP)) {
				logInfo.pass("Size is displayed");
				logInfo.addScreenCaptureFromPath(captureScreenShot(driver));	
			}
			else {
				logInfo.fail("Size is not displayed");
				logInfo.addScreenCaptureFromPath(captureScreenShot(driver));	
			}
			
			
			if(colorfromcart.contains(colorfromPLP)) {
				logInfo.pass("Color is displayed");
				logInfo.addScreenCaptureFromPath(captureScreenShot(driver));	
			}
			else {
				logInfo.fail("Color is not displayed");
				logInfo.addScreenCaptureFromPath(captureScreenShot(driver));	
			}
			
			if(status.contains("In Stock"))
			{
				logInfo.pass("status is displayed");	
			}
			else {
				logInfo.fail("status is not displayed");
					
			}
			
		driver.close();
		
	
	} catch (AssertionError | Exception e) {
		testStepHandle("FAIL",driver,logInfo,e);			
	}
	}
	
	@Given("^guest/registered user is in home page to verify PDP$")
	public void guest_registered_user_is_in_home_page_to_verify_PDP()  {
		ExtentTest logInfo=null;
		try {
			test = extent.createTest(Feature.class, "Sanity Functionality");							
			test=test.createNode(Scenario.class, "Verify the product description");						
			logInfo=test.createNode(new GherkinKeyword("Given"), "guest_registered_user_is_in_home_page_to_verify_PDP");
			driver = WebUIUtilty.launchBrowser(browser);
			driver.get(ScriptHelper.getURL());
			driver.findElement(By.xpath("//button[@aria-label='Close']")).click();
			landingPage = new HomePage(driver);
			
			logInfo.pass("User is in Home Page");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));			
			
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}
			
	}	

	@When("^the user navigates to a product details page$")
	public void the_user_navigates_to_a_product_details_page() {
		 {
				ExtentTest logInfo=null;
				try {
										
					logInfo=test.createNode(new GherkinKeyword("When"), "the_user_navigates_to_a_product_details_page");
		
		WebUIUtilty.timeOUt(10);
		driver.findElement(By.xpath("//span[text()=' Search ']")).click();
		Actions searchProduct = new Actions(driver);
		searchProduct.sendKeys(Keyword, Keys.ENTER).build().perform();
		logInfo.pass("User searches a product");
		logInfo.addScreenCaptureFromPath(captureScreenShot(driver));			
		

		WebUIUtilty.timeOUt(10);
		driver.findElement(By.xpath("//*[contains(@class,'styles__StyledImage')]")).click();
		logInfo.pass("User clicks the first product");
		logInfo.addScreenCaptureFromPath(captureScreenShot(driver));	
		

	}
				
			catch (AssertionError | Exception e) {
				testStepHandle("FAIL",driver,logInfo,e);			
			}
		 }
	}
	
	@Then("^verify the ItemID product title color size addtocart button are displayed properly$")
	public void verify_the_itemID_product_title_color_size_addtocart_button_are_displayed_properly() {
		 {
				ExtentTest logInfo=null;
				try {
										
					logInfo=test.createNode(new GherkinKeyword("Then"), "verify_the_itemID_product_title_color_size_addtocart_button_are_displayed_properly");
		
		
		
		boolean itemID=driver.findElement(By.xpath("//*[@class='style_number']")).isDisplayed();
		if(itemID) {
			logInfo.pass("Item ID is displayed");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));	
		}
		else {
			logInfo.fail("Item ID is not displayed");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));	
		}
		
		String itemId=driver.findElement(By.xpath("//*[@class='style_number']")).getText();
		System.out.println("Item ID is"   + itemId );
		
		boolean productTitle=driver.findElement(By.xpath("//*[@class='title']")).isDisplayed();
		if(productTitle) {
			logInfo.pass("Product title is displayed");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));	
		}
		else {
			logInfo.fail("product title is not displayed");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));	
		}
		
		String prodTitle=driver.findElement(By.xpath("//*[@class='title']")).getText();
		System.out.println("Product title is "   + prodTitle);
		
		boolean price=driver.findElement(By.xpath("//*[contains(@class,'old_price')]")).isDisplayed();
		if(price) {
			logInfo.pass("Price is displayed");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));	
		}
		else {
			logInfo.fail("Price is not displayed");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));	
		}
		
		String priceDisplayed=driver.findElement(By.xpath("//*[contains(@class,'old_price')]")).getText();
		
		if(driver.getCurrentUrl().contains(".com")) {
			
			if(priceDisplayed.contains("$"))
			{
				logInfo.pass("Price in PDP is displayed in USD");
					
			}
			
			else {
				
				logInfo.fail("Incorrect price is displayed");
			}
		}
		
		else {
			if(priceDisplayed.contains("CAD")) {
				logInfo.pass("Price in PDP is displayed in CAD");
			}
			
			else {
				logInfo.fail("Incorrect price is displayed");
			}
			
		}
		
		boolean color=driver.findElement(By.xpath("//*[@id='product-colors']")).isDisplayed();
		if(color) {
			logInfo.pass("Color is displayed properly");
		}
		else {
			logInfo.fail("Color is not displayed properly");
		}
		
		boolean size=driver.findElement(By.xpath("//*[@id='product-sizes']")).isDisplayed();
		if(size) {
			logInfo.pass("Size is displayed properly");
		}
		else {
			logInfo.fail("Size is not displayed properly");
		}
		
		boolean addtocartbutton=driver.findElement(By.xpath("//*[text()='Add to Bag']")).isDisplayed();
		if(addtocartbutton) {
			
			logInfo.pass("Add to cart button");
		}
		
		else {
			logInfo.fail("Add to cart button is not displayed");
		}
	driver.close();
	}
				
				catch (AssertionError | Exception e) {
					testStepHandle("FAIL",driver,logInfo,e);			
				}
		 }
	}
	
	@Given("guest\\/registered user is in home page to verify the categories")
	public void guest_registered_user_is_in_home_page_to_verify_the_categories() {
		ExtentTest logInfo=null;
		try {
			test = extent.createTest(Feature.class, "Sanity Functionality");							
			test=test.createNode(Scenario.class, "Verify the category functionality - refine, sort by and left hand navigation");						
			logInfo=test.createNode(new GherkinKeyword("Given"), "guest_registered_user_is_in_home_page_to_verify_categories");
			driver = WebUIUtilty.launchBrowser(browser);
			driver.get(ScriptHelper.getURL());
			driver.findElement(By.xpath("//button[@aria-label='Close']")).click();
			landingPage = new HomePage(driver);
			
			logInfo.pass("User is in Home Page");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));			
			
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}
			
	}	
	
	@When("^the user navigates to a subcategory$")
	public void the_user_navigates_to_a_subcategory() {
		ExtentTest logInfo=null;
		try {
								
			logInfo=test.createNode(new GherkinKeyword("When"), "the_user_navigates_to_a_subcategory");
		
		Actions category=new Actions(driver);
		category.moveToElement(driver.findElement(By.xpath("//*[text()='Men']"))).build().perform();
		driver.findElement(By.xpath("//*[text()='Jackets & Vests']")).click();
		logInfo.pass("User vaigates to category Page");
		logInfo.addScreenCaptureFromPath(captureScreenShot(driver));		
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}
		
	}
	
	@Then("^verify the sortby refineby left hand navigation are displayed properly$")
	public void verify_the_sortby_refineby_left_hand_navigation_are_displayed_properly() {
		ExtentTest logInfo=null;
		try {
								
			logInfo=test.createNode(new GherkinKeyword("Then"), "verify_the_sortby_refineby_left_hand_navigation_are_displayed_properly");
		
		
		driver.findElement(By.xpath("//*[text()='Insulated']")).click();
		driver.findElement(By.xpath("//*[text()='Refine']")).click();
		 String currentPage = driver.getPageSource().toLowerCase();
		 String currentURL = driver.getCurrentUrl().toString().toLowerCase();
		 if(currentPage.contains("insulated")) {
			 logInfo.pass("Refine by action is successful");
		 }
		 else {
			 logInfo.fail("Refine by option was not clickable");
		 }
		
		driver.findElement(By.xpath("//*[text()='Best Match']")).click();
		WebUIUtilty.timeOUt(5);
		driver.findElement(By.xpath("//*[text()='Price Low to High']")).click();
		WebUIUtilty.timeOUt(5);
		
		logInfo.pass("Sort by action is successful");
	
		 driver.findElement(By.xpath("//*[text()='Men' and @role='link']")).click();
		 
		 WebUIUtilty.timeOUt(5);
		   if(currentURL.contains("men")) {
			   logInfo.pass("Left hand navigation is successful");
		   }
		   else {
			   logInfo.fail("Left hand navigation unsuccessful");
		   }
		driver.close();
	
	} catch (AssertionError | Exception e) {
		testStepHandle("FAIL",driver,logInfo,e);			
	}
	}

	@Given("^guest/registered user is in home page to verify recommendations$")
	public void guest_registered_user_is_in_home_page_to_verify_recommendations()  {
		ExtentTest logInfo=null;
		try {
			test = extent.createTest(Feature.class, "Sanity Functionality");							
			test=test.createNode(Scenario.class, "Verify the recommendations section");						
			logInfo=test.createNode(new GherkinKeyword("Given"), "guest_registered_user_is_in_home_page_to_Verify_Recommendations");
			driver = WebUIUtilty.launchBrowser(browser);
			driver.get(ScriptHelper.getURL());
			driver.findElement(By.xpath("//button[@aria-label='Close']")).click();
			landingPage = new HomePage(driver);
			
			logInfo.pass("User is in Home Page");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));			
			
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}
			
	}	
	
	@When("^the user navigates to a PDP pages$")
	public void the_user_navigates_to_a_PDP_page() {
		ExtentTest logInfo=null;
		try {
								
			logInfo=test.createNode(new GherkinKeyword("When"), "the user navigates to a PDP pages");
		
		WebUIUtilty.timeOUt(10);
		driver.findElement(By.xpath("//span[text()=' Search ']")).click();
		Actions searchProduct = new Actions(driver);
		searchProduct.sendKeys(Keyword, Keys.ENTER).build().perform();

		WebUIUtilty.timeOUt(10);
		driver.findElement(By.xpath("//*[contains(@class,'styles__StyledImage')]")).click();
		logInfo.pass("User clicks the first product");
		logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
		}
		catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}
		
		
	}
	@Then("^verify the recommendations are displayed properly$")
	public void verify_the_recommendations_are_displayed_properly() {
		ExtentTest logInfo=null;
		try {
								
			logInfo=test.createNode(new GherkinKeyword("Then"), "verify_the_recommendations_are_displayed_properly in PDP");
		
		boolean flag=driver.findElement(By.xpath("//*[text()='You May Also Like']")).isDisplayed();
		if(flag) {
			logInfo.pass("Recommendation section is displayed properly in PDP page");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
		}
		else {
			logInfo.fail("Recommendation section is not displayed");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
		}
		
	}

		catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}
	}
	
	@When("^the user navigates to a minicart pages$")
	public void the_user_navigates_to_a_minicart_page() {
		ExtentTest logInfo=null;
		try {
								
			logInfo=test.createNode(new GherkinKeyword("When"), "the_user_navigates_to_a_minicart_page");
		

		driver.findElement(By.xpath("//*[@class='size_option_wrapper']")).click();
		
		driver.findElement(By.xpath("//button[contains(@class,'add_to_cart')]")).click();
		logInfo.pass("user navigates to mini cart");
		logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
		
	}

		catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}
	}
	
	@Then("^verify the recommendation are displayed properly$")
	public void verify_the_recommendation_are_displayed_properly() {
		ExtentTest logInfo=null;
		try {
								
			logInfo=test.createNode(new GherkinKeyword("Then"), "verify_the_recommendation_are_displayed_properly in mini cart page");
		WebUIUtilty.timeOUt(20);
		
		boolean flag=driver.findElement(By.xpath("//h3[text()='Recommended for You']")).isDisplayed();
		if(flag) {
			logInfo.pass("Recommendation section is displayed properly in mini cart");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
		}
		else {
			logInfo.fail("Recommendation section is not displayed");
		}
		
	}
		catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}
	}
	
	@When("^the user navigates to a cart pages$")
	public void the_user_navigates_to_a_cart_page() {
		ExtentTest logInfo=null;
		try {
								
			logInfo=test.createNode(new GherkinKeyword("When"), "the_user_navigates_to_a_cart_page");
		
		

		driver.findElement(By.className("button-link")).click();
		logInfo.pass("User navigates to cart page");
		logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
		}	
		catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}
		
		
	}
	
	@Then("^verify the recommendation section$")
	public void verify_the_recommendation_section() {
		ExtentTest logInfo=null;
		try {
								
			logInfo=test.createNode(new GherkinKeyword("Then"), "verify_the_recommendation_section_in_cart_page");
		
		
		boolean flag=driver.findElement(By.xpath("//*[text()='RECOMMENDED FOR YOU']")).isDisplayed();
		if(flag) {

			logInfo.pass("Recommendation section is displayed in cart page");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
		}
		else {
			logInfo.fail	("Recommendation section is not displayed");
		}
		driver.close();
	}
		catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}
	}

	@Given("^guest/registered user is in home page to verify filters$")
	public void guest_registered_user_is_in_home_page_to_verify_filters()  {
		ExtentTest logInfo=null;
		try {
			test = extent.createTest(Feature.class, "Sanity Functionality");							
			test=test.createNode(Scenario.class, "Verify whether the filers and sub filters are displayed properly");						
			logInfo=test.createNode(new GherkinKeyword("Given"), "guest_registered_user_is_in_home_page_to_verify_filters");
			driver = WebUIUtilty.launchBrowser(browser);
			driver.get(ScriptHelper.getURL());
			driver.findElement(By.xpath("//button[@aria-label='Close']")).click();
			landingPage = new HomePage(driver);
			
			logInfo.pass("User is in Home Page");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));			
			
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}
			
	}	
	
	@When("^the user navigate to search results$")
	public void the_user_navigates_to_search_results() {
		ExtentTest logInfo=null;
		try {
								
			logInfo=test.createNode(new GherkinKeyword("When"), "the_user_navigates_to_search_results");
		WebUIUtilty.timeOUt(10);
		driver.findElement(By.xpath("//span[text()=' Search ']")).click();
		Actions searchProduct = new Actions(driver);
		searchProduct.sendKeys(Keyword, Keys.ENTER).build().perform();
		logInfo.pass("Search results are displayed");
		logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}
		
	}

	@Then("^the category and subcategory link should be displayed$")
	public void the_category_and_subcategory_link_should_be_displayed() {
		ExtentTest logInfo=null;
		try {
								
			logInfo=test.createNode(new GherkinKeyword("When"), "the category and subcategory link should be displayed");
		WebUIUtilty.timeOUt(20);
		driver.findElement(By.xpath("//*[@class='filter_label']")).click();
		logInfo.pass("Refine by option is clicked");
		driver.findElement(By.xpath("//*[@class='filter_label']")).click();
		driver.findElement(By.xpath("//button[@aria-labelledby='size-head']")).click();
		logInfo.pass("Size option is clicked");
		driver.findElement(By.xpath("//*[text()='Colors']")).click();
		logInfo.pass("Color option is clicked");
		driver.close();
		
	}
		catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}
	}
	
	@Given("guest\\/registered user is in home page to register")
	public void guest_registered_user_is_in_home_page_to_register() {
		ExtentTest logInfo=null;
		try {
			test = extent.createTest(Feature.class, "Sanity Functionality");							
			test=test.createNode(Scenario.class, "Verify User is able to sign up");						
			logInfo=test.createNode(new GherkinKeyword("Given"), "guest_registered_user_is_in_home_page_for_registration");
			driver = WebUIUtilty.launchBrowser(browser);
			driver.get(ScriptHelper.getURL());
			driver.findElement(By.xpath("//button[@aria-label='Close']")).click();
			landingPage = new HomePage(driver);
			
			logInfo.pass("User is in Home Page");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));			
			
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}
			
	}	
	
	@When("^the user clicks signup button and fills the details$")
	public void the_user_clicks_signup_button_and_fills_the_details() {
		ExtentTest logInfo=null;
		try {
								
			logInfo=test.createNode(new GherkinKeyword("When"), "the_user_clicks_signup_button_and_fills_the_details");
			driver.findElement(By.xpath("//*[@class='sign_in']")).click();
			driver.findElement(By.xpath("//*[contains(text(),'Create one NOW')]")).click();
			logInfo.pass("User is in registration Page");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));	
			Actions registration = new Actions(driver);
			registration.sendKeys(driver.findElement(By.xpath("//*[@id='firstname']")), "arjun").build().perform();
			registration.sendKeys(driver.findElement(By.xpath("//*[@id='lastname']")), "kumar").build().perform();
			String generatedString = RandomStringUtils.random(10, false, true);
			String email = generatedString + "@ebtesting.com";
			registration.sendKeys(driver.findElement(By.xpath("//*[@id='login']")), email).build().perform();
			registration.sendKeys(driver.findElement(By.xpath("//*[@id='loginPwd']")), "Cameraman123$").build().perform();
			logInfo.pass("User details are entered");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));	
		}
		catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}
	
		
	}
	
	@Then("^upon valid details registration should happen successfully$")
	public void upon_valid_details_registration_should_happen_successfully() {
		ExtentTest logInfo=null;
		try {
								
			logInfo=test.createNode(new GherkinKeyword("Then"), "upon_valid_details_registration_should_happen_successfully");
		driver.findElement(By.id("regCheckoutFormSubmit")).click();
		logInfo.pass("register button is clicked");
		logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
		 String currentPage = driver.getPageSource();
		 if(currentPage.contains("Member Number")) {
			 
				logInfo.pass("User created successfully");
				logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
		 }
		 
		 else {
			 
				logInfo.fail("unable to register a new user");
				logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
		 }
		
		driver.close();
		}
		catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}
	}
	
	@Given("^guest/registered user is in home page to validate addresses$")
	public void guest_registered_user_is_in_home_page_to_validate_addresses()  {
		ExtentTest logInfo=null;
		try {
			test = extent.createTest(Feature.class, "Sanity Functionality");							
			test=test.createNode(Scenario.class, "Verify User is able to add edit and delete address");						
			logInfo=test.createNode(new GherkinKeyword("Given"), "guest_registered_user_is_in_home_page_to_validate_addresses");
			driver = WebUIUtilty.launchBrowser(browser);
			driver.get(ScriptHelper.getURL());
			driver.findElement(By.xpath("//button[@aria-label='Close']")).click();
			landingPage = new HomePage(driver);
			
			logInfo.pass("User is in Home Page");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));			
			
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}
			
	}	
	
	@When("^the user navigates to address page$")
	public void the_user_navigates_to_address_page() {
		
		ExtentTest logInfo=null;
		try {
					
			logInfo=test.createNode(new GherkinKeyword("When"), "the_user_navigates_to_address_page");
			
			driver.findElement(By.xpath("//*[@class='sign_in']")).click();
			Actions login = new Actions(driver);
			login.sendKeys(driver.findElement(By.xpath("//*[@id='loginEmail']")),email).build().perform();
			login.sendKeys(driver.findElement(By.xpath("//*[@id='loginPwd']")),password).build().perform();
			driver.findElement(By.id("signinFormSubmit")).click();
			logInfo.pass("User logged in");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
			WebUIUtilty.timeOUt(10);
			driver.findElement(By.xpath("//*[contains(text(),'Address Book')]")).click();
			driver.findElement(By.xpath("//a[contains(@href,'add-address')]")).click();
		
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}
			
	}
	
	@Then("^the add edit and delete address should work properly$")
	public void the_add_edit_and_delete_address_should_work_properly() {
		
		ExtentTest logInfo=null;
		try {
					
			logInfo=test.createNode(new GherkinKeyword("Then"), "the_add_edit_and_delete_address_should_work_properly");
			WebUIUtilty.timeOUt(25);
			if(driver.getCurrentUrl().contains(".com")) {
				
				Actions usaddress = new Actions(driver);
				usaddress.sendKeys(driver.findElement(By.xpath("//*[@id='addNewStreetAddr']")),"100 S. Murphy Ave").build().perform();
				usaddress.sendKeys(driver.findElement(By.xpath("//*[@id='addNewCity']")),"sunnyvale").build().perform();
				usaddress.sendKeys(driver.findElement(By.xpath("//*[@id='addNewZip']")),"94086").build().perform();
				usaddress.sendKeys(driver.findElement(By.xpath("//*[@id='addNewState']")),"california").build().perform();
				usaddress.sendKeys(Keys.TAB).build().perform();
				usaddress.sendKeys(driver.findElement(By.xpath("//*[@id='addNewCountry']")),"united states").build().perform();
				usaddress.sendKeys(Keys.TAB).build().perform();
				usaddress.sendKeys(driver.findElement(By.xpath("//*[@id='editAddressDaytime']")),"7845269020").build().perform();
				driver.findElement(By.xpath("//*[@value='Add Address']")).click();
				logInfo.pass("address is entered");
				logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
				
				if(driver.getCurrentUrl().toLowerCase().contains("requestid"))
				{
					logInfo.pass("address is entered successfully");
				}
				else
				{
					logInfo.fail("address is not entered");
				}
			}
			else {
				Actions caaddress= new Actions(driver);
				caaddress.sendKeys(driver.findElement(By.xpath("//*[@id='addNewStreetAddr']")),"1010 east st").build().perform();
				caaddress.sendKeys(driver.findElement(By.xpath("//*[@id='addNewCity']")),"ottawa").build().perform();
				caaddress.sendKeys(driver.findElement(By.xpath("//*[@id='addNewZip']")),"K1A 0B1").build().perform();
				caaddress.sendKeys(driver.findElement(By.xpath("//*[@id='addNewState']")),"ontorio").build().perform();
				caaddress.sendKeys(Keys.TAB).build().perform();
				caaddress.sendKeys(driver.findElement(By.xpath("//*[@id='addNewCountry']")),"canada").build().perform();
				caaddress.sendKeys(Keys.TAB).build().perform();
				caaddress.sendKeys(driver.findElement(By.xpath("//*[@id='editAddressDaytime']")),"7845269020").build().perform();
				driver.findElement(By.xpath("//*[@value='Add Address']")).click();
				logInfo.pass("address is entered");
				logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
				
			}
			
			driver.findElement(By.xpath("//*[contains(@aria-label,'Select this link to Delete')]")).click();
			WebUIUtilty.timeOUt(5);
			driver.findElement(By.xpath("//*[@src='/static/img/btn-ok.png']")).click();
			if(driver.getCurrentUrl().toLowerCase().contains("requestid"))
			{
				logInfo.pass("address is deleted successfully");
			}
			else
			{
				logInfo.fail("address is not deleted");
			}
			
			driver.findElement(By.xpath("(//a[@class='edit-address link'])[2]")).click();
			Actions editAddress = new Actions(driver);
			editAddress.sendKeys(driver.findElement(By.xpath("//*[@id='addNewStreetAddr2']")), "Test").build().perform();
			driver.findElement(By.xpath("//*[@value='Add Address']")).click();
			if(driver.getCurrentUrl().toLowerCase().contains("requestid"))
			{
				logInfo.pass("address is edited successfully");
			}
			else
			{
				logInfo.fail("address is not edited");
			}
			
			editAddress.sendKeys(driver.findElement(By.xpath("//*[@id='addNewStreetAddr2']")), "1").build().perform();
			driver.findElement(By.xpath("//*[@value='Add Address']")).click();
			
			
			driver.close();
			
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}
			
	}

	@Given("^registered user is in home page to place an order$")
	public void registered_user_is_in_home_page_to_place_an_order()  {
		ExtentTest logInfo=null;
		try {
			test = extent.createTest(Feature.class, "Sanity Functionality");							
			test=test.createNode(Scenario.class, "Verify User is able to place an order as a logged in user");						
			logInfo=test.createNode(new GherkinKeyword("Given"), "registered_user_is_in_home_page_for_checkout_case");
			driver = WebUIUtilty.launchBrowser(browser);
			driver.get(ScriptHelper.getURL());
			driver.findElement(By.xpath("//button[@aria-label='Close']")).click();
			landingPage = new HomePage(driver);
			
			logInfo.pass("User is in Home Page");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));			
			
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}
			
	}	
		
	
	@When("^the user add a product and moves to cart$")
	public void the_user_add_a_product_and_moves_to_cart() {
		ExtentTest logInfo=null;
		try {
									
			logInfo=test.createNode(new GherkinKeyword("When"), "the_user_add_a_product_and_moves_to_cart");
			
			WebUIUtilty.timeOUt(10);
			driver.findElement(By.xpath("//span[text()=' Search ']")).click();
			Actions searchProduct = new Actions(driver);
			searchProduct.sendKeys(Keyword, Keys.ENTER).build().perform();
			logInfo.pass("User searches a product");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));	
			WebUIUtilty.timeOUt(10);
			driver.findElement(By.xpath("//*[contains(@class,'styles__StyledImage')]")).click();

			logInfo.pass("First product is clicked");
			
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));			
			driver.findElement(By.xpath("//*[@class='size_option_wrapper']")).click();
			
			driver.findElement(By.xpath("//button[contains(@class,'add_to_cart')]")).click();
			logInfo.pass("Product added to bag");
			WebUIUtilty.timeOUt(5);
		
		driver.findElement(By.className("button-link")).click();
		
			
			
			
		
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}
	}
	
	@Then("^the user should be able to place an order successfully with checkout page validations$")
	public void the_user_should_be_able_to_place_an_order_successfully_with_checkout_page_validations() {
		ExtentTest logInfo=null;
		try {
			
			logInfo=test.createNode(new GherkinKeyword("Then"), "the_user_should_be_able_to_place_an_order_successfully_with_checkout_page_validations");
			
			
			String priceincheckOut=driver.findElement(By.xpath("//span[contains(@class,'bag-order-amount right')]")).getText();
			if(driver.getCurrentUrl().contains(".com")) {
				if(priceincheckOut.contains("$")) {
					
					logInfo.pass("Amount is in USD");
					
					logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
					
				}
				else
				{
					logInfo.fail("Incorrect amount is displayed");
					
					logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
				}
				
				
			}
			else {
				
if(priceincheckOut.contains("CAD")) {
					
					logInfo.pass("Amount is in CAD");
					
					logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
					
				}
				else
				{
					logInfo.fail("Incorrect amount is displayed");
					
					logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
				}
				
			}
			
			driver.findElement(By.xpath("//*[@alt='Proceed to Checkout']")).click();
			
			Actions login = new Actions(driver);
			login.sendKeys(driver.findElement(By.xpath("//*[@id='loginEmail']")), email).build().perform();
			login.sendKeys(driver.findElement(By.xpath("//*[@id='loginPwd']")), password).build().perform();
			driver.findElement(By.xpath("//*[contains(@id,'loginFormSubmit')]")).click();
			
			logInfo.pass("User logged in");
			
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
			
			String pageSource=driver.getPageSource().toLowerCase();
			if(pageSource.contains("shipping address")) {
				
				logInfo.pass("Shipping address is present");
				
				logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
			}
			else {
				
				logInfo.fail("Shipping address is not present");
				
				logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
			}
			
if(pageSource.contains("billing address")) {
				
				logInfo.pass("billing address is present");
				
				logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
			}
			else {
				
				logInfo.fail("billing address is not present");
				
				logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
			}

if(pageSource.contains("contact information")) {
	
	logInfo.pass("contact information is present");
	
	logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
}
else {
	
	logInfo.fail("contact information is not present");
	
	logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
}
if(pageSource.contains("shipping method")) {
	
	logInfo.pass("Shipping method is present");
	
	logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
}
else {
	
	logInfo.fail("Shipping method is not present");
	
	logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
}	

driver.findElement(By.xpath("//*[@id='btnContinueCheckout']")).click();

if(pageSource.contains("payment")) {
	
	logInfo.pass("payment section is present");
	
	logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
}
else {
	
	logInfo.fail("payment section is not present");
	
	logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
}
		
driver.close();
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}
		
	}
	
	@Given("^the user is in home page to verify search$")
	public void the_user_is_in_home_page_to_verify_search()  {
		ExtentTest logInfo=null;
		try {
			test = extent.createTest(Feature.class, "Sanity Functionality");							
			test=test.createNode(Scenario.class, "Verify User is able to do a search");						
			logInfo=test.createNode(new GherkinKeyword("Given"), "the_user_is_in_home_page_to_verify_search");
			driver = WebUIUtilty.launchBrowser(browser);
			driver.get(ScriptHelper.getURL());
			driver.findElement(By.xpath("//button[@aria-label='Close']")).click();
			landingPage = new HomePage(driver);
			
			logInfo.pass("User is in Home Page");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));			
			
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}
			
	}	
	
	
	@When("^the user enters the SKU ID and hits enter$")
	public void the_user_enters_the_SKU_ID_and_hits_enter()  {
		ExtentTest logInfo=null;
		try {
								
			logInfo=test.createNode(new GherkinKeyword("When"), "the_user_enters_the_SKU_ID_and_hits_enter");
	
			WebUIUtilty.timeOUt(10);
			driver.findElement(By.xpath("//*[@id='Men-menu']")).click();
			WebUIUtilty.timeOUt(5);
			driver.findElement(By.xpath("//*[contains(@id,'tile_')]")).click();
			String itemID=driver.findElement(By.xpath("//*[@class='style_number']")).getText();
			 productID=itemID.substring(8, 12);
			
			 driver.findElement(By.xpath("//span[text()=' Search ']")).click();
			Actions searchProduct = new Actions(driver);
			searchProduct.sendKeys(productID, Keys.ENTER).build().perform();
			logInfo.pass("Search icon is clicked");
			
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
			
			
	} catch (AssertionError | Exception e) {
		testStepHandle("FAIL",driver,logInfo,e);			
	}
	}
	
	@Then("^the appropriate search results should be displayed$")
	public void the_appropriate_search_results_should_be_displayed() {
		ExtentTest logInfo=null;
		try {
								
			logInfo=test.createNode(new GherkinKeyword("Then"), "the_appropriate_search_results_should_be_displayed");
			String pageSource=driver.getPageSource();
			if(pageSource.contains(productID)) {
				
				logInfo.pass("Correct search results are displayed");
				
				logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
			}
			
			else {
				
				
				logInfo.fail("Incorrect search results are displayed");
				
				logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
				
			}
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}
		
	}
	
	@When("^the user enters the product name and hits enter$")
	public void the_user_enters_the_product_name_and_hits_enter()  {
		ExtentTest logInfo=null;
		try {
					
			logInfo=test.createNode(new GherkinKeyword("When"), "the_user_enters_the_product_name_and_hits_enter");
			WebUIUtilty.timeOUt(10);
			driver.findElement(By.xpath("//*[@id='Men-menu']")).click();
			WebUIUtilty.timeOUt(5);
			driver.findElement(By.xpath("//*[contains(@id,'tile_')]")).click();
			productTitle=driver.findElement(By.xpath("//*[@class='title']")).getText();
			 driver.findElement(By.xpath("//span[text()=' Search ']")).click();
				Actions searchProduct = new Actions(driver);
				searchProduct.sendKeys(productTitle, Keys.ENTER).build().perform();
				logInfo.pass("Search icon is clicked");
				
				logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
					
			
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}
	}
	
	@Then("^the appropriate search result should be displayed$")
	public void the_appropriate_search_result_should_be_displayed() {
		ExtentTest logInfo=null;
		try {
								
			logInfo=test.createNode(new GherkinKeyword("Then"), "the_appropriate_search_result_should_be_displayed");
			String pageSource=driver.getPageSource();
			if(pageSource.contains(productTitle)) {
				
				logInfo.pass("Correct search results are displayed");
				
				logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
			}
			
			else {
				
				
				logInfo.fail("Incorrect search results are displayed");
				
				logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
				
			}
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}
		
	}
	
	
	@When("the user enters the brandname and hits enter")
	public void the_user_enters_the_brandname_and_hits_enter() {
		ExtentTest logInfo=null;
		try {
					
			logInfo=test.createNode(new GherkinKeyword("When"), "the_user_enters_the_brand_name_and_hits_enter");
			WebUIUtilty.timeOUt(10);
			
		
			 driver.findElement(By.xpath("//span[text()=' Search ']")).click();
				Actions searchProduct = new Actions(driver);
				searchProduct.sendKeys("katabatic", Keys.ENTER).build().perform();
				logInfo.pass("Search icon is clicked");
				
				logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
					
			
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}
	}
	
	
	@Then("^the appropriate brand products should be displayed$")
	public void the_appropriate_brand_products_should_be_displayed() {
		ExtentTest logInfo=null;
		try {
								
			logInfo=test.createNode(new GherkinKeyword("Then"), "the_appropriate_brand_products_should_be_displayed");
			String pageSource=driver.getPageSource();
			if(pageSource.contains("katabatic")) {
				
				logInfo.pass("Correct search results are displayed");
				
				logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
			}
			
			else {
				
				
				logInfo.fail("Incorrect search results are displayed");
				
				logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
				
			}
			
		
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}
		
	}
	
	@When("the user enters the invalid keyword and hits enter")
	public void the_user_enters_the_invalid_keyword_and_hits_enter() {
		ExtentTest logInfo=null;
		try {
					
			logInfo=test.createNode(new GherkinKeyword("When"), "the_user_enters_the_invalid_keyword_and_hits_enter");
			WebUIUtilty.timeOUt(10);

			 driver.findElement(By.xpath("//span[text()=' Search ']")).click();
				Actions searchProduct = new Actions(driver);
				searchProduct.sendKeys("invalid keyword", Keys.ENTER).build().perform();
				logInfo.pass("Search icon is clicked");
				
				logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
					
			
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}
	}
	
	
	@Then("^the error message should be displayed$")
	public void the_error_message_should_be_displayed() {
		ExtentTest logInfo=null;
		try {
								
			logInfo=test.createNode(new GherkinKeyword("Then"), "the_error_message_should_be_displayed");
			String pageSource=driver.getPageSource();
			if(pageSource.contains("katabatic")) {
				
				logInfo.pass("Error message is displayed");
				
				logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
			}
			
			else {
				
				
				logInfo.fail("Error message is not displayed");
				
				logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
				
			}
			
			driver.close();
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}
		
	}
	@Given("^user enters the url on chrome$")
	public void user_enters_the_url_on_chrome() throws Throwable {
		ExtentTest logInfo=null;
		try {
			logInfo=test.createNode(new GherkinKeyword("Given"), "user_enters_the_url_on_chrome");
			driver = WebUIUtilty.launchBrowser("Chrome");
			driver.get(ScriptHelper.getURL());
			driver.findElement(By.xpath("//button[@aria-label='Close']")).click();
			landingPage = new HomePage(driver);
			
			logInfo.pass("user_enters_the_url_on_chrome");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));			
			
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}
	}

	@Then("^user enters the url on FF$")
	public void user_enters_the_url_on_FF() throws Throwable {
		ExtentTest logInfo=null;
		try {
			logInfo=test.createNode(new GherkinKeyword("Given"), "user_enters_the_url_on_chrome");
			driver = WebUIUtilty.launchBrowser("Firefox");
			driver.get(ScriptHelper.getURL());
			driver.findElement(By.xpath("//button[@aria-label='Close']")).click();
			landingPage = new HomePage(driver);
			
			logInfo.pass("user_enters_the_url_on_chrome");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));			
			
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}  
	}
	@Then("^user enters the url on IE$")
	public void user_enters_the_url_on_IE() throws Throwable {
		ExtentTest logInfo=null;
		try {
			logInfo=test.createNode(new GherkinKeyword("Then"), "user_enters_the_url_on_chrome");
			driver = WebUIUtilty.launchBrowser("InternetExplorer");
			driver.get(ScriptHelper.getURL());
			driver.findElement(By.xpath("//button[@aria-label='Close']")).click();
			landingPage = new HomePage(driver);
			
			logInfo.pass("user_enters_the_url_on_chrome");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));			
			
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}  
	}



		
	}	

	