*** Settings ***
Library   String
Library   ExtendedSelenium2Library
Library   SeleniumLibrary
*** Variables ***
${EVENTS_PAGE_ICON} =            xpath=/html/body/div[2]/strax-sidebar/div/ul/li[3]/a/md-icon                 #OLD xpath=/html/body/div[2]/strax-sidebar/div/ul/li[4]/a/md-icon
${USER_ACCOUNT_BUTTON} =         xpath=/html/body/div[2]/strax-sidebar/div/ul/li[1]/div                       #OLD xpath=/html/body/div[2]/strax-sidebar/div/ul/li[1]/md-menu/div/div/md-icon      
${ACCOUNT_SETTINGS_ICON} =       xpath=/html/body/div[2]/strax-sidebar/div/ul/li[7]/div/md-icon               #OLD  css=body > div:nth-child(2) > strax-sidebar > div > ul > li:nth-child(11) > div    
${LOGOUT_BUTTON} =               xpath=/html/body/div[2]/strax-sidebar/div/ul/li[8]/div                       #OLD  xpath=/html/body/div[2]/strax-sidebar/div/ul/li[12]/div   
${SIDEBAR_BUTTON} =              xpath=(//span)[3]
${SIDEBAR_CLOSE} =               xpath=//md-icon[@class="ng-binding material-icons"]
${USERS_BUTTON} =                xpath=(//md-icon[@class="ng-binding ng-scope material-icons"])[4]

*** Keywords ***
######################
###General Keywords###
######################
OpenSideBar
    Element Should Be Visible  ${SIDEBAR_BUTTON}
    Click Element  ${SIDEBAR_BUTTON}
#    Element Should Be Visible  xpath=//md-icon[@class="ng-binding material-icons"]
#    Click Element    xpath=//md-icon[@class="ng-binding material-icons"]

CloseSideBar
    Click Element  ${SIDEBAR_CLOSE}   
 
############################
###User Account Settings####
############################
ClickUsersAccount
    Focus  xpath=/html/body/div[2]/strax-sidebar/div/ul  #SIDEBAR CONTAINER
#    Click Button  ${USER_ACCOUNT_BUTTON}
    Click Element  //span[@class="sidebar__item__name sidebar__user-name ng-binding"]
################
###Dashboard####
################

##################
###Events Page####
##################
GotoEventsPage
    Click Element  ${EVENTS_PAGE_ICON} 
VerifyPageLoad
    Set Focus To Element  id=tab-item-213
    Page Should Contain Element  id=tab-item-162
#################
###IER Alerts####
#################

#################
###Users Page####
#################
ClickUsersIcon
    Click Element  ${USERS_BUTTON}  

############
###UAS'S####
############

#################
###Map Layers####
#################

##################
###Video Feeds####
##################

################
###Pipelines####
################

#######################
###Account Settings####
#######################
VerifyAccountVisible
    Element Should Be Visible  ${ACCOUNT_SETTINGS_ICON}

ClickAccountSettings
    Click Element  ${ACCOUNT_SETTINGS_ICON}
####################
###Logout Button####
####################
ClickLogOut
    Click Element  ${LOGOUT_BUTTON} 
    
VerifyLogOutVisible
    Element SHould Be Visible  ${LOGOUT_BUTTON}    








    