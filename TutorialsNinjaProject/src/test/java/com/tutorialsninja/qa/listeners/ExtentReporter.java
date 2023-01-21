package com.tutorialsninja.qa.listeners;

import java.io.File;


import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReporter {

	public static ExtentReports generateExtentReport()
	{
		ExtentReports extentReport = new ExtentReports();
		File extentReportFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\ExtentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
		
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("TestAutomationFrameworkOne Results");
		sparkReporter.config().setDocumentTitle("TestAutomationFrameworkOne Report ");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		extentReport.attachReporter(sparkReporter);
		
		Properties configProp = new Properties();
		File configPropFile = new File(System.getProperty("user.dir")+"\\TestData\\Config.properties");
		try {
		FileInputStream fisConfigProp = new FileInputStream(configPropFile);
		configProp.load(fisConfigProp);
		}
		catch(Throwable e)
		{
			e.printStackTrace();
		}
		
		extentReport.setSystemInfo("Application URL",configProp.getProperty("url"));
		extentReport.setSystemInfo("Browser Name", configProp.getProperty("browserName"));
		extentReport.setSystemInfo("Operating System", System.getProperty("os.version"));
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
		extentReport.setSystemInfo("java.vm.name", System.getProperty("java.vm.name"));
		extentReport.setSystemInfo("java.vm.version", System.getProperty("java.vm.version"));

		return extentReport;
	}
	
}
