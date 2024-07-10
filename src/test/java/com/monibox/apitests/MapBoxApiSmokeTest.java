package com.monibox.apitests;

import org.json.JSONObject;
import org.testng.annotations.Test;

import com.monibox.framework.PreAndPostRestCalls;
import com.monibox.helperUtils.PropFileHandler;

import io.restassured.http.ContentType;

public class MapBoxApiSmokeTest extends PreAndPostRestCalls {
	PropFileHandler propFile;
	JSONObject formData;
//	public void init() {
//		 propFile = new PropFileHandler("apiRequestData");
//		 formData = new JSONObject();
//	}
	
	@Test(description="I should be able to create a user data set at mapbox")
	public void TC01_createDataSet() {
		basePathTerm="datasets/v1/manmohan786";
		formData.put("name","U.S. East coast");
		formData.put("description", "Data points around United states east coast region");
		request.contentType(ContentType.JSON);
		request.header("ContentType","application/json");
		response = reqLib.POST(request, getBasePath(), formData);
		propFile.writeToFile("newDatasetId",reqLib.getValueFromJSON(response, "id"));
		reqLib.clearJSONobject(formData);
	}

	
	@Test(description="I should be able to get user data set from mapbox endpoint")
	public void TC02_getDataSet() {
		basePathTerm="datasets/v1/manmohan786";
		response = reqLib.GET(request,getBasePath());
		propFile.writeToFile("datasetId", reqLib.getValueFromJSONArray(response, "id"));

	}
	
	@Test(description="I should be able to update details of existing data set")
	public void TC03_updateDataSet() {
		basePathTerm="datasets/v1/manmohan786/"+propFile.readProperty("datasetId");
		formData.put("name","U.S. West coast");
		formData.put("description", "Data points around United states west coast region");
		request.contentType(ContentType.JSON);
		response = reqLib.PATCH(request,getBasePath(), formData);
	}
	
	@Test(description="I should be able to delete the dataset")
	public void TC04_deleteDataSet() {
		basePathTerm="datasets/v1/manmohan786/"+propFile.readProperty("datasetId");
		reqLib.DELETE(request, getBasePath());
	}
	@Test(description="I should be able to create a user data set at mapbox")
	public void TC05_createDataSetWithUnauthorizedAccess() {
		basePathTerm="datasets/v1/manmohan786";
		response = reqLib.GET(request,basePathTerm); //Running the get request without access token.
		
	}
	
	
	
}
