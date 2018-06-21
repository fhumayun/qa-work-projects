package com.eagleeye.services;


import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class CucumberHooks {
	@Before
	public void setUp(Scenario scenario){
		
		System.out.println("in cucumber..");
	}
	
	@After
	public void teardown(Scenario scenario){
	}
	}


