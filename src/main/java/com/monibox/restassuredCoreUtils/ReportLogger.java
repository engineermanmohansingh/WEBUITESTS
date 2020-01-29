package com.monibox.restassuredCoreUtils;

import static com.monibox.restassuredCoreUtils.ExtentReportListenerForApis.test;

import org.testng.Reporter;

import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ReportLogger {
//	Markup codeMark = MarkupHelper.createCodeBlock(req,CodeLanguage.JSON);

	public void logInfo(String message) {
		Reporter.log( message,true);
	    test.get().info(message);
	    
	}
	public void logSuccess(String message) {
		Reporter.log("✔  "  + message,true);
	  	 test.get().pass(message);
	  	  
	}
	public void logWarning(String message) {
		Reporter.log("⚠  "  + message,true);
		 test.get().warning(message);
		    
	}
	
	public void logError(String message) {
		Reporter.log("❌ "  + message,true);
		 test.get().fail(message);
	
	}
	
	public void logJSONRequest(String req) {
		Markup codeMark = MarkupHelper.createCodeBlock(req,CodeLanguage.JSON);
		test.get().info(codeMark);
		
	}
	
	public void logJSONResponseAsPass(String req) {
		Markup codeMark = MarkupHelper.createCodeBlock(req,CodeLanguage.JSON);
		test.get().pass(codeMark);
	
	}
	public void logJSONResponseAsWarn(String req) {
		Markup codeMark = MarkupHelper.createCodeBlock(req,CodeLanguage.JSON);
		test.get().warning(codeMark);
	
	}
	
	public void logJSONResponseAsFail(String req) {
		if(req.contains("<html>")) {
			Markup codeMark = MarkupHelper.createCodeBlock(req,CodeLanguage.XML);
			test.get().fail(codeMark);
			return;
		}
		Markup codeMark = MarkupHelper.createCodeBlock(req,CodeLanguage.JSON);
		test.get().fail(codeMark);
	
	}
}
