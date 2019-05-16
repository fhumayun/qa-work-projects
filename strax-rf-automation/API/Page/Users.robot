*** Settings ***
Library   String
Library   ExtendedSelenium2Library
*** Variables ***
${usersHeader}=   //*[@id="app"]/div/div/main/div[2]/div/div[2]/div/div[1]/div/div[2]  
${postUser}=      API Tester

***Keywords***

verifyPageLoad
    Wait Until Page Contains  ${usersHeader} 

verifyUserCreated
    Page Should Contain  ${postUser}
  