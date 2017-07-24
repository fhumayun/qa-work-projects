package utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONObject;
import cucumber.api.Scenario;
import utils.APIClient;
import utils.APIException;

public class TestrailResultUpload {
	
	static PropertiesFileReader prreader = new PropertiesFileReader();

	public void uploadResult(Scenario scenario) throws MalformedURLException, IOException, APIException {
		
		APIClient client = new APIClient(prreader.getPropertyvalues("TestRailUrl"));
		client.setUser(prreader.getPropertyvalues("TestRailUser"));
		client.setPassword(prreader.getPropertyvalues("TestRailPassword"));
		Map data = new HashMap();
		List<String> testResultDetails = getTestCaseIDAndStatus(scenario);
		String caseID = testResultDetails.get(0);
		int statusID = Integer.parseInt(testResultDetails.get(1));
		List<String> eachteststep = new ArrayList<String>();
		data.put("status_id", statusID);
		data.put("comment", "Automation framework executed [Test case - "+caseID+"](https://eei.testrail.com/index.php?/cases/view/"+caseID+")\n "
				+"Click on this link [SauceLabRecording](https://saucelabs.com/beta/dashboard/tests) to watch video recording of tests \n" + 
				"Click on this link [BDD Report](http://ci.eagleeye.io:8080/view/Nightlies/job/QAT%201.2.1%20Selenium%20Strax%20App%20Nightlies/Extent_Report) to see detailed test results.");
		JSONObject r = (JSONObject) client.sendPost("add_result_for_case/123/" + caseID + "", data);
		
		
	}

	public List<String> getTestCaseIDAndStatus(Scenario scenario) {
		String testCaseID = null;
		String status_id;
		Collection<String> tags = scenario.getSourceTagNames();
		List<String> testResult = new ArrayList<String>();
		for (String tag : tags) {
			if (tag.matches("@(?i)C[0-9]*")) {
				testCaseID = formatString(tag);
				// testCaseID = tag;

			}

		}
		switch (scenario.getStatus().toUpperCase()) {
		case "PASSED":
			status_id = "1";
			break;
		case "BLOCKED":
			status_id = "2";
			break;
		case "UNTESTED":
			status_id = "3";
			break;
		case "RETEST":
			status_id = "4";
			break;
		case "FAILED":
			status_id = "4";
			break;
		default:
			throw new IllegalArgumentException("Invalid test result status " + scenario.getStatus());

		}
		testResult.add(testCaseID);
		testResult.add(status_id);

		return testResult;

	}

	private String formatString(String tag) {

		String formattedTag = null;
		if (tag.startsWith("@C")) {
			formattedTag = tag.substring(2);
			return formattedTag;
		} else {
			return tag;
		}

	}

}
