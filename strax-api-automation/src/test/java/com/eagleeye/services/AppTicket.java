package com.eagleeye.services;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
	public RequestSpecification requestSpec;
	static PropertiesFileReader prreader = new PropertiesFileReader();
	public static String BASEURI = prreader.getPropertyvalues("STRAXUrl");

	public static Map<?, ?> getAppTicket(String userName, String password) throws MalformedURLException {
		Map<?, ?> map1 = null;
		try {
			String payload = "{\"username\":\"" + userName + "\",\"password\":\"" + password
					+ "\",\"applnName\":\"STRAX APP AngularJS\",\"applnType\":\"Web Application\",\"fingerprint\":\"74681325572dc861723eff28c006a55f\",\"fpData\":[]}";
			String requestURL = BASEURI + "/api/login";
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

		} catch (Exception e) {
			System.out.println("Exception while getting AppTicket..");
		}
		return map1;
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
		return hawkId;
	}

}
