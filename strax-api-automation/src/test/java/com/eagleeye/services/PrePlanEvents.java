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

public class PrePlanEvents extends BaseService {

	Response response;
	JsonParser parser = new JsonParser();
	JSONFileReader reader = new JSONFileReader();
	EncoderConfig ec = new EncoderConfig();
	JSONObject obj = new JSONObject();

	public PrePlanEvents(RequestSpecification requestSpec) {
		this.requestSpec = RestAssured.given().contentType("application/json");
	}

	public Response getPrePlanEvents(Map appTicket) throws MalformedURLException {
		String requestURL = BASEURI + "/api/eventplans";
		response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(requestURL, "GET", appTicket)).given()
				.get(requestURL);
		// System.out.println(response.getBody().asString());
		return response;
	}

	public Response createPrePlanEvents(Map appTicket) throws MalformedURLException, ParseException {
		String requestURL = BASEURI + "/api/eventplans";
		JSONObject obj = reader.jsonReader("src/test/resources/testData/PrePlanEvent_post.json");
		System.out.println("Response from the API end point : " + obj.toString());
		response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(requestURL, "POST", appTicket))
				.given()
				.config(RestAssured.config()
						.encoderConfig(ec.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
				.contentType("application/json").body(obj).post(requestURL);

		return response;
	}

	public Response updatePrePlanEvents(Map appTicket, String eventPlanName)
			throws ParseException, MalformedURLException {
		String getRequestURL = BASEURI + "/api/eventplans";
		response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(getRequestURL, "GET", appTicket))
				.given().get(getRequestURL);
		String eventPlanDocId = parser.getDocID(response, "planName", eventPlanName);
		System.out.println(eventPlanDocId);
		obj = reader.jsonReader("src/test/resources/testData/PrePlanEvent_put.json");
		String requestURL = BASEURI + "/api/eventplans/" + eventPlanDocId;
		System.out.println(requestURL);
		requestSpec = RestAssured.given().contentType("application/json");
		response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(requestURL, "PUT", appTicket))
				.given()
				.config(RestAssured.config()
						.encoderConfig(ec.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
				.contentType("application/json").body(obj).put(requestURL);
		return response;
	}

	public Response deletePrePlanEvents(Map appTicket, String eventPlanName)
			throws ParseException, MalformedURLException {
		String getRequestURL = BASEURI + "/api/eventplans";
		response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(getRequestURL, "GET", appTicket))
				.given().get(getRequestURL);
		String prePlanrDocId = parser.getDocID(response, "planName", eventPlanName);
		System.out.println(prePlanrDocId);
		String deletRequestURL = BASEURI + "/api/eventplans/" + prePlanrDocId;
		requestSpec = RestAssured.given().contentType("application/json");
		response = requestSpec
				.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(deletRequestURL, "DELETE", appTicket)).given()
				.contentType("application/json").delete(deletRequestURL);
		return response;
	}

}
