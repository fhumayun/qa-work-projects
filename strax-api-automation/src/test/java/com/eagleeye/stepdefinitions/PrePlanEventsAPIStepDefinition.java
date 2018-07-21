package com.eagleeye.stepdefinitions;

import java.net.MalformedURLException;
import java.util.Map;
import com.eagleeye.services.BaseService;
import com.eagleeye.services.PrePlanEvents;
import com.jayway.restassured.response.Response;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PrePlanEventsAPIStepDefinition extends BaseService{
	public Response res;
	@Given("^The STRAX pre-plan event API end point is available$")
	public void openAPI() {
		System.out.println("in step definition");

	}
	@When("^User reuests the event plan information with GET method$")
	public void getPrePlanEvent(Map appTicket) throws MalformedURLException {

		PrePlanEvents pevents = new PrePlanEvents(requestSpec);
		res = pevents.getPrePlanEvents(appTicket);
	}
	@Then("^The pre-plan event API should return valid response and status as 200$")
	public void verifyResponse() throws MalformedURLException {
		res.then().statusCode(200);
		res.getBody().asString();
		//DatabaseConnection conn =  new DatabaseConnection();
		//conn.getAccount();
	}

}
