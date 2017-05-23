$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/features/STRAXLogin.feature");
formatter.feature({
  "line": 1,
  "name": "STRAX Login functionality",
  "description": "",
  "id": "strax-login-functionality",
  "keyword": "Feature"
});
formatter.before({
  "duration": 6415920552,
  "status": "passed"
});
formatter.scenario({
  "line": 3,
  "name": "Verify title bar reads STRAX(tm)",
  "description": "",
  "id": "strax-login-functionality;verify-title-bar-reads-strax(tm)",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "The STRAX Application login page is open",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "The title bar should contain STRAX",
  "keyword": "Then "
});
formatter.match({
  "location": "STRAXStepDefinition.openLoginPage()"
});
formatter.result({
  "duration": 3855088138,
  "status": "passed"
});
formatter.match({
  "location": "STRAXStepDefinition.The_title_bar_should_contain_STRAX()"
});
formatter.result({
  "duration": 372126568,
  "status": "passed"
});
formatter.after({
  "duration": 3653014028,
  "status": "passed"
});
formatter.scenarioOutline({
  "line": 7,
  "name": "Verify footer information is correct",
  "description": "",
  "id": "strax-login-functionality;verify-footer-information-is-correct",
  "type": "scenario_outline",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 8,
  "name": "The STRAX Application login page is open",
  "keyword": "Given "
});
formatter.step({
  "line": 9,
  "name": "The footer copyright should contain \"\u003cfooterString\u003e\"",
  "keyword": "Then "
});
formatter.examples({
  "line": 11,
  "name": "footer string",
  "description": "",
  "id": "strax-login-functionality;verify-footer-information-is-correct;footer-string",
  "rows": [
    {
      "cells": [
        "footerString"
      ],
      "line": 12,
      "id": "strax-login-functionality;verify-footer-information-is-correct;footer-string;1"
    },
    {
      "cells": [
        "© EagleEye Intelligence - 2015/2017 - Customer/Technical Support: 561-894-9865"
      ],
      "line": 13,
      "id": "strax-login-functionality;verify-footer-information-is-correct;footer-string;2"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 5087452410,
  "status": "passed"
});
formatter.scenario({
  "line": 13,
  "name": "Verify footer information is correct",
  "description": "",
  "id": "strax-login-functionality;verify-footer-information-is-correct;footer-string;2",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 8,
  "name": "The STRAX Application login page is open",
  "keyword": "Given "
});
formatter.step({
  "line": 9,
  "name": "The footer copyright should contain \"© EagleEye Intelligence - 2015/2017 - Customer/Technical Support: 561-894-9865\"",
  "matchedColumns": [
    0
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "STRAXStepDefinition.openLoginPage()"
});
formatter.result({
  "duration": 8830328136,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "© EagleEye Intelligence - 2015/2017 - Customer/Technical Support: 561-894-9865",
      "offset": 37
    }
  ],
  "location": "STRAXStepDefinition.The_footer_copyright_should_contain_String(String)"
});
formatter.result({
  "duration": 659994295,
  "status": "passed"
});
formatter.after({
  "duration": 2514352034,
  "status": "passed"
});
formatter.scenarioOutline({
  "line": 15,
  "name": "Login with valid credentials",
  "description": "",
  "id": "strax-login-functionality;login-with-valid-credentials",
  "type": "scenario_outline",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 16,
  "name": "The STRAX Application login page is open",
  "keyword": "Given "
});
formatter.step({
  "line": 17,
  "name": "User Enters Valid \"\u003cusername\u003e\" and \"\u003cpassword\u003e\"",
  "keyword": "When "
});
formatter.step({
  "line": 18,
  "name": "The Login should be successful",
  "keyword": "Then "
});
formatter.examples({
  "line": 20,
  "name": "valid username/password combination",
  "description": "",
  "id": "strax-login-functionality;login-with-valid-credentials;valid-username/password-combination",
  "rows": [
    {
      "cells": [
        "username",
        "password"
      ],
      "line": 21,
      "id": "strax-login-functionality;login-with-valid-credentials;valid-username/password-combination;1"
    },
    {
      "cells": [
        "yogi@msys.com",
        "eei"
      ],
      "line": 22,
      "id": "strax-login-functionality;login-with-valid-credentials;valid-username/password-combination;2"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 5693635397,
  "status": "passed"
});
formatter.scenario({
  "line": 22,
  "name": "Login with valid credentials",
  "description": "",
  "id": "strax-login-functionality;login-with-valid-credentials;valid-username/password-combination;2",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 16,
  "name": "The STRAX Application login page is open",
  "keyword": "Given "
});
formatter.step({
  "line": 17,
  "name": "User Enters Valid \"yogi@msys.com\" and \"eei\"",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "When "
});
formatter.step({
  "line": 18,
  "name": "The Login should be successful",
  "keyword": "Then "
});
formatter.match({
  "location": "STRAXStepDefinition.openLoginPage()"
});
formatter.result({
  "duration": 3144566334,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "yogi@msys.com",
      "offset": 19
    },
    {
      "val": "eei",
      "offset": 39
    }
  ],
  "location": "STRAXStepDefinition.User_Enters_Valid_username_and_password(String,String)"
});
formatter.result({
  "duration": 3348096109,
  "status": "passed"
});
formatter.match({
  "location": "STRAXStepDefinition.The_Login_should_be_successful()"
});
formatter.result({
  "duration": 2843514227,
  "status": "passed"
});
formatter.after({
  "duration": 2387589381,
  "status": "passed"
});
formatter.scenarioOutline({
  "line": 24,
  "name": "Login with invalid credentials",
  "description": "",
  "id": "strax-login-functionality;login-with-invalid-credentials",
  "type": "scenario_outline",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 25,
  "name": "The STRAX Application login page is open",
  "keyword": "Given "
});
formatter.step({
  "line": 26,
  "name": "User Enters invalid \"\u003cusername\u003e\" and \"\u003cpassword\u003e\"",
  "keyword": "When "
});
formatter.step({
  "line": 27,
  "name": "The Login should be failed",
  "keyword": "Then "
});
formatter.examples({
  "line": 29,
  "name": "Invalid username/password combination",
  "description": "",
  "id": "strax-login-functionality;login-with-invalid-credentials;invalid-username/password-combination",
  "rows": [
    {
      "cells": [
        "username",
        "password"
      ],
      "line": 30,
      "id": "strax-login-functionality;login-with-invalid-credentials;invalid-username/password-combination;1"
    },
    {
      "cells": [
        "yogi@msys.com",
        "eei123"
      ],
      "line": 31,
      "id": "strax-login-functionality;login-with-invalid-credentials;invalid-username/password-combination;2"
    },
    {
      "cells": [
        "yogi",
        "1234"
      ],
      "line": 32,
      "id": "strax-login-functionality;login-with-invalid-credentials;invalid-username/password-combination;3"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 5088604883,
  "status": "passed"
});
formatter.scenario({
  "line": 31,
  "name": "Login with invalid credentials",
  "description": "",
  "id": "strax-login-functionality;login-with-invalid-credentials;invalid-username/password-combination;2",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 25,
  "name": "The STRAX Application login page is open",
  "keyword": "Given "
});
formatter.step({
  "line": 26,
  "name": "User Enters invalid \"yogi@msys.com\" and \"eei123\"",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "When "
});
formatter.step({
  "line": 27,
  "name": "The Login should be failed",
  "keyword": "Then "
});
formatter.match({
  "location": "STRAXStepDefinition.openLoginPage()"
});
formatter.result({
  "duration": 3332586684,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "yogi@msys.com",
      "offset": 21
    },
    {
      "val": "eei123",
      "offset": 41
    }
  ],
  "location": "STRAXStepDefinition.User_Enters_invalid_username_and_password(String,String)"
});
formatter.result({
  "duration": 3385723187,
  "status": "passed"
});
formatter.match({
  "location": "STRAXStepDefinition.The_Login_should_be_failed()"
});
formatter.result({
  "duration": 2768915208,
  "status": "passed"
});
formatter.after({
  "duration": 2302407707,
  "status": "passed"
});
formatter.before({
  "duration": 4885099055,
  "status": "passed"
});
formatter.scenario({
  "line": 32,
  "name": "Login with invalid credentials",
  "description": "",
  "id": "strax-login-functionality;login-with-invalid-credentials;invalid-username/password-combination;3",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 25,
  "name": "The STRAX Application login page is open",
  "keyword": "Given "
});
formatter.step({
  "line": 26,
  "name": "User Enters invalid \"yogi\" and \"1234\"",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "When "
});
formatter.step({
  "line": 27,
  "name": "The Login should be failed",
  "keyword": "Then "
});
formatter.match({
  "location": "STRAXStepDefinition.openLoginPage()"
});
formatter.result({
  "duration": 2995476917,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "yogi",
      "offset": 21
    },
    {
      "val": "1234",
      "offset": 32
    }
  ],
  "location": "STRAXStepDefinition.User_Enters_invalid_username_and_password(String,String)"
});
formatter.result({
  "duration": 2978989065,
  "status": "passed"
});
formatter.match({
  "location": "STRAXStepDefinition.The_Login_should_be_failed()"
});
formatter.result({
  "duration": 10317817921,
  "error_message": "org.openqa.selenium.TimeoutException: Expected condition failed: waiting for element to be clickable: By.xpath: //span[@class\u003d\u0027md-toast-text ng-binding\u0027] (tried for 10 second(s) with 500 MILLISECONDS interval)\r\n\tat org.openqa.selenium.support.ui.WebDriverWait.timeoutException(WebDriverWait.java:80)\r\n\tat org.openqa.selenium.support.ui.FluentWait.until(FluentWait.java:232)\r\n\tat page_objects.LoginPage.getLoginFailedError(LoginPage.java:58)\r\n\tat step_definitions.STRAXStepDefinition.The_Login_should_be_failed(STRAXStepDefinition.java:107)\r\n\tat ✽.Then The Login should be failed(src/test/resources/features/STRAXLogin.feature:27)\r\nCaused by: org.openqa.selenium.NoSuchElementException: Cannot locate an element using By.xpath: //span[@class\u003d\u0027md-toast-text ng-binding\u0027]\nFor documentation on this error, please visit: http://seleniumhq.org/exceptions/no_such_element.html\nBuild info: version: \u00273.4.0\u0027, revision: \u0027unknown\u0027, time: \u0027unknown\u0027\nSystem info: host: \u0027MSYS\u0027, ip: \u0027192.168.101.32\u0027, os.name: \u0027Windows 8.1\u0027, os.arch: \u0027amd64\u0027, os.version: \u00276.3\u0027, java.version: \u00271.8.0_121\u0027\nDriver info: driver.version: unknown\r\n\tat org.openqa.selenium.support.ui.ExpectedConditions.lambda$findElement$0(ExpectedConditions.java:883)\r\n\tat java.util.Optional.orElseThrow(Optional.java:290)\r\n\tat org.openqa.selenium.support.ui.ExpectedConditions.findElement(ExpectedConditions.java:882)\r\n\tat org.openqa.selenium.support.ui.ExpectedConditions.access$000(ExpectedConditions.java:44)\r\n\tat org.openqa.selenium.support.ui.ExpectedConditions$7.apply(ExpectedConditions.java:206)\r\n\tat org.openqa.selenium.support.ui.ExpectedConditions$7.apply(ExpectedConditions.java:202)\r\n\tat org.openqa.selenium.support.ui.ExpectedConditions$22.apply(ExpectedConditions.java:644)\r\n\tat org.openqa.selenium.support.ui.ExpectedConditions$22.apply(ExpectedConditions.java:641)\r\n\tat org.openqa.selenium.support.ui.FluentWait.until(FluentWait.java:209)\r\n\tat page_objects.LoginPage.getLoginFailedError(LoginPage.java:58)\r\n\tat step_definitions.STRAXStepDefinition.The_Login_should_be_failed(STRAXStepDefinition.java:107)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n\tat java.lang.reflect.Method.invoke(Method.java:498)\r\n\tat cucumber.runtime.Utils$1.call(Utils.java:37)\r\n\tat cucumber.runtime.Timeout.timeout(Timeout.java:13)\r\n\tat cucumber.runtime.Utils.invoke(Utils.java:31)\r\n\tat cucumber.runtime.java.JavaStepDefinition.execute(JavaStepDefinition.java:37)\r\n\tat cucumber.runtime.StepDefinitionMatch.runStep(StepDefinitionMatch.java:37)\r\n\tat cucumber.runtime.Runtime.runStep(Runtime.java:298)\r\n\tat cucumber.runtime.model.StepContainer.runStep(StepContainer.java:44)\r\n\tat cucumber.runtime.model.StepContainer.runSteps(StepContainer.java:39)\r\n\tat cucumber.runtime.model.CucumberScenario.run(CucumberScenario.java:48)\r\n\tat cucumber.runtime.junit.ExecutionUnitRunner.run(ExecutionUnitRunner.java:91)\r\n\tat org.junit.runners.Suite.runChild(Suite.java:127)\r\n\tat org.junit.runners.Suite.runChild(Suite.java:26)\r\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:238)\r\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:63)\r\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:236)\r\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:53)\r\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:229)\r\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:309)\r\n\tat cucumber.runtime.junit.ExamplesRunner.run(ExamplesRunner.java:59)\r\n\tat org.junit.runners.Suite.runChild(Suite.java:127)\r\n\tat org.junit.runners.Suite.runChild(Suite.java:26)\r\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:238)\r\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:63)\r\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:236)\r\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:53)\r\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:229)\r\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:309)\r\n\tat cucumber.runtime.junit.ScenarioOutlineRunner.run(ScenarioOutlineRunner.java:53)\r\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:63)\r\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:18)\r\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:238)\r\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:63)\r\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:236)\r\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:53)\r\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:229)\r\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:309)\r\n\tat cucumber.runtime.junit.FeatureRunner.run(FeatureRunner.java:70)\r\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:93)\r\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:37)\r\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:238)\r\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:63)\r\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:236)\r\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:53)\r\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:229)\r\n\tat org.junit.internal.runners.statements.RunAfters.evaluate(RunAfters.java:27)\r\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:309)\r\n\tat cucumber.api.junit.Cucumber.run(Cucumber.java:98)\r\n\tat org.apache.maven.surefire.junit4.JUnit4Provider.execute(JUnit4Provider.java:252)\r\n\tat org.apache.maven.surefire.junit4.JUnit4Provider.executeTestSet(JUnit4Provider.java:141)\r\n\tat org.apache.maven.surefire.junit4.JUnit4Provider.invoke(JUnit4Provider.java:112)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n\tat java.lang.reflect.Method.invoke(Method.java:498)\r\n\tat org.apache.maven.surefire.util.ReflectionUtils.invokeMethodWithArray(ReflectionUtils.java:189)\r\n\tat org.apache.maven.surefire.booter.ProviderFactory$ProviderProxy.invoke(ProviderFactory.java:165)\r\n\tat org.apache.maven.surefire.booter.ProviderFactory.invokeProvider(ProviderFactory.java:85)\r\n\tat org.apache.maven.surefire.booter.ForkedBooter.runSuitesInProcess(ForkedBooter.java:115)\r\n\tat org.apache.maven.surefire.booter.ForkedBooter.main(ForkedBooter.java:75)\r\n",
  "status": "failed"
});
formatter.after({
  "duration": 2364376132,
  "status": "passed"
});
});