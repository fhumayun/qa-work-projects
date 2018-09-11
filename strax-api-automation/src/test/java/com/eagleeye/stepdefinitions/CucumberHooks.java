package com.eagleeye.stepdefinitions;


import com.eagleeye.services.BaseService;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class CucumberHooks extends BaseService {
	@Before
	public void setUp(Scenario scenario){
		

	}
	
	@After
	public void teardown(Scenario scenario){
		System.gc();
	}
	}


