package com.eagleeye.stepdefinitions;

import java.net.MalformedURLException;

import com.eagleeye.services.Account;
import com.eagleeye.services.BaseService;
import com.eagleeye.services.Cluster;
import com.eagleeye.utils.DatabaseConnection;
import com.jayway.restassured.response.Response;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ClustersAPIStepDefinition extends BaseService{
	public Response res;
	@Given("^The STRAX cluster API end point is available$")
	public void openAPI() {
		System.out.println("in step definition");

	}
	@When("^User reuests the events information with GET method$")
	public void getAccountAPI() throws MalformedURLException {

		Cluster cl = new Cluster(requestSpec);
		res = cl.getCluster();
	}
	@Then("^The cluster API should return valid response and status as 200$")
	public void verifyResponse() throws MalformedURLException {
		res.then().statusCode(200);
		res.getBody().asString();
		DatabaseConnection conn =  new DatabaseConnection();
		conn.getAccount();
	}

}
