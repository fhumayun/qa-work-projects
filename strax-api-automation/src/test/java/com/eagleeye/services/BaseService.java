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
import com.jayway.restassured.specification.RequestSpecification;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.wealdtech.hawk.HawkClient;
import com.wealdtech.hawk.HawkCredentials;

public class BaseService {
	
	public  String app;
    public  String key;
    public  String algorithm;
    public   String id;
    public   String[] scope;
    public   double exp;
    public RequestSpecification requestSpec;
    PropertiesFileReader prreader = new PropertiesFileReader();
    public String BASEURI = prreader.getPropertyvalues("STRAXUrl");
    
    public void getAppTicket() throws MalformedURLException
    {

    	try {
        String payload= "{\"username\":\"yogi@msys.com\",\"password\":\"Password1@\",\"applnName\":\"STRAX APP AngularJS\",\"applnType\":\"Web Application\",\"fingerprint\":\"74681325572dc861723eff28c006a55f\",\"fpData\":[]}";
        Client restClient = Client.create();
        WebResource request = restClient.resource(BASEURI+"/api/login");
        JSONParser parser = new JSONParser();
        JSONObject jo = (JSONObject) parser.parse(payload);
        ClientResponse response2  = request.header("Content-Type", "application/json").post(ClientResponse.class, jo);
        response2.bufferEntity();
        JSONObject json = response2.getEntity(JSONObject.class);
        System.out.print(json);
        Map<?,?> map1  = (HashMap<?, ?>) json.get("appTicket");
        System.out.println(map1);
        System.out.println(map1.get("algorithm"));
        app = (String) map1.get("app");
        key = (String) map1.get("key");
        algorithm =  (String) map1.get("algorithm");
        id =(String) map1.get("id");
    	}
    	catch(Exception e)
    	{
    		System.out.println("Exception while getting AppTicket..");
    	}
    }
	
	public String getHawkId(String requestUrl, String requestMethod) throws MalformedURLException {
       	 getAppTicket();
         HawkCredentials hawkCredentials = new HawkCredentials.Builder()
                 .keyId(this.id)
                 .key(this.key)
                 .algorithm(HawkCredentials.Algorithm.parse(algorithm.toString()))
                 .build();
         //Once these have been configuration you can create a Hawk client:
         HawkClient hawkClient = new HawkClient.Builder().credentials(hawkCredentials).build();
         String hawkId = hawkClient.generateAuthorizationHeader(URI.create(requestUrl),requestMethod,null,null,app,null);
         hawkId = hawkId + ", app=\"" + app + "\"";
         return hawkId;
     }
}
