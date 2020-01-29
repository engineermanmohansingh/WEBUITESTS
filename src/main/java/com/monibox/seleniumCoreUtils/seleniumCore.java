/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monibox.seleniumCoreUtils;



import static com.monibox.framework.ExtentTestNGITestListener.test;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;



/**
 *
 * @author Manmohan Singh
 */
public class seleniumCore {

    protected WebDriver driver;
    public SeleniumWait wait;
	
    protected seleniumCore(WebDriver driver) {

        this.driver = driver;

        this.wait = new SeleniumWait(driver, Integer.parseInt("30"));
     
    }

   
    public void logMessage(String message) {
    	
        Reporter.log(message, true);
        test.get().info(message);
    }
    public void logSuccessMessage(String message){
    	Reporter.log("✔  "+message,true);
    	 test.get().pass(message);
    }
     public void logWarning(String message){
    	Reporter.log("⚠  "+message,true);
    	 test.get().warning(message);
    	   	
    }

    
  //___________________________________________________________________________________________
    /*
     * Wrapper methods for handling javascript executions
     * 
     */

    public void executeJavascript(String script) {
    	logMessage("Executing Javascript");
    	logMessage(script);
        ((JavascriptExecutor) driver).executeScript(script);
    }

    public Object executeJavascriptObject(Object script) {

        return ((JavascriptExecutor) driver).executeScript(script.toString());
    }

    public String executeJavascriptWithReturnValue(String script) {
        return ((JavascriptExecutor) driver).executeScript("return " + script).toString();
    }

   
    public void clickUsingXpathInJavaScriptExecutor(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);

    }

    public void sendKeysUsingXpathInJavaScriptExecutor(WebElement element,
            String text) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].setAttribute('value', '" + text
                + "')", element);
    }
        
    
    public void handleAlert() {
        try {
            switchToAlert().accept();
            logMessage("Alert handled..");
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            System.out.println("No Alert window appeared...");
        }
    }

    public String getAlertText() {
        try {
            Alert alert = switchToAlert();
            String alertText = alert.getText();
            logMessage("Alert message is " + alertText);
            //alert.accept();
            return alertText;
        } catch (Exception e) {
            System.out.println("No Alert window appeared...");
            return null;
        }
    }

    public String getAlertTextAndCancelIt() {
        try {
            Alert alert = switchToAlert();
            String alertText = alert.getText();
            logMessage("Alert message is " + alertText);
            alert.dismiss();
            return alertText;
        } catch (Exception e) {
            System.out.println("No Alert window appeared...");
            return null;
        }
    }

    public Alert switchToAlert() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        return wait.until(ExpectedConditions.alertIsPresent());
    }
    
    public void waitForElementToDisplay(By locator){
    	  WebDriverWait wait = new WebDriverWait(driver, 60);
    	  wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }  
    
    public void waitForElementToBeInteractive(By locator) {
    	WebDriverWait wait = new WebDriverWait(driver, 60);
  	  wait.until(ExpectedConditions.elementToBeClickable(locator));
  	
    }
    
    
   
    public void holdExecution(int milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
        	Reporter.log("Failed while trying to halt execution!");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


 
}
