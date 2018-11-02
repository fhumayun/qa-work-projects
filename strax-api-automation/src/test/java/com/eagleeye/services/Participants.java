package com.eagleeye.services;


import java.io.File;
import java.net.MalformedURLException;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import com.eagleeye.utils.JSONFileReader;
import com.eagleeye.utils.JsonParser;
import com.google.common.net.HttpHeaders;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.EncoderConfig;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.builder.MultiPartSpecBuilder; 
import com.jayway.restassured.builder.RequestSpecBuilder; 

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
				participantDocId = parser.getPropertyValue(response, "_id");
		System.out.println(response.getBody().asString());
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
        		.given().contentType("application/json; charset=UTF-8").delete(deleteRequestURL);
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
		obj.put("_id", participantDocId);
		obj.put("accountDocId", "000000000000000000000002");
		obj.put("color", "#FF0000");
	    obj.put("vertical", "eagleeye");
		obj.put("firstName", "API1");
	    obj.put("lastName", "Test1");
	    obj.put("password", "Password1@");
	    obj.put("loginId", "z-apitest@ee.io");
	    obj.put("status", true);
	    obj.put("suffix", "Sr.");
	    obj.put("mobilePhone", "+1234567890");
		String updateRequestURL = BASEURI+"/api/participants/"+participantDocId;
		requestSpec = RestAssured.given().contentType("application/json");
		response =requestSpec.header(HttpHeaders.AUTHORIZATION,AppTicket.getHawkId(updateRequestURL,"PUT",appTicket))
        		.given().config(RestAssured.config().encoderConfig(ec.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
        		.contentType("application/json").body(obj).put(updateRequestURL);
		System.out.println(response.getBody().asString());
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
		requestSpec = RestAssured.given().contentType("application/json");
		response = requestSpec.header(HttpHeaders.AUTHORIZATION,AppTicket.getHawkId(changeRequestURL,"POST",appTicket))
        		.given().config(RestAssured.config().encoderConfig(ec.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
        		.contentType("application/json").body(obj).post(changeRequestURL);
		return response;
	}

	public Response getCallsignsAPI(Map appTicket) {
		String requestURL = BASEURI+"/api/callsigns";
        try {
			response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(requestURL,"GET",appTicket))
					.given().contentType("application/json").get(requestURL);	
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	public Response createCallsignAPI(Map appTicket,String loginId) {
		
		try {
			String getParticipantIdrequestURL = BASEURI+"/api/participants";
	        response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(getParticipantIdrequestURL,"GET",appTicket))
	        		.given().contentType("application/json").get(getParticipantIdrequestURL);
	        participantDocId = parser.getDocumentID(response,loginId);
		    obj.put("callSignName", "Tango6");
		    obj.put("participant", participantDocId);
		    String requestURL = BASEURI+"/api/callsigns";
		    requestSpec = RestAssured.given().contentType("application/json");
		response = requestSpec.header(HttpHeaders.AUTHORIZATION,AppTicket.getHawkId(requestURL,"POST",appTicket))
        		.given().config(RestAssured.config().encoderConfig(ec.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
        		.contentType("application/json").body(obj).post(requestURL);
		System.out.println("Response from the API end point : "+response.getBody().asString());
		}
		catch(Exception e)
		{
			
		}
		return response;

	}

	@SuppressWarnings("unchecked")
	public Response updateCallsignAPI(Map appTicket, String callSignName, String loginId) {
		try {
			String getCallSignIdrequestURL = BASEURI+"/api/callsigns";
			response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(getCallSignIdrequestURL,"GET",appTicket))
					.given().contentType("application/json").get(getCallSignIdrequestURL);
	       String callSignDocId = parser.getDocID(response,"callSignName",callSignName );
		requestSpec = RestAssured.given().contentType("application/json");
		String getParticipantIdrequestURL = BASEURI+"/api/participants";
        response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(getParticipantIdrequestURL,"GET",appTicket))
        		.given().contentType("application/json").get(getParticipantIdrequestURL);
        participantDocId = parser.getDocumentID(response,loginId);
		obj.put("callSignType", "Individual");
		obj.put("status", "Active");
		obj.put("isArchived", false);
	    obj.put("participant", participantDocId);
	    obj.put("_id", callSignDocId);
		String putRequestURL = BASEURI+"/api/callsigns";
	    requestSpec = RestAssured.given().contentType("application/json");
		response =requestSpec.header(HttpHeaders.AUTHORIZATION,AppTicket.getHawkId(putRequestURL,"PUT",appTicket))
        		.given().config(RestAssured.config().encoderConfig(ec.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
        		.contentType("application/json").body(obj).put(putRequestURL);
		}
		catch(Exception e)
		{
			
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	public Response archiveCallsignAPI(Map appTicket, String callSignName) {
		try {

		String getCallSignIdrequestURL = BASEURI+"/api/callsigns";
		response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(getCallSignIdrequestURL,"GET",appTicket))
				.given().contentType("application/json").get(getCallSignIdrequestURL);
       String callSignDocId = parser.getDocID(response,"callSignName",callSignName );
		obj.put("isArchived", false);
	    obj.put("participant", "");
	    obj.put("Status", "Available");
	    obj.put("_id", callSignDocId);
		String putRequestURL = BASEURI+"/api/callsigns";
	    requestSpec = RestAssured.given().contentType("application/json");
		response =requestSpec.header(HttpHeaders.AUTHORIZATION,AppTicket.getHawkId(putRequestURL,"PUT",appTicket))
       		.given().config(RestAssured.config().encoderConfig(ec.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
       		.contentType("application/json").body(obj).put(putRequestURL);
       String deleteRequestURL = BASEURI+"/api/callsigns/"+callSignDocId+"/archive";
     		requestSpec = RestAssured.given().contentType("application/json");
     		obj.put("isArchived", true);
     		response = requestSpec.header(HttpHeaders.AUTHORIZATION,AppTicket.getHawkId(deleteRequestURL,"PUT",appTicket))
             		.given().contentType("application/json; charset=UTF-8").body(obj).put(deleteRequestURL);
     		}
		catch(Exception e)
		{
			
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	public Response unarchivedCallsignAPI(Map appTicket, String callSignName) {
		try {
			String getCallSignIdrequestURL = BASEURI+"/api/callsigns";
			response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(getCallSignIdrequestURL,"GET",appTicket))
					.given().contentType("application/json").get(getCallSignIdrequestURL);
	       String callSignDocId = parser.getDocID(response,"callSignName",callSignName );
	       String unArchiveRequestURL = BASEURI+"/api/callsigns/"+callSignDocId+"/archive";
	     		requestSpec = RestAssured.given().contentType("application/json");
	     		obj.put("isArchived", false);
	     		response = requestSpec.header(HttpHeaders.AUTHORIZATION,AppTicket.getHawkId(unArchiveRequestURL,"PUT",appTicket))
	             		.given().contentType("application/json; charset=UTF-8").body(obj).put(unArchiveRequestURL);
			}
			catch(Exception e)
			{
				
			}
			return response;
	}

	public Response bulkUploadCallsignAPI(Map appTicket) {

		try {
		String uploadCallSignrequestURL = BASEURI+"/api/callsigns-upload";
	    response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(uploadCallSignrequestURL,"POST",appTicket))
	    .given().config(RestAssured.config().encoderConfig(ec.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
	    .contentType("multipart/form-data").multiPart("file", new File("src/test/resources/testData/callsigns.csv"))
	    .post(uploadCallSignrequestURL);
	    System.out.println("Response of upload..."+response.asString());

	 }
		catch(Exception e)
		{
			
		}
		return response;
	}
	@SuppressWarnings("unchecked")
	public Response addSubunit(Map appTicket) {
		try {
			String requestURL = BASEURI+"/api/subunits";
			obj = reader.jsonReader("src/test/resources/testData/Subunit_post.json");
			response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(requestURL, "POST", appTicket))
					.given()
					.config(RestAssured.config()
							.encoderConfig(ec.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
					.contentType("application/json; charset=UTF-8").body(obj).post(requestURL);
			
			}
			catch(Exception e)
			{
				
			}
			return response;
		
	}

	public Response getSubunit(Map appTicket) {
		try {
			String requestURL = BASEURI+"/api/subunits/5bc9b350d7c4ff001c8f2bc9";
			response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(requestURL,"GET",appTicket))
	        		.given().contentType("application/json").get(requestURL);
			System.out.println(response.getBody().asString());
			}
			catch(Exception e)
			{
				
			}
			return response;
		}

	public Response updateSubunit(Map appTicket,String subUnit) {
		try {
			response = getSubunit(appTicket);
			String subUnitDocId = parser.getDocID(response, "name", subUnit);
			String requestURL = BASEURI+"/api/subunits/"+subUnitDocId;
			obj = reader.jsonReader("src/test/resources/testData/Subunit_put.json");
			requestSpec = RestAssured.given().contentType("application/json");
			response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(requestURL, "PUT", appTicket))
					.given()
					.config(RestAssured.config()
							.encoderConfig(ec.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
					.contentType("application/json; charset=UTF-8").body(obj).put(requestURL);
		
			}
			catch(Exception e)
			{
				
			}
			return response;
	}

	public Response updateOrderSubunit(Map appTicket,String subUnit) {
		try {
			response = getSubunit(appTicket);
			String subUnitDocId = parser.getDocID(response, "name", subUnit);
			String requestURL = BASEURI+"/api/subunits/"+subUnitDocId+"/changeOrder";
			obj.put("new", 2);
			obj.put("old", 1);
			requestSpec = RestAssured.given().contentType("application/json");
			response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(requestURL, "PUT", appTicket))
					.given()
					.config(RestAssured.config()
							.encoderConfig(ec.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
					.contentType("application/json; charset=UTF-8").body(obj).put(requestURL);
		
			}
			catch(Exception e)
			{
				
			}
			return response;
	}

	public Response archiveSubunit(Map appTicket,String subUnit) {
		try {
			response = getSubunit(appTicket);
			String subUnitDocId = parser.getDocID(response, "name", subUnit);
			String requestURL = BASEURI+"/api/subunits/"+subUnitDocId+"/archive";
			obj.put("status", false);
			requestSpec = RestAssured.given().contentType("application/json");
			response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(requestURL, "PUT", appTicket))
					.given()
					.config(RestAssured.config()
							.encoderConfig(ec.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
					.contentType("application/json; charset=UTF-8").body(obj).put(requestURL);
			
			}
			catch(Exception e)
			{
				
			}
			return response;
	}

	public Response addParticipantToSubunit(Map appTicket, String subUnit) {
		try {
			response = getSubunit(appTicket);
			String subUnitDocId = parser.getDocID(response, "name", subUnit);
			String requestURL = BASEURI+"/api/subunits/"+subUnitDocId+"/addParticipant";
			//obj = reader.jsonReader("src/test/resources/testData/Subunit_put.json");
			String getParticipantIDRequestURL = BASEURI+"/api/participants";
			requestSpec = RestAssured.given().contentType("application/json");
			 response = requestSpec.header(HttpHeaders.AUTHORIZATION,AppTicket.getHawkId(getParticipantIDRequestURL,"GET",appTicket))
		        		.given().contentType("application/json").get(getParticipantIDRequestURL);
			// System.out.print(response.getBody().asString());
			String participantDocId = parser.getDocumentID(response,"z-apitest@ee.io");
			obj.put("participantDocId", participantDocId);
			requestSpec = RestAssured.given().contentType("application/json");
			response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(requestURL, "PUT", appTicket))
					.given()
					.config(RestAssured.config()
							.encoderConfig(ec.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
					.contentType("application/json; charset=UTF-8").body(obj).put(requestURL);
			System.out.print(response.getBody().asString());
		
			}
			catch(Exception e)
			{
				
			}
			return response;
	}

	public Response archiveParticipant(Map appTicket, String loginId) {
		try
		{
			String requestURL = BASEURI+"/api/participants";
			 response = requestSpec.header(HttpHeaders.AUTHORIZATION,AppTicket.getHawkId(requestURL,"GET",appTicket))
		        		.given().contentType("application/json").get(requestURL);
			// System.out.print(response.getBody().asString());
			String participantDocId = parser.getDocumentID(response,loginId);
			System.out.print("doc id.."+participantDocId);
			obj.put("accessLevel", "2");
			obj.put("_id", participantDocId);
			obj.put("accountDocId", "000000000000000000000002");
			obj.put("color", "#FF0000");
		    obj.put("vertical", "eagleeye");
			obj.put("firstName", "API1");
		    obj.put("lastName", "Test1");
		    obj.put("password", "Password1@");
		    obj.put("loginId", "z-apitest@ee.io");
		    obj.put("status", false);
		    obj.put("suffix", "Sr.");
		    obj.put("mobilePhone", "+1234567890");
			String updateRequestURL = BASEURI+"/api/participants/"+participantDocId;
			
			requestSpec = RestAssured.given().contentType("application/json");
			response =requestSpec.header(HttpHeaders.AUTHORIZATION,AppTicket.getHawkId(updateRequestURL,"PUT",appTicket))
	        		.given().config(RestAssured.config().encoderConfig(ec.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
	        		.contentType("application/json; charset=UTF-8").body(obj).put(updateRequestURL);
			System.out.println(response.getBody().asString());
					
	}
		catch(Exception e)
		{
			
		}
		return response;

	}

	public String verifyArchivedUserSubunit(Map appTicket, String subUnit) {
		String val="";
		try {
			response = getSubunit(appTicket);
			String subUnitDocId = parser.getDocID(response, "name", subUnit);
			String requestURL = BASEURI+"/api/subunits/"+subUnitDocId;
			response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(requestURL,"GET",appTicket))
	        		.given().contentType("application/json").get(requestURL);
			System.out.println(response.getBody().asString());
			val = parser.getPropertyValue(response, "participants");
			}
			catch(Exception e)
			{
				
			}
			return val;
	}

	public Response unArchiveSubunit(Map appTicket, String subUnit) {
		try {
			response = getSubunit(appTicket);
			String subUnitDocId = parser.getDocID(response, "name", subUnit);
			String requestURL = BASEURI+"/api/subunits/"+subUnitDocId+"/archive";
			obj.put("status", true);
			requestSpec = RestAssured.given().contentType("application/json");
			response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(requestURL, "PUT", appTicket))
					.given()
					.config(RestAssured.config()
							.encoderConfig(ec.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
					.contentType("application/json; charset=UTF-8").body(obj).put(requestURL);
			
			}
			catch(Exception e)
			{
				
			}
			return response;
	}
	
	


}
