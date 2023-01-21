package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.utils.Utilities;

public class MyListeners extends Base implements ITestListener{

	ExtentReports extentReport;
	ExtentTest extentTest;
	String testName;
	@Override
	public void onStart(ITestContext context) {
		 extentReport = ExtentReporter.generateExtentReport();
	}
	
	
	
	@Override
	public void onTestStart(ITestResult result) {
		 testName = result.getName();
		extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO, testName+" started executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		 testName = result.getName();
        extentTest.log(Status.PASS, testName+" got successfully executed");
	}
	@Override
	public void onTestFailure(ITestResult result) {
		 testName = result.getName();
		 try {
		 
		 String destinationScreenshotPath =Utilities.captureScreenshot(driver, result.getName());
      
		
         
			extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		 }
		 catch (Throwable e) {
			e.printStackTrace();
		}
        extentTest.log(Status.INFO, result.getThrowable());
        extentTest.log(Status.FAIL, testName+ " got failed");
        
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		 testName = result.getName();
        extentTest.log(Status.INFO, result.getThrowable());
        extentTest.log(Status.SKIP, testName+" got skipped");
	}

	

	@Override
	public void onFinish(ITestContext context) {
extentReport.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\ExtentReport.html";
		File extentReport = new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}


