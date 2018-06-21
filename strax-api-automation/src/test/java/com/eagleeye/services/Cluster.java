package com.eagleeye.services;

import java.net.MalformedURLException;

import com.google.common.net.HttpHeaders;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class Cluster extends BaseService {
	
	public Cluster(RequestSpecification requestSpec) throws MalformedURLException
	{
		this.requestSpec = RestAssured.given().contentType("application/json");
	    this.requestSpec.header(HttpHeaders.AUTHORIZATION, getHawkId("https://qa.strax.co/api/clusters","GET"));
	}
	
	public void getCluster()
	{
		Response response = requestSpec.given().get("https://qa.strax.co/api/clusters");
		System.out.println(response.getBody().asString());
	}

}
