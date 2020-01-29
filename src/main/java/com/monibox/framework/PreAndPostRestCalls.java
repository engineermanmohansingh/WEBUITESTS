package com.monibox.framework;

import static com.monibox.helperUtils.ConfigPropertyReader.getProperty;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import com.monibox.restassuredCoreUtils.RequestLibrary;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@Listeners({ com.monibox.restassuredCoreUtils.ExtentReportListenerForApis.class })

public class PreAndPostRestCalls {
	// Global Setup Variables
	public String path;
	public String basePathTerm;
	public RequestSpecification request = RestAssured.given();
	public Header xAuthToken;
	public RequestLibrary reqLib;
	public Response response;

	public void init() {

	}

	@BeforeClass
	public void setBaseURI() {
		RestAssured.baseURI = getProperty("RESTURI");	
		xAuthToken = new Header("X-Auth-Token",getProperty("xAuthToken"));
		reqLib = new RequestLibrary();

		init();
	}

	public String getBasePath() {
		return (basePathTerm);
	}

	@BeforeMethod
	public void preTestInitiation() {
		
		request = RestAssured.given();
	}

	@AfterClass
	public void resetBaseURI() {
		request.baseUri("");
	}

	//@AfterMethod
	public void resetBasePath() {
		request.basePath("");
	}

}
