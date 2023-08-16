package com.FK.base;

import net.lightbody.bmp.BrowserMobProxy;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class SuiteListener implements IInvokedMethodListener {

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {

	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		if (!testResult.isSuccess()) {
			 
			CoreBase.reportVP(Status.FAIL.toString(), "Exception occured." + testResult.getThrowable());
		}
		
		if (method.isTestMethod()) {
			WebDriver driver = ScriptHelper.getDriver();
			if (driver != null) {
				System.out
						.println("#### MESSAGE::WebDriver-Instance closed successfully. Hashcode:" + driver.hashCode());
				driver.quit();
				BaseFactory.proxy.stop();
			}
		}
	}
}
