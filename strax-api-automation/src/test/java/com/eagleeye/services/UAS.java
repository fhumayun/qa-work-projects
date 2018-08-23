package com.eagleeye.services;

import java.net.MalformedURLException;
import java.util.Map;

import org.json.simple.JSONObject;

import com.eagleeye.utils.JSONFileReader;
import com.eagleeye.utils.JsonParser;
import com.google.common.net.HttpHeaders;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.EncoderConfig;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class UAS extends BaseService {
	JsonParser parser = new JsonParser();
	String clusterDocId = "";
	Response response;
	JSONObject obj = new JSONObject();
	JSONFileReader reader = new JSONFileReader();
	EncoderConfig ec = new EncoderConfig();

	public UAS(RequestSpecification requestSpec) {
		this.requestSpec = RestAssured.given().contentType("application/json");

	}

	public Response getAllUAS(Map appTicket) throws MalformedURLException {
		String requestURL = BASEURI + "/api/fidgets";
		response = requestSpec.header(HttpHeaders.AUTHORIZATION, AppTicket.getHawkId(requestURL, "GET", appTicket))
				.given().get(requestURL);
		System.out.println(response.getBody().asString());
		return response;
	}
}
