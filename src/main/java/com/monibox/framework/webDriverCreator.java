package com.monibox.framework;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.google.common.io.Files;
import com.monibox.seleniumCoreUtils.PageLibrary;


public class webDriverCreator {

	protected  WebDriver driver;
	private final browserFactory wdfactory;
	String datafileloc ;
	String screenShotUrl;
	static String browser;
	static int timeout;
	Map<String, Object> chromeOptions = null;
	DesiredCapabilities capabilities;

	/**
	 * Initializing the Selenium Core and other (new) utility objects here. 
	 */
	
	public PageLibrary page;
	
	
	

	public WebDriver getDriver() {
		return this.driver;
	}




	

	public webDriverCreator(String browserName) {
		wdfactory = new browserFactory();
		browser = browserName;
		testInitiator(browserName);
	}

	private void testInitiator(String browserName) {  //---<<---<<---The manifestation method 
		_configureBrowser(browserName); 
		_initPage();
	
//		launchApplication("");
	}

	/**
	 * Page object Instantiation done 
	 */
	
	private void _initPage() {
		
		page = new PageLibrary(driver);
	
	}
	
	
	private void _configureBrowser(String browserName) {
		
		
		driver = wdfactory.getDriver(browserName);
		driver.manage().window().maximize();
	
		
		driver.manage()
				.timeouts()
				.implicitlyWait(Integer.parseInt("30"),
						TimeUnit.SECONDS);
	}



	

	public void launchApplication(String baseurl) {
		deleteAllCookies();
		System.out.println("inside Browser details");
		driver.get(baseurl);
		System.out.println("\nThe application url is :- " + baseurl);
		//handleSSLCertificateCondition();
	}
	
	

	public void openUrl(String url) {
		driver.get(url);
	}

	public void closeBrowserSession() {
		driver.quit();
	}

	public void deleteAllCookies() {
		driver.manage().deleteAllCookies();
	}

	public void closeBrowserWindow() {
		driver.close();
	}

	 public void takeScreenshotOfFailureFromLocalMachine(ITestResult result) {
	        Calendar calendar = Calendar.getInstance();
	        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
	        String methodName = result.getName();
	        if (!result.isSuccess()) {
	            try {
	                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	                File savedScreenshotFile = new File("target/failsafe-reports/screenshots/"
	                        + methodName + "_" + formater.format(calendar.getTime()) + ".jpg");
	                Files.copy(scrFile, savedScreenshotFile);
	               String filePath = savedScreenshotFile.toString();
	               screenShotUrl = "<td><a href='" + System.getProperty("user.dir")+File.separator+filePath
	                       + "'><img src='" +System.getProperty("user.dir")+File.separator+filePath
	                       + "' height='100' width='100' /></a></td>";
	               Reporter.log(screenShotUrl,true);
	               
//	               page.logMessage("Saved screenshot at --> "+System.getProperty("user.dir")+File.separator+filePath);
	          
	            } catch (Exception ex) {
	            	System.out.println("Uh oh !, An exception occurred while capturing screenshot!");
	                ex.printStackTrace();
	            }
	        }
	    }
	
	
}
