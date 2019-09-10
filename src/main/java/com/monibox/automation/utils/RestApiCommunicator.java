package com.monibox.automation.utils;


import static com.monibox.automation.ExtentTestNGITestListener.test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
public class RestApiCommunicator {

	public void RestCallFactory()  {
        ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(UriBuilder.fromUri("http://localhost:8080/").build());
	
		
		String req = "{ " + 
				"   \"callerDetails\":{  " + 
				"      \"username\":\"admin\"," + 
				"      \"databaseIdentifier\" : \"SonataDatasource\"" + 
				"   }," + 
				"   \"country\":{  " + 
				"      \"code\":\"UK\"" + 
				"   }," + 
				"   \"caution\":\"accept\"" + 
				"}";
		Markup codeMark = MarkupHelper.createCodeBlock(req,CodeLanguage.JSON);
		test.get().info(codeMark);
		
		ClientResponse response = service.path("sonata/rest/sbs/sysTestabilityService").path("setCountryOfOperation").type(MediaType.APPLICATION_JSON).post(ClientResponse.class, req);
		codeMark = MarkupHelper.createCodeBlock(response.getEntity(String.class),CodeLanguage.JSON);
		test.get().pass(codeMark);
		
		
	}
}
