package com.eagleeye.testrunner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(format = { "progress", "html:target/cucumber" }, 
plugin = {"ru.yandex.qatools.allure.cucumberjvm.AllureReporter"}, features = {"src/test/resources/features/" }, glue = { "com.eagleeye.stepdefinitions" },
tags = { "~@ignore" })
public class runCukesTest {
}


