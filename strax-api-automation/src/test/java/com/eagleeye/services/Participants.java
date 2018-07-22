package com.eagleeye.services;

import java.io.FileReader;
import java.net.MalformedURLException;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.eagleeye.utils.DatabaseConnection;
import com.eagleeye.utils.JSONFileReader;
import com.google.common.net.HttpHeaders;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.EncoderConfig;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class Participants extends BaseService {
	
	
	public Participants(RequestSpecification requestSpec)
	{
		this.requestSpec = RestAssured.given().contentType("application/json");
						
	}
	
	@SuppressWarnings("unchecked")
	public Response createParticipants(Map appTicket) throws MalformedURLException, ParseException
	{
		String requestURL = BASEURI+"/api/participants";
		JSONObject obj = new JSONObject();
		JSONFileReader reader = new JSONFileReader(); 
		obj = reader.jsonReader("src/test/resources/testData/participant_post.json");
		EncoderConfig ec = new EncoderConfig();
        Response response = requestSpec.header(HttpHeaders.AUTHORIZATION,AppTicket.getHawkId(requestURL,"POST",appTicket))
        		.given().config(RestAssured.config().encoderConfig(ec.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
        		.contentType("application/json").body(obj).post(requestURL);
		System.out.println("Response from the API end point : "+response.getBody().asString());
		return response;
	}
	public Response unlockParticipant(Map appTicket) throws MalformedURLException, ParseException
	{
		String requestURL = BASEURI+"/api/participants/59c37cd7efabe50001795b20/unlock";
		//Response response = requestSpec.header(HttpHeaders.AUTHORIZATION,getHawkId(requestURL,"POST")).given().contentType(ContentType.JSON).body(jo).when().post(requestURL);
        EncoderConfig ec = new EncoderConfig();
        Response response = requestSpec.header(HttpHeaders.AUTHORIZATION,AppTicket.getHawkId(requestURL,"POST",appTicket))
        		.given().config(RestAssured.config().encoderConfig(ec.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
        		.contentType("application/json").post(requestURL);
		System.out.println("Response from the API end point : "+response.getBody().asString());
		return response;
	}

	public Response getSingleParticipant(Map appTicket,String loginId) throws MalformedURLException {
		
		
		DatabaseConnection con = new DatabaseConnection();
		String docId = con.getParticipantDocId(loginId);
		String requestURL = BASEURI+"/api/participants/"+docId;
        Response response = requestSpec.header(HttpHeaders.AUTHORIZATION,AppTicket.getHawkId(requestURL,"GET",appTicket))
        		.given().contentType("application/json").get(requestURL);
		System.out.println("Response from the API end point : "+response.getBody().asString());
		return response;
	}

	public Response deleteParticipant(Map appTicket) throws MalformedURLException {
		String requestURL = BASEURI+"/api/participants/5b3f3d783de40bd6bb65071f";
        Response response = requestSpec.header(HttpHeaders.AUTHORIZATION,AppTicket.getHawkId(requestURL,"DELETE",appTicket))
        		.given().contentType("application/json").delete(requestURL);
		System.out.println("Response from the API end point : "+response.getBody().asString());
		return response;
	}

	@SuppressWarnings("unchecked")
	public Response updateParticipant(Map appTicket) throws MalformedURLException {
		String requestURL = BASEURI+"/api/participants/5b45d272d37ef6c6d626c22a";
		JSONObject obj = new JSONObject();
	
		obj.put("accessLevel", "2");
	    obj.put("accountDocId", "000000000000000000000002");
		obj.put("color", "#FF0000");
	    obj.put("vertical", "eagleeye");
		obj.put("firstName", "API1");
	    obj.put("lastName", "Test1");
	    obj.put("loginId", "z-qatest11@ee.io");
	    obj.put("password", "Password1@");
	    obj.put("status", true);

        System.out.println(obj.toString());
		EncoderConfig ec = new EncoderConfig();
        Response response = requestSpec.header(HttpHeaders.AUTHORIZATION,AppTicket.getHawkId(requestURL,"PUT",appTicket))
        		.given().config(RestAssured.config().encoderConfig(ec.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
        		.contentType("application/json").body(obj).put(requestURL);
		System.out.println("Response from the API end point : "+response.getBody().asString());
		return response;
	}

	@SuppressWarnings("unchecked")
	public Response changePassword(Map appTicket) throws MalformedURLException {
		String requestURL = BASEURI+"/api/password/change";
		JSONObject obj = new JSONObject();

	    obj.put("partId", "5b45d272d37ef6c6d626c22a");
	    obj.put("password", "Password1@");
        System.out.println(obj.toString());
		EncoderConfig ec = new EncoderConfig();
        Response response = requestSpec.header(HttpHeaders.AUTHORIZATION,AppTicket.getHawkId(requestURL,"POST",appTicket))
        		.given().config(RestAssured.config().encoderConfig(ec.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
        		.contentType("application/json").body(obj).post(requestURL);
		System.out.println("Response from the API end point : "+response.getBody().asString());
		return response;
	}
}
