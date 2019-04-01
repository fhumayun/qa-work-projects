*** Settings ***
Library   String
Library   ExtendedSelenium2Library
*** Variables ***
${EVENTS_PAGE_ICON} =                                                                                   # xpath=/html/body/div[2]/strax-sidebar/div/ul/li[3]/a/md-icon          <-- 1.6         
${USER_ACCOUNT_BUTTON} =                                                                                # xpath=/html/body/div[2]/strax-sidebar/div/ul/li[1]/div                <-- 1.6        
${ACCOUNT_SETTINGS_ICON} =                                                                              # xpath=/html/body/div[2]/strax-sidebar/div/ul/li[7]/div/md-icon        <-- 1.6        
${LOGOUT_BUTTON} =                                                                                      # xpath=/html/body/div[2]/strax-sidebar/div/ul/li[8]/div                <-- 1.6        
${SIDEBAR_OPEN} =              id=sidebar__open                                                         # xpath=(//span)[3]                                                     <-- 1.6 
${SIDEBAR_CLOSE} =             id=sidebar__close                                                        # xpath=//md-icon[@class="ng-binding material-icons"]                   <-- 1.6     
${USERS_DROPDOWN} =            id=menu-users                                                            # xpath=(//md-icon[@class="ng-binding ng-scope material-icons"])[4]     <-- 1.6 
${USERS_BUTTON} =              id=menu-users__users
${CALLSIGN_BUTTON} =           id=menu-users__callsigns
${SUBUNITS_BUTTON} =           id=menu-users__subunits
*** Keywords ***

# General Keywords #
OpenSideBar
    Element Should Be Visible  ${SIDEBAR_OPEN}
    Click Element  ${SIDEBAR_OPEN}


#CloseSideBar
#    Click Element  ${SIDEBAR_CLOSE}   
# 
#
## User Account Settings #
#ClickUsersAccount
#    Focus  xpath=/html/body/div[2]/strax-sidebar/div/ul  #SIDEBAR CONTAINER
#    Click Element  //span[@class="sidebar__item__name sidebar__user-name ng-binding"]
#
#
## Events Page #
#GotoEventsPage
#    Click Element  ${EVENTS_PAGE_ICON} 
#VerifyPageLoad
#    Set Focus To Element  id=tab-item-213
#    Page Should Contain Element  id=tab-item-162


# Users Page #
OpenUserDropDown
    Click Element  ${USERS_DROPDOWN}

ClickUsersIcon
    Click Element  ${USERS_BUTTON}  


## Account Settings #
#VerifyAccountVisible
#    Element Should Be Visible  ${ACCOUNT_SETTINGS_ICON}
#
#ClickAccountSettings
#    Click Element  ${ACCOUNT_SETTINGS_ICON}
#
#
## Logout Button #
#ClickLogOut
#    Click Element  ${LOGOUT_BUTTON} 
#    
#VerifyLogOutVisible
#    Element SHould Be Visible  ${LOGOUT_BUTTON}    








    