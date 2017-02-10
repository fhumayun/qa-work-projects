#!/bin/bash

# Takes parameters:
#     int: 0 pass, >0 failed
# Uploads test run results to Test Rail

echo '-------------------------------'
echo $0
echo '-------------------------------'

# Check an arg was passed
if [[ -z "$1" ]]; then
	echo 'Pass/fail required... Exit code required...'
	exit 1
fi

# Set the comment text for when we push the results
# *!* Bash pass is 0 but testrail pass is 1 *!*
# *!* Bash fail is >0 but testrail fail is 5 *!*
if [[ "$1" == 0 ]]; then
	COMMENT="Pass"
    STATUSID=1
else
	COMMENT="Fail"
    STATUSID=5
fi

TMPOUTPUTFILE="/tmp/$(uuidgen)"
echo "Created tmp file: ${TMPOUTPUTFILE}..."

# --- Create a test run w no results yet

TESTCASEID=6232
AUTHORIZATION="Basic anNoYW5haGFuQGVhZ2xlZXllaW50ZWxsaWdlbmNlLmNvbTpEZXZudWxsOTkq"
PROJECTID=1
SUITEID=1
NAME="Automated Cucumber&Selenium CI tests"
ASSIGNEDTOID=1

echo "Creating new TestRail run..."

curl -o ${TMPOUTPUTFILE} -# -X POST -H "Authorization: ${AUTHORIZATION}" \
	-H "Content-Type: application/json" \
	-H "Cache-Control: no-cache" \
	-d '{
    "suite_id": "'"${SUITEID}"'",
    "name": "'"${NAME}"'",
    "assignedto_id": "'"${ASSIGNEDTOID}"'",
    "include_all": false,
    "case_ids": ['${TESTCASEID}'] }' \
        "https://eei.testrail.com/index.php?/api/v2/add_run/${PROJECTID}="

# Grab test run id:
TESTRUNID=$(cat ${TMPOUTPUTFILE} | jq '.id' | xargs)

# --- Add results to test run

echo "Adding results to new test run: ${TESTRUNID}..."

curl -o /dev/null -# -X POST -H "Authorization: ${AUTHORIZATION}" \
	-H "Content-Type: application/json" \
	-H "Cache-Control: no-cache" \
	-d '{
    "status_id": "'"${STATUSID}"'",
    "comment": "'"${COMMENT}"'",
    "version":"1.1",
    "elapsed":"2m"
}' "https://eei.testrail.com/index.php?/api/v2/add_result_for_case/${TESTRUNID}/${TESTCASEID}="

echo "Cleaning up..."
rm ${TMPOUTPUTFILE}
echo "Done..."
