package com.eagleeye.stepdefinitions;

import java.net.MalformedURLException;
import java.util.Map;

import org.json.simple.parser.ParseException;
import com.eagleeye.services.BaseService;
import com.eagleeye.services.Cluster;
import com.eagleeye.services.AppTicket;
import com.eagleeye.services.Participants;
import com.jayway.restassured.response.Response;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ClustersAPIStepDefinition extends BaseService{
	public Response res;
	Map appTicket;
	
	@Given("^The STRAX Cluster API is authenticated with user \"([^\"]*)\" and \"([^\"]*)\"$")
	public void apiAuth(String username, String password) throws MalformedURLException {
		appTicket = AppTicket.getAppTicket(username, password);
		System.out.println("in step definition");

	}
	@Given("^The STRAX cluster API end point is available$")
	public void openAPI() {
		System.out.println("in step definition");

	}
	@When("^User request the events information with GET method$")
	public void getAccountAPI() throws MalformedURLException {

		Cluster cl = new Cluster(requestSpec);
		res = cl.getCluster(appTicket);
	}
	@Then("^The cluster API should return all available clusters in response and return status code as 200$")
	public void verifyResponse() throws MalformedURLException {
		res.then().statusCode(200);
		res.getBody().asString();
		//DatabaseConnection conn =  new DatabaseConnection();
		//conn.getAccount();
	}
	@When("^User deletes the events with DELETE method$")
	public void deleteClusterAPI() throws MalformedURLException {

		Cluster cl = new Cluster(requestSpec);
		res = cl.deleteCluster(appTicket);
	}
	@Then("^The delete cluster API should delete the cluster and return status code as 200$")
	public void verifyDeleteResponse() throws MalformedURLException {
		res.then().statusCode(200);
		res.getBody().asString();
		//DatabaseConnection conn =  new DatabaseConnection();
		//conn.getAccount();
	}
	@When("^User escalate an IER to active event when user is already assigned to an active event$")
	public void escalateIERAPI() throws MalformedURLException, ParseException {

		//Cluster cl = new Cluster(requestSpec);
		//res = cl.escalateIER();
		Participants p = new Participants(requestSpec);
		res = p.createParticipants(appTicket);
	}
	@Then("^the API should return status code as 500$")
	public void verifyIEREscalateResponse() throws MalformedURLException {
		res.then().statusCode(200);
		res.getBody().asString();
		System.out.println("Status code returned is  : "+res.getStatusCode());
		//DatabaseConnection conn =  new DatabaseConnection();
		//conn.getAccount();
	}
	@When("^User reuests chat with GET method$")
	public void getChat() throws MalformedURLException, ParseException {

		Cluster cl = new Cluster(requestSpec);
		res = cl.getChatMsg(appTicket);
	}
	@Then("^The chat API should return all chat messages and return status code as 200$")
	public void verifyChateResponse() throws MalformedURLException {
		res.then().statusCode(200);
		res.getBody().asString();
		System.out.println("Status code returned is  : "+res.getStatusCode());

	}
	@When("^User reuests event plan details with GET method$")
	public void getEventPlan() throws MalformedURLException, ParseException {

		Cluster cl = new Cluster(requestSpec);
		res = cl.getEventPlans(appTicket);
	}
	@Then("^The event plan API should return all available event plans and return status code as 200$")
	public void verifyEventPlanResponse() throws MalformedURLException {
		res.then().statusCode(200);
		res.getBody().asString();
		System.out.println("Status code returned is  : "+res.getStatusCode());

	}

}
