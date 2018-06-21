package com.eagleeye.services;

import java.net.MalformedURLException;

import com.eagleeye.utils.DatabaseConnection;
import com.google.common.net.HttpHeaders;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class Account extends BaseService{

	public Account(RequestSpecification requestSpec) throws MalformedURLException
	{
		this.requestSpec = RestAssured.given().contentType("application/json");
	    this.requestSpec.header(HttpHeaders.AUTHORIZATION, getHawkId("https://qa.strax.co/api/accounts","GET"));
	}
	
	public Response getAccount()
	{
		Response response = requestSpec.given().get("https://qa.strax.co/api/accounts");
		System.out.println(response.getBody().asString());
		DatabaseConnection con = new DatabaseConnection();
		con.getAccount();
		return response;
	}
	
}
