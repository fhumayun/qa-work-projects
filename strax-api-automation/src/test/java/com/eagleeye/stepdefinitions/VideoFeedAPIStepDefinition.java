package com.eagleeye.stepdefinitions;

import java.net.MalformedURLException;
import java.util.Map;

import org.json.simple.parser.ParseException;

import com.eagleeye.services.AppTicket;
import com.eagleeye.services.BaseService;
import com.eagleeye.services.MapLayers;
import com.eagleeye.services.VideoFeed;
import com.jayway.restassured.response.Response;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class VideoFeedAPIStepDefinition extends BaseService{
	public Response res;
	Map appTicket;
	VideoFeed feed = new VideoFeed(requestSpec);
	@Given("^The STRAX video feed API is authenticated with user \"([^\"]*)\" and \"([^\"]*)\"$")
	public void apiAuth(String username, String password) throws MalformedURLException {
		appTicket = AppTicket.getAppTicket(username, password);
		System.out.println("in step definition");

	}
	@When("^User request the video feed information with GET method$")
	public void getVideoFeedAPI() throws MalformedURLException {

		res = feed.getAllVideoFeeds(appTicket);
	}
	@When("^User request the video feed from mobile information with GET method$")
	public void getTelemetryAPI() throws MalformedURLException {

		res = feed.getMobileFeed(appTicket);
	}
	@Then("^The media API should return valid response and status as 200$")
	public void verifyResponse() throws MalformedURLException {
		res.then().statusCode(200);
		res.getBody().asString();
		//DatabaseConnection conn =  new DatabaseConnection();
		//conn.getAccount();
	}
	@When("^User request to create a new video feed with POST method$")
	public void createFeed() throws MalformedURLException {

	
		res = feed.createFeed(appTicket);
	}
	@Then("^The video feed API should create a new feed and return status as 201$")
	public void verifyCreateFeedResponse() throws MalformedURLException {
		res.then().statusCode(201);
		res.getBody().asString();
		//DatabaseConnection conn =  new DatabaseConnection();
		//conn.getAccount();
	}
	@When("^User request to update a existing video feed \"([^\"]*)\" with PUT method$")
	public void updateFeed(String feedName) throws MalformedURLException, ParseException {

	
		res = feed.updateFeed(appTicket,feedName);
	}
	@When("^User request to delete an existing video feed \"([^\"]*)\" with DELETE method$")
	public void deleteFeed(String feedName) throws MalformedURLException, ParseException {

	
		res = feed.deleteFeed(appTicket,feedName);
	}
	@Then("^The video feed API should update an existing feed and return status as 201$")
	public void verifyUpdateFeedResponse() throws MalformedURLException {
		res.then().statusCode(201);
		res.getBody().asString();
		//DatabaseConnection conn =  new DatabaseConnection();
		//conn.getAccount();
	}
	@Then("^The video feed API should delete an existing feed and return status as 201$")
	public void verifyDeleteFeedResponse() throws MalformedURLException {
		res.then().statusCode(204);
		res.getBody().asString();
		//DatabaseConnection conn =  new DatabaseConnection();
		//conn.getAccount();
	}
}
