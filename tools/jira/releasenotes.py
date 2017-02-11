#!/usr/bin/env python

import json
import requests

jql_string = 'Project = STX and \"Epic Link\" = STX-1.1 and Type != Bug and Type != Risk and Type != Incident'
jira_search_url = 'https://eagleeyeintel.atlassian.net/rest/api/2/search'

req_headers = {'Content-type': 'application/json', 'Authorization': 'Basic Zmh1bWF5dW5AZ3JvdXBjYXJldGVjaC5jb206Y2FyZXRlYW0='}
req_payload = {"jql":jql_string}

#"jql": "project = STX AND Type = Release",
#"startAt": 0,
#"maxResults": 1,
#"fields": [
        #"customfield_10020"
    #]
#}' ${URL} 2> /dev/null

r = requests.post(jira_search_url, json=req_payload, headers=req_headers)

if r.status_code is not 200:
    exit('Non 200 status code')

json_res = json.loads(r.text)

json_issues = json_res['issues']

for issue in json_issues:
    print('Issue summary: ' + str(issue['fields']['summary']))
