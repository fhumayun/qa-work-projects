*** Keywords ***

Open test browser
    Open browser  about:  ${BROWSER}
    ...  remote_url=${REMOTE_URL}
    ...  desired_capabilities=${DESIRED_CAPABILITIES}

Close test browser
    Run keyword if  '${REMOTE_URL}' != ''
    ...  Report Sauce status
    ...  ${SUITE_NAME} | ${TEST_NAME}
    ...  ${TEST_STATUS}  ${TEST_TAGS}  ${REMOTE_URL}
    Close all browsers