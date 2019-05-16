package com.eagleeye.services;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.eagleeye.utils.PropertiesFileReader;
import com.google.common.net.HttpHeaders;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.EncoderConfig;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.wealdtech.hawk.HawkClient;
import com.wealdtech.hawk.HawkCredentials;

public class AppTicket {
	public static String app;
	public static String key;
	public static String algorithm;
	public static String id;
	public static String[] scope;
	public static double exp;
	public static RequestSpecification requestSpec;
	static PropertiesFileReader prreader = new PropertiesFileReader();
	public static String BASEURI = prreader.getPropertyvalues("STRAXUrl");
	static String rsvp="";

	public static Map<?, ?> getAppTicket(String userName, String password) throws MalformedURLException {
		Map<?, ?> map1 = null;
		try {
			String payload = "{\"username\":\"" + userName + "\",\"password\":\"" + password
					+ "\",\"applnName\":\"STRAX APP AngularJS\",\"applnType\":\"Web Application\",\"fingerprint\":\"74681325572dc861723eff28c006a55f\",\"fpData\":[]}";
			String requestURL = BASEURI + "/api/participants/doAuthenticate";
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(payload);
			RequestSpecification requestSpec = RestAssured.given().contentType("application/json");
			requestSpec.header("Content-Type", "application/json");
			EncoderConfig ec = new EncoderConfig();
			Response response = requestSpec.given()
					.config(RestAssured.config()
							.encoderConfig(ec.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
					.contentType("application/json").body(obj).post(requestURL);
			String jsonAsString = response.getBody().asString();
			JSONObject object = (JSONObject) parser.parse(jsonAsString);
			map1 = (HashMap<?, ?>) object.get("appTicket");
			rsvp = (String) object.get("rsvp");

		} catch (Exception e) {
			System.out.println("Exception while getting AppTicket..");
		}
		return map1;
	}
	
	public static JSONObject getLoginObject(String userName, String password) throws MalformedURLException {
		Map<?, ?> map1 = null;
		JSONObject object = null;
		JSONObject object1 = null;
		try {
			String payload = "{\"username\":\"" + userName + "\",\"password\":\"" + password
					+ "\",\"applnName\":\"STRAX APP AngularJS\",\"applnType\":\"Web Application\",\"fingerprint\":\"74681325572dc861723eff28c006a55f\",\"fpData\":[]}";
			String requestURL = BASEURI + "/api/login";
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(payload);
			requestSpec = RestAssured.given().contentType("application/json");
			requestSpec.header("Content-Type", "application/json");
			EncoderConfig ec = new EncoderConfig();
			Response response = requestSpec.given()
					.config(RestAssured.config()
							.encoderConfig(ec.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
					.contentType("application/json").body(obj).post(requestURL);
			String jsonAsString = response.getBody().asString();
			object = (JSONObject) parser.parse(jsonAsString);
			object1 = getAuthTicket(object);
			

		} catch (Exception e) {
			System.out.println("Exception while getting AppTicket..");
		}
		return object1;
	}

	public static String getHawkId(String requestUrl, String requestMethod, Map<?, ?> map1)
			throws MalformedURLException {
		app = (String) map1.get("app");
		HawkCredentials hawkCredentials = new HawkCredentials.Builder().keyId((String) map1.get("id"))
				.key((String) map1.get("key"))
				.algorithm(HawkCredentials.Algorithm.parse(map1.get("algorithm").toString())).build();
		// Once these have been configuration you can create a Hawk client:
		HawkClient hawkClient = new HawkClient.Builder().credentials(hawkCredentials).build();
		String hawkId = hawkClient.generateAuthorizationHeader(URI.create(requestUrl), requestMethod, null, null, app,
				null);
		hawkId = hawkId + ", app=\"" + app + "\"";
		//System.out.println(hawkId);
		return hawkId;
	}
	
	public static JSONObject getAuthTicket(JSONObject loginAuth) throws ParseException, MalformedURLException
	{
	//	hawkId = 
		String requestURL = BASEURI + "/oz/rsvp";
		JSONObject obj = new JSONObject();
		String rsvp1 = (String) loginAuth.get("rsvp");
		JSONParser parser = new JSONParser();
		obj.put("rsvp",rsvp1);
		Map appTicket1 = (Map) loginAuth.get("appTicket");
		requestSpec = RestAssured.given().contentType("application/json");
		requestSpec.header("Content-Type", "application/json;charset=UTF-8");
		requestSpec.header(HttpHeaders.AUTHORIZATION,AppTicket.getHawkId(requestURL, "POST", appTicket1));
		EncoderConfig ec = new EncoderConfig();
		Response response = requestSpec.given()
				.config(RestAssured.config()
						.encoderConfig(ec.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
				.contentType("application/json").body(obj).post(requestURL);
		String jsonAsString = response.getBody().asString();
		JSONObject object = (JSONObject) parser.parse(jsonAsString);
		//System.out.println("AuthTicket is ...."+object.toString());
		return object;
		
	}

}
