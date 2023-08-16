package com.FK.Listeners;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.FK.base.ScriptHelper;
import com.FK.util.LoadConfigFile;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import static com.FK.base.BaseFactory.reportFolder;
import static com.FK.base.BaseFactory.setReportPath;
import static com.FK.base.ScriptHelper.createReportFile;
import static com.FK.base.ScriptHelper.getConvertedDate;

public class ExtentReportListeners {


	public static ExtentHtmlReporter report = null;
	public static ExtentReports extent = null;
	public static ExtentTest test = null;
	static WebDriver driver;

	public static ExtentReports setUp() {

		StringBuilder FILE_NAME = new StringBuilder();
		String dt = "EBSanityTestReport_"+ getConvertedDate();
		FILE_NAME.append(dt.replace(":", "_").replace(" ", "_"));
		reportFolder = System.getProperty("user.dir") +"/"+ reportFolder + FILE_NAME + "/";
		reportFolder = reportFolder.replace("./","");
		FILE_NAME.append(".html").toString();
		File dirFile = new File(reportFolder + "screenshots");
		dirFile.mkdirs();
		setReportPath(reportFolder);
		createReportFile(FILE_NAME);
		String reportLocation = reportFolder.substring(0,reportFolder.length()).replaceAll("/","\\\\")+ FILE_NAME.toString();
		report = new ExtentHtmlReporter(reportLocation);		
		report.config().setDocumentTitle("Automation Test Report");
		report.config().setReportName("Automation Test Report");
		report.config().setTheme(Theme.STANDARD);		
		System.out.println("Extent Report location initialized . . .");
		report.start();
		extent = new ExtentReports();
		extent.attachReporter(report);		
		extent.setSystemInfo("Application", "FK");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		System.out.println("System Info. set in Extent Report");		
		return extent;
	}
	
	public static void testStepHandle(String teststatus,WebDriver driver,ExtentTest extenttest,Throwable throwable) {
		switch (teststatus) {
		case "FAIL":		
			extenttest.fail(MarkupHelper.createLabel("Test Case is Failed : ", ExtentColor.RED));			
			extenttest.error(throwable.fillInStackTrace());
			
			try {
				extenttest.addScreenCaptureFromPath(captureScreenShot(driver));
				} catch (IOException e) {
				e.printStackTrace();
				}
			
			if (driver != null) {
				driver.quit();
			}		
		
			break;
			
		case "PASS":			
			extenttest.pass(MarkupHelper.createLabel("Test Case is Passed : ", ExtentColor.GREEN));
			break;
			
		default:
			break;
		case "SKIP":			
			extenttest.skip(MarkupHelper.createLabel("Test Case is Skipped : ", ExtentColor.BLUE));
			break;
		}
		}
	
	public static String captureScreenShot(WebDriver driver) throws IOException {
		TakesScreenshot screen = (TakesScreenshot) driver;
		File src = screen.getScreenshotAs(OutputType.FILE);
		String filePath = reportFolder + "screenshots//"  +  getTimeStamp() + ".png";
		File target = new File(filePath);
		FileUtils.copyFile(src, target);
		return filePath;
	}
	private static String getTimeStamp() {
		 SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String date = sd.format(new Date());
		 String date1 = date.replaceAll("[^0-9]", "");
		 return date1;
		 
	 }

	
}