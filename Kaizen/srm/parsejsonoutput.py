#!/usr/bin/env python

import json

json_filename = "jsonoutput.json"

""" TODO
make this look better for robots, not humans. Ex:

{
    "status_id": 5,
    "comment": "This test failed",
    "elapsed": "15s",
    "defects": "TR-7",
    "version": "1.0 RC1 build 3724",

    ..

    "custom_step_results": [
        {
            "content": "Step 1",
            "expected": "Expected Result 1",
            "actual": "Actual Result 1",
            "status_id": 1
        },
        {
            "content": "Step 2",
            "expected": "Expected Result 2",
            "actual": "Actual Result 2",
            "status_id": 2
        },

        ..
    ]

    ..
}
"""

with open(json_filename, 'r') as json_file_obj:
    json_file_contents = json.loads(json_file_obj.read())
    json_file_obj.close()

for scenario in json_file_contents[0]["elements"]:
    scenario_name = scenario['name']

    print('-----------------------------------------------')
    print(scenario_name)
    print('steps: ')
    for step in scenario['steps']:
        print('    ' + str(step['name']) + ' : ' + str(step['result']['status']))

    print('')