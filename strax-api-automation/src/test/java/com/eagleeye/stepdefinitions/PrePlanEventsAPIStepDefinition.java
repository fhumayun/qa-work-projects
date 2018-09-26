package com.eagleeye.stepdefinitions;

import java.net.MalformedURLException;
import java.util.Map;

import org.json.simple.parser.ParseException;

import com.eagleeye.services.AppTicket;
import com.eagleeye.services.BaseService;
import com.eagleeye.services.Cluster;
import com.eagleeye.services.PrePlanEvents;
import com.jayway.restassured.response.Response;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PrePlanEventsAPIStepDefinition extends BaseService {
	public Response res;
	Map appTicket;
	PrePlanEvents pplan = new PrePlanEvents(requestSpec);

	@Given("^The STRAX pre plan API is authenticated with user \"([^\"]*)\" and \"([^\"]*)\"$")
	public void apiAuth(String username, String password) throws MalformedURLException {
		appTicket = AppTicket.getAppTicket(username, password);
	}

	@When("^User request the pre plan information with GET method$")
	public void getPrePlanEvent() throws MalformedURLException {

		res = pplan.getPrePlanEvents(appTicket);
	}

	@Then("^The pre plan API should return all available pre plans in response and return status code as 200$")
	public void verifyResponse() throws MalformedURLException {
		res.then().statusCode(200);

	}

	@When("^User requests the create new pre plan with POST method with valid data$")
	public void createPrePlanEvent() throws MalformedURLException, ParseException {

		res = pplan.createPrePlanEvents(appTicket);
	}

	@Then("^The pre plan API should create a new pre plan and return status as 201$")
	public void verifyCreatePrePLanResponse() throws MalformedURLException {
		res.then().statusCode(201);

	}

	@When("^User requests to update an existing pre plan \"([^\"]*)\" with PUT method with valid data$")
	public void updatePrePlanEvent(String eventPlanName) throws MalformedURLException, ParseException {

		res = pplan.updatePrePlanEvents(appTicket, eventPlanName);
	}

	@Then("^The pre plan API should update the pre plan and return status as 200$")
	public void verifyUpdatePrePLanResponse() throws MalformedURLException {
		res.then().statusCode(200);

	}

	@When("^User deletes the pre plan \"([^\"]*)\" with DELETE method$")
	public void deletePrePlanEvent(String eventPlanName) throws MalformedURLException, ParseException {

		res = pplan.deletePrePlanEvents(appTicket, eventPlanName);
	}

	@Then("^The delete pre plan API should delete the pre plan and return status code as 200$")
	public void verifyDeletePrePLanResponse() throws MalformedURLException {
		res.then().statusCode(200);

	}

}
