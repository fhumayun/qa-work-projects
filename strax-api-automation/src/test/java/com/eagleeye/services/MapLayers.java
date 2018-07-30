package com.eagleeye.services;

import java.net.MalformedURLException;
import java.util.Map;

import com.google.common.net.HttpHeaders;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class MapLayers extends BaseService {
	
	public MapLayers(RequestSpecification requestSpec)
	{
		this.requestSpec = RestAssured.given().contentType("application/json");
				
	}

	public Response getAllMapLayers(Map appTicket) throws MalformedURLException {
		String requestURL = BASEURI+"/api/gis/maplayers";
		Response response = requestSpec.header(HttpHeaders.AUTHORIZATION,AppTicket.getHawkId(requestURL,"GET",appTicket)).given().get(requestURL);
		System.out.println("Response from the API end point : "+response.getBody().asString());
		return response;
	}

	public Response createMapLayers(Map appTicket) throws MalformedURLException {
		String requestURL = BASEURI+"/api/gis/maplayers";
		Response response = requestSpec.header(HttpHeaders.AUTHORIZATION,AppTicket.getHawkId(requestURL,"GET",appTicket)).given().get(requestURL);
		System.out.println("Response from the API end point : "+response.getBody().asString());
		return response;
	}

	public Response getAllIcons(Map appTicket) throws MalformedURLException {
		String requestURL = BASEURI+"/api/gis/icons";
		Response response = requestSpec.header(HttpHeaders.AUTHORIZATION,AppTicket.getHawkId(requestURL,"GET",appTicket)).given().get(requestURL);
		System.out.println("Response from the API end point : "+response.getBody().asString());
		return response;
	}

}
