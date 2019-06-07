***Settings***
Documentation   API Test Suite w/ Selenium Validation
...
...  Strax Version: 2.0
...
...  Included Librarys
...      - ExtendedSelenium2Library
...      - ExtendedRequestsLibrary
...      - OperatingSystem
...      - Collections
...      - String
...  Additional Docs: /strax-rf-automation/Documentation
...
...  Run On LocalHost CMD:   robot -d ../Logs newAPI.robot
...  Run On SauceLabs CMD:   robot -v DESIRED_CAPABILITIES:"platform:Windows 10,browserName:Chrome" -v REMOTE_URL:http://mmaney:ad0b6583-5d8f-46f9-8ca8-f32fc56bc2ba@ondemand.saucelabs.com:80/wd/hub newAPI.robot
# Libraries #
Library     ExtendedSelenium2Library   
Library     ExtendedRequestsLibrary
Library     OperatingSystem
Library     Collections
Library     String 
# Resources #
Resource  ../Resources/validate-api.robot                       # /Users/Mack/Development/strax-qa/strax-rf-automation/API   <-- Path if changes dont work
Resource  ../Resources/setup.robot                              # /Users/Mack/Development/strax-qa/strax-rf-automation/API   <-- Path if changes dont work

# Setup/Teardown #
Suite Teardown  Delete All Sessions

***Variables***
# Selenium Test Variables #
${BROWSER} =  gc
#${ENV} =   http://localhost:8091 
${USER} =  Mack@ee.io  
${PASS} =  Password1@
# API Test Variables #
${uri}=         http://localhost:8080  
${json_path}=  ../Utils/testData/    
${partDocId}=  5c88079f440022002010ba1a

***Test Cases***
#Login
#    Create Session  localhost   ${uri}
#    ${file_data}=  Get File  ${json_path}participants-add.json
#    &{headers}  Create Dictionary   Content-Type=application/json = ${file_data}
#    ${response} =   Post Request  localhost  /api/participants/doAuthenticate
#    Should Be Equal As Strings  ${response.status_code}  200
#    Log    ${response}

Get Users     
    Create Session  localhost   ${uri}
    ${response} =  Get Request  localhost  /api/participants  
    Should Be Equal As Strings  ${response.status_code}  200
    ${json} =  Set Variable  ${response.json()}
    Log  ${json}
 
Post Users
    Create Session  localhost  ${uri}
    &{headers}  Create Dictionary  Content-Type=application/json; charset=utf-8
    ${file_data}=  Get File  ${json_path}participants-add.json
    ${resp}=  Post Request  localhost  /api/participants/${partDocId}  data=${file_data}  headers=${headers}
    Should Be Equal As Strings  ${resp.status_code}  200

#Verify User Has Been Created
#    [Documentation]  Verify User has been created via API POST Call
#    [Setup]     Begin Web Test
#    [Teardown]  End Web Test
#    [Tags]  Smoke
#    Given a successful POST request
#    When Testbot opens strax
#    Then API-Test user will be visible

PUT Requests with Json Data
    [Tags]  patch
    Create Session  localhost  ${uri}
    &{headers}  Create Dictionary  Content-Type=application/json; charset=utf-8
    ${file_data}=  Get File  ${json_path}participants-update.json
    ${resp}=  Put Request  localhost  /api/participants/${partDocId}  data=${file_data}  headers=${headers}
    Should Be Equal As Strings  ${resp.status_code}  200

Delete Request With No Data
    [Tags]  delete
    Create Session  localhost  ${uri}
    ${resp}=  Delete Request  localhost  /api/participants/${partDocId}
    Should Be Equal As Strings  ${resp.status_code}  200
