package com.eagleeye.services;

import java.net.MalformedURLException;

import com.google.common.net.HttpHeaders;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class Cluster extends BaseService {
	
	public Cluster(RequestSpecification requestSpec)
	{
		this.requestSpec = RestAssured.given().contentType("application/json");
				
	}
	
	public Response getCluster() throws MalformedURLException
	{
		String requestURL = BASEURI+"/api/clusters/5b2d0cff4470860a7714f82e";
		Response response = requestSpec.header(HttpHeaders.AUTHORIZATION,getHawkId(requestURL,"GET")).given().get(requestURL);
		//System.out.println(response.getBody().asString());
		return response;
	}

}
