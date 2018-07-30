package com.eagleeye.stepdefinitions;

import java.net.MalformedURLException;
import java.util.Map;

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
	
	@Given("^The STRAX video feed API is authenticated with user \"([^\"]*)\" and \"([^\"]*)\"$")
	public void apiAuth(String username, String password) throws MalformedURLException {
		appTicket = AppTicket.getAppTicket(username, password);
		System.out.println("in step definition");

	}
	@When("^User request the video feed information with GET method$")
	public void getVideoFeedAPI() throws MalformedURLException {

		VideoFeed feed = new VideoFeed(requestSpec);
		res = feed.getAllVideoFeeds(appTicket);
	}
	@When("^User request the video feed from mobile information with GET method$")
	public void getTelemetryAPI() throws MalformedURLException {

		VideoFeed feed = new VideoFeed(requestSpec);
		res = feed.getMobileFeed(appTicket);
	}
	@Then("^The media API should return valid response and status as 200$")
	public void verifyResponse() throws MalformedURLException {
		res.then().statusCode(200);
		res.getBody().asString();
		//DatabaseConnection conn =  new DatabaseConnection();
		//conn.getAccount();
	}
}
