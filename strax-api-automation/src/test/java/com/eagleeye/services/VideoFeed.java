package com.eagleeye.services;

import java.net.MalformedURLException;
import java.util.Map;

import com.google.common.net.HttpHeaders;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class VideoFeed extends BaseService {
	
	public VideoFeed(RequestSpecification requestSpec)
	{
		this.requestSpec = RestAssured.given().contentType("application/json");
				
	}

	public Response getAllVideoFeeds(Map appTicket) throws MalformedURLException {
		String requestURL = BASEURI+"/api/media/videofeed/available";
		Response response = requestSpec.header(HttpHeaders.AUTHORIZATION,AppTicket.getHawkId(requestURL,"GET",appTicket)).given().get(requestURL);
		System.out.println("Response from the API end point : "+response.getBody().asString());
		return response;
	}

	public Response getMobileFeed(Map appTicket) throws MalformedURLException {
		String requestURL = BASEURI+"/api/media/videofeed/mobile";
		Response response = requestSpec.header(HttpHeaders.AUTHORIZATION,AppTicket.getHawkId(requestURL,"GET",appTicket)).given().get(requestURL);
		System.out.println("Response from the API end point : "+response.getBody().asString());
		return response;
	}

}
