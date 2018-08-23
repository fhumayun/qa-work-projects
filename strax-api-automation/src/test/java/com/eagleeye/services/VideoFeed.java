package com.eagleeye.services;

import java.net.MalformedURLException;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.eagleeye.utils.JSONFileReader;
import com.eagleeye.utils.JsonParser;
import com.google.common.net.HttpHeaders;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.EncoderConfig;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class VideoFeed extends BaseService {
	
	Response response;
	JsonParser parser = new JsonParser();
	JSONFileReader reader = new JSONFileReader();
	EncoderConfig ec = new EncoderConfig();
	JSONObject obj = new JSONObject();
	
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

	public Response createFeed(Map appTicket) throws MalformedURLException {
		String requestURL = BASEURI + "/api/media/videofeed";
		obj = reader.jsonReader("src/test/resources/testData/VideoFeed_post.json");
		System.out.println("Response from the API end point : " + obj.toString());
		response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(requestURL, "POST", appTicket))
				.given()
				.config(RestAssured.config()
						.encoderConfig(ec.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
				.contentType("application/json").body(obj).post(requestURL);
		System.out.println(response.getBody().asString());
		return response;
	}

	public Response updateFeed(Map appTicket,String feedName) throws ParseException, MalformedURLException {
		String requestURL = BASEURI+"/api/media/videofeed";
		 response = requestSpec.header(HttpHeaders.AUTHORIZATION,AppTicket.getHawkId(requestURL,"GET",appTicket))
	        		.given().contentType("application/json").get(requestURL);
		String videoFeedDocId = parser.getDocumentID(response,feedName);
		obj.put("klvPort","3355");
		String updateRequestURL = BASEURI + "/api/media/videofeed/media"+videoFeedDocId;
		//obj = reader.jsonReader("src/test/resources/testData/VideoFeed_post.json");
		//System.out.println("Response from the API end point : " + obj.toString());
		response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(updateRequestURL, "PUT", appTicket))
				.given()
				.config(RestAssured.config()
						.encoderConfig(ec.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
				.contentType("application/json").body(obj).put(updateRequestURL);
		System.out.println(response.getBody().asString());
		return response;
	}

}
