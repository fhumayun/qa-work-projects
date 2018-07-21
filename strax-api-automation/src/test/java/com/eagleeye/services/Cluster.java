package com.eagleeye.services;

import java.net.MalformedURLException;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.google.common.net.HttpHeaders;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;


public class Cluster extends BaseService {
	
	public Cluster(RequestSpecification requestSpec)
	{
		this.requestSpec = RestAssured.given().contentType("application/json");
				
	}
	
	public Response getCluster(Map appTicket) throws MalformedURLException
	{
		String requestURL = BASEURI+"/api/clusters/5b35142dc92b73001c9813d1";
		Response response = requestSpec.header(HttpHeaders.AUTHORIZATION,AppTicket.getHawkId(requestURL,"GET",appTicket)).given().get(requestURL);
		System.out.println("Response from the API end point : "+response.getBody().asString());
		return response;
	}
	
	public Response deleteCluster(Map appTicket) throws MalformedURLException
	{
		String requestURL = BASEURI+"/api/clusters/5b3a33700852828fecbef7c6";
		String body = "{\"Type\":\"object\",\"status\":\"true\"}";
		Response response = requestSpec.header(HttpHeaders.AUTHORIZATION,AppTicket.getHawkId(requestURL,"DELETE",appTicket)).given().contentType("application/json").delete(requestURL);
		System.out.println("Response from the API end point : "+response.getBody().asString());
		return response;
	}

	public Response escalateIER(Map appTicket) throws MalformedURLException {
		String requestURL = BASEURI+"/api/ier/alerts/5b3b1d609ff8c20026ff6c95/escalate";
		Response response = requestSpec.header(HttpHeaders.AUTHORIZATION,AppTicket.getHawkId(requestURL,"GET",appTicket)).given().contentType("application/json").get(requestURL);
		System.out.println("Response from the API end point : "+response.getBody().asString());
		return response;
	}
	public Response createCluster(Map appTicket) throws MalformedURLException, ParseException {
		String requestURL = BASEURI+"/api/clusters";
		String body = "{\"accountDocId\":\"000000000000000000000002\",\"address\":\"\",\"clusterType\":\"DYNAMIC\",\"description\":\"APITEST\",\"fidgets\":\"\",\"incident\":\"API-POST-TEST\",\"caseNumber\":\"1222\",\"participants\":[]}";
        JSONParser parser = new JSONParser();
        JSONObject jo = (JSONObject) parser.parse(body);
        System.out.println(jo.toString());
		Response response = requestSpec.header(HttpHeaders.AUTHORIZATION,AppTicket.getHawkId(requestURL,"POST",appTicket)).given().contentType("application/json").body(jo).post(requestURL);
		System.out.println("Response from the API end point : "+response.getBody().asString());
		return response;
	}

	public Response getChatMsg(Map appTicket) throws MalformedURLException {
		String requestURL = BASEURI+"/api/mq/messages/clusters/5b4dedaec2556e3ea745a2fd/chats";
		
        Response response = requestSpec.header(HttpHeaders.AUTHORIZATION,AppTicket.getHawkId(requestURL,"GET",appTicket)).given().contentType("application/json").get(requestURL);
		System.out.println("Response from the API end point : "+response.getBody().asString());
		return response;
	}

	public Response getEventPlans(Map appTicket) throws MalformedURLException {
		String requestURL = BASEURI+"/api/eventplans";
		
        Response response = requestSpec.header(HttpHeaders.AUTHORIZATION,AppTicket.getHawkId(requestURL,"GET",appTicket)).given().contentType("application/json").get(requestURL);
		System.out.println("Response from the API end point : "+response.getBody().asString());
		return response;
	}

}
