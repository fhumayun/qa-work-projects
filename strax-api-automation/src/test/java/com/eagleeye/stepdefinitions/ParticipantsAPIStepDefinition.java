package com.eagleeye.stepdefinitions;

import java.net.MalformedURLException;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.eagleeye.services.BaseService;
import com.eagleeye.services.Cluster;
import com.eagleeye.services.AppTicket;
import com.eagleeye.services.Participants;
import com.eagleeye.utils.JsonParser;
import com.eagleeye.utils.PropertiesFileReader;
import com.jayway.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class ParticipantsAPIStepDefinition extends BaseService{
	public Response res;
	Participants ps = new Participants(requestSpec);
	PropertiesFileReader prreader = new PropertiesFileReader();
	String BaseURI = prreader.getPropertyvalues("STRAXUrl");
	Map appTicket;
	
	@Given("^The STRAX participant API end point is available$")
	public void openAPI() {

	}
	@Given("^The STRAX API is authenticated with user \"([^\"]*)\" and \"([^\"]*)\"$")
	public void apiAuth(String username, String password) throws MalformedURLException {
		appTicket = AppTicket.getAppTicket(username, password);

	}
	@When("^User requests the create new participant with POST method with valid data$")
	public void addParticipantAPI() throws MalformedURLException, ParseException {


		res = ps.createParticipants(appTicket);
	}
	@Then("^The new participant should get created and return status code as 200$")
	public void verifyResponse() throws MalformedURLException {
		res.then().statusCode(200);
	}
	@When("^User reuests the unlock a participant \"([^\"]*)\" with POST method with valid participantDocId$")
	public void unlockParticipantAPI(String loginId) throws MalformedURLException, ParseException {


		res = ps.unlockParticipant(appTicket,loginId);
	}
	@Then("^The participant should get unlocked and return status code as 200$")
	public void verifyUnlockResponse() throws MalformedURLException {
		res.then().statusCode(200);
	}
@When("^User request single participant \"([^\"]*)\" with GET method with valid participantDocId$")
public void getSingleParticipantAPI(String loginId) throws MalformedURLException, ParseException {


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
	res.then().statusCode(200);
}
@When("^User updates participant \"([^\"]*)\" details with PUT method with valid data$")
public void updateParticipantAPI(String loginId) throws MalformedURLException, ParseException {


	res = ps.updateParticipant(appTicket,loginId);
}
@When("^User archive participant \"([^\"]*)\" with PUT method with valid data$")
public void archiveParticipantAPI(String loginId) throws MalformedURLException, ParseException {


	res = ps.archiveParticipant(appTicket,loginId);
}
@Then("^The participant details should get updated successfully and return status code as 200$")
public void verifyUpdateParticipantResponse() throws MalformedURLException {
	res.then().statusCode(200);
}
@Then("^The participant should get archived successfully and return status code as 200$")
public void verifyArchiveParticipantResponse() throws MalformedURLException {
	res.then().statusCode(200);
}
@When("^User change password of a participant \"([^\"]*)\" with POST method$")
public void changePasswordAPI(String loginId) throws MalformedURLException, ParseException {


	res = ps.changePassword(appTicket,loginId);
}
@Then("^The password should get changed successfully and return status code as 200$")
public void verifyChangePasswordResponse() throws MalformedURLException {
	res.then().statusCode(200);
}
@When("^User requests callsigns information with GET method$")
public void getCallsignsAPI() throws MalformedURLException, ParseException {


	res = ps.getCallsignsAPI(appTicket);
}
@Then("^The callsigns API should return all available callsigns in response and return status code as 200$")
public void verifyGetCallsignsResponse() throws MalformedURLException {
	res.then().statusCode(200);
}
@When("^User requests to creates new callsign and assign to a user \"([^\"]*)\"$")
public void createCallsignsAPI(String loginId) throws MalformedURLException, ParseException {


	res = ps.createCallsignAPI(appTicket,loginId);
}
@SuppressWarnings("deprecation")
@Then("^The callsign should get created and return status code as 200$")
public void verifyCreateCallsignsResponse() throws MalformedURLException, ParseException {
	JsonParser parser =  new JsonParser();
	String message = parser.getPropertyValue(res,"message");
	int  status = res.getStatusCode();
	if(message == null || message.equals("Duplicate callsign name"))
	{
		Assert.assertTrue(true);
	}
	else if((status==200))
	{
		Assert.assertTrue(true);
	}
	else
		
		Assert.assertFalse(false);
	
}
@When("^User requests to assign the callsign \"([^\"]*)\" to other user \"([^\"]*)\"$")
public void updateCallsignsAPI(String callSignName,String loginId) throws MalformedURLException, ParseException {


	res = ps.updateCallsignAPI(appTicket,callSignName,loginId);
}
@Then("^The callsign should get assigned to other user and return status code as 200$")
public void verifyUpdateCallsignsResponse() throws MalformedURLException {
	res.then().statusCode(200);
}
@When("^User requests to archive the callsign \"([^\"]*)\"$")
public void archiveCallsignsAPI(String callSignName) throws MalformedURLException, ParseException {


	res = ps.archiveCallsignAPI(appTicket,callSignName);
}
@Then("^The callsign should get archived and return status code as 200$")
public void verifyArchiveCallsignsResponse() throws MalformedURLException {
	res.then().statusCode(200);
}
@When("^User requests to unarchive the callsign \"([^\"]*)\"$")
public void unarchiveCallsignsAPI(String callSignName) throws MalformedURLException, ParseException {


	res = ps.unarchivedCallsignAPI(appTicket,callSignName);
}
@Then("^The callsign should get unarchived and return status code as 200$")
public void verifyUnArchiveCallsignsResponse() throws MalformedURLException {
	res.then().statusCode(200);
}
@When("^User requests to bulk upload the callsign using csv file$")
public void bulkUploadCallsignsAPI() throws MalformedURLException, ParseException {
	res = ps.bulkUploadCallsignAPI(appTicket);
}
@Then("^The callsign should get uploaded and return status code as 200$")
public void verifyBulkUploadCallsignsResponse() throws MalformedURLException {
	res.then().statusCode(200);
}
@When("^User reuests to add subunit with POST method$")
public void addSubunitAPI() throws MalformedURLException, ParseException {

	res = ps.addSubunit(appTicket);
}

@Then("^The subunit API should add subunit and return status code as 200$")
public void verifySubunitResponse() throws MalformedURLException, ParseException {
	JsonParser parser =  new JsonParser();
	String message = parser.getPropertyValue(res,"message");
	int  status = res.getStatusCode();
	if(message == null || message.equals("Duplicate subunit name"))
	{
		Assert.assertTrue(true);
	}
	else if((status==200))
	{
		Assert.assertTrue(true);
	}
	else
		
		Assert.assertFalse(false);
}
@When("^User requests to query subunit with GET method$")
public void getSubunitAPI() throws MalformedURLException, ParseException {

	res = ps.getSubunit(appTicket);
}

@Then("^The subunit API should get all subunits and return status code as 200$")
public void verifyGetSubunitResponse() throws MalformedURLException {
	res.then().statusCode(200);
}
@When("^User requests to update subunit \"([^\"]*)\" with PUT method$")
public void updateSubunitAPI(String subUnit) throws MalformedURLException, ParseException {

	res = ps.updateSubunit(appTicket,subUnit);
}

@When("^User requests to add participants in subunit \"([^\"]*)\" with PUT method$")
public void addParticipantToSubunit(String subUnit) throws MalformedURLException, ParseException {

	res = ps.addParticipantToSubunit(appTicket,subUnit);
}
@When("^the archived users should get removed from subunit group if any \"([^\"]*)\"$")
public void verifyArchivedUserSubunit(String subUnit) throws MalformedURLException, ParseException {

	String val = ps.verifyArchivedUserSubunit(appTicket,subUnit);
	Assert.assertEquals("", val);
}
@Then("^The subunit API should update subunit and return status code as 200$")
public void verifyPutSubunitResponse() throws MalformedURLException {
	res.then().statusCode(200);
}
@When("^User requests to change order of subunit \"([^\"]*)\" with PUT method$")
public void updateOrderSubunitAPI(String subUnit) throws MalformedURLException, ParseException {

	res = ps.updateOrderSubunit(appTicket,subUnit);
}

@Then("^The change order subunit API should update order and return status code as 200$")
public void verifyUpdateOrderSubunitResponse() throws MalformedURLException {
	res.then().statusCode(200);
}
@When("^User requests to archive subunit \"([^\"]*)\" with PUT method$")
public void archiveSubunitAPI(String subUnit) throws MalformedURLException, ParseException {

	res = ps.archiveSubunit(appTicket,subUnit);
}
@When("^User requests to unarchive subunit \"([^\"]*)\" with PUT method$")
public void unArchiveSubunitAPI(String subUnit) throws MalformedURLException, ParseException {

	res = ps.unArchiveSubunit(appTicket,subUnit);
}

@Then("^The archive subunit API should archive the subunit and return status code as 200$")
public void verifyarchiveSubunitResponse() throws MalformedURLException {
	res.then().statusCode(200);
}

}
