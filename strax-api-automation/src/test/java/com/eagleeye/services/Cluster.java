package com.eagleeye.services;

import java.net.MalformedURLException;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.eagleeye.utils.JSONFileReader;
import com.eagleeye.utils.JsonParser;
import com.google.common.net.HttpHeaders;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.EncoderConfig;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class Cluster extends BaseService {
	JsonParser parser = new JsonParser();
	String clusterDocId = "";
	Response response;
	JSONObject obj = new JSONObject();
	JSONFileReader reader = new JSONFileReader();
	EncoderConfig ec = new EncoderConfig();

	public Cluster(RequestSpecification requestSpec) {
		this.requestSpec = RestAssured.given().contentType("application/json");

	}

	public Response getCluster(Map appTicket) throws MalformedURLException {
		String requestURL = BASEURI + "/api/clusters";
		response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(requestURL, "GET", appTicket))
				.given().get(requestURL);
		return response;
	}
	public Response getSpecificCluster(Map appTicket,String eventName) throws MalformedURLException {
		String requestURL = BASEURI + "/api/clusters";
		try {
		response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(requestURL, "GET", appTicket))
				.given().get(requestURL);
		String clusterDocId = parser.getDocID(response, "incident", eventName);
		String getClusterDocURL = BASEURI + "/api/clusters/" + clusterDocId;
		requestSpec = RestAssured.given().contentType("application/json");
		response = requestSpec
				.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(getClusterDocURL, "GET", appTicket)).given()
				.contentType("application/json; charset=UTF-8").get(getClusterDocURL);
		return response;
		}
		catch(Exception e)
		{
			
		}
		return response;
	}
	public Response deleteCluster(Map appTicket, String eventName) throws MalformedURLException, ParseException {
		String getRequestURL = BASEURI + "/api/clusters";
		response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(getRequestURL, "GET", appTicket))
				.given().get(getRequestURL);
		String clusterDocId = parser.getDocID(response, "incident", eventName);
		String deletRequestURL = BASEURI + "/api/clusters/" + clusterDocId;
		requestSpec = RestAssured.given().contentType("application/json");
		response = requestSpec
				.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(deletRequestURL, "DELETE", appTicket)).given()
				.contentType("application/json; charset=UTF-8").delete(deletRequestURL);
		return response;
	}

	public Response escalateIER(Map appTicket) throws MalformedURLException {
		String requestURL = BASEURI + "/api/ier/alerts/5b3b1d609ff8c20026ff6c95/escalate";
		Response response = requestSpec
				.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(requestURL, "GET", appTicket)).given()
				.contentType("application/json").get(requestURL);
				return response;
	}

	public Response createCluster(Map appTicket) throws MalformedURLException, ParseException {
		String requestURL = BASEURI + "/api/events";
		obj = reader.jsonReader("src/test/resources/testData/cluster_post.json");
		String cluster = (String) obj.get("incident");
		response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(requestURL, "POST", appTicket)).given().config(RestAssured.config().encoderConfig(ec.appendDefaultContentCharsetToContentTypeIfUndefined(false))).contentType("application/json").body(obj).post(requestURL);
		String clusterDocID = parser.getPropertyValue(response, "_id");
		String referenceId = parser.getPropertyValue(response, "referenceId");
		String addMediaURL = BASEURI + "/api/media/eagleeye/event";
		requestSpec = RestAssured.given().contentType("application/json");
		JSONObject obj1 = new JSONObject();
		obj1.put("name", referenceId);
		obj1.put("accountId", "000000000000000000000002");
		obj1.put("clusterId", clusterDocID);
		response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(addMediaURL, "POST", appTicket))
				.given()
				.config(RestAssured.config()
						.encoderConfig(ec.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
				.contentType("application/json").body(obj1).post(addMediaURL);
		return response;
	}

	public Response getChatMsg(Map appTicket,String eventName) throws MalformedURLException, ParseException {
		String getRequestURL = BASEURI + "/api/clusters";
		response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(getRequestURL, "GET", appTicket))
				.given().get(getRequestURL);
		String clusterDocId = parser.getDocID(response, "incident", eventName);
		String getChatRequestURL = BASEURI + "/api/mq/messages/clusters/" + clusterDocId + "/chats";
		requestSpec = RestAssured.given().contentType("application/json");
		response =  requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(getChatRequestURL, "GET", appTicket))
				.given().get(getChatRequestURL);
		System.out.println(response.getBody().asString());
		return response;
	}

	public Response getEventPlans(Map appTicket) throws MalformedURLException {
		String requestURL = BASEURI + "/api/eventplans";

		response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(requestURL, "GET", appTicket))
				.given().contentType("application/json").get(requestURL);
		return response;
	}

	public Response joinEvent(Map appTicket, String cluster, String participant)
			throws MalformedURLException, ParseException {
		String requestURL = BASEURI + "/api/clusters";
		participant = "59ad8df9070d610001e6bc80";
		response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(requestURL, "GET", appTicket))
				.given().get(requestURL);
		String clusterDocId = parser.getDocID(response, "incident", cluster);
		obj.put("participantDocId", participant);
		String joinEventURL = BASEURI + "/api/events" + clusterDocId + "/participant/join";
		requestSpec = RestAssured.given().contentType("application/json");
		response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(joinEventURL, "POST", appTicket))
				.given()
				.config(RestAssured.config()
						.encoderConfig(ec.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
				.contentType("application/json").body(obj).post(joinEventURL);
		return response;
	}

	public Response updateEvent(Map appTicket, String eventName) throws MalformedURLException, ParseException {
		String getRequestURL = BASEURI + "/api/clusters";
		response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(getRequestURL, "GET", appTicket))
				.given().get(getRequestURL);
		String clusterDocId = parser.getDocID(response, "incident", eventName);
		obj = reader.jsonReader("src/test/resources/testData/cluster_put.json");
		String requestURL = BASEURI + "/api/clusters/" + clusterDocId;
		requestSpec = RestAssured.given().contentType("application/json");
		response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(requestURL, "PUT", appTicket))
				.given()
				.config(RestAssured.config()
						.encoderConfig(ec.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
				.contentType("application/json").body(obj).put(requestURL);
		return response;
	}

	public Response getEventMessages(Map appTicket) throws MalformedURLException {
		String getRequestURL = BASEURI + "/api/mq/messages";
		response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(getRequestURL, "GET", appTicket))
				.given().get(getRequestURL);
		return response;
	}

	public Response getEventNotes(Map appTicket,String eventName) throws MalformedURLException, ParseException {
		String getRequestURL = BASEURI + "/api/clusters";
		response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(getRequestURL, "GET", appTicket))
				.given().get(getRequestURL);
		String clusterDocId = parser.getDocID(response, "incident", eventName);
		String getNotesRequestURL = BASEURI + "/api/mq/messages/clusters/" + clusterDocId + "/notes";
		requestSpec = RestAssured.given().contentType("application/json");
		response =  requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(getNotesRequestURL, "GET", appTicket))
				.given().get(getNotesRequestURL);
		return response;
	}

	public Response getEventGeoTags(Map appTicket, String eventName) throws MalformedURLException, ParseException {
		String getRequestURL = BASEURI + "/api/clusters";
		response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(getRequestURL, "GET", appTicket))
				.given().get(getRequestURL);
		String clusterDocId = parser.getDocID(response, "incident", eventName);
		String getNotesRequestURL = BASEURI + "/api/mq/messages/clusters/" + clusterDocId + "/geotags";
		requestSpec = RestAssured.given().contentType("application/json");
		response =  requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(getNotesRequestURL, "GET", appTicket))
				.given().get(getNotesRequestURL);
		return response;
	}

	public Response getScribeNotes(Map appTicket, String eventName,String loginId) throws MalformedURLException {
		try{
			
		String getClusterIdrequestURL = BASEURI + "/api/clusters";
		response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(getClusterIdrequestURL, "GET", appTicket))
				.given().get(getClusterIdrequestURL);
		String clusterDocId = parser.getDocID(response, "incident", eventName);
		String getParticipantIdrequestURL = BASEURI+"/api/participants";
		requestSpec = RestAssured.given().contentType("application/json");
		 response = requestSpec.header(HttpHeaders.AUTHORIZATION,AppTicket.getHawkId(getParticipantIdrequestURL,"GET",appTicket))
	        		.given().contentType("application/json").get(getParticipantIdrequestURL);
		String participantDocId = parser.getDocumentID(response,loginId);
		requestSpec = RestAssured.given().contentType("application/json");
		String getScribeNotesRequestURL = BASEURI + "/api/mq/scribes/participants/"+participantDocId+"/clusters/"+clusterDocId;
		response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(getScribeNotesRequestURL, "GET", appTicket))
				.given().get(getScribeNotesRequestURL);
		}
		catch(Exception e)
		{
			
		}
		return response;
	}
	@SuppressWarnings("unchecked")
	public Response addScribeNote(Map appTicket,String eventName, String loginId) throws MalformedURLException {
		try {
		String getClusterIdrequestURL = BASEURI + "/api/clusters";
		response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(getClusterIdrequestURL, "GET", appTicket))
				.given().get(getClusterIdrequestURL);
		String clusterDocId = parser.getDocID(response, "incident", eventName);
		String getParticipantIdrequestURL = BASEURI+"/api/participants";
		requestSpec = RestAssured.given().contentType("application/json");
		 response = requestSpec.header(HttpHeaders.AUTHORIZATION,AppTicket.getHawkId(getParticipantIdrequestURL,"GET",appTicket))
	        		.given().contentType("application/json").get(getParticipantIdrequestURL);
		String participantDocId = parser.getDocumentID(response,loginId);
		String addRequestURL = BASEURI + "/api/mq/scribes/event";
		obj.put("data", "Automated Note2");
		obj.put("accountDocId", "000000000000000000000002");
		obj.put("firstName", "Z-Auto");
		obj.put("lastName", "Z-Bot");
	    obj.put("referenceId", "STXPBSO201809111392");
		obj.put("participantDocId", participantDocId);
	    obj.put("clusterDocId", clusterDocId);	 
	    requestSpec = RestAssured.given().contentType("application/json");
		response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(addRequestURL, "POST", appTicket))
				.given()
				.config(RestAssured.config()
						.encoderConfig(ec.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
				.contentType("application/json").body(obj).post(addRequestURL);
		}
		catch(Exception e)
		{
			
		}
		return response;
	}

	public boolean verifyCallsignNamePresent(Map appTicket,Response response, String loginId ) {
		String callSignName="";
		try {
			String requestURL = BASEURI+"/api/participants";
			requestSpec = RestAssured.given().contentType("application/json");
			Response participantresponse = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(requestURL,"GET",appTicket))
	        		.given().contentType("application/json").get(requestURL);
	       String participantDocId = parser.getDocumentID(participantresponse,loginId);
	       callSignName  = parser.getCallSignName(response, "participantDocId", participantDocId);
		}
		catch(Exception e)
		{
			
		}
		if(!callSignName.isEmpty())
		return true;
		else
			return false;
	
	}

	@SuppressWarnings("unchecked")
	public Response promoteScribeToChat(Map appTicket,String loginId,String eventName) {
		try {
			
			response =  getScribeNotes(appTicket,eventName,loginId);		
			String clusterDocId = parser.getKeyValueByKey(response, "data", "Automated Note2", "clusterDocId");
			String referencerDocId = parser.getKeyValueByKey(response, "data", "Automated Note2", "referenceId");
			String scribeDocId = parser.getKeyValueByKey(response, "data", "Automated Note2", "_id");
			String participantDocId = parser.getKeyValueByKey(response, "data", "Automated Note2", "participantDocId");	
			obj.put("firstName", "Z-Auto");
			obj.put("lastName", "Z-Bot");
		    obj.put("referenceId", referencerDocId);
		    obj.put("participantDocId", participantDocId);
		    obj.put("clusterDocId", clusterDocId);	 
			String promoteScribeToChatrequestURL = BASEURI + "/api/mq/scribes/"+scribeDocId+"/promote/chat";
		    requestSpec = RestAssured.given().contentType("application/json");
			response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(promoteScribeToChatrequestURL, "POST", appTicket))
					.given()
					.config(RestAssured.config()
							.encoderConfig(ec.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
					.contentType("application/json").body(obj).post(promoteScribeToChatrequestURL);
			System.out.println(response.getBody().toString());
			}
			catch(Exception e)
			{
				
			}
			return response;
	}

	public Response getPreferences(JSONObject authTicket) {
		try {
		String requestURL = BASEURI+"/api/preferences";
		response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(requestURL,"GET",authTicket))
        		.given().contentType("application/json").get(requestURL);
		System.out.println("Response here of req.."+response.getBody().asString());
		System.out.println("Response Code.."+response.getStatusCode());
		//System.out.println(requestURL);
		}
		catch(Exception e)
		{
			
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	public Response addPreferences(JSONObject authTicket) {
		try {
			String requestURL = BASEURI+"/api/preferences";
			obj.put("save_enter", true);
			response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(requestURL, "PUT", authTicket))
					.given()
					.config(RestAssured.config()
							.encoderConfig(ec.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
					.contentType("application/json").body(obj).put(requestURL);
			System.out.println("Response.."+response.getBody().asString());
			System.out.println("Response Code.."+response.getStatusCode());
			System.out.println(requestURL);
			System.out.println(obj.toJSONString());
			
			}
			catch(Exception e)
			{
				
			}
			return response;
		
	}
	
	public Response addAndroidPreferences(JSONObject authTicket) {
		try {
			String requestURL = BASEURI+"/api/preferences/android";
			obj.put("theme", "blue");
			response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(requestURL, "PUT", authTicket))
					.given()
					.config(RestAssured.config()
							.encoderConfig(ec.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
					.contentType("application/json").body(obj).put(requestURL);
			System.out.println("Response.."+response.getBody().asString());
			System.out.println("Response Code.."+response.getStatusCode());
			System.out.println(requestURL);
			System.out.println(obj.toJSONString());
			
			}
			catch(Exception e)
			{
				
			}
			return response;
	}

	public Response addMapPreferences(JSONObject authTicket) {
		try {
			String requestURL = BASEURI+"/api/preferences/5bbdbe7aef37cf001ceb2da6/map";
			obj = reader.jsonReader("src/test/resources/testData/Preference_put.json");
			requestSpec = RestAssured.given().contentType("application/json");
			response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(requestURL, "PUT", authTicket))
					.given()
					.config(RestAssured.config()
							.encoderConfig(ec.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
					.contentType("application/json").body(obj).put(requestURL);
			System.out.println("Response.."+response.getBody().asString());
			System.out.println("Response Code.."+response.getStatusCode());
			System.out.println(requestURL);
			System.out.println(obj.toJSONString());
			
			}
			catch(Exception e)
			{
				
			}
			return response;
	}

	public Response deletePreferences(JSONObject authTicket) {
		try {
			String requestURL = BASEURI+"/api/preferences/participants/59ad8d32070d610001e6bc7f";
						response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(requestURL, "DELETE", authTicket))
					.given().contentType("application/json; charset=UTF-8").delete(requestURL);
			System.out.println("Response.."+response.getBody().asString());
			System.out.println("Response Code.."+response.getStatusCode());
			System.out.println(requestURL);
			System.out.println(obj.toJSONString());
			
			}
			catch(Exception e)
			{
				
			}
			return response;
	}

	

}

