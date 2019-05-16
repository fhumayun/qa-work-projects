package com.eagleeye.services;

import java.net.MalformedURLException;
import java.util.Map;

import com.eagleeye.utils.DatabaseConnection;
import com.eagleeye.utils.PropertiesFileReader;
import com.google.common.net.HttpHeaders;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class Account extends BaseService{

	PropertiesFileReader prreader;
	public Account(RequestSpecification requestSpec)
	{
		this.requestSpec = RestAssured.given().contentType("application/json");
	    prreader = new PropertiesFileReader();
	}
	
	public Response getAccount(Map appTicket) throws MalformedURLException
	{
		String requestURL = BASEURI+"/api/accounts";
		Response response = requestSpec.header(HttpHeaders.AUTHORIZATION,AppTicket.getHawkId(requestURL,"GET",appTicket)).given().get(requestURL);
		//System.out.println(response.getBody().asString());
		return response;
	}
	
}
