package com.eagleeye.stepdefinitions;

import java.net.MalformedURLException;
import java.util.Map;

import org.json.simple.parser.ParseException;

import com.eagleeye.services.BaseService;
import com.eagleeye.services.Cluster;
import com.eagleeye.services.AppTicket;
import com.eagleeye.services.Participants;
import com.eagleeye.utils.PropertiesFileReader;
import com.jayway.restassured.response.Response;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ParticipantsAPIStepDefinition extends BaseService{
	public Response res;
	Participants ps = new Participants(requestSpec);
	PropertiesFileReader prreader = new PropertiesFileReader();
	String BaseURI = prreader.getPropertyvalues("STRAXUrl");
	Map appTicket;
	
	@Given("^The STRAX participant API end point is available$")
	public void openAPI() {
		System.out.println("in step definition");

	}
	@Given("^The STRAX API is authenticated with user \"([^\"]*)\" and \"([^\"]*)\"$")
	public void apiAuth(String username, String password) throws MalformedURLException {
		appTicket = AppTicket.getAppTicket(username, password);

	}
	@When("^User requests the create new participant with POST method with valid data$")
	public void addParticipantAPI() throws MalformedURLException, ParseException {

		//Participants ps = new Participants(requestSpec);
		res = ps.createParticipants(appTicket);
	}
	@Then("^The new participant should get created and return status code as 200$")
	public void verifyResponse() throws MalformedURLException {
		res.then().statusCode(200);
	}
	@When("^User reuests the unlock a participant \"([^\"]*)\" with POST method with valid participantDocId$")
	public void unlockParticipantAPI(String loginId) throws MalformedURLException, ParseException {

		//Participants ps = new Participants(requestSpec);
		res = ps.unlockParticipant(appTicket,loginId);
	}
	@Then("^The participant should get unlocked and return status code as 200$")
	public void verifyUnlockResponse() throws MalformedURLException {
		res.then().statusCode(200);
	}
@When("^User request single participant \"([^\"]*)\" with GET method with valid participantDocId$")
public void getSingleParticipantAPI(String loginId) throws MalformedURLException, ParseException {

	//Participants ps = new Participants(requestSpec);
	res = ps.getSingleParticipant(appTicket,loginId);
}
@Then("^The participant API should return single participant details and return status code as 200$")
public void verifyGetParticipantResponse() throws MalformedURLException {
	res.then().statusCode(200);
}
@When("^User deletes a participant \"([^\"]*)\" with DELETE method with valid participantDocId$")
public void deleteParticipantAPI(String loginId) throws MalformedURLException, ParseException {

	res = ps.deleteParticipant(appTicket,loginId);
}
@Then("^The participant should get deleted and return status code as 200$")
public void verifyDeleteParticipantResponse() throws MalformedURLException {
	res.then().statusCode(500);
}
@When("^User updates participant \"([^\"]*)\" details with PUT method with valid data$")
public void updateParticipantAPI(String loginId) throws MalformedURLException, ParseException {

	//Participants ps = new Participants(requestSpec);
	res = ps.updateParticipant(appTicket,loginId);
}
@Then("^The participant details should get updated successfully and return status code as 200$")
public void verifyUpdateParticipantResponse() throws MalformedURLException {
	res.then().statusCode(200);
}
@When("^User change password of a participant \"([^\"]*)\" with POST method$")
public void changePasswordAPI(String loginId) throws MalformedURLException, ParseException {

	//Participants ps = new Participants(requestSpec);
	res = ps.changePassword(appTicket,loginId);
}
@Then("^The password should get changed successfully and return status code as 200$")
public void verifyChangePasswordResponse() throws MalformedURLException {
	res.then().statusCode(200);
}
}
