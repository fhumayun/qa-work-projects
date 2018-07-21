package com.eagleeye.stepdefinitions;

import java.net.MalformedURLException;
import java.util.Map;
import com.eagleeye.services.Account;
import com.eagleeye.services.BaseService;
import com.jayway.restassured.response.Response;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class AccountsAPIStepDefinition extends BaseService{
	public Response res;
	@Given("^The STRAX Accounts API end point is available$")
	public void openAPI() {
		System.out.println("in step definition");

	}
	@When("^User reuests the accounts information with GET method$")
	public void getAccountAPI(Map appTicket) throws MalformedURLException {

		Account acc = new Account(requestSpec);
		res = acc.getAccount(appTicket);
	}
	@Then("^The API should return valid response and status as 200$")
	public void verifyResponse() throws MalformedURLException {
		res.then().statusCode(200);
		res.getBody().asString();
		//DatabaseConnection conn =  new DatabaseConnection();
		//conn.getAccount();
	}

}
