package com.eagleeye.utils;

import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.jayway.restassured.response.Response;

public class JsonParser {
	JSONParser parser = new JSONParser();
	
	public String getPropertyValue(Response response, String key) throws ParseException
	{
		 String jsonAsString = response.getBody().asString();
	       JSONObject object =  (JSONObject) parser.parse(jsonAsString);
	       String value = (String) object.get(key);
		return value;
	}
	
	public String getDocumentID(Response response, String key1) throws ParseException
	{

		 String value="";
		 String jsonAsString = response.getBody().asString();
	       JSONArray arr =  (JSONArray) parser.parse(jsonAsString);
	       for(int i=0;i<arr.size();i++) {
	    	   JSONObject obj = (JSONObject) arr.get(i);
	    	  Set<?> keys = obj.keySet();
	    	  Iterator<?> a = keys.iterator();
	    	  while (a.hasNext()) {
	    		    String key = (String) a.next();
	    		    if(key.contains("loginId") && obj.get("loginId").equals(key1) )
	    		    {
	    		    	value = (String) obj.get("_id");
	    		    	break;
	    		    }

	    		}
	    	 
	    		  	}
		return value;
	}
	public String getDocID(Response response, String key,String value) throws ParseException
	{

		 String docId="";
		 String jsonAsString = response.getBody().asString();
	       JSONArray arr =  (JSONArray) parser.parse(jsonAsString);
	       for(int i=0;i<arr.size();i++) {
	    	   JSONObject obj = (JSONObject) arr.get(i);
	    	  Set<?> keys = obj.keySet();
	    	  Iterator<?> a = keys.iterator();
	    	  while (a.hasNext()) {
	    		    String k = (String) a.next();
	    		    if(k.contains(key) && obj.get(key).equals(value) )
	    		    {
	    		    	docId = (String) obj.get("_id");
	    		    	break;
	    		    }

	    		}
	    	 
	    		  	}
		return docId;
	}
	
	
	
}
