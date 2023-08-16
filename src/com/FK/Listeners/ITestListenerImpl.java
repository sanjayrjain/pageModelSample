package com.FK.Listeners;

import com.aventstack.extentreports.ExtentTest;
import com.FK.base.BaseFactory;
import com.FK.base.ScriptHelper;
import com.FK.util.LoadConfigFile;
import org.jsoup.Connection;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import org.testng.TestRunner;

public class ITestListenerImpl extends ExtentReportListeners implements ITestListener{

	private static ExtentReports extent;
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		LoadConfigFile.getInstance();
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("PASS");
		extent.flush();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("FAIL");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("SKIP");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onStart(ITestContext context) {
		System.out.println("Execution started on Production env...");
		extent= setUp();
	}

	public void onFinish(ITestContext context) {
		System.out.println("Execution completed on Production env...");
		extent.flush();		
		System.out.println("Generated Report. . .");
	}
}
