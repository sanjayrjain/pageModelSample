package com.FK.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.FK.base.CoreBase;
import com.FK.base.ScriptHelper;

public class AbstractPage extends CoreBase {
	
	  public void clickonbag()
	    {
	    	WebElement cart= ScriptHelper.getDriver().findElement(By.xpath("//*[contains(@href,'/checkout/bag.jsp')]"));
	    	explicitWaitVisibilityOfElement(cart, 30);
	    	  JavascriptExecutor executor = (JavascriptExecutor)ScriptHelper.getDriver();
	          executor.executeScript("arguments[0].click();", new Object[]{cart});
	    	
	    }

}
