package com.eagleeye.stepdefinitions;

import java.net.MalformedURLException;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.junit.Assert;

import com.eagleeye.services.BaseService;
import com.eagleeye.services.Cluster;
import com.eagleeye.services.AppTicket;
import com.eagleeye.services.Participants;
import com.eagleeye.utils.JsonParser;
import com.jayway.restassured.response.Response;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ClustersAPIStepDefinition extends BaseService {
	public Response res;
	Map appTicket;
	JsonParser parser =  new JsonParser();
	Cluster cl = new Cluster(requestSpec);

	@Given("^The STRAX Cluster API is authenticated with user \"([^\"]*)\" and \"([^\"]*)\"$")
	public void apiAuth(String username, String password) throws MalformedURLException {
		appTicket = AppTicket.getAppTicket(username, password);
	}

	@Given("^The STRAX cluster API end point is available$")
	public void openAPI() {

	}

	@When("^User request the events information with GET method$")
	public void getAllClusterAPI() throws MalformedURLException {

		res = cl.getCluster(appTicket);
	}
	
	@When("^User requests scribe notes of an event with GET method for \"([^\"]*)\" and \"([^\"]*)\"$")
	public void getScribeNotes(String eventName, String loginId) throws MalformedURLException {

		res = cl.getScribeNotes(appTicket,eventName,loginId);
	}
	@Then("^The scribe notes API should return all available notes and return status code as 200$")
	public void verifyGetScribeResponse() throws MalformedURLException {
		res.then().statusCode(200);
	}
	@When("^User requests to add a new scribe notes to an event with POST method for \"([^\"]*)\" and \"([^\"]*)\"$")
	public void addScribeNotes(String eventName, String loginId) throws MalformedURLException {

		res = cl.addScribeNote(appTicket,eventName,loginId);
	}
	@Then("^The scribe notes API should add a new note to the event and return status code as 200$")
	public void verifyPostScribeResponse() throws MalformedURLException {
		res.then().statusCode(200);
	}

	@Then("^The cluster API should return all available clusters in response and return status code as 200$")
	public void verifyResponse() throws MalformedURLException {
		res.then().statusCode(200);
	}

	@When("^User requests the create new cluster with POST method with valid data$")
	public void createClusterAPI() throws MalformedURLException, ParseException {

		res = cl.createCluster(appTicket);
	}

	@Then("^The cluster API should create a new cluster and return status as 200$")
	public void verifyCreateResponse() throws MalformedURLException {
		res.then().statusCode(201);
	}

	@When("^User deletes the events \"([^\"]*)\" with DELETE method$")
	public void deleteClusterAPI(String eventName) throws MalformedURLException, ParseException {

		res = cl.deleteCluster(appTicket, eventName);
	}

	@Then("^The delete cluster API should delete the cluster and return status code as 200$")
	public void verifyDeleteResponse() throws MalformedURLException {
		res.then().statusCode(200);
	}

	@When("^User escalate an IER to active event when user is already assigned to an active event$")
	public void escalateIERAPI() throws MalformedURLException, ParseException {

		Participants p = new Participants(requestSpec);
		res = p.createParticipants(appTicket);
	}

	@Then("^the API should return status code as 500$")
	public void verifyIEREscalateResponse() throws MalformedURLException {
		res.then().statusCode(200);
	}

	@When("^User reuests get chat of an event \"([^\"]*)\" with GET method$")
	public void getChat(String eventName) throws MalformedURLException, ParseException {

		res = cl.getChatMsg(appTicket,eventName);
	}

	@Then("^The chat API should return all chat messages of an event and return status code as 200$")
	public void verifyChateResponse() throws MalformedURLException {
		res.then().statusCode(200);
	}

	@When("^User reuests event plan details with GET method$")
	public void getEventPlan() throws MalformedURLException, ParseException {

		res = cl.getEventPlans(appTicket);
	}

	@Then("^The event plan API should return all available event plans and return status code as 200$")
	public void verifyEventPlanResponse() throws MalformedURLException {
		res.then().statusCode(200);

	}

	@When("^User\"([^\"]*)\" requests the join cluster \"([^\"]*)\" with POST method with valid data$")
	public void joinEvent(String event, String participant) throws MalformedURLException, ParseException {

		res = cl.joinEvent(appTicket, event, participant);
	}

	@Then("^The join event API should add a participant to active event and return status as 200$")
	public void verifyEventJoinResponse() throws MalformedURLException {
		res.then().statusCode(200);

	}

	@When("^User requests to update an existing cluster \"([^\"]*)\" with PUT method with valid data$")
	public void updateEvent(String eventName) throws MalformedURLException, ParseException {

		res = cl.updateEvent(appTicket, eventName);
	}

	@Then("^The cluster API should update the cluster and return status as 200$")
	public void verifyEventUpdateResponse() throws MalformedURLException {
		res.then().statusCode(200);
	}
	@When("^User reuests event messages details with GET method$")
	public void getEventMessages() throws MalformedURLException, ParseException {

		res = cl.getEventMessages(appTicket);
	}

	@Then("^The event message API should return all available messages and return status code as 200$")
	public void verifyEventMessagesResponse() throws MalformedURLException {
		res.then().statusCode(200);
	}
	@When("^User reuests notes of an event \"([^\"]*)\" with GET method$")
	public void getEventNotes(String eventName) throws MalformedURLException, ParseException {

		res = cl.getEventNotes(appTicket,eventName);
	}

	@Then("^The notes API should return all notes of an event and return status code as 200$")
	public void verifyEventNotesResponse() throws MalformedURLException {
		res.then().statusCode(200);
	}
	@When("^User reuests Geotags of an event \"([^\"]*)\" with GET method$")
	public void getEventGeoTags(String eventName) throws MalformedURLException, ParseException {

		res = cl.getEventGeoTags(appTicket,eventName);
	}

	@Then("^The notes API should return all Geotags of an event and return status code as 200$")
	public void verifyEventGeoTagsResponse() throws MalformedURLException {
		res.then().statusCode(200);
	}
	@When("^User requests the events information of \"([^\"]*)\" with GET method$")
	public void getClusterAPI(String eventName) throws MalformedURLException, ParseException {

		res = cl.getSpecificCluster(appTicket,eventName);
	}

	@Then("^The GET cluster API should return all the cluster details and return status code as 200$")
	public void verifygetSpecificClusterResponse() throws MalformedURLException {
		res.then().statusCode(200);
	}
	@And("^The participant list should have callsign name for \"([^\"]*)\" along with other details$")
	public void verifyCallsignResponse(String loginId) throws MalformedURLException, ParseException {

		Assert.assertTrue(cl.verifyCallsignNamePresent(appTicket,res,loginId));
	}
	@When("^User \"([^\"]*)\" requests to promote a scribe note to chat channel for \"([^\"]*)\" with valid request body using POST method$")
	public void promoteScribeToChatAPI(String userName,String eventName) throws MalformedURLException, ParseException {

		res = cl.promoteScribeToChat(appTicket,userName,eventName);
	}

	@Then("^the response should have JSON <promoted> object which should have a boolean property <chat>$")
	public void verifyPromotedTrue() throws MalformedURLException, ParseException {
		Assert.assertNotNull(parser.getPropertyObject(res, "promoted"));
	}
	@And("^return the statusCode as 200$")
	public void verifyScribePromotedResponse() throws MalformedURLException, ParseException {
		res.then().statusCode(200);
	}

}
