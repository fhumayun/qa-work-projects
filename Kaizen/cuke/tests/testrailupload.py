#!/usr/bin/env python3

import requests
import sys
import uuid
import json

# Globals
# Takes parameters:
#     int: 0 pass, >0 failed
# Uploads test run results to Test Rail
cuke_exit_code = int(sys.argv[1]) if len(sys.argv) > 1 else sys.exit('[ERROR] No exit code specified...')
project_id = 1
suite_id = 1
suite_name = "Automated Cucumber&Selenium CI tests"
assignedto_id = 1
new_test_run_id = 0
testcase_id = 8379
authorization = ("jshanahan@eagleeyeintelligence.com", "mytestrailpassword")

tmp_ouput_file = "/tmp/{}".format(uuid.uuid4())

debug = False

# Set the comment text for when we push the results
# *!* Bash pass is 0 but fail is >0
# *!* TestRail pass:1, retest:4, fail:5
if cuke_exit_code == 0:
    comment = "Pass"
    status_id = 1
else:
    comment = "Retest"
    status_id = 4

# Funcs

def _generate_step_results(json_filename="jsonoutput.json"):
    """ Opens the cucumberjs json results file and returns a json string
    correctly formatted for the TestRail api
    """
    if debug:
        print('[DEBUG] Func: _generate_step_results...')

    testrail_format = {"custom_step_results": []}    # Json format that TestRail requires
    step_counter = 1

    with open(json_filename, 'r') as json_file_obj:
        json_file_contents = json.loads(json_file_obj.read())
        json_file_obj.close()

    for feature in json_file_contents:
        for step in feature['elements'][0]['steps']:

            # Create "content": "Step j"
            test_step = {}

            test_step["content"] = "Step " + str(step_counter)

            # Create "expected": "foo"
            test_step["name"] = str(step['name'])
            test_step["expected"] = "passed"

            # Create "actual": "foo"
            test_step["actual"] = str(step['result']['status'])

            # Create "status_id": 1 pass, 4 retest
            test_step["status_id"] = 1 if test_step["expected"] == test_step["actual"] else 4

            testrail_format['custom_step_results'].append(test_step)

            step_counter += 1

    return json.dumps(testrail_format["custom_step_results"])

def create_new_test_run():
    """ Create a new test run """
    if debug:
        print('[DEBUG] Func: create_new_test_run...')

    new_test_run_url = "https://eei.testrail.com/index.php?/api/v2/add_run/{0}=".format(project_id)

    new_test_run_json = {
        "suite_id": suite_id,
        "name": suite_name,
        "assignedto_id": assignedto_id,
        "include_all": False,
        "case_ids": [testcase_id]
    }

    new_test_run = requests.post(new_test_run_url, auth=authorization, json=new_test_run_json)

    if str(new_test_run.status_code) != '200':
        print('[ERROR] new_test_run: non 200 status code... ' + str(new_test_run.status_code))
        print(str(new_test_run.json()))
        sys.exit(1)

    global new_test_run_id
    new_test_run_id = str(new_test_run.json()["id"])


def upload_test_run_results():
    """ Upload test run results """
    if debug:
        print('[DEBUG] Func: upload_test_run_results...')

    if new_test_run_id == 0:
        print('[ERROR] new_test_run: id could not be found... ' + str(new_test_run_id))
        sys.exit(1)

    if debug:
        print('[DEBUG] Adding results to new test run: ID: {0}...'.format(new_test_run_id))

    upload_results_url = "https://eei.testrail.com/index.php?/api/v2/add_result_for_case/{0}/{1}=".format(new_test_run_id, testcase_id)

    upload_results_json = {
        "status_id": status_id,
        "comment": comment,
        "version": "1",
        "elapsed": "2m",
        "custom_step_results": json.loads(custom_step_results)
    }

    update_results = requests.post(upload_results_url, auth=authorization, json=upload_results_json)

    if str(update_results.status_code) != '200':
        print('[ERROR] update_results: non 200 status code... ' + str(update_results.status_code))
        print(str(update_results.json()))
        sys.exit(1)


def close_test_rail_run():
    """ Close test run """
    if debug:
        print('[DEBUG] Func: close_test_rail_run...')

    close_test_run_url = "https://eei.testrail.com/index.php?/api/v2/close_run/{0}".format(new_test_run_id)

    close_test_run = requests.post(close_test_run_url, auth=authorization, json='[]')

    if str(close_test_run.status_code) != '200':
        print('[ERROR] close_test_run: non 200 status code... ' + str(close_test_run.status_code))
        print(str(close_test_run.json()))
        sys.exit(1)

# Start
custom_step_results = _generate_step_results()

if custom_step_results == '':
    print('[ERROR] Json output file contains no JSON...')
    sys.exit(1)

create_new_test_run()
upload_test_run_results()
close_test_rail_run()

# Fin
# todo Clean up tmp file
print('[INFO] Done...')