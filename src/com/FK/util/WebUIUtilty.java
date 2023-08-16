package com.FK.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebUIUtilty {
	
	 static WebDriver driver;
		
	public static WebDriver launchBrowser(String browserName) {
		String browserstackflag = LoadConfigFile.getValue("BrowserStackRunFlag");
		String USERNAME = LoadConfigFile.getValue("BrowserStackUsername");
		String ACCESS_KEY = LoadConfigFile.getValue("BrowserStackPassword");
		String BSURL = "https://" + USERNAME + ":" + ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";

		if(browserName.equalsIgnoreCase("chrome")) {
		// getLocator("chomreDriverexePath")

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--user-agent=" + LoadConfigFile.getValue("UserAgent"));
			options.addArguments("disable-infobars");
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-popup-blocking");
			options.addArguments("--start-maximized");


			if(browserstackflag.equalsIgnoreCase("Yes")){
				options.setCapability("browser", browserName);
				options.setCapability("browser_version", LoadConfigFile.getValue("BrowserVersion"));
				options.setCapability("os", LoadConfigFile.getValue("DefaultPlatform"));
				options.setCapability("os_version", LoadConfigFile.getValue("OSVersion"));
				options.setCapability("resolution", LoadConfigFile.getValue("BrowserStackResolution"));
				options.setCapability("name", "Eddie Bauer : "+ LoadConfigFile.getValue("Environment").toUpperCase()+" - Desktop Sanity Test Suite");
				options.setCapability("acceptSslCerts", "true");
				options.setCapability("browserstack.debug", "true");
				options.setCapability("browserstack.local", "false");
				options.setCapability("browserstack.console", "verbose");
				options.setCapability("browserstack.networkLogs", "true");
				try {
					driver = new RemoteWebDriver(new URL(BSURL),options);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				System.out.println("INFO: 	Running on Browser Stack now..!!!!!!");

			}else{
				System.setProperty("webdriver.chrome.driver", "resources\\chromedriver.exe");
				driver = new ChromeDriver(options);
			}

		}else if(browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "resources\\geckodriver.exe");
			 driver = new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("internetexplorer")) {
			System.setProperty("webdriver.IE.driver", "resources\\IEDriverServer.exe");
			 driver = new FirefoxDriver();
		}
   		driver.manage().window().maximize();
   		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
   		return driver;
	}
	
	
	public static void lauchApplication(String url) {
		driver.navigate().to(url);
	}
	
	
	
	
	public static void timeOUt(long seconds) {
		long milliseconds = seconds*1000;
		try {
			Thread.sleep(milliseconds);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void acceptOrDismissAlert(String acceptOrDismiss) {
		if(acceptOrDismiss.equalsIgnoreCase("Accept")) {
			  Alert alertObj = driver.switchTo().alert();
		        alertObj.accept();
		}else if(acceptOrDismiss.equalsIgnoreCase("Dismiss")) {
			  Alert alertObj = driver.switchTo().alert();
		        alertObj.dismiss();
		}
	}
	
	public static String getTextOfAlert() {
		 Alert alertObj = driver.switchTo().alert();
		 String alertText = alertObj.getText();
		 return alertText;
		
	}
	
	
	
	 public static WebUIUtilty scrollArrowDown(int numberOfTimes) {
  	   Actions actionObj = new Actions(driver);
  	   for(int i=0;i<numberOfTimes;++i) {
  		   actionObj.sendKeys(Keys.ARROW_DOWN).build().perform();
  	   }
  	   WebUIUtilty obj = new WebUIUtilty();
  	   return obj;
  	
     }
     

     public static void scrollArrowUP(int numberOfTimes) {
  	   Actions actionObj = new Actions(driver);
  	   for(int i=0;i<numberOfTimes;++i) {
  		   actionObj.sendKeys(Keys.ARROW_UP).build().perform();
  	   }
  	
     }
     
     public static void pressTab(int numberOfTimes) {
  	   Actions actionObj = new Actions(driver);
  	   for(int i=0;i<numberOfTimes;++i) {
  		   actionObj.sendKeys(Keys.TAB).build().perform();
  	   }
     }
     
     
     public static void scrollPageDown(int numberOfTimes) {
  	   Actions actionObj = new Actions(driver);
  	   for(int i=0;i<numberOfTimes;++i) {
  		   actionObj.sendKeys(Keys.PAGE_DOWN).build().perform();
  	   }
  	
     }
     
     public static void scrollPageUP(int numberOfTimes) {
  	   Actions actionObj = new Actions(driver);
  	   for(int i=0;i<numberOfTimes;++i) {
  		   actionObj.sendKeys(Keys.PAGE_UP).build().perform();
  	   }
  	
     }
     
    public static void pressEnter() {
 	   Actions actionObj = new Actions(driver);
		   actionObj.sendKeys(Keys.ENTER).build().perform();
    }
    
		 public static String getTimeStamp() {
			 SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 String date = sd.format(new Date());
			 String date1 = date.replaceAll("[^0-9]", "");
			 return date1;
			 
		 }

}
