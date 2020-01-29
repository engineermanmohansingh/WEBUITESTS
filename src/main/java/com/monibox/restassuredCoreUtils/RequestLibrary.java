package com.monibox.restassuredCoreUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.testng.TestNGException;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class RequestLibrary extends ReportLogger {

	public Response GET(RequestSpecification request,String basePath) {

		logInfo("GET :"+RestAssured.baseURI+basePath);
		Response response = request.get(basePath);
		if (response.statusCode() == 200 && (response.getBody().asString().length()>2)) {
			logSuccess(response.getStatusCode()+":"+basePath);
			logJSONResponseAsPass(response.getBody().asString());
		} else if(response.statusCode() == 200 && (response.getBody().asString().length()<=2)){
			logWarning("Empty response body! No data received!");
			logJSONResponseAsWarn(response.getBody().asString());
			
		}
		else {
			logError(response.getStatusCode()+":"+basePath);			
			logJSONResponseAsFail(response.getBody().asString());
			throw new TestNGException("Expected status code not retrieved, the status code retrieved was:"+response.getStatusCode());
		}

		return response;

	}

	public Response POST(RequestSpecification request, String basePath, JSONObject body) {
		Response response;
		logInfo("POST :"+RestAssured.baseURI+ basePath);
		logJSONRequest(body.toString());
		request.body(body);
		response = request.post();
		if (response.statusCode() == 200) {
			logSuccess(response.getStatusCode()+":"+RestAssured.baseURI+basePath);
			logJSONResponseAsPass(response.prettyPrint());
		} else {
			logWarning(response.getStatusCode()+":"+RestAssured.baseURI+basePath);
			logJSONResponseAsFail(response.prettyPrint());
			throw new TestNGException("Expected status code not retrieved, the status code retrieved was:"+response.getStatusCode());
		}
		return response;
	}

	public Response PUT(String path, Map<String, String> params) {
		Response response;
		logInfo("PUT :" + path);
		logJSONRequest(params.toString());

		response = RestAssured.put(path, params);
		if (response.statusCode() == 200) {
			logSuccess(response.getStatusCode()+":"+path);
			logJSONResponseAsPass(response.prettyPrint());
		} else {
			logWarning(response.getStatusCode()+":"+path);
			logJSONResponseAsFail(response.prettyPrint());
			throw new TestNGException("Expected status code not retrieved, the status code retrieved was:"+response.getStatusCode());
		}
		return response;
	}

	public Response DELETE(RequestSpecification request, String URI) {
		Response response;
		logInfo("DELETE :" + URI);

		response = request.delete(URI);
		if (response.statusCode() == 204) {
			logSuccess(response.getStatusCode()+":"+URI);
			logJSONResponseAsPass(response.prettyPrint());
		} else {
			logWarning(response.getStatusCode()+":"+URI);
			logJSONResponseAsFail(response.prettyPrint());
			throw new TestNGException("Expected status code not retrieved, the status code retrieved was:"+response.getStatusCode());
		}
		return response;
	}
	
	public Response PATCH(RequestSpecification request, String basePath, JSONObject body) {
		Response response;
		logInfo("PATCH :"+RestAssured.baseURI+ basePath);
		logJSONRequest(body.toString());
		request.body(body);
		response = request.patch(basePath);
		if (response.statusCode() == 200) {
			logSuccess(response.getStatusCode()+RestAssured.baseURI+basePath);
			logJSONResponseAsPass(response.prettyPrint());
		} else {
			logWarning(response.getStatusCode()+RestAssured.baseURI+basePath);
			logJSONResponseAsFail(response.prettyPrint());
			throw new TestNGException("Expected status code not retrieved, the status code retrieved was:"+response.getStatusCode());
		}
		return response;
	}
	
	public void verifyResponse(Response response,String value) {
		
	}
	
	public String getValueFromJSON(Response response,String path) {
		logInfo("Extracting "+path+" from response");
	return	response.jsonPath().get(path).toString();
	}
	
	public String getValueFromJSONArray(Response response,String path) {
		logInfo("Extracting "+path+" from response");
		
		String value = null;
		List<Object> incomingData =  response.jsonPath().getList("");
		for(Object mapData:incomingData) {
			Map<String,String> mymap = (Map<String, String>) mapData;
			value = mymap.get(path);
		}
		logInfo("Extracted "+path+" : "+value);
	return value;
		
	}
	
	public void clearJSONobject(JSONObject json) {
		Iterator keys = json.keys();
		while(keys.hasNext())
		  json.remove((String)json.keys().next());
		
	}
	
	
}
