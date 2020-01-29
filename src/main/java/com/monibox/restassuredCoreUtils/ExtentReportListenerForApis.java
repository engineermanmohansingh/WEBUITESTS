package com.monibox.restassuredCoreUtils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.monibox.framework.ExtentManager;

public class ExtentReportListenerForApis  implements ITestListener {
	private static ExtentReports extent = ExtentManager.getInstance();
	private static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	public synchronized void onStart(ITestContext context) {
		System.out.println("Extent Reports Version 3 Test Suite started!");
		ExtentTest parent = extent.createTest(context.getName());
		parentTest.set(parent);
	}

	public synchronized void onFinish(ITestContext context) {
		System.out.println(("Extent Reports Version 3  Test Suite is ending!"));
		extent.flush();
	}

	public synchronized void onTestStart(ITestResult result) {
		System.out.println((result.getMethod().getDescription() + " started!"));
		ExtentTest child = parentTest.get().createNode(result.getMethod().getDescription());
		test.set(child);
	
	}

	public synchronized void onTestSuccess(ITestResult result) {
		System.out.println((result.getMethod().getDescription() + " passed!"));
		test.get().pass("Test passed");
		
	}

	public synchronized void onTestFailure(ITestResult result) {
		
       
	}

	public synchronized void onTestSkipped(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " skipped!"));
		test.get().skip(result.getThrowable());
	}

	public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}
}
