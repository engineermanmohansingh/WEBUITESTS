package com.monibox.framework;

import java.io.File;

import org.openqa.selenium.Platform;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/*
 * https://www.swtestacademy.com/extent-reports-version-3-reporting-testng/
 * */
public class ExtentManager {

	private static ExtentReports extent;
	private static Platform platform;
	private static String reportFileName = "Extent-Test-Report.html";
	private static String path = System.getProperty("user.dir") +File.separator + "TestReport";
	private static String FileLoc = path + File.separator + reportFileName;

	public static ExtentReports getInstance() {
		if (extent == null) {
			createInstance();
		}
		return extent;
	}

	public static ExtentReports createInstance() {
		String fileName = getReportFileLocation();
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle(fileName);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName(fileName);
		htmlReporter.config().setCSS(".r-img { width : 30%; }");
		htmlReporter.config().enableTimeline(true);
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		return extent;
	}

	/**************************************************************************
	 * Custom code for determining file path location based on OS/environment
	 **************************************************************************/

	private static String getReportFileLocation() {
		String reportFileLocation = FileLoc;
		createReportPath(path);
		System.out.println(FileLoc);
		return reportFileLocation;
	}


	
	//Create the report path if it does not exist
    private static void createReportPath (String path) {
        File testDirectory = new File(path);
        if (!testDirectory.exists()) {
            if (testDirectory.mkdir()) {
                System.out.println("Directory: " + path + " is created!" );
            } else {
                System.out.println("Failed to create directory: " + path);
            }
        } else {
            System.out.println("Directory already exists: " + path);
        }
    }

}