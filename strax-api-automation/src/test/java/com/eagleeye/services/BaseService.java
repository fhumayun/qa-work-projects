package com.eagleeye.services;
import com.eagleeye.utils.PropertiesFileReader;
import com.jayway.restassured.specification.RequestSpecification;


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
	//Get STRAX API url from Jenkins environment variable
	//public static String BASEURI = System.getProperty("BaseURI");
}
