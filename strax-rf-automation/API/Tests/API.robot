*** Settings ***
Library         REST    http://localhost:8080/
***Variables***

@{json}=    /Users/Mack/Development/strax-qa/strax-rf-automation/API/Utils/Videofeed.json


*** Test Cases ***
GET a user in STRAX
    GET         /api/participants           #Getting Users
    Integer     response status             200      
    Output      response body               # file_path=${CURDIR}/user.json

Get a Event Plan
    POST        /api/eventplans             # Getting Event Plans
    Integer     response status             200     
    Output      response body
