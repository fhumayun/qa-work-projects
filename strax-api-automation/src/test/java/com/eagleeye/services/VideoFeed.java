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
		return response;
	}

	@SuppressWarnings("unchecked")
	public Response updateFeed(Map appTicket,String feedName) throws ParseException, MalformedURLException {
		String requestURL = BASEURI+"/api/media/videofeed";
		 response = requestSpec.header(HttpHeaders.AUTHORIZATION,AppTicket.getHawkId(requestURL,"GET",appTicket))
	        		.given().contentType("application/json").get(requestURL);
		 
		String videoFeedDocId = parser.getDocID(response,"name",feedName);
		System.out.println("Feed Name..."+videoFeedDocId);
		obj.put("klvPort","3355");
		obj.put("live",true);
		obj.put("frameRate",111);
		obj.put("capture",false);
		obj.put("cameraType","KLV");
		obj.put("feedType","uav");
		obj.put("klv","false");
		obj.put("account","000000000000000000000002");
		obj.put("wowzaStreamPort","2255");
		obj.put("_id",videoFeedDocId);
		String updateRequestURL = BASEURI + "/api/media/videofeed/media";
		System.out.println("object..."+obj.toString());
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

	public Response deleteFeed(Map appTicket, String feedName) throws ParseException {
		String requestURL = BASEURI+"/api/media/videofeed";
		String videoFeedDocId="";
		 try {
			response = requestSpec.header(HttpHeaders.AUTHORIZATION,AppTicket.getHawkId(requestURL,"GET",appTicket))
			    		.given().contentType("application/json").get(requestURL);
			videoFeedDocId = parser.getDocID(response,"name",feedName);
			 String deleteRequestURL = BASEURI+"/api/media/videofeed/"+videoFeedDocId;
			requestSpec = RestAssured.given().contentType("application/json");
			response = requestSpec.header(HttpHeaders.AUTHORIZATION,AppTicket.getHawkId(deleteRequestURL,"DELETE",appTicket))
						.given().config(RestAssured.config()
								.encoderConfig(ec.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
						.contentType("application/json").delete(deleteRequestURL);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			return response;
		 
	}

}
