package com.eagleeye.stepdefinitions;

import java.net.MalformedURLException;
import java.util.Map;

import com.eagleeye.services.AppTicket;
import com.eagleeye.services.BaseService;
import com.eagleeye.services.Cluster;
import com.eagleeye.services.MapLayers;
import com.jayway.restassured.response.Response;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MapLayersAPIStepDefinition extends BaseService{
	public Response res;
	Map appTicket;
	
	@Given("^The STRAX map layer API is authenticated with user \"([^\"]*)\" and \"([^\"]*)\"$")
	public void apiAuth(String username, String password) throws MalformedURLException {
		appTicket = AppTicket.getAppTicket(username, password);
		System.out.println("in step definition");

	}
	@When("^User request the map layers information with GET method$")
	public void getAccountAPI() throws MalformedURLException {

		MapLayers layer = new MapLayers(requestSpec);
		res = layer.getAllMapLayers(appTicket);
	}
	@When("^User request all the gis icons with GET method$")
	public void getIcons() throws MalformedURLException {

		MapLayers layer = new MapLayers(requestSpec);
		res = layer.getAllIcons(appTicket);
	}
	@Then("^The map layer API should return valid response and status as 200$")
	public void verifyResponse() throws MalformedURLException {
		res.then().statusCode(200);
		res.getBody().asString();
		//DatabaseConnection conn =  new DatabaseConnection();
		//conn.getAccount();
	}
	@When("^User request to create a new map layers with POST method$")
	public void createMapLayerAPI() throws MalformedURLException {

		MapLayers layer = new MapLayers(requestSpec);
		res = layer.createMapLayers(appTicket);
	}

}
