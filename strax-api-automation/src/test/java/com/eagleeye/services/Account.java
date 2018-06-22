package com.eagleeye.services;

import java.net.MalformedURLException;

import com.eagleeye.utils.DatabaseConnection;
import com.eagleeye.utils.PropertiesFileReader;
import com.google.common.net.HttpHeaders;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class Account extends BaseService{

	PropertiesFileReader prreader;
	public Account(RequestSpecification requestSpec) throws MalformedURLException
	{
		this.requestSpec = RestAssured.given().contentType("application/json");
	    this.requestSpec.header(HttpHeaders.AUTHORIZATION, getHawkId(BASEURI+"/api/accounts","GET"));
	    prreader = new PropertiesFileReader();
	}
	
	public Response getAccount()
	{
		Response response = requestSpec.given().get(BASEURI+"/api/accounts");
		System.out.println(response.getBody().asString());
		return response;
	}
	
}
