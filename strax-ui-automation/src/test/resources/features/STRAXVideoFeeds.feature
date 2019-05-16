Feature: STRAX Video Feeds functionality

  @C73395 @SRM @VideoFeeds @ignore
  Scenario Outline: Verify users have access to create a new feed
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User navigates to Video Feeds menu
    Then User should have access to create a new video feed

    Examples: 
      | username        | password   |
      | z-autobot@ee.io | Password1@ |

  @NewTestCases @SRM @VideoFeeds @C63680 @ignore
  Scenario Outline: Verify user can add a new security fixed camera feed
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User navigates to Video Feeds menu
    And User navigates to Fixed camera tab
    And User clicks on add new button to create a new fixed camera feed
    And Enters valid values for the following fields.
      | CameraName | <cameraname> |
      | Account    | <account>    |
      | URL        | <url>        |
      | ViewAngle  | <viewangle>  |
      | Address    | <address>    |
      | Latitude   | <latitude>   |
      | Longitude  | <longitude>  |
    Then A new fixed camera feed with name "<cameraname>" should get created successfully

    Examples: 
      | username        | password   | cameraname                  | account | url                                                                                                    | viewangle | address                                              | latitude | longitude |
      | z-autobot@ee.io | Password1@ | AutomationTestFixedCamera01 | PBSO    | rtsp://root:Biscayne!10@162.252.125.210:5521/axis-media/media.amp?videocodec=h264&streamprofile=Mobile |       100 | 1004 Broken Sound Pkwy NW, Boca Raton, FL 33487, USA |          |           |

  @NewTestCases @SRM @VideoFeeds @C63679 @ignore
  Scenario Outline: Verify Fixed Cam Simulator Functionality is working
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User navigates to Video Feeds menu
    And User navigates to Fixed camera tab
    And User selects the fixed camera "<cameraName>" from the list
    Then User navigates to video simulation page
    And User should be able to play the fixed camera video

    Examples: 
      | username        | password   | cameraName                  |
      | z-autobot@ee.io | Password1@ | AutomationTestFixedCamera01 |

  
    
  @SRM @VideoFeeds @NewTestCases @C63682 @ignore
  Scenario Outline: Verify user can Update a Security Camera Feed
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User navigates to Video Feeds menu
    And User navigates to Fixed camera tab
    And Selects fixed camera feed "<cameraName>" to edit
    And User Enters valid values for the following fields and click update button:
      | CameraName | <cameraname> |
      | Account    | <account>    |
      | URL        | <url>        |
      | ViewAngle  | <viewangle>  |
      | Address    | <address>    |
      | Latitude   | <latitude>   |
      | Longitude  | <longitude>  |
    Then User should be able to Update Security Camera Feed successfully

    Examples: 
      | username        | password   | cameraName                  | account | url                                                                                                    | viewangle | address                                              | latitude | longitude |
      | z-autobot@ee.io | Password1@ | AutomationTestFixedCamera01 | PBSO    | rtsp://root:Biscayne!10@162.252.125.210:5521/axis-media/media.amp?videocodec=h264&streamprofile=Mobile |       101 | 1004 Broken Sound Pkwy NW, Boca Raton, FL 33487, USA |          |           |
  @C86148 @SAC
Scenario Outline: Verify user can Update a Security Camera Feed
Given The STRAX Application login page is open
When User Enters Valid "<username>" and "<password>"
And User navigates to Video Feeds menu
And User select the video feed "<feedName>" to edit
And enter details for following fields to update the feed
      | Name       | <name>       |
      | Account    | <account>    |
      | CameraType | <cameraType> |
      | WowzaPort  | <wowzaPort>  |
      | KlvPort    | <klvPort>    |
      | FrameRate  | <frameRate> |
      | FeedVideo  | <feedVideo> |
Then video feed should get updated successfully "<name>"
  Examples: 
  | username        | password   | name                  | account |cameraType|wowzaPort|klvPort|frameRate|feedVideo|
  | z-autobot@ee.io | Password1@ | QA-Automation-Feed    | PBSO    |  KLV     |1450		|1440   | 25	  |Indago|
  @SAC @C86129
  Scenario Outline: Verify user can delete the security camera feed successfully
    Given The STRAX Application login page is open
    When User Enters Valid "<username>" and "<password>"
    And User navigates to Video Feeds menu
    And User select the video feed "<feedName>" to delete
    Then User should be able to delete the video feed "<feedName>" successfully

    Examples: 
      | username        | password   | feedName           |
      | z-autobot@ee.io | Password1@ | QA-Automation-Feed |
