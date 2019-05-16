package com.eagleeye.stepdefinitions;


import java.io.IOException;
import java.net.MalformedURLException;

import com.eagleeye.services.BaseService;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import com.eagleeye.utils.APIException;
import com.eagleeye.utils.TestrailResultUpload;

public class CucumberHooks extends BaseService {
	TestrailResultUpload testresult = new TestrailResultUpload();
	@Before
	public void setUp(Scenario scenario){
		System.out.println("Executing Scenario : "+scenario.getName());

	}
	
	@After
	public void teardown(Scenario scenario) throws MalformedURLException, IOException, APIException{
		System.gc();
		testresult.uploadResult(scenario);
	}
	}


