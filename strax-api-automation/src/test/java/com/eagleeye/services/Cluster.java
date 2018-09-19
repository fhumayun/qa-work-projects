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
		System.out.println("Response from the API end point : " + response.getBody().asString());
		return response;
	}

	public Response createCluster(Map appTicket) throws MalformedURLException, ParseException {
		String requestURL = BASEURI + "/api/events";
		obj = reader.jsonReader("src/test/resources/testData/cluster_post.json");
		String cluster = (String) obj.get("incident");
		System.out.println(cluster);
		response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(requestURL, "POST", appTicket)).given().config(RestAssured.config().encoderConfig(ec.appendDefaultContentCharsetToContentTypeIfUndefined(false))).contentType("application/json").body(obj).post(requestURL);
		System.out.println("Response from the API end point : " + response.getBody().asString());
		// String clusterDocId = parser.getDocIDFromObject(response,"incident",cluster);
		String clusterDocID = parser.getPropertyValue(response, "_id");
		System.out.println("_id............"+clusterDocID);
		String referenceId = parser.getPropertyValue(response, "referenceId");
		String addMediaURL = BASEURI + "/api/media/eagleeye/event";
		requestSpec = RestAssured.given().contentType("application/json");
		JSONObject obj1 = new JSONObject();
		obj1.put("name", referenceId);
		obj1.put("accountId", "000000000000000000000002");
		obj1.put("clusterId", clusterDocID);
		System.out.println("Response from the API end point : " + obj1.toString());
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
		System.out.println(clusterDocId);
		String getChatRequestURL = BASEURI + "/api/mq/messages/clusters/" + clusterDocId + "/chats";
		System.out.println(getChatRequestURL);
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
		System.out.println(joinEventURL);
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
		System.out.println(clusterDocId);
		obj = reader.jsonReader("src/test/resources/testData/cluster_put.json");
		String requestURL = BASEURI + "/api/clusters/" + clusterDocId;
		System.out.println(requestURL);
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
		System.out.println(response.getBody().asString());
		return response;
	}

	public Response getEventNotes(Map appTicket,String eventName) throws MalformedURLException, ParseException {
		String getRequestURL = BASEURI + "/api/clusters";
		response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(getRequestURL, "GET", appTicket))
				.given().get(getRequestURL);
		String clusterDocId = parser.getDocID(response, "incident", eventName);
		System.out.println(clusterDocId);
		String getNotesRequestURL = BASEURI + "/api/mq/messages/clusters/" + clusterDocId + "/notes";
		System.out.println(getNotesRequestURL);
		requestSpec = RestAssured.given().contentType("application/json");
		response =  requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(getNotesRequestURL, "GET", appTicket))
				.given().get(getNotesRequestURL);
		System.out.println(response.getBody().asString());
		return response;
	}

	public Response getEventGeoTags(Map appTicket, String eventName) throws MalformedURLException, ParseException {
		String getRequestURL = BASEURI + "/api/clusters";
		response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(getRequestURL, "GET", appTicket))
				.given().get(getRequestURL);
		String clusterDocId = parser.getDocID(response, "incident", eventName);
		System.out.println(clusterDocId);
		String getNotesRequestURL = BASEURI + "/api/mq/messages/clusters/" + clusterDocId + "/geotags";
		System.out.println(getNotesRequestURL);
		requestSpec = RestAssured.given().contentType("application/json");
		response =  requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(getNotesRequestURL, "GET", appTicket))
				.given().get(getNotesRequestURL);
		System.out.println(response.getBody().asString());
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
	      System.out.println("call sign name....."+callSignName);
		}
		catch(Exception e)
		{
			System.out.println(e.getStackTrace().toString());
			
		}
		if(!callSignName.isEmpty())
		return true;
		else
			return false;
	
	}

}
