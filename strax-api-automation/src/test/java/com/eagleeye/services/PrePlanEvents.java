package com.eagleeye.services;

import java.net.MalformedURLException;

import com.google.common.net.HttpHeaders;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class PrePlanEvents extends BaseService {
	
	public PrePlanEvents(RequestSpecification requestSpec) throws MalformedURLException
	{
		this.requestSpec = RestAssured.given().contentType("application/json");
	}
	
	public Response getPrePlanEvents() throws MalformedURLException
	{
		String requestURL = BASEURI+"/api/eventplans";
		Response response = requestSpec.header(HttpHeaders.AUTHORIZATION,getHawkId(requestURL,"GET")).given().get(requestURL);
		//System.out.println(response.getBody().asString());
		return response;
	}

}
