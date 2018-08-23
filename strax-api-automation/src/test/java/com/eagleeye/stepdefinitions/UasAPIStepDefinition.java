package com.eagleeye.stepdefinitions;

import java.net.MalformedURLException;
import java.util.Map;

import com.eagleeye.services.AppTicket;
import com.eagleeye.services.BaseService;
import com.eagleeye.services.MapLayers;
import com.eagleeye.services.UAS;
import com.jayway.restassured.response.Response;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UasAPIStepDefinition extends BaseService{
	public Response res;
	Map appTicket;
	UAS layer = new UAS(requestSpec);
	
	@Given("^The STRAX UAS API is authenticated with user \"([^\"]*)\" and \"([^\"]*)\"$")
	public void apiAuth(String username, String password) throws MalformedURLException {
		appTicket = AppTicket.getAppTicket(username, password);
		System.out.println("in step definition");

	}
	@When("^User request the UAS information with GET method$")
	public void getIcons() throws MalformedURLException {

		
		res = layer.getAllUAS(appTicket);
	}
	@Then("^The UAS API should return valid response and status as 200$")
	public void verifyResponse() throws MalformedURLException {
		res.then().statusCode(200);
		res.getBody().asString();
		//DatabaseConnection conn =  new DatabaseConnection();
		//conn.getAccount();
	}
	

}
