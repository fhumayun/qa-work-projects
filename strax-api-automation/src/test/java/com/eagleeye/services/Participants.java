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

public class Participants extends BaseService {
	
	String participantDocId = "";
	JsonParser parser = new JsonParser();
	Response response;
	JSONObject obj = new JSONObject();
	JSONFileReader reader = new JSONFileReader(); 
	EncoderConfig ec = new EncoderConfig();
	
	public Participants(RequestSpecification requestSpec)
	{
		this.requestSpec = RestAssured.given().contentType("application/json");
						
	}
	
	@SuppressWarnings("unchecked")
	public Response createParticipants(Map appTicket) throws MalformedURLException, ParseException
	{
		String requestURL = BASEURI+"/api/participants";
		obj = reader.jsonReader("src/test/resources/testData/participant_post.json");
        response = requestSpec.header(HttpHeaders.AUTHORIZATION,AppTicket.getHawkId(requestURL,"POST",appTicket))
        		.given().config(RestAssured.config().encoderConfig(ec.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
        		.contentType("application/json").body(obj).post(requestURL);
		System.out.println("Response from the API end point : "+response.getBody().asString());
		participantDocId = parser.getPropertyValue(response, "_id");
		System.gc();
		return response;
	}
	public Response unlockParticipant(Map appTicket,String loginId) throws MalformedURLException, ParseException
	{
		String requestURL = BASEURI+"/api/participants";
        response = requestSpec.header(HttpHeaders.AUTHORIZATION,AppTicket.getHawkId(requestURL,"GET",appTicket))
        		.given().contentType("application/json").get(requestURL);
       String id = parser.getDocumentID(response,loginId);
       String unlockRequestURL = BASEURI+"/api/participants/"+id+"/unlock";
       requestSpec = RestAssured.given().contentType("application/json");
        response = requestSpec.header(HttpHeaders.AUTHORIZATION,AppTicket.getHawkId(unlockRequestURL,"POST",appTicket))
        		.given().config(RestAssured.config().encoderConfig(ec.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
        		.contentType("application/json").post(unlockRequestURL);
		System.out.println("Response from the API end point : "+response.getBody().asString());
		System.out.println("unlock...");
		return response;
	}

	public Response getSingleParticipant(Map appTicket,String loginId) throws MalformedURLException, ParseException {
		
		String requestURL = BASEURI+"/api/participants";
        response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(requestURL,"GET",appTicket))
        		.given().contentType("application/json").get(requestURL);
        participantDocId = parser.getDocumentID(response,loginId);
        String requestURL1 = BASEURI+"/api/participants/"+participantDocId;
        requestSpec = RestAssured.given().contentType("application/json");
        response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(requestURL1,"GET",appTicket))
        		.given().contentType("application/json").get(requestURL1);
       System.out.println("Response from the API end point : "+response.getBody().asString());
		return response;
	}

	public Response deleteParticipant(Map appTicket,String loginId) throws MalformedURLException, ParseException {
		
		String requestURL = BASEURI+"/api/participants";
        response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(requestURL,"GET",appTicket))
        		.given().contentType("application/json").get(requestURL);
        participantDocId = parser.getDocumentID(response,loginId);
        String deleteRequestURL = BASEURI+"/api/participants/"+participantDocId;
		System.out.println("befor edelete..."+requestURL);
		requestSpec = RestAssured.given().contentType("application/json");
		response = requestSpec.header(HttpHeaders.AUTHORIZATION,AppTicket.getHawkId(deleteRequestURL,"DELETE",appTicket))
        		.given().contentType("application/json").delete(deleteRequestURL);
		System.out.println("Response from the API end point : "+response.getBody().asString());
		System.out.println("Delete...");
		return response;
	}

	@SuppressWarnings("unchecked")
	public Response updateParticipant(Map appTicket,String loginId) throws MalformedURLException, ParseException {
		
		String requestURL = BASEURI+"/api/participants";
		 response = requestSpec.header(HttpHeaders.AUTHORIZATION,AppTicket.getHawkId(requestURL,"GET",appTicket))
	        		.given().contentType("application/json").get(requestURL);
		String participantDocId = parser.getDocumentID(response,loginId);
		obj.put("accessLevel", "2");
		obj.put("accountDocId", "000000000000000000000002");
		obj.put("color", "#FF0000");
	    obj.put("vertical", "eagleeye");
		obj.put("firstName", "API1");
	    obj.put("lastName", "Test1");
	    obj.put("password", "Password1@");
	    obj.put("loginId", "z-apitest@ee.io");
	    obj.put("status", true);
        System.out.println(obj.toString());
		String updateRequestURL = BASEURI+"/api/participants/"+participantDocId;
		requestSpec = RestAssured.given().contentType("application/json");
		response =requestSpec.header(HttpHeaders.AUTHORIZATION,AppTicket.getHawkId(updateRequestURL,"POST",appTicket))
        		.given().config(RestAssured.config().encoderConfig(ec.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
        		.contentType("application/json").body(obj).post(updateRequestURL);
		System.out.println("Response from the Update API end point : "+response.getBody().asString());
		return response;
	}

	@SuppressWarnings("unchecked")
	public Response changePassword(Map appTicket,String loginId) throws MalformedURLException, ParseException {
		String requestURL = BASEURI+"/api/participants";
        response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(requestURL,"GET",appTicket))
        		.given().contentType("application/json").get(requestURL);
        participantDocId = parser.getDocumentID(response,loginId);
        String changeRequestURL = BASEURI+"/api/password/change";
	    obj.put("partId", participantDocId);
	    obj.put("password", "Password1@");
        System.out.println(obj.toString());
		requestSpec = RestAssured.given().contentType("application/json");
		response = requestSpec.header(HttpHeaders.AUTHORIZATION,AppTicket.getHawkId(changeRequestURL,"POST",appTicket))
        		.given().config(RestAssured.config().encoderConfig(ec.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
        		.contentType("application/json").body(obj).post(changeRequestURL);
		System.out.println("Response from the API end point : "+response.getBody().asString());
		return response;
	}


}
