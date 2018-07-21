package com.eagleeye.services;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.eagleeye.utils.PropertiesFileReader;
import com.jayway.restassured.specification.RequestSpecification;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.wealdtech.hawk.HawkClient;
import com.wealdtech.hawk.HawkCredentials;

public class AppTicket {
	public  static String app;
    public static String key;
    public static String algorithm;
    public static  String id;
    public  static String[] scope;
    public  static double exp;
    public RequestSpecification requestSpec;
    static PropertiesFileReader prreader = new PropertiesFileReader();
    public static String BASEURI = prreader.getPropertyvalues("STRAXUrl");
    
	 public static Map<?,?> getAppTicket(String userName,String password) throws MalformedURLException
	    {
		 Map<?,?> map1=null; 
	    	try {
	        String payload= "{\"username\":\""+userName+"\",\"password\":\""+password+"\",\"applnName\":\"STRAX APP AngularJS\",\"applnType\":\"Web Application\",\"fingerprint\":\"74681325572dc861723eff28c006a55f\",\"fpData\":[]}";
	        Client restClient = Client.create();
	        WebResource request = restClient.resource(BASEURI+"/api/login");
	        JSONParser parser = new JSONParser();
	        JSONObject jo = (JSONObject) parser.parse(payload);
	        ClientResponse response2  = request.header("Content-Type", "application/json").post(ClientResponse.class, jo);
	        response2.bufferEntity();
	        JSONObject json = response2.getEntity(JSONObject.class);
	        map1 = (HashMap<?, ?>) json.get("appTicket");
	        //AppTicket appTicket = (AppTicket) json.get("appTicket");
	  /*      app = (String) map1.get("app");
	        key = (String) map1.get("key");
	        algorithm =  (String) map1.get("algorithm");
	        id =(String) map1.get("id");*/
	        
	    	}
	    	catch(Exception e)
	    	{
	    		System.out.println("Exception while getting AppTicket..");
	    	}
			return map1;
	    }
		
		public static String getHawkId(String requestUrl, String requestMethod,Map<?,?> map1) throws MalformedURLException {
	       	// getAppTicket(userName,password);
			System.out.println("in hawk ID method...");
			app = (String) map1.get("app");
	         HawkCredentials hawkCredentials = new HawkCredentials.Builder()
	                 .keyId((String) map1.get("id"))
	                 .key((String) map1.get("key"))
	                 .algorithm(HawkCredentials.Algorithm.parse(map1.get("algorithm").toString()))
	                 .build();
	         //Once these have been configuration you can create a Hawk client:
	         HawkClient hawkClient = new HawkClient.Builder().credentials(hawkCredentials).build();
	         String hawkId = hawkClient.generateAuthorizationHeader(URI.create(requestUrl),requestMethod,null,null,app,null);
	         hawkId = hawkId + ", app=\"" + app + "\"";
	         System.out.println(hawkId);
	         return hawkId;
	     }

}
