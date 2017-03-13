/**
 *
 */
'use strict';

// Setup
var host = process.env.MONGOHOST;
var port = process.env.MONOGPORT;

if (!host || host == 'localhost') host = '127.0.0.1';
if (!port) port = '27017';

// Accounts
var accountsdata =
    [
        {
            "_id": "000000000000000000000001",
            "accountname": "Central Station",
            "account_no": "ACC1",
            "phone": "(561) 222 2222",
            "website": "http://www.centralstation.com",
            "email1": "admin@cc.com",
            "bill_street": "1000 Broken Sound Parkway",
            "ship_street": "1000 Broken Sound Parkway",
            "bill_city": "Boca Raton",
            "ship_city": "Boca Raton",
            "bill_state": "Florida",
            "ship_state": "Florida",
            "bill_code": "33487",
            "ship_code": "33487",
            "bill_pobox": "",
            "ship_pobox": "",
            "description": "Central Station",
            "status": "ACTIVE",
            "__v": 0.0000000000000000
        },
        {
            "_id": "000000000000000000000002",
            "accountname": "PBSO",
            "account_no": "1000",
            "phone": "(561) 688-3000",
            "website": "www.pbso.org",
            "email1": "admin@pbso.org",
            "bill_street": "3228 Gun Club Road",
            "ship_street": "3228 Gun Club Road",
            "bill_city": "West Palm Beach",
            "ship_city": "West Palm Beach",
            "bill_state": "Florida",
            "ship_state": "Florida",
            "bill_code": "33406",
            "ship_code": "33406",
            "bill_pobox": "",
            "ship_pobox": "",
            "description": "Palm Bach Sheriff Office",
            "status": "ACTIVE",
            "__v": 0.0000000000000000
        }
    ];


// Participants
var participantsdata =
    [
        {
            "_id": "000000000000000000000001",
            "loginId": "central@cc.com",
            "firstName": "Central",
            "lastName": "Station",
            "title": "Sheriff",
            "accessLevel": "3",
            "accountDocId": "000000000000000000000001",
            "password": "$2a$13$P7tKirx7bjlCFuYUeLcvO.8JAuJPT/KfcQpXnqPlYzdWHOe/8H.H6",
            "fidgets": [],
            "status": true,
            "createdOn": "2016-05-10T17:31:04.346Z",
            "isLocShared": true,
            "isOnline": false,
            "secondaryAddress": {
                "verified": false
            },
            "contactAddress": {
                "verified": false
            },
            "dutyStatus": true,
            "vertical": "safetrax",
            "roles": ["PRIMARY"],
            "mobilePhone": "5619457595",
            "carrier": "Sprint",
            "phoneType": "iPhone",
            "phoneId": "b6e20f21 c01ec0fa f17fc437 a6a30847 a50abd1e c2a4cbb7 9a28fb87 b938732a",
            "color": "#FF0000",
            "__v": 0
        },
        {
            "_id": "000000000000000000000002",
            "loginId": "sheriff@pbso.org",
            "firstName": "John",
            "lastName": "Sheriff",
            "title": "Sheriff",
            "accessLevel": "3",
            "accountDocId": "000000000000000000000002",
            "password": "$2a$13$P7tKirx7bjlCFuYUeLcvO.8JAuJPT/KfcQpXnqPlYzdWHOe/8H.H6",
            "fidgets": [],
            "status": true,
            "createdOn": "2016-05-10T17:31:04.346Z",
            "isLocShared": true,
            "isOnline": false,
            "secondaryAddress": {
                "verified": false
            },
            "contactAddress": {
                "verified": false
            },
            "dutyStatus": true,
            "vertical": "eagleeye",
            "roles": [],
            "__v": 0
        }
    ];


// Fidgets
var fidgetsdata =
    [
        {
            "_id" : "5741f57f56d61f0d0074925e",
            "profile" : "5741f57f56d61f0d0074925e",
            "type" : "eagleeye",
            "feedId" : "5748ad74870c41330225038a",
            "accountDocId" : "000000000000000000000002",
            "gears" : [],
            "zones" : [],
            "vehicleInfo" : [],
            "photo" : [],
            "__v" : 0
        },
        {
            "_id" : "5741f57f56d61f0d0074925f",
            "profile" : "5741f57f56d61f0d0074925f",
            "type" : "eagleeye",
            "feedId" : "5748ad74870c41330225038b",
            "accountDocId" : "000000000000000000000002",
            "gears" : [],
            "zones" : [],
            "vehicleInfo" : [],
            "photo" : [],
            "__v" : 0
        },
        {
            "_id" : "5741f57f56d61f0d007492af",
            "profile" : "5741f57f56d61f0d007492af",
            "type" : "eagleeye",
            "feedId" : "5748ad74870c41330225038c",
            "accountDocId" : "000000000000000000000002",
            "gears" : [],
            "zones" : [],
            "vehicleInfo" : [],
            "photo" : [],
            "__v" : 0
        }
    ];


// Clusters
var clustersdata =
    [
        {
            "_id" : "57474cee56d61f0d00749df4",
            "referenceId" : "123456789012345",
            "accountDocId" : "000000000000000000000001",
            "type" : "SWAT",
            "customer" : "Prison Facility",
            "description" : "test",
            "startTime" : "2016-05-26T19:22:22.350Z",
            "status" : false,
            "address" : {
                "latitude" : 1,
                "longitude" : 1,
                "verified" : false
            },
            "zones" : [],
            "endTime" : "2016-05-26T21:07:49.015Z",
            "participants" : [
                {
                    "participantDocId" : "000000000000000000000001",
                    "_id" : "57474cee56d61f0d00749df6"
                },
                {
                    "participantDocId" : "000000000000000000000002",
                    "_id" : "57474cee56d61f0d00749df5"
                }
            ],
            "fidgets" : [
                "5741f57f56d61f0d0074925e"
            ],
            "__v" : 0,
            "recordedFeedId" : "5747658f5945821200195707"
        },
        {
            "_id" : "574868ec56d61f0d00749df8",
            "referenceId" : "689060",
            "accountDocId" : "000000000000000000000002",
            "type" : "SWAT",
            "customer" : "Coast Guard",
            "description" : "Test",
            "startTime" : "2016-05-27T15:34:04.903Z",
            "status" : false,
            "address" : {
                "latitude" : 80.22,
                "longitude" : -25,
                "verified" : false
            },
            "zones" : [],
            "endTime" : "2016-05-27T21:08:31.875Z",
            "participants" : [
                {
                    "participantDocId" : "000000000000000000000001",
                    "_id" : "574868ec56d61f0d00749dfa"
                },
                {
                    "participantDocId" : "000000000000000000000002",
                    "_id" : "574868ec56d61f0d00749df9"
                }
            ],
            "fidgets" : [
                "5741f57f56d61f0d0074925f"
            ],
            "__v" : 0,
            "recordedFeedId" : "5748b73a594582120019570a"
        }
    ];


// Fidget profiles
var fidgetProfilesdata =
    [
        {
            "_id" : "5741f57f56d61f0d0074925e",
            "type" : "eagleeye",
            "name" : "Fast Quad",
            "model" : "Indago",
            "uniqueId" : "111222",
            "uasType" : "Multi-copter",
            "uasStatus" : "In Use",
            "make" : "Lockheed Martin",
            "__v" : 0
        },
        {
            "_id" : "5741f57f56d61f0d0074925f",
            "type" : "eagleeye",
            "name" : "Merlin UAS 1",
            "model" : "Merlin",
            "uniqueId" : "111223",
            "uasType" : "Prop",
            "uasStatus" : "In Use",
            "make" : "Eagle Eye",
            "__v" : 0
        }
    ];


// Fidgets blobs
var fidgetblobsdata =
    [
        {
            "_id" : "56210e72db15410a001e9407",
            "fidgetDocId" : "5741f57f56d61f0d0074925e",
            "stream" : {
                "contentType" : "application/octet-stream",
                "data" : { "binary" : "", "type" : "00" }
            },
            "__v" : 0
        },
        {
            "_id" : "56210e72db15410a001e9408",
            "fidgetDocId" : "5741f57f56d61f0d0074925f",
            "stream" : {
                "contentType" : "application/octet-stream",
                "data" : { "binary" : "", "type" : "00" }
            },
            "__v" : 0
        },
        {
            "_id" : "56210e72db15410a001e9409",
            "fidgetDocId" : "5741f57f56d61f0d007492af",
            "stream" : {
                "contentType" : "application/octet-stream",
                "data" : { "binary" : "", "type" : "00" }
            },
            "__v" : 0
        }
    ];


// Gears
var gearsdata =
    [
        {
            "_id" : "000000000000000000000001",
            "serialId" : "$301$0-0-0-03000189d",
            "macId" : "1234567890",
            "gearType" : "TRACKER",
            "doMfg" : "2016-02-28T04:00:00.000Z",
            "vFirmware" : "1.0.0",
            "vHardware" : "1.0.0",
            "status" : "ACTIVE",
            "protocolVersion" : "1.0.0",
            "utcOffset" : 5.0,
            "bdAddr" : "1234",
            "__v" : 0.0
        },
        {
            "_id" : "000000000000000000000002",
            "serialId" : "$301$0-0-0-03000189e",
            "macId" : "1234567891",
            "gearType" : "TRACKER",
            "doMfg" : "2016-02-28T04:00:00.000Z",
            "vFirmware" : "1.0.0",
            "vHardware" : "1.0.0",
            "status" : "ACTIVE",
            "protocolVersion" : "1.0.0",
            "utcOffset" : 5.0,
            "bdAddr" : "1234",
            "__v" : 0.0
        },
        {
            "_id" : "000000000000000000000003",
            "serialId" : "$301$0-0-0-03000189f",
            "macId" : "1234567892",
            "gearType" : "TRACKER",
            "doMfg" : "2016-02-28T04:00:00.000Z",
            "vFirmware" : "1.0.0",
            "vHardware" : "1.0.0",
            "status" : "ACTIVE",
            "protocolVersion" : "1.0.0",
            "utcOffset" : 5.0,
            "bdAddr" : "1234",
            "__v" : 0.0
        },
        {
            "_id" : "000000000000000000000004",
            "serialId" : "$301$0-0-0-03000189g",
            "macId" : "1234567893",
            "gearType" : "TRACKER",
            "doMfg" : "2016-02-28T04:00:00.000Z",
            "vFirmware" : "1.0.0",
            "vHardware" : "1.0.0",
            "status" : "ACTIVE",
            "protocolVersion" : "1.0.0",
            "utcOffset" : 5.0,
            "bdAddr" : "1234",
            "__v" : 0.0
        }
    ];

// Insta zones
var instazonesdata =
    [
        {
            "_id" : "55edb77f8f06061f0186c412",
            "fidgetDocId" : "5741f57f56d61f0d0074925e",
            "radius" : 179.4,
            "startTime" : "2015-09-07T16:12:47.872Z",
            "endTime" : "2015-09-07T17:12:47.872Z",
            "loc" : {
                "coordinates" : [
                    [
                        "26.4088818",
                        "-80.1252830"
                    ],
                    [
                        "26.4083843",
                        "-80.1240817"
                    ],
                    [
                        "26.4071830",
                        "-80.1235842"
                    ],
                    [
                        "26.4059817",
                        "-80.1240817"
                    ],
                    [
                        "26.4054842",
                        "-80.1252830"
                    ],
                    [
                        "26.4059817",
                        "-80.1264843"
                    ],
                    [
                        "26.4071830",
                        "-80.1269818"
                    ],
                    [
                        "26.4083843",
                        "-80.1264843"
                    ],
                    [
                        "26.4088818",
                        "-80.1252830"
                    ]
                ],
                "type" : "Polygon"
            },
            "center" : [
                26.407183,
                -80.125283
            ],
            "__v" : 0
        },
        {
            "_id" : "55edb77f8f06061f0186c412",
            "fidgetDocId" : "5741f57f56d61f0d0074925f",
            "radius" : 179.4,
            "startTime" : "2015-09-07T16:12:47.872Z",
            "endTime" : "2015-09-07T17:12:47.872Z",
            "loc" : {
                "coordinates" : [
                    [
                        "26.4088818",
                        "-80.1252830"
                    ],
                    [
                        "26.4083843",
                        "-80.1240817"
                    ],
                    [
                        "26.4071830",
                        "-80.1235842"
                    ],
                    [
                        "26.4059817",
                        "-80.1240817"
                    ],
                    [
                        "26.4054842",
                        "-80.1252830"
                    ],
                    [
                        "26.4059817",
                        "-80.1264843"
                    ],
                    [
                        "26.4071830",
                        "-80.1269818"
                    ],
                    [
                        "26.4083843",
                        "-80.1264843"
                    ],
                    [
                        "26.4088818",
                        "-80.1252830"
                    ]
                ],
                "type" : "Polygon"
            },
            "center" : [
                26.407183,
                -80.125283
            ],
            "__v" : 0
        },
        {
            "_id" : "55edb77f8f06061f0186c412",
            "fidgetDocId" : "5741f57f56d61f0d007492af",
            "radius" : 179.4,
            "startTime" : "2015-09-07T16:12:47.872Z",
            "endTime" : "2015-09-07T17:12:47.872Z",
            "loc" : {
                "coordinates" : [
                    [
                        "26.4088818",
                        "-80.1252830"
                    ],
                    [
                        "26.4083843",
                        "-80.1240817"
                    ],
                    [
                        "26.4071830",
                        "-80.1235842"
                    ],
                    [
                        "26.4059817",
                        "-80.1240817"
                    ],
                    [
                        "26.4054842",
                        "-80.1252830"
                    ],
                    [
                        "26.4059817",
                        "-80.1264843"
                    ],
                    [
                        "26.4071830",
                        "-80.1269818"
                    ],
                    [
                        "26.4083843",
                        "-80.1264843"
                    ],
                    [
                        "26.4088818",
                        "-80.1252830"
                    ]
                ],
                "type" : "Polygon"
            },
            "center" : [
                26.407183,
                -80.125283
            ],
            "__v" : 0
        }
    ];


// Participant geo
var participantgeodata =
    [
        {
            "_id" : "5745f2e756d61f0d0074978a",
            "participantDocId" : "000000000000000000000001",
            "loc" : {
                "coordinates" : [
                    26.3933587353922,
                    -80.1011338737608
                ],
                "type" : "Point"
            },
            "timeStamp" : "2016-05-25T18:45:59.884Z",
            "__v" : 0
        },
        {
            "_id" : "5745f2e756d61f0d0074978b",
            "participantDocId" : "000000000000000000000002",
            "loc" : {
                "coordinates" : [
                    26.3933587353923,
                    -80.1011338737607
                ],
                "type" : "Point"
            },
            "timeStamp" : "2016-05-25T18:45:59.884Z",
            "__v" : 0
        }
    ];


// Safe bridge
var safebridgedata =
    [
        {
            "_id" : "571ff402077a0c0d00c675fb",
            "fidgetDocId" : "5741f57f56d61f0d0074925e",
            "clusterDocId" : "57474cee56d61f0d00749df4",
            "status" : true,
            "startTime" : "2016-04-26T23:04:34.354Z",
            "geoInfo" : [],
            "eventLog" : [],
            "chatMessage" : [],
            "callLog" : [],
            "push2talk" : {
                "channels" : []
            },
            "channels" : [
                {
                    "_id" : "57113cf42ff90edf52ddee6c",
                    "status" : "true"
                },
                {
                    "_id" : "571ff2fb077a0c0d00c675f3",
                    "status" : "true"
                }
            ],
            "bridges" : [],
            "config" : {
                "name" : "571ff38f077a0c0d00c675f8",
                "type" : "mixing"
            },
            "origin" : "EEI",
            "__v" : 0
        },
        {
            "_id" : "571ff402077a0c0d00c675fb",
            "fidgetDocId" : "5741f57f56d61f0d0074925f",
            "clusterDocId" : "574868ec56d61f0d00749df8",
            "status" : true,
            "startTime" : "2016-04-26T23:04:34.354Z",
            "geoInfo" : [],
            "eventLog" : [],
            "chatMessage" : [],
            "callLog" : [],
            "push2talk" : {
                "channels" : []
            },
            "channels" : [
                {
                    "_id" : "57113cf42ff90edf52ddee6c",
                    "status" : "true"
                },
                {
                    "_id" : "571ff2fb077a0c0d00c675f3",
                    "status" : "true"
                }
            ],
            "bridges" : [],
            "config" : {
                "name" : "571ff38f077a0c0d00c675f8",
                "type" : "mixing"
            },
            "origin" : "EEI",
            "__v" : 0
        }
    ];


// Schedules
var schedulesdata =
    [
        {
            "_id" : "000000000000000000000002",
            "fidgetDocId" : "5741f57f56d61f0d0074925e",
            "zoneId" : 2.0,
            "description" : "Test schedule E",
            "endDate" : "2016-01-01T05:00:00.000Z",
            "startTime" : "0000",
            "endTime" : "2359",
            "exceptionDates" : [],
            "startDate" : "2015-01-01T05:00:00.000Z",
            "repeatDays" : [],
            "__v" : 0.0
        },
        {
            "_id" : "000000000000000000000002",
            "fidgetDocId" : "5741f57f56d61f0d0074925f",
            "zoneId" : 2.0,
            "description" : "Test schedule F",
            "endDate" : "2016-01-01T05:00:00.000Z",
            "startTime" : "0000",
            "endTime" : "2359",
            "exceptionDates" : [],
            "startDate" : "2015-01-01T05:00:00.000Z",
            "repeatDays" : [],
            "__v" : 0.0
        }
    ];


// Static content
var staticcontentdata =
    [
        {
            "_id" : "56a18db2e118370b00d54d8c",
            "isConsentRequired" : false,
            "data" : "",
            "startDate" : "2016-01-22T02:02:26.297Z",
            "endDate" : "2016-01-22T02:02:26.297Z",
            "domainName" : "SAFETRAX",
            "topic" : "ABOUT",
            "consentLog" : [],
            "__v" : 0
        },
        {
            "_id" : "56a18ddbe118370b00d54d8d",
            "isConsentRequired" : false,
            "data" : "GROUPCARE TECHNOLOGIES may use third-party ad serving companies to place advertisements about our products and services on other websites.  These companies may use cookies and other technology such as Web beacons or tagging to measure the effectiveness of our ads.  To measure advertising effectiveness and offer selective ad content, the ad serving companies may use anonymous information about your visits to our and other websites.  However, the ad serving companies use an anonymous number to identify you, NOT your name, address, phone number, e-mail address, or anything that personally identifies you.  The use of such cookies is subject to the ad serving company's privacy policy, not the Policy of GROUPCARE TECHNOLOGIES.  If you would like more information about these companies we use, their privacy practices, or to learn your choices about not having this non-personal information used to serve ads to you, please notify us. We maintain a variety of physical, electronic, and procedural safeguards to guard your personal information.  For example, we use accepted tools and techniques to protect against unauthorized access to our systems.  Also, we grant access to personal information about you to employees and contractors who need to know that information to provide products or services to you.  In addition, we work to protect the security of your personal information when you are ordering new service via the GROUPCARE TECHNOLOGIES.",
            "startDate" : "2016-01-22T02:03:07.542Z",
            "endDate" : "2099-12-31T00:00:00.000Z",
            "domainName" : "SAFETRAX",
            "topic" : "ABOUT",
            "consentLog" : [],
            "__v" : 0
        },
        {
            "_id" : "56a18ea2e118370b00d54d8e",
            "isConsentRequired" : false,
            "data" : "GROUPCARE TECHNOLOGIES may use third-party ad serving companies to place advertisements about our products and services on other websites.  These companies may use cookies and other technology such as Web beacons or tagging to measure the effectiveness of our ads.  To measure advertising effectiveness and offer selective ad content, the ad serving companies may use anonymous information about your visits to our and other websites.  However, the ad serving companies use an anonymous number to identify you, NOT your name, address, phone number, e-mail address, or anything that personally identifies you.  The use of such cookies is subject to the ad serving company's privacy policy, not the Policy of GROUPCARE TECHNOLOGIES.  If you would like more information about these companies we use, their privacy practices, or to learn your choices about not having this non-personal information used to serve ads to you, please notify us. We maintain a variety of physical, electronic, and procedural safeguards to guard your personal information.  For example, we use accepted tools and techniques to protect against unauthorized access to our systems.  Also, we grant access to personal information about you to employees and contractors who need to know that information to provide products or services to you.  In addition, we work to protect the security of your personal information when you are ordering new service via the GROUPCARE TECHNOLOGIES.",
            "startDate" : "2016-01-22T02:06:26.393Z",
            "endDate" : "2016-01-24T15:59:52.771Z",
            "domainName" : "SAFETRAX",
            "topic" : "HELP",
            "consentLog" : [],
            "__v" : 0
        },
        {
            "_id" : "56a8ee402343951d0080b2ac",
            "isConsentRequired" : true,
            "data" : "New data",
            "domainName" : "SAFETRAX",
            "topic" : "TERMS",
            "startDate" : "2016-01-27T16:20:16.327Z",
            "endDate" : "2016-02-02T11:13:43.434Z",
            "consentLog" : [
                {
                    "loginId" : "dev4@dev.com",
                    "consent" : "ACCEPTED",
                    "timeStamp" : "2016-01-28T05:36:28.383Z",
                    "_id" : "56a9a8dc2343951d0080b2db"
                },
                {
                    "loginId" : "dev1@dev.com",
                    "consent" : "ACCEPTED",
                    "timeStamp" : "2016-01-28T05:36:30.885Z",
                    "_id" : "56a9a8de2343951d0080b2dc"
                },
                {
                    "loginId" : "dev6@dev.com",
                    "consent" : "ACCEPTED",
                    "timeStamp" : "2016-01-28T07:47:17.349Z",
                    "_id" : "56a9c7852343951d0080b302"
                },
                {
                    "loginId" : "dev8@dev.com",
                    "consent" : "ACCEPTED",
                    "timeStamp" : "2016-01-28T13:23:47.928Z",
                    "_id" : "56aa16632343951d0080b39e"
                },
                {
                    "loginId" : "dev5@dev.com",
                    "consent" : "ACCEPTED",
                    "timeStamp" : "2016-01-28T13:24:40.140Z",
                    "_id" : "56aa16982343951d0080b39f"
                },
                {
                    "loginId" : "dev2@dev.com",
                    "consent" : "ACCEPTED",
                    "timeStamp" : "2016-01-29T05:56:17.805Z",
                    "_id" : "56aaff012343951d0080b443"
                }
            ],
            "__v" : 6
        },
        {
            "_id" : "56a18225e118370b00d54d86",
            "isConsentRequired" : true,
            "data" : "<div class=\"item-page\">\n<div class=\"icons pull-right\"></div>\n<div class=\"page-header\"><h2>Terms of Use</h2></div><h3 style=\"text-align: center;\">GROUPCARE TECHNOLOGIES WEBSITE TERMS OF USE</h3>\n<h4 style=\"text-align: center;\">Effective date: March 1, 2014</h4>\n<p>GROUPCARE TECHNOLOGIES, together with its subsidiaries and affiliates (collectively, \"GROUPCARE TECHNOLGIES\") provide you access to GROUPCARE TECHNOLOGIES websites (\"the Sites\") containing information and data available via your computer and/or a wireless device subject to the terms and conditions set forth below, including all documents, policies and guidelines incorporated therein (the \"Agreement\"). PLEASE READ THIS AGREEMENT CAREFULLY.&nbsp; By accessing, browsing, or using any of our Sites, you agree to be bound by the terms and conditions of the Agreement, including all documents, policies and guidelines incorporated by reference.&nbsp; This Agreement does not alter in any way the terms or conditions of any other written or online agreement you may have or will have with GROUPCARE TECHNOLOGIES.&nbsp;</p>\n<p>GROUPCARE TECHNOLOGIES reserves the right to change or modify, at any time without notice, any of the terms and conditions contained in this Agreement or any policy or guideline referenced herein at any time and in its sole discretion.&nbsp; If the Agreement is changed, we will post the new terms on the Sites and change the date accordingly.&nbsp; Any changes or modifications will be effective upon posting of the new Agreement on the Sites as revised, and your access to and use of the Sites following the posting of such changes or modifications will constitute your acceptance of the Agreement as revised.&nbsp; Therefore, we ask that you periodically check our website for the most up to date information.<br>Copyright and Authorized Access</p>\n<p>All copyrighted and copyrightable materials (“Materials”) on these Sites, including, without limitation, the design, text, graphics, pictures, sound, video files and other files, and the selection and arrangement of Materials, are the property of GROUPCARE TECHNOLOGIES. The foregoing license grant does NOT include the right for you to:</p>\n<ol>\n<li>publish, publicly perform or display, or distribute to any third party any Materials, including reproduction on any computer network or broadcast or publications media;</li>\n<li>market, sell, or make commercial use of the Sites or any Materials;</li>\n<li>systematically collect and use any data or content, including the use of any data mining, robots, or similar data gathering and extraction methods;</li>\n<li>make derivative uses of the Sites or the Materials; or</li>\n<li>use, frame, or utilize framing techniques to enclose any portion of the Sites (including the images found at the Sites or any text or the layout/design of any page or form contained on a page).</li>\n</ol>\n<p>Except for the limited license granted to you, you are not conveyed any other right or license by implication, estoppels, or otherwise in or under any patent, trademark, copyright, or proprietary right of GROUPCARE TECHNOLOGIES or any third party.&nbsp; Any unauthorized use of the Sites will terminate the permission or license granted by this Agreement and may violate applicable law including copyright laws, trademark laws (including trade dress), and communications regulations and statutes.</p>\n<p>If you use any part of the Sites that requires a username or password, then you are responsible for maintaining the confidentiality of that username and password and for restricting access to your computer, and you agree to accept responsibility for all activities that occur under your account or password.&nbsp; In the event the confidentiality of your account or password is compromised in any manner, you should notify GROUPCARE TECHNOLOGIES immediately.&nbsp; GROUPCARE TECHNOLOGIES reserves the right to take any and all action, as it deems necessary or reasonable, to ensure the security of the Sites and your account, including without limitation terminating your account, changing your password, or requesting additional information to authorize transactions on your account.&nbsp; Notwithstanding the above, GROUPCARE TECHNOLOGIES may rely on the authority of anyone accessing your account or using your password and in no event and under no circumstances shall GROUPCARE TECHNOLOGIES be held liable to you for any liabilities or damages resulting from or arising out of (i) any action or inaction of GROUPCARE TECHNOLOGIES under this provision, (ii) any compromise of the confidentiality of your account or password and (iii) any unauthorized access to your account or use of your password. Any unauthorized use of the Sites will terminate the permission or license granted herein and may violate applicable law, including copyright laws, trademark laws (including trade dress), and communications regulations and statutes.&nbsp; All violators will be prosecuted to the fullest extent of the law.</p>\n<h3>Trademarks and Service Marks</h3>\n<p>The trademarks and service marks displayed on the Sites including GroupCare Technologies™ and SafeTrax™ are registered and unregistered trademarks of GROUPCARE TECHNOLOGIES and may not be copied, imitated or used, in whole or in part, without prior written permission of GROUPCARE TECHNOLOGIES or, if applicable, its licensor.&nbsp; In addition, GROUPCARE TECHNOLOGIES custom graphics, button icons, scripts, and page headers are covered by trademark, trade dress, copyright or other proprietary right law, and may not be copied, imitated, or used, in whole or in part, without prior written permission.&nbsp; GROUPCARE TECHNOLGIGES trademarks, service marks, and trade dress may not be used in any manner that is likely to cause confusion among customers or in any manner that disparages or discredits GROUPCARE TECHNOLOGIES.&nbsp; \"GROUPCARE TECHNOLOGIES\" the \"Graphic Icon\" design and all other GROUPCARE TECHNOLOGIES marks and logos, and the GROUPCARE TECHNOLOGIES products and services described on the Sites, are either trademarks, service marks, or registered trademarks of GROUPCARE TECHNOLOGIES or other GROUPCARE TECHNOLGIES affiliates, and may not be copied, imitated or used, in whole or in part, without the prior written permission.&nbsp; All other trademarks, service marks, registered trademarks, product and service names, and company names or logos that appear on the Sites are the property of their respective owners, who may or may not be affiliated with, connected to, or sponsored by GROUPCARE TECHNOLOGIES.&nbsp; You may not use any metatags or any other \"hidden text\" utilizing a GROUPCARE TECHNOLOGIES name, trademark, or product name without GROUPCARE TECHNOLOGIES express written consent.</p>\n<h3>Third-Party Products and Services</h3>\n<p>Parties other than GROUPCARE TECHNOLOGIES offer and provide products and services on or through the Sites.&nbsp; Except for GROUPCARE TECHNOLOGIES-branded information, products or services that are specifically identified as being supplied by GROUPCARE TECHNOLOGIES, GROUPCARE TECHNOLOGIES does not operate, control, or endorse any information, products, or services on the Sites, or accessible through the Sites, in any way.&nbsp; Except for GROUPCARE TECHNOLOGIES-identified information and GROUPCARE TECHNOLOGIES -branded products or services, all information, products, and services offered through the Sites or Internet generally are offered by third parties that are not affiliated with GROUPCARE TECHNOLOGIES. GROUPCARE TECHNOLOGIES is not responsible for examining or evaluating, and GROUPCARE TECHNOLOGIES does not warrant the offerings of, any of these businesses or individuals or the content of their websites.&nbsp; GROUPCARE TECHNOLOGIES does not assume any responsibility or liability for the actions, product, and content of all these and any other third parties.&nbsp; You should carefully review their privacy statements and other conditions of use.</p>\n<h3>Product and Service Descriptions and Pricing</h3>\n<p>GROUPCARE TECHNOLGIES and third parties offering information, products, or services on the Sites attempt to be as accurate as possible.&nbsp; However, GROUPCARE TECHNOLOGIES does not warrant that information, product, and service descriptions or other content of the Sites are accurate, complete, reliable, current, or error-free.&nbsp; Despite our efforts, it is possible, due to computer or other error or cause, that a product or service offered on the Sites may be mispriced or contain an inaccuracy in its description.&nbsp; In the event GROUPCARE TECHNOLOGIES determines that a product or service is mispriced or contains an inaccurate description, GROUPCARE TECHNOLOGIES reserves the right to take any action it deems reasonable and necessary, in its sole discretion, to rectify the error, including without limitation canceling your order. You agree to notify GROUPCARE TECHNOLOGIES immediately if you become aware of any pricing or descriptive errors or inconsistencies with any products or services you order through the Sites and comply with any corrective action taken by GROUPCARE TECHNOLOGIES.</p>\n<h3>Linking</h3>\n<p>You may not use a GROUPCARE TECHNOLOGIES or other proprietary graphic or trademark of GROUPCARE TECHNOLOGIES to link to the Sites without the express written permission of GROUPCARE TECHNOLOGIES.</p>\n<p>Third-Party Links.&nbsp; GROUPCARE TECHNOLOGIES makes no claim or representation regarding, and accepts no responsibility for, the quality, content, nature, or reliability of third-party websites or services accessible by hyperlink from the Sites, or third-party websites linking to the Sites.&nbsp; Such linked Web sites are not under the control of GROUPCARE TECHNOLOGIES and GROUPCARE TECHNOLOGIES is not responsible for the content of any such linked Web site or any link contained in a linked Web site, or any review, changes or updates to such Web sites. GROUPCARE TECHNOLGIES is providing these links to you only as a convenience, and the inclusion of any link does not imply affiliation, endorsement, or adoption by GROUPCARE TECHNOLOGIES of the Web site or any information contained therein. When leaving the GROUPCARE TECHNOLOGIES Sites, you should be aware that GROUPCARE TECHNOLOGIES terms and policies no longer govern, and, therefore, you should review the applicable terms and policies, including privacy and data gathering practices, of that Web site.</p>\n<h3>Submissions</h3>\n<p>You agree that any materials, including but not limited to questions, comments, suggestions, ideas, plans, notes, drawings, original or creative materials, or other information, provided by you in the form of e-mail or submissions to GROUPCARE TECHNOLOGIES, or postings to or on the Sites, are non-confidential (on the condition that personal information provided on non-public areas of the Sites is subject to GROUPCARE TECHNOLOGIES Privacy Policy) and you grant GROUPCARE TECHNOLOGIES a nonexclusive, royalty-free, perpetual, irrevocable, and fully sublicensable right to use, reproduce, modify, adapt, publish, translate, create derivative works from, distribute, and display such materials throughout the world in any media now known or hereafter developed with or without acknowledgment to you in GROUPCARE TECHNOLOGIES sole discretion and without compensation to you.&nbsp; You also grant to GROUPCARE TECHNOLOLGIES the right to use your name in connection with the submitted materials and other information, as well as in connection with all advertising, marketing and promotional material related thereto.&nbsp; You agree that you shall have no recourse against GROUPCARE TECHNOLOGIES for any alleged or actual infringement or misappropriation of any proprietary right in your submitted materials and that the submission of any such materials to GROUPCARE TECHNOLGIES, including the posting of materials to any forum or interactive area on the Sites, irrevocably waives any and all \"moral rights\" in such materials, including the rights of paternity and integrity.&nbsp; You represent and warrant that you own or otherwise control all of the rights to the material that you post; that the material is accurate; that use of the material you supply does not violate this Agreement and will not cause injury to any person or entity; and that you will indemnify GROUPCARE TECHNOLOGIES for all claims resulting from material you supply.</p>\n<h3>Export Restrictions</h3>\n<p>Any software and all underlying information and technology downloaded from the Sites (collectively the \"Software\") by you may be subject to U.S. export controls, and may be subject to export or import regulations in other countries.&nbsp; You are responsible for complying with all trade regulations and laws both foreign and domestic. Except as authorized by law, you agree and warrant not to export or re-export the Software to any country, or to any person, entity, or end-user subject to U.S. export controls, including without limitation persons or entities listed on the U.S. Department of Commerce Bureau of Export Administration's Denied Parties List and the U.S. Department of Treasury's Specially Designated Nationals. You further represent and warrant that no U.S. federal agency has suspended, revoked, or denied your export privileges.</p>\n<h3>Disclaimers</h3>\n<p>\"AS IS\" AND \"AS AVAILABLE\" BASIS:<br>THE SITES AND THE MATERIALS CONTAINED THEREIN ARE PROVIDED ON AN \"AS IS\" AND \"AS AVAILABLE\" BASIS WITHOUT WARRANTIES OF ANY KIND, EITHER EXPRESS OR IMPLIED, AS TO THE OPERATION OF THE SITES OR THE INFORMATION, CONTENT, MATERIALS, PRODUCTS OR SERVICES INCLUDED ON THE SITES. YOU EXPRESSLY AGREE THAT USE OF THE SITES, INCLUDING ALL CONTENT, DATA OR SOFTWARE DISTRIBUTED BY, DOWNLOADED OR ACCESSED FROM OR THROUGH THE SITES, IS AT YOUR SOLE RISK AND RESPONSIBILITY.</p>\n<h3>WARRANTY DISCLAIMER:</h3>\n<p>GROUPCARE TECHNOLOGIES DISCLAIMS ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING, BUT NOT LIMITED TO, IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, TITLE, AND NON-INFRINGEMENT AS TO THE INFORMATION, MATERIALS, CONTENT, SERVICES AND PRODUCTS ON THE SITES. GROUPCARE TECHNOLOGIES DOES NOT REPRESENT OR WARRANT THAT MATERIALS IN THE SITES ARE ACCURATE, COMPLETE, RELIABLE, CURRENT, ERROR-FREE, SUBJECT TO CORRECTION OR THAT ANY SUCH MATERIALS AVAILABLE FOR DOWNLOAD FROM THE SITES ARE FREE OF INFECTION OR VIRUSES, WORMS, TROJAN HORSES, OR OTHER CODE THAT MANIFESTS CONTAMINATING OR DESTRUCTIVE PROPERTIES.&nbsp; GROUPCARE TECHNOLOGIES IS NOT RESPONSIBLE FOR TYPOGRAPHICAL ERRORS OR OMISSIONS RELATING TO PRICING, TEXT, OR PHOTOGRAPHY. WHILE GROUPCARE TECHNOLOGIES ATTEMPTS TO ENSURE YOUR ACCESS AND USE OF THE SITES IS SAFE, GROUPCARE TECHNOLOGIES CANNOT AND DOES NOT REPRESENT OR WARRANT THAT THE SITES OR ITS SERVER(S) WILL BE ERROR-FREE, UNINTERRUPTED, FREE FROM UNAUTHORIZED ACCESS (INCLUDING THIRD-PARTY HACKERS OR DENIAL OF SERVICE ATTACKS) OR OTHERWISE MEET CUSTOMER'S REQUIREMENTS.<br><br>GROUPCARE TECHNOLOGIES reserves the right to change any and all content contained on the Sites at any time without notice.&nbsp; Reference to any products, services, processes, or other information, by trade name, trademark, manufacturer, supplier, or otherwise does not constitute or imply endorsement, sponsorship or recommendation thereof by GROUPCARE TECHNOLOGIES.</p>\n<h3>YOUR RESPONSIBILITY AND RISK:</h3>\n<p>It is solely your responsibility to evaluate the accuracy, completeness, and usefulness of all opinions, advice, services, merchandise, and other information provided on or through the Sites or on the Internet generally. You access such materials at your own risk.&nbsp; GROUPCARE TECHNOLOGIES has no control over and accepts no responsibility whatsoever for such materials.</p>\n<h3>Enforcement</h3>\n<p>GROUPCARE TECHNOLOGIES reserves the right but does not assume the obligation to strictly enforce this Agreement, including without limitation by issuing warnings, suspension, or termination of service, and/or removal, screening, or editing of posted message, data, information, text or other material (\"Content\"), self help and active investigation, litigation and prosecution in any court or appropriate venue. GROUPCARE TECHNOLOGIES may access, use, and disclose transaction information and Content to comply with the law (e.g., a lawful government request); enforce or apply our customer agreements; to initiate, render, bill, and collect for services; to protect our rights or property, or to protect users of those services and other carriers from fraudulent, abusive, or unlawful use of, or subscription to, such services. INDIRECT OR ATTEMPTED VIOLATIONS OF THIS AGREEMENT OR ANY RELATED POLICY, AND ACTUAL OR ATTEMPTED VIOLATIONS BY A THIRD PARTY ON BEHALF OF A GROUPCARE TECHNOLOGIES CUSTOMER, SHALL BE CONSIDERED VIOLATIONS OF THIS AGREEMENT BY SUCH CUSTOMER.</p>\n<h3>Limitation of Liability</h3>\n<p>IN NO EVENT SHALL GROUPCARE TECHNOLOGIES BE LIABLE FOR ANY DIRECT, SPECIAL, INDIRECT OR CONSEQUENTIAL DAMAGES, OR ANY OTHER DAMAGES OF ANY KIND, INCLUDING BUT NOT LIMITED TO, LOSS OF USE, LOSS OF PROFITS, OR LOSS OF DATA, WHETHER IN AN ACTION IN CONTRACT, TORT (INCLUDING BUT NOT LIMITED TO NEGLIGENCE), OR OTHERWISE, ARISING OUT OF OR IN ANY WAY CONNECTED WITH (I) THE USE OR INABILITY TO USE THE SITES OR THE CONTENT, MATERIALS, INFORMATION OR TRANSACTIONS PROVIDED ON OR THROUGH THE SITES, OR (II) ANY CLAIM ATTRIBUTABLE TO ERRORS, OMISSIONS, OR OTHER INACCURACIES IN THE SITES OR THE CONTENT, MATERIALS, INFORMATION, PRODUCTS, OR SERVICES ON OR AVAILABLE THROUGH THE SITES, EVEN IF GROUPCARE TECHNOLOGIES OR ITS AUTHORIZED REPRESENTATIVES HAVE BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.</p>\n<p>Certain state laws do not allow limitations on implied warranties, or the exclusion or limitation of certain damages.&nbsp; If these laws apply, some or all of the above disclaimers, exclusions, or limitations, may not apply to you, and you may have additional rights to those contained herein. In such states, GROUPCARE TECHNOLOGIES liability is limited to the greatest extent permitted by law.</p>\n<h3>Indemnification</h3>\n<p>You agree to indemnify, defend, and hold harmless GROUPCARE TECHNOLOGIES, its officers, directors, employees, agents, licensors, suppliers, and any third-party information providers to the Sites from and against all losses, expenses, damages, and costs, including reasonable attorneys' fees, resulting from any violation of this Agreement by you, or your violation of any rights of a third party.</p>\n<h3>Third-Party Rights</h3>\n<p>Notwithstanding any of these terms and conditions, this Agreement may be terminated by either party without notice at any time for any reason; provided that you may no longer access or use the Sites after this Agreement has been terminated.&nbsp; The provisions of the paragraphs entitled Copyright, Trademarks and Service Marks, Third-Party Content, Submissions, Export Restrictions, Disclaimers, Limitation of Liability, Indemnification, Third-Party Rights, Applicable Law, Venue and Limitation of Actions, Termination, Severability, Enforceability and Admissibility, and Miscellaneous shall survive any termination of this Agreement.</p>\n<h3>Severability</h3>\n<p>If any provision of this Agreement shall be deemed unlawful, void, or for any reason unenforceable, then that provision shall be deemed severable from these terms and conditions and shall not affect the validity and enforceability of any remaining provisions.</p>\n<h3>Enforceability and Admissibility</h3>\n<p>This electronic document and any other electronic documents, policies, and guidelines incorporated herein will be:&nbsp; (a) deemed for all purposes to be a \"writing\" or \"in writing,\" and to comply with all statutory, contractual, and other legal requirements for a writing; (b) legally enforceable as a signed writing as against the parties subject to the electronic documents; and (c) deemed an \"original\" when printed from electronic records established and maintained in the ordinary course of business.&nbsp; Electronic documents introduced as evidence in any judicial, arbitration, mediation, or administrative proceeding will, if established and maintained in the ordinary course of business, be admissible to the same extent as business records in written form that are similarly established and maintained.</p>\n<h3>Miscellaneous</h3>\n<p>GROUPCARE TECHNOLOGIES failure to insist upon or enforce strict performance of any provision of this Agreement shall not be construed as a waiver of any provision or right.&nbsp; Neither the course of conduct between the parties nor trade practice shall act to modify any provision of this Agreement.&nbsp; GROUPCARE TECHNOLOGIES may assign its rights and duties under this Agreement to any party at any time without notice to you.</p>\n<p>In order to protect GROUPCARE TECHNOLOGIES and its customers from fraudulent activity or “bulk resale scheme”, we may limit the number of devices and services that may be ordered online by a single individual or entity. GROUPCARE TECHNOLOGIES reserves the right to further limit quantities or to cancel or reject orders in its sole discretion.</p><div class=\"clearfix\"></div></div>",
            "startDate" : "2016-01-22T01:13:09.109Z",
            "endDate" : "2016-01-26T16:20:16.329Z",
            "domainName" : "SAFETRAX",
            "topic" : "TERMS",
            "consentLog" : [
                {
                    "loginId" : "test@gmail.com",
                    "consent" : "ACCEPTED",
                    "timeStamp" : "2016-01-22T01:21:03.855Z",
                    "_id" : "56a183ffe118370b00d54d87"
                },
                {
                    "loginId" : "test1@gmail.com",
                    "consent" : "REJECTED",
                    "timeStamp" : "2016-01-22T01:23:16.694Z",
                    "_id" : "56a18484e118370b00d54d88"
                },
                {
                    "loginId" : "test1@gmail.com",
                    "consent" : "REJECTED",
                    "timeStamp" : "2016-01-22T01:23:48.127Z",
                    "_id" : "56a184a4e118370b00d54d89"
                },
                {
                    "loginId" : "test1@gmail.com",
                    "consent" : "REJECTED",
                    "timeStamp" : "2016-01-22T01:23:48.982Z",
                    "_id" : "56a184a4e118370b00d54d8a"
                },
                {
                    "loginId" : "test1@gmail.com",
                    "consent" : "ACCEPTED",
                    "timeStamp" : "2016-01-22T01:24:06.984Z",
                    "_id" : "56a184b6e118370b00d54d8b"
                },
                {
                    "loginId" : "dev1@dev.com",
                    "consent" : "ACCEPTED",
                    "timeStamp" : "2016-01-22T14:41:15.841Z",
                    "_id" : "56a23f8be118370b00d54fe6"
                },
                {
                    "loginId" : "dev2@dev.com",
                    "consent" : "ACCEPTED",
                    "timeStamp" : "2016-01-22T15:09:52.101Z",
                    "_id" : "56a24640e118370b00d54fe7"
                },
                {
                    "loginId" : "dev3@dev.com",
                    "consent" : "ACCEPTED",
                    "timeStamp" : "2016-01-22T15:22:11.528Z",
                    "_id" : "56a24923e118370b00d54fe8"
                },
                {
                    "loginId" : "qa1@qa.com",
                    "consent" : "ACCEPTED",
                    "timeStamp" : "2016-01-22T15:37:15.400Z",
                    "_id" : "56a24cabe118370b00d54ff5"
                },
                {
                    "loginId" : "dev4@dev.com",
                    "consent" : "ACCEPTED",
                    "timeStamp" : "2016-01-25T12:09:56.550Z",
                    "_id" : "56a61094e118370b00d550ec"
                },
                {
                    "loginId" : "dev6@dev.com",
                    "consent" : "ACCEPTED",
                    "timeStamp" : "2016-01-25T20:02:44.472Z",
                    "_id" : "56a67f64e118370b00d551a3"
                },
                {
                    "loginId" : "test2@gmail.com",
                    "consent" : "ACCEPTED",
                    "timeStamp" : "2016-01-27T16:16:13.698Z",
                    "_id" : "56a8ed4d2343951d0080b2a9"
                }
            ],
            "__v" : 12
        },
        {
            "_id" : "56a64678e118370b00d5514a",
            "isConsentRequired" : false,
            "data" : "<div class=\"item-page\">\n<div class=\"icons pull-right\"></div>\n<div class=\"page-header\"><h2>Terms of Use</h2></div><h3 style=\"text-align: center;\">GROUPCARE TECHNOLOGIES WEBSITE TERMS OF USE</h3>\n<h4 style=\"text-align: center;\">Effective date: March 1, 2014</h4>\n<p>GROUPCARE TECHNOLOGIES, together with its subsidiaries and affiliates (collectively, \"GROUPCARE TECHNOLGIES\") provide you access to GROUPCARE TECHNOLOGIES websites (\"the Sites\") containing information and data available via your computer and/or a wireless device subject to the terms and conditions set forth below, including all documents, policies and guidelines incorporated therein (the \"Agreement\"). PLEASE READ THIS AGREEMENT CAREFULLY.&nbsp; By accessing, browsing, or using any of our Sites, you agree to be bound by the terms and conditions of the Agreement, including all documents, policies and guidelines incorporated by reference.&nbsp; This Agreement does not alter in any way the terms or conditions of any other written or online agreement you may have or will have with GROUPCARE TECHNOLOGIES.&nbsp;</p>\n<p>GROUPCARE TECHNOLOGIES reserves the right to change or modify, at any time without notice, any of the terms and conditions contained in this Agreement or any policy or guideline referenced herein at any time and in its sole discretion.&nbsp; If the Agreement is changed, we will post the new terms on the Sites and change the date accordingly.&nbsp; Any changes or modifications will be effective upon posting of the new Agreement on the Sites as revised, and your access to and use of the Sites following the posting of such changes or modifications will constitute your acceptance of the Agreement as revised.&nbsp; Therefore, we ask that you periodically check our website for the most up to date information.<br>Copyright and Authorized Access</p>\n<p>All copyrighted and copyrightable materials (“Materials”) on these Sites, including, without limitation, the design, text, graphics, pictures, sound, video files and other files, and the selection and arrangement of Materials, are the property of GROUPCARE TECHNOLOGIES. The foregoing license grant does NOT include the right for you to:</p>\n<ol>\n<li>publish, publicly perform or display, or distribute to any third party any Materials, including reproduction on any computer network or broadcast or publications media;</li>\n<li>market, sell, or make commercial use of the Sites or any Materials;</li>\n<li>systematically collect and use any data or content, including the use of any data mining, robots, or similar data gathering and extraction methods;</li>\n<li>make derivative uses of the Sites or the Materials; or</li>\n<li>use, frame, or utilize framing techniques to enclose any portion of the Sites (including the images found at the Sites or any text or the layout/design of any page or form contained on a page).</li>\n</ol>\n<p>Except for the limited license granted to you, you are not conveyed any other right or license by implication, estoppels, or otherwise in or under any patent, trademark, copyright, or proprietary right of GROUPCARE TECHNOLOGIES or any third party.&nbsp; Any unauthorized use of the Sites will terminate the permission or license granted by this Agreement and may violate applicable law including copyright laws, trademark laws (including trade dress), and communications regulations and statutes.</p>\n<p>If you use any part of the Sites that requires a username or password, then you are responsible for maintaining the confidentiality of that username and password and for restricting access to your computer, and you agree to accept responsibility for all activities that occur under your account or password.&nbsp; In the event the confidentiality of your account or password is compromised in any manner, you should notify GROUPCARE TECHNOLOGIES immediately.&nbsp; GROUPCARE TECHNOLOGIES reserves the right to take any and all action, as it deems necessary or reasonable, to ensure the security of the Sites and your account, including without limitation terminating your account, changing your password, or requesting additional information to authorize transactions on your account.&nbsp; Notwithstanding the above, GROUPCARE TECHNOLOGIES may rely on the authority of anyone accessing your account or using your password and in no event and under no circumstances shall GROUPCARE TECHNOLOGIES be held liable to you for any liabilities or damages resulting from or arising out of (i) any action or inaction of GROUPCARE TECHNOLOGIES under this provision, (ii) any compromise of the confidentiality of your account or password and (iii) any unauthorized access to your account or use of your password. Any unauthorized use of the Sites will terminate the permission or license granted herein and may violate applicable law, including copyright laws, trademark laws (including trade dress), and communications regulations and statutes.&nbsp; All violators will be prosecuted to the fullest extent of the law.</p>\n<h3>Trademarks and Service Marks</h3>\n<p>The trademarks and service marks displayed on the Sites including GroupCare Technologies™ and SafeTrax™ are registered and unregistered trademarks of GROUPCARE TECHNOLOGIES and may not be copied, imitated or used, in whole or in part, without prior written permission of GROUPCARE TECHNOLOGIES or, if applicable, its licensor.&nbsp; In addition, GROUPCARE TECHNOLOGIES custom graphics, button icons, scripts, and page headers are covered by trademark, trade dress, copyright or other proprietary right law, and may not be copied, imitated, or used, in whole or in part, without prior written permission.&nbsp; GROUPCARE TECHNOLGIGES trademarks, service marks, and trade dress may not be used in any manner that is likely to cause confusion among customers or in any manner that disparages or discredits GROUPCARE TECHNOLOGIES.&nbsp; \"GROUPCARE TECHNOLOGIES\" the \"Graphic Icon\" design and all other GROUPCARE TECHNOLOGIES marks and logos, and the GROUPCARE TECHNOLOGIES products and services described on the Sites, are either trademarks, service marks, or registered trademarks of GROUPCARE TECHNOLOGIES or other GROUPCARE TECHNOLGIES affiliates, and may not be copied, imitated or used, in whole or in part, without the prior written permission.&nbsp; All other trademarks, service marks, registered trademarks, product and service names, and company names or logos that appear on the Sites are the property of their respective owners, who may or may not be affiliated with, connected to, or sponsored by GROUPCARE TECHNOLOGIES.&nbsp; You may not use any metatags or any other \"hidden text\" utilizing a GROUPCARE TECHNOLOGIES name, trademark, or product name without GROUPCARE TECHNOLOGIES express written consent.</p>\n<h3>Third-Party Products and Services</h3>\n<p>Parties other than GROUPCARE TECHNOLOGIES offer and provide products and services on or through the Sites.&nbsp; Except for GROUPCARE TECHNOLOGIES-branded information, products or services that are specifically identified as being supplied by GROUPCARE TECHNOLOGIES, GROUPCARE TECHNOLOGIES does not operate, control, or endorse any information, products, or services on the Sites, or accessible through the Sites, in any way.&nbsp; Except for GROUPCARE TECHNOLOGIES-identified information and GROUPCARE TECHNOLOGIES -branded products or services, all information, products, and services offered through the Sites or Internet generally are offered by third parties that are not affiliated with GROUPCARE TECHNOLOGIES. GROUPCARE TECHNOLOGIES is not responsible for examining or evaluating, and GROUPCARE TECHNOLOGIES does not warrant the offerings of, any of these businesses or individuals or the content of their websites.&nbsp; GROUPCARE TECHNOLOGIES does not assume any responsibility or liability for the actions, product, and content of all these and any other third parties.&nbsp; You should carefully review their privacy statements and other conditions of use.</p>\n<h3>Product and Service Descriptions and Pricing</h3>\n<p>GROUPCARE TECHNOLGIES and third parties offering information, products, or services on the Sites attempt to be as accurate as possible.&nbsp; However, GROUPCARE TECHNOLOGIES does not warrant that information, product, and service descriptions or other content of the Sites are accurate, complete, reliable, current, or error-free.&nbsp; Despite our efforts, it is possible, due to computer or other error or cause, that a product or service offered on the Sites may be mispriced or contain an inaccuracy in its description.&nbsp; In the event GROUPCARE TECHNOLOGIES determines that a product or service is mispriced or contains an inaccurate description, GROUPCARE TECHNOLOGIES reserves the right to take any action it deems reasonable and necessary, in its sole discretion, to rectify the error, including without limitation canceling your order. You agree to notify GROUPCARE TECHNOLOGIES immediately if you become aware of any pricing or descriptive errors or inconsistencies with any products or services you order through the Sites and comply with any corrective action taken by GROUPCARE TECHNOLOGIES.</p>\n<h3>Linking</h3>\n<p>You may not use a GROUPCARE TECHNOLOGIES or other proprietary graphic or trademark of GROUPCARE TECHNOLOGIES to link to the Sites without the express written permission of GROUPCARE TECHNOLOGIES.</p>\n<p>Third-Party Links.&nbsp; GROUPCARE TECHNOLOGIES makes no claim or representation regarding, and accepts no responsibility for, the quality, content, nature, or reliability of third-party websites or services accessible by hyperlink from the Sites, or third-party websites linking to the Sites.&nbsp; Such linked Web sites are not under the control of GROUPCARE TECHNOLOGIES and GROUPCARE TECHNOLOGIES is not responsible for the content of any such linked Web site or any link contained in a linked Web site, or any review, changes or updates to such Web sites. GROUPCARE TECHNOLGIES is providing these links to you only as a convenience, and the inclusion of any link does not imply affiliation, endorsement, or adoption by GROUPCARE TECHNOLOGIES of the Web site or any information contained therein. When leaving the GROUPCARE TECHNOLOGIES Sites, you should be aware that GROUPCARE TECHNOLOGIES terms and policies no longer govern, and, therefore, you should review the applicable terms and policies, including privacy and data gathering practices, of that Web site.</p>\n<h3>Submissions</h3>\n<p>You agree that any materials, including but not limited to questions, comments, suggestions, ideas, plans, notes, drawings, original or creative materials, or other information, provided by you in the form of e-mail or submissions to GROUPCARE TECHNOLOGIES, or postings to or on the Sites, are non-confidential (on the condition that personal information provided on non-public areas of the Sites is subject to GROUPCARE TECHNOLOGIES Privacy Policy) and you grant GROUPCARE TECHNOLOGIES a nonexclusive, royalty-free, perpetual, irrevocable, and fully sublicensable right to use, reproduce, modify, adapt, publish, translate, create derivative works from, distribute, and display such materials throughout the world in any media now known or hereafter developed with or without acknowledgment to you in GROUPCARE TECHNOLOGIES sole discretion and without compensation to you.&nbsp; You also grant to GROUPCARE TECHNOLOLGIES the right to use your name in connection with the submitted materials and other information, as well as in connection with all advertising, marketing and promotional material related thereto.&nbsp; You agree that you shall have no recourse against GROUPCARE TECHNOLOGIES for any alleged or actual infringement or misappropriation of any proprietary right in your submitted materials and that the submission of any such materials to GROUPCARE TECHNOLGIES, including the posting of materials to any forum or interactive area on the Sites, irrevocably waives any and all \"moral rights\" in such materials, including the rights of paternity and integrity.&nbsp; You represent and warrant that you own or otherwise control all of the rights to the material that you post; that the material is accurate; that use of the material you supply does not violate this Agreement and will not cause injury to any person or entity; and that you will indemnify GROUPCARE TECHNOLOGIES for all claims resulting from material you supply.</p>\n<h3>Export Restrictions</h3>\n<p>Any software and all underlying information and technology downloaded from the Sites (collectively the \"Software\") by you may be subject to U.S. export controls, and may be subject to export or import regulations in other countries.&nbsp; You are responsible for complying with all trade regulations and laws both foreign and domestic. Except as authorized by law, you agree and warrant not to export or re-export the Software to any country, or to any person, entity, or end-user subject to U.S. export controls, including without limitation persons or entities listed on the U.S. Department of Commerce Bureau of Export Administration's Denied Parties List and the U.S. Department of Treasury's Specially Designated Nationals. You further represent and warrant that no U.S. federal agency has suspended, revoked, or denied your export privileges.</p>\n<h3>Disclaimers</h3>\n<p>\"AS IS\" AND \"AS AVAILABLE\" BASIS:<br>THE SITES AND THE MATERIALS CONTAINED THEREIN ARE PROVIDED ON AN \"AS IS\" AND \"AS AVAILABLE\" BASIS WITHOUT WARRANTIES OF ANY KIND, EITHER EXPRESS OR IMPLIED, AS TO THE OPERATION OF THE SITES OR THE INFORMATION, CONTENT, MATERIALS, PRODUCTS OR SERVICES INCLUDED ON THE SITES. YOU EXPRESSLY AGREE THAT USE OF THE SITES, INCLUDING ALL CONTENT, DATA OR SOFTWARE DISTRIBUTED BY, DOWNLOADED OR ACCESSED FROM OR THROUGH THE SITES, IS AT YOUR SOLE RISK AND RESPONSIBILITY.</p>\n<h3>WARRANTY DISCLAIMER:</h3>\n<p>GROUPCARE TECHNOLOGIES DISCLAIMS ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING, BUT NOT LIMITED TO, IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, TITLE, AND NON-INFRINGEMENT AS TO THE INFORMATION, MATERIALS, CONTENT, SERVICES AND PRODUCTS ON THE SITES. GROUPCARE TECHNOLOGIES DOES NOT REPRESENT OR WARRANT THAT MATERIALS IN THE SITES ARE ACCURATE, COMPLETE, RELIABLE, CURRENT, ERROR-FREE, SUBJECT TO CORRECTION OR THAT ANY SUCH MATERIALS AVAILABLE FOR DOWNLOAD FROM THE SITES ARE FREE OF INFECTION OR VIRUSES, WORMS, TROJAN HORSES, OR OTHER CODE THAT MANIFESTS CONTAMINATING OR DESTRUCTIVE PROPERTIES.&nbsp; GROUPCARE TECHNOLOGIES IS NOT RESPONSIBLE FOR TYPOGRAPHICAL ERRORS OR OMISSIONS RELATING TO PRICING, TEXT, OR PHOTOGRAPHY. WHILE GROUPCARE TECHNOLOGIES ATTEMPTS TO ENSURE YOUR ACCESS AND USE OF THE SITES IS SAFE, GROUPCARE TECHNOLOGIES CANNOT AND DOES NOT REPRESENT OR WARRANT THAT THE SITES OR ITS SERVER(S) WILL BE ERROR-FREE, UNINTERRUPTED, FREE FROM UNAUTHORIZED ACCESS (INCLUDING THIRD-PARTY HACKERS OR DENIAL OF SERVICE ATTACKS) OR OTHERWISE MEET CUSTOMER'S REQUIREMENTS.<br><br>GROUPCARE TECHNOLOGIES reserves the right to change any and all content contained on the Sites at any time without notice.&nbsp; Reference to any products, services, processes, or other information, by trade name, trademark, manufacturer, supplier, or otherwise does not constitute or imply endorsement, sponsorship or recommendation thereof by GROUPCARE TECHNOLOGIES.</p>\n<h3>YOUR RESPONSIBILITY AND RISK:</h3>\n<p>It is solely your responsibility to evaluate the accuracy, completeness, and usefulness of all opinions, advice, services, merchandise, and other information provided on or through the Sites or on the Internet generally. You access such materials at your own risk.&nbsp; GROUPCARE TECHNOLOGIES has no control over and accepts no responsibility whatsoever for such materials.</p>\n<h3>Enforcement</h3>\n<p>GROUPCARE TECHNOLOGIES reserves the right but does not assume the obligation to strictly enforce this Agreement, including without limitation by issuing warnings, suspension, or termination of service, and/or removal, screening, or editing of posted message, data, information, text or other material (\"Content\"), self help and active investigation, litigation and prosecution in any court or appropriate venue. GROUPCARE TECHNOLOGIES may access, use, and disclose transaction information and Content to comply with the law (e.g., a lawful government request); enforce or apply our customer agreements; to initiate, render, bill, and collect for services; to protect our rights or property, or to protect users of those services and other carriers from fraudulent, abusive, or unlawful use of, or subscription to, such services. INDIRECT OR ATTEMPTED VIOLATIONS OF THIS AGREEMENT OR ANY RELATED POLICY, AND ACTUAL OR ATTEMPTED VIOLATIONS BY A THIRD PARTY ON BEHALF OF A GROUPCARE TECHNOLOGIES CUSTOMER, SHALL BE CONSIDERED VIOLATIONS OF THIS AGREEMENT BY SUCH CUSTOMER.</p>\n<h3>Limitation of Liability</h3>\n<p>IN NO EVENT SHALL GROUPCARE TECHNOLOGIES BE LIABLE FOR ANY DIRECT, SPECIAL, INDIRECT OR CONSEQUENTIAL DAMAGES, OR ANY OTHER DAMAGES OF ANY KIND, INCLUDING BUT NOT LIMITED TO, LOSS OF USE, LOSS OF PROFITS, OR LOSS OF DATA, WHETHER IN AN ACTION IN CONTRACT, TORT (INCLUDING BUT NOT LIMITED TO NEGLIGENCE), OR OTHERWISE, ARISING OUT OF OR IN ANY WAY CONNECTED WITH (I) THE USE OR INABILITY TO USE THE SITES OR THE CONTENT, MATERIALS, INFORMATION OR TRANSACTIONS PROVIDED ON OR THROUGH THE SITES, OR (II) ANY CLAIM ATTRIBUTABLE TO ERRORS, OMISSIONS, OR OTHER INACCURACIES IN THE SITES OR THE CONTENT, MATERIALS, INFORMATION, PRODUCTS, OR SERVICES ON OR AVAILABLE THROUGH THE SITES, EVEN IF GROUPCARE TECHNOLOGIES OR ITS AUTHORIZED REPRESENTATIVES HAVE BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.</p>\n<p>Certain state laws do not allow limitations on implied warranties, or the exclusion or limitation of certain damages.&nbsp; If these laws apply, some or all of the above disclaimers, exclusions, or limitations, may not apply to you, and you may have additional rights to those contained herein. In such states, GROUPCARE TECHNOLOGIES liability is limited to the greatest extent permitted by law.</p>\n<h3>Indemnification</h3>\n<p>You agree to indemnify, defend, and hold harmless GROUPCARE TECHNOLOGIES, its officers, directors, employees, agents, licensors, suppliers, and any third-party information providers to the Sites from and against all losses, expenses, damages, and costs, including reasonable attorneys' fees, resulting from any violation of this Agreement by you, or your violation of any rights of a third party.</p>\n<h3>Third-Party Rights</h3>\n<p>Notwithstanding any of these terms and conditions, this Agreement may be terminated by either party without notice at any time for any reason; provided that you may no longer access or use the Sites after this Agreement has been terminated.&nbsp; The provisions of the paragraphs entitled Copyright, Trademarks and Service Marks, Third-Party Content, Submissions, Export Restrictions, Disclaimers, Limitation of Liability, Indemnification, Third-Party Rights, Applicable Law, Venue and Limitation of Actions, Termination, Severability, Enforceability and Admissibility, and Miscellaneous shall survive any termination of this Agreement.</p>\n<h3>Severability</h3>\n<p>If any provision of this Agreement shall be deemed unlawful, void, or for any reason unenforceable, then that provision shall be deemed severable from these terms and conditions and shall not affect the validity and enforceability of any remaining provisions.</p>\n<h3>Enforceability and Admissibility</h3>\n<p>This electronic document and any other electronic documents, policies, and guidelines incorporated herein will be:&nbsp; (a) deemed for all purposes to be a \"writing\" or \"in writing,\" and to comply with all statutory, contractual, and other legal requirements for a writing; (b) legally enforceable as a signed writing as against the parties subject to the electronic documents; and (c) deemed an \"original\" when printed from electronic records established and maintained in the ordinary course of business.&nbsp; Electronic documents introduced as evidence in any judicial, arbitration, mediation, or administrative proceeding will, if established and maintained in the ordinary course of business, be admissible to the same extent as business records in written form that are similarly established and maintained.</p>\n<h3>Miscellaneous</h3>\n<p>GROUPCARE TECHNOLOGIES failure to insist upon or enforce strict performance of any provision of this Agreement shall not be construed as a waiver of any provision or right.&nbsp; Neither the course of conduct between the parties nor trade practice shall act to modify any provision of this Agreement.&nbsp; GROUPCARE TECHNOLOGIES may assign its rights and duties under this Agreement to any party at any time without notice to you.</p>\n<p>In order to protect GROUPCARE TECHNOLOGIES and its customers from fraudulent activity or “bulk resale scheme”, we may limit the number of devices and services that may be ordered online by a single individual or entity. GROUPCARE TECHNOLOGIES reserves the right to further limit quantities or to cancel or reject orders in its sole discretion.</p><div class=\"clearfix\"></div></div>",
            "startDate" : "2016-01-25T15:59:52.770Z",
            "endDate" : "2099-12-31T00:00:00.000Z",
            "domainName" : "SAFETRAX",
            "topic" : "HELP",
            "consentLog" : [],
            "__v" : 0
        },
        {
            "_id" : "56b1e0e7889797170034f145",
            "isConsentRequired" : true,
            "data" : "GROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USE……",
            "startDate" : "2016-02-03T11:13:43.433Z",
            "endDate" : "2016-02-03T11:13:43.433Z",
            "domainName" : "SAFETRAX",
            "topic" : "TERMS",
            "consentLog" : [
                {
                    "loginId" : "qa1@qa.com",
                    "consent" : "ACCEPTED",
                    "timeStamp" : "2016-02-03T11:15:10.091Z",
                    "_id" : "56b1e13e889797170034f146"
                }
            ],
            "__v" : 1
        },
        {
            "_id" : "56b1e16a889797170034f147",
            "isConsentRequired" : true,
            "data" : "GROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USE……",
            "startDate" : "2016-02-03T11:15:54.564Z",
            "endDate" : "2016-02-03T11:15:54.564Z",
            "domainName" : "SAFETRAX",
            "topic" : "TERMS",
            "consentLog" : [
                {
                    "loginId" : "qa1@qa.com",
                    "consent" : "ACCEPTED",
                    "timeStamp" : "2016-02-03T11:16:25.723Z",
                    "_id" : "56b1e189889797170034f148"
                }
            ],
            "__v" : 1
        },
        {
            "_id" : "56b1e1ab889797170034f14a",
            "isConsentRequired" : true,
            "data" : "GROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USE……",
            "startDate" : "2016-02-03T11:16:59.596Z",
            "endDate" : "2016-02-03T11:16:59.596Z",
            "domainName" : "SAFETRAX",
            "topic" : "TERMS",
            "consentLog" : [
                {
                    "loginId" : "qa1@qa.com",
                    "consent" : "ACCEPTED",
                    "timeStamp" : "2016-02-03T11:17:34.053Z",
                    "_id" : "56b1e1ce889797170034f14b"
                }
            ],
            "__v" : 1
        },
        {
            "_id" : "56b1e1da889797170034f14c",
            "isConsentRequired" : true,
            "data" : "GROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TRMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USEGROUPCARE TECHNOLOGIES WEBSITE TERMS OF USE……",
            "startDate" : "2016-02-03T11:17:46.315Z",
            "endDate" : "2016-02-03T11:17:46.315Z",
            "domainName" : "SAFETRAX",
            "topic" : "TERMS",
            "consentLog" : [
                {
                    "loginId" : "qa1@qa.com",
                    "consent" : "ACCEPTED",
                    "timeStamp" : "2016-02-03T11:18:03.341Z",
                    "_id" : "56b1e1eb889797170034f14d"
                }
            ],
            "__v" : 1
        },
        {
            "_id" : "56b1e1fc889797170034f14e",
            "isConsentRequired" : true,
            "data" : "<div class=\"item-page\">\n<div class=\"icons pull-right\"></div>\n<div class=\"page-header\"><h2>Terms of Use</h2></div><h3 style=\"text-align: center;\">GROUPCARE TECHNOLOGIES WEBSITE TERMS OF USE</h3>\n<h4 style=\"text-align: center;\">Effective date: March 1, 2014</h4>\n<p>GROUPCARE TECHNOLOGIES, together with its subsidiaries and affiliates (collectively, \"GROUPCARE TECHNOLGIES\") provide you access to GROUPCARE TECHNOLOGIES websites (\"the Sites\") containing information and data available via your computer and/or a wireless device subject to the terms and conditions set forth below, including all documents, policies and guidelines incorporated therein (the \"Agreement\"). PLEASE READ THIS AGREEMENT CAREFULLY.&nbsp; By accessing, browsing, or using any of our Sites, you agree to be bound by the terms and conditions of the Agreement, including all documents, policies and guidelines incorporated by reference.&nbsp; This Agreement does not alter in any way the terms or conditions of any other written or online agreement you may have or will have with GROUPCARE TECHNOLOGIES.&nbsp;</p>\n<p>GROUPCARE TECHNOLOGIES reserves the right to change or modify, at any time without notice, any of the terms and conditions contained in this Agreement or any policy or guideline referenced herein at any time and in its sole discretion.&nbsp; If the Agreement is changed, we will post the new terms on the Sites and change the date accordingly.&nbsp; Any changes or modifications will be effective upon posting of the new Agreement on the Sites as revised, and your access to and use of the Sites following the posting of such changes or modifications will constitute your acceptance of the Agreement as revised.&nbsp; Therefore, we ask that you periodically check our website for the most up to date information.<br>Copyright and Authorized Access</p>\n<p>All copyrighted and copyrightable materials (“Materials”) on these Sites, including, without limitation, the design, text, graphics, pictures, sound, video files and other files, and the selection and arrangement of Materials, are the property of GROUPCARE TECHNOLOGIES. The foregoing license grant does NOT include the right for you to:</p>\n<ol>\n<li>publish, publicly perform or display, or distribute to any third party any Materials, including reproduction on any computer network or broadcast or publications media;</li>\n<li>market, sell, or make commercial use of the Sites or any Materials;</li>\n<li>systematically collect and use any data or content, including the use of any data mining, robots, or similar data gathering and extraction methods;</li>\n<li>make derivative uses of the Sites or the Materials; or</li>\n<li>use, frame, or utilize framing techniques to enclose any portion of the Sites (including the images found at the Sites or any text or the layout/design of any page or form contained on a page).</li>\n</ol>\n<p>Except for the limited license granted to you, you are not conveyed any other right or license by implication, estoppels, or otherwise in or under any patent, trademark, copyright, or proprietary right of GROUPCARE TECHNOLOGIES or any third party.&nbsp; Any unauthorized use of the Sites will terminate the permission or license granted by this Agreement and may violate applicable law including copyright laws, trademark laws (including trade dress), and communications regulations and statutes.</p>\n<p>If you use any part of the Sites that requires a username or password, then you are responsible for maintaining the confidentiality of that username and password and for restricting access to your computer, and you agree to accept responsibility for all activities that occur under your account or password.&nbsp; In the event the confidentiality of your account or password is compromised in any manner, you should notify GROUPCARE TECHNOLOGIES immediately.&nbsp; GROUPCARE TECHNOLOGIES reserves the right to take any and all action, as it deems necessary or reasonable, to ensure the security of the Sites and your account, including without limitation terminating your account, changing your password, or requesting additional information to authorize transactions on your account.&nbsp; Notwithstanding the above, GROUPCARE TECHNOLOGIES may rely on the authority of anyone accessing your account or using your password and in no event and under no circumstances shall GROUPCARE TECHNOLOGIES be held liable to you for any liabilities or damages resulting from or arising out of (i) any action or inaction of GROUPCARE TECHNOLOGIES under this provision, (ii) any compromise of the confidentiality of your account or password and (iii) any unauthorized access to your account or use of your password. Any unauthorized use of the Sites will terminate the permission or license granted herein and may violate applicable law, including copyright laws, trademark laws (including trade dress), and communications regulations and statutes.&nbsp; All violators will be prosecuted to the fullest extent of the law.</p>\n<h3>Trademarks and Service Marks</h3>\n<p>The trademarks and service marks displayed on the Sites including GroupCare Technologies™ and SafeTrax™ are registered and unregistered trademarks of GROUPCARE TECHNOLOGIES and may not be copied, imitated or used, in whole or in part, without prior written permission of GROUPCARE TECHNOLOGIES or, if applicable, its licensor.&nbsp; In addition, GROUPCARE TECHNOLOGIES custom graphics, button icons, scripts, and page headers are covered by trademark, trade dress, copyright or other proprietary right law, and may not be copied, imitated, or used, in whole or in part, without prior written permission.&nbsp; GROUPCARE TECHNOLGIGES trademarks, service marks, and trade dress may not be used in any manner that is likely to cause confusion among customers or in any manner that disparages or discredits GROUPCARE TECHNOLOGIES.&nbsp; \"GROUPCARE TECHNOLOGIES\" the \"Graphic Icon\" design and all other GROUPCARE TECHNOLOGIES marks and logos, and the GROUPCARE TECHNOLOGIES products and services described on the Sites, are either trademarks, service marks, or registered trademarks of GROUPCARE TECHNOLOGIES or other GROUPCARE TECHNOLGIES affiliates, and may not be copied, imitated or used, in whole or in part, without the prior written permission.&nbsp; All other trademarks, service marks, registered trademarks, product and service names, and company names or logos that appear on the Sites are the property of their respective owners, who may or may not be affiliated with, connected to, or sponsored by GROUPCARE TECHNOLOGIES.&nbsp; You may not use any metatags or any other \"hidden text\" utilizing a GROUPCARE TECHNOLOGIES name, trademark, or product name without GROUPCARE TECHNOLOGIES express written consent.</p>\n<h3>Third-Party Products and Services</h3>\n<p>Parties other than GROUPCARE TECHNOLOGIES offer and provide products and services on or through the Sites.&nbsp; Except for GROUPCARE TECHNOLOGIES-branded information, products or services that are specifically identified as being supplied by GROUPCARE TECHNOLOGIES, GROUPCARE TECHNOLOGIES does not operate, control, or endorse any information, products, or services on the Sites, or accessible through the Sites, in any way.&nbsp; Except for GROUPCARE TECHNOLOGIES-identified information and GROUPCARE TECHNOLOGIES -branded products or services, all information, products, and services offered through the Sites or Internet generally are offered by third parties that are not affiliated with GROUPCARE TECHNOLOGIES. GROUPCARE TECHNOLOGIES is not responsible for examining or evaluating, and GROUPCARE TECHNOLOGIES does not warrant the offerings of, any of these businesses or individuals or the content of their websites.&nbsp; GROUPCARE TECHNOLOGIES does not assume any responsibility or liability for the actions, product, and content of all these and any other third parties.&nbsp; You should carefully review their privacy statements and other conditions of use.</p>\n<h3>Product and Service Descriptions and Pricing</h3>\n<p>GROUPCARE TECHNOLGIES and third parties offering information, products, or services on the Sites attempt to be as accurate as possible.&nbsp; However, GROUPCARE TECHNOLOGIES does not warrant that information, product, and service descriptions or other content of the Sites are accurate, complete, reliable, current, or error-free.&nbsp; Despite our efforts, it is possible, due to computer or other error or cause, that a product or service offered on the Sites may be mispriced or contain an inaccuracy in its description.&nbsp; In the event GROUPCARE TECHNOLOGIES determines that a product or service is mispriced or contains an inaccurate description, GROUPCARE TECHNOLOGIES reserves the right to take any action it deems reasonable and necessary, in its sole discretion, to rectify the error, including without limitation canceling your order. You agree to notify GROUPCARE TECHNOLOGIES immediately if you become aware of any pricing or descriptive errors or inconsistencies with any products or services you order through the Sites and comply with any corrective action taken by GROUPCARE TECHNOLOGIES.</p>\n<h3>Linking</h3>\n<p>You may not use a GROUPCARE TECHNOLOGIES or other proprietary graphic or trademark of GROUPCARE TECHNOLOGIES to link to the Sites without the express written permission of GROUPCARE TECHNOLOGIES.</p>\n<p>Third-Party Links.&nbsp; GROUPCARE TECHNOLOGIES makes no claim or representation regarding, and accepts no responsibility for, the quality, content, nature, or reliability of third-party websites or services accessible by hyperlink from the Sites, or third-party websites linking to the Sites.&nbsp; Such linked Web sites are not under the control of GROUPCARE TECHNOLOGIES and GROUPCARE TECHNOLOGIES is not responsible for the content of any such linked Web site or any link contained in a linked Web site, or any review, changes or updates to such Web sites. GROUPCARE TECHNOLGIES is providing these links to you only as a convenience, and the inclusion of any link does not imply affiliation, endorsement, or adoption by GROUPCARE TECHNOLOGIES of the Web site or any information contained therein. When leaving the GROUPCARE TECHNOLOGIES Sites, you should be aware that GROUPCARE TECHNOLOGIES terms and policies no longer govern, and, therefore, you should review the applicable terms and policies, including privacy and data gathering practices, of that Web site.</p>\n<h3>Submissions</h3>\n<p>You agree that any materials, including but not limited to questions, comments, suggestions, ideas, plans, notes, drawings, original or creative materials, or other information, provided by you in the form of e-mail or submissions to GROUPCARE TECHNOLOGIES, or postings to or on the Sites, are non-confidential (on the condition that personal information provided on non-public areas of the Sites is subject to GROUPCARE TECHNOLOGIES Privacy Policy) and you grant GROUPCARE TECHNOLOGIES a nonexclusive, royalty-free, perpetual, irrevocable, and fully sublicensable right to use, reproduce, modify, adapt, publish, translate, create derivative works from, distribute, and display such materials throughout the world in any media now known or hereafter developed with or without acknowledgment to you in GROUPCARE TECHNOLOGIES sole discretion and without compensation to you.&nbsp; You also grant to GROUPCARE TECHNOLOLGIES the right to use your name in connection with the submitted materials and other information, as well as in connection with all advertising, marketing and promotional material related thereto.&nbsp; You agree that you shall have no recourse against GROUPCARE TECHNOLOGIES for any alleged or actual infringement or misappropriation of any proprietary right in your submitted materials and that the submission of any such materials to GROUPCARE TECHNOLGIES, including the posting of materials to any forum or interactive area on the Sites, irrevocably waives any and all \"moral rights\" in such materials, including the rights of paternity and integrity.&nbsp; You represent and warrant that you own or otherwise control all of the rights to the material that you post; that the material is accurate; that use of the material you supply does not violate this Agreement and will not cause injury to any person or entity; and that you will indemnify GROUPCARE TECHNOLOGIES for all claims resulting from material you supply.</p>\n<h3>Export Restrictions</h3>\n<p>Any software and all underlying information and technology downloaded from the Sites (collectively the \"Software\") by you may be subject to U.S. export controls, and may be subject to export or import regulations in other countries.&nbsp; You are responsible for complying with all trade regulations and laws both foreign and domestic. Except as authorized by law, you agree and warrant not to export or re-export the Software to any country, or to any person, entity, or end-user subject to U.S. export controls, including without limitation persons or entities listed on the U.S. Department of Commerce Bureau of Export Administration's Denied Parties List and the U.S. Department of Treasury's Specially Designated Nationals. You further represent and warrant that no U.S. federal agency has suspended, revoked, or denied your export privileges.</p>\n<h3>Disclaimers</h3>\n<p>\"AS IS\" AND \"AS AVAILABLE\" BASIS:<br>THE SITES AND THE MATERIALS CONTAINED THEREIN ARE PROVIDED ON AN \"AS IS\" AND \"AS AVAILABLE\" BASIS WITHOUT WARRANTIES OF ANY KIND, EITHER EXPRESS OR IMPLIED, AS TO THE OPERATION OF THE SITES OR THE INFORMATION, CONTENT, MATERIALS, PRODUCTS OR SERVICES INCLUDED ON THE SITES. YOU EXPRESSLY AGREE THAT USE OF THE SITES, INCLUDING ALL CONTENT, DATA OR SOFTWARE DISTRIBUTED BY, DOWNLOADED OR ACCESSED FROM OR THROUGH THE SITES, IS AT YOUR SOLE RISK AND RESPONSIBILITY.</p>\n<h3>WARRANTY DISCLAIMER:</h3>\n<p>GROUPCARE TECHNOLOGIES DISCLAIMS ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING, BUT NOT LIMITED TO, IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, TITLE, AND NON-INFRINGEMENT AS TO THE INFORMATION, MATERIALS, CONTENT, SERVICES AND PRODUCTS ON THE SITES. GROUPCARE TECHNOLOGIES DOES NOT REPRESENT OR WARRANT THAT MATERIALS IN THE SITES ARE ACCURATE, COMPLETE, RELIABLE, CURRENT, ERROR-FREE, SUBJECT TO CORRECTION OR THAT ANY SUCH MATERIALS AVAILABLE FOR DOWNLOAD FROM THE SITES ARE FREE OF INFECTION OR VIRUSES, WORMS, TROJAN HORSES, OR OTHER CODE THAT MANIFESTS CONTAMINATING OR DESTRUCTIVE PROPERTIES.&nbsp; GROUPCARE TECHNOLOGIES IS NOT RESPONSIBLE FOR TYPOGRAPHICAL ERRORS OR OMISSIONS RELATING TO PRICING, TEXT, OR PHOTOGRAPHY. WHILE GROUPCARE TECHNOLOGIES ATTEMPTS TO ENSURE YOUR ACCESS AND USE OF THE SITES IS SAFE, GROUPCARE TECHNOLOGIES CANNOT AND DOES NOT REPRESENT OR WARRANT THAT THE SITES OR ITS SERVER(S) WILL BE ERROR-FREE, UNINTERRUPTED, FREE FROM UNAUTHORIZED ACCESS (INCLUDING THIRD-PARTY HACKERS OR DENIAL OF SERVICE ATTACKS) OR OTHERWISE MEET CUSTOMER'S REQUIREMENTS.<br><br>GROUPCARE TECHNOLOGIES reserves the right to change any and all content contained on the Sites at any time without notice.&nbsp; Reference to any products, services, processes, or other information, by trade name, trademark, manufacturer, supplier, or otherwise does not constitute or imply endorsement, sponsorship or recommendation thereof by GROUPCARE TECHNOLOGIES.</p>\n<h3>YOUR RESPONSIBILITY AND RISK:</h3>\n<p>It is solely your responsibility to evaluate the accuracy, completeness, and usefulness of all opinions, advice, services, merchandise, and other information provided on or through the Sites or on the Internet generally. You access such materials at your own risk.&nbsp; GROUPCARE TECHNOLOGIES has no control over and accepts no responsibility whatsoever for such materials.</p>\n<h3>Enforcement</h3>\n<p>GROUPCARE TECHNOLOGIES reserves the right but does not assume the obligation to strictly enforce this Agreement, including without limitation by issuing warnings, suspension, or termination of service, and/or removal, screening, or editing of posted message, data, information, text or other material (\"Content\"), self help and active investigation, litigation and prosecution in any court or appropriate venue. GROUPCARE TECHNOLOGIES may access, use, and disclose transaction information and Content to comply with the law (e.g., a lawful government request); enforce or apply our customer agreements; to initiate, render, bill, and collect for services; to protect our rights or property, or to protect users of those services and other carriers from fraudulent, abusive, or unlawful use of, or subscription to, such services. INDIRECT OR ATTEMPTED VIOLATIONS OF THIS AGREEMENT OR ANY RELATED POLICY, AND ACTUAL OR ATTEMPTED VIOLATIONS BY A THIRD PARTY ON BEHALF OF A GROUPCARE TECHNOLOGIES CUSTOMER, SHALL BE CONSIDERED VIOLATIONS OF THIS AGREEMENT BY SUCH CUSTOMER.</p>\n<h3>Limitation of Liability</h3>\n<p>IN NO EVENT SHALL GROUPCARE TECHNOLOGIES BE LIABLE FOR ANY DIRECT, SPECIAL, INDIRECT OR CONSEQUENTIAL DAMAGES, OR ANY OTHER DAMAGES OF ANY KIND, INCLUDING BUT NOT LIMITED TO, LOSS OF USE, LOSS OF PROFITS, OR LOSS OF DATA, WHETHER IN AN ACTION IN CONTRACT, TORT (INCLUDING BUT NOT LIMITED TO NEGLIGENCE), OR OTHERWISE, ARISING OUT OF OR IN ANY WAY CONNECTED WITH (I) THE USE OR INABILITY TO USE THE SITES OR THE CONTENT, MATERIALS, INFORMATION OR TRANSACTIONS PROVIDED ON OR THROUGH THE SITES, OR (II) ANY CLAIM ATTRIBUTABLE TO ERRORS, OMISSIONS, OR OTHER INACCURACIES IN THE SITES OR THE CONTENT, MATERIALS, INFORMATION, PRODUCTS, OR SERVICES ON OR AVAILABLE THROUGH THE SITES, EVEN IF GROUPCARE TECHNOLOGIES OR ITS AUTHORIZED REPRESENTATIVES HAVE BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.</p>\n<p>Certain state laws do not allow limitations on implied warranties, or the exclusion or limitation of certain damages.&nbsp; If these laws apply, some or all of the above disclaimers, exclusions, or limitations, may not apply to you, and you may have additional rights to those contained herein. In such states, GROUPCARE TECHNOLOGIES liability is limited to the greatest extent permitted by law.</p>\n<h3>Indemnification</h3>\n<p>You agree to indemnify, defend, and hold harmless GROUPCARE TECHNOLOGIES, its officers, directors, employees, agents, licensors, suppliers, and any third-party information providers to the Sites from and against all losses, expenses, damages, and costs, including reasonable attorneys' fees, resulting from any violation of this Agreement by you, or your violation of any rights of a third party.</p>\n<h3>Third-Party Rights</h3>\n<p>Notwithstanding any of these terms and conditions, this Agreement may be terminated by either party without notice at any time for any reason; provided that you may no longer access or use the Sites after this Agreement has been terminated.&nbsp; The provisions of the paragraphs entitled Copyright, Trademarks and Service Marks, Third-Party Content, Submissions, Export Restrictions, Disclaimers, Limitation of Liability, Indemnification, Third-Party Rights, Applicable Law, Venue and Limitation of Actions, Termination, Severability, Enforceability and Admissibility, and Miscellaneous shall survive any termination of this Agreement.</p>\n<h3>Severability</h3>\n<p>If any provision of this Agreement shall be deemed unlawful, void, or for any reason unenforceable, then that provision shall be deemed severable from these terms and conditions and shall not affect the validity and enforceability of any remaining provisions.</p>\n<h3>Enforceability and Admissibility</h3>\n<p>This electronic document and any other electronic documents, policies, and guidelines incorporated herein will be:&nbsp; (a) deemed for all purposes to be a \"writing\" or \"in writing,\" and to comply with all statutory, contractual, and other legal requirements for a writing; (b) legally enforceable as a signed writing as against the parties subject to the electronic documents; and (c) deemed an \"original\" when printed from electronic records established and maintained in the ordinary course of business.&nbsp; Electronic documents introduced as evidence in any judicial, arbitration, mediation, or administrative proceeding will, if established and maintained in the ordinary course of business, be admissible to the same extent as business records in written form that are similarly established and maintained.</p>\n<h3>Miscellaneous</h3>\n<p>GROUPCARE TECHNOLOGIES failure to insist upon or enforce strict performance of any provision of this Agreement shall not be construed as a waiver of any provision or right.&nbsp; Neither the course of conduct between the parties nor trade practice shall act to modify any provision of this Agreement.&nbsp; GROUPCARE TECHNOLOGIES may assign its rights and duties under this Agreement to any party at any time without notice to you.</p>\n<p>In order to protect GROUPCARE TECHNOLOGIES and its customers from fraudulent activity or “bulk resale scheme”, we may limit the number of devices and services that may be ordered online by a single individual or entity. GROUPCARE TECHNOLOGIES reserves the right to further limit quantities or to cancel or reject orders in its sole discretion.</p><div class=\"clearfix\"></div></div>",
            "startDate" : "2016-02-03T11:18:20.300Z",
            "endDate" : "2099-12-31T00:00:00.000Z",
            "domainName" : "SAFETRAX",
            "topic" : "TERMS",
            "consentLog" : [
                {
                    "loginId" : "qa1@qa.com",
                    "consent" : "ACCEPTED",
                    "timeStamp" : "2016-02-03T11:18:34.628Z",
                    "_id" : "56b1e20a889797170034f14f"
                },
                {
                    "loginId" : "qa3@qa.com",
                    "consent" : "ACCEPTED",
                    "timeStamp" : "2016-02-03T12:28:02.846Z",
                    "_id" : "56b1f252889797170034f19a"
                },
                {
                    "loginId" : "qa2@qa.com",
                    "consent" : "ACCEPTED",
                    "timeStamp" : "2016-02-03T12:28:43.012Z",
                    "_id" : "56b1f27b889797170034f19c"
                },
                {
                    "loginId" : "dev6@dev.com",
                    "consent" : "ACCEPTED",
                    "timeStamp" : "2016-02-03T12:59:33.579Z",
                    "_id" : "56b1f9b5889797170034f1a1"
                },
                {
                    "loginId" : "ctm1@gct.com",
                    "consent" : "ACCEPTED",
                    "timeStamp" : "2016-02-03T19:44:37.270Z",
                    "_id" : "56b258a5ec9fe40b000bfdd2"
                },
                {
                    "loginId" : "pcg1@gct.com",
                    "consent" : "ACCEPTED",
                    "timeStamp" : "2016-02-03T19:50:09.192Z",
                    "_id" : "56b259f1ec9fe40b000bfdde"
                },
                {
                    "loginId" : "scott@gct.com",
                    "consent" : "ACCEPTED",
                    "timeStamp" : "2016-02-03T20:29:11.568Z",
                    "_id" : "56b26317ec9fe40b000bfe22"
                },
                {
                    "loginId" : "james@gct.com",
                    "consent" : "ACCEPTED",
                    "timeStamp" : "2016-02-04T20:57:20.419Z",
                    "_id" : "56b3bb30f57e430a000df006"
                },
                {
                    "loginId" : "tom@gct.com",
                    "consent" : "ACCEPTED",
                    "timeStamp" : "2016-02-04T20:58:13.037Z",
                    "_id" : "56b3bb65f57e430a000df00a"
                },
                {
                    "loginId" : "ctm2@gct.com",
                    "consent" : "ACCEPTED",
                    "timeStamp" : "2016-02-08T19:35:32.382Z",
                    "_id" : "56b8ee04f57e430a000e09c8"
                },
                {
                    "loginId" : "fred@fred.com",
                    "consent" : "ACCEPTED",
                    "timeStamp" : "2016-02-11T14:55:08.699Z",
                    "_id" : "56bca0ccf57e430a000e18b5"
                },
                {
                    "loginId" : "pcg2@gct.com",
                    "consent" : "ACCEPTED",
                    "timeStamp" : "2016-02-12T20:12:39.695Z",
                    "_id" : "56be3cb7f57e430a000e2085"
                },
                {
                    "loginId" : "pcg3@gct.com",
                    "consent" : "ACCEPTED",
                    "timeStamp" : "2016-02-16T19:01:34.332Z",
                    "_id" : "56c3720ef57e430a000e39bc"
                },
                {
                    "loginId" : "ctm3@gct.com",
                    "consent" : "ACCEPTED",
                    "timeStamp" : "2016-02-16T20:52:17.724Z",
                    "_id" : "56c38c0190f7150b00063efc"
                },
                {
                    "loginId" : "heath@gct.com",
                    "consent" : "ACCEPTED",
                    "timeStamp" : "2016-02-16T22:10:27.761Z",
                    "_id" : "56c39e5390f7150b0006404e"
                },
                {
                    "loginId" : "James@gct.com",
                    "consent" : "ACCEPTED",
                    "timeStamp" : "2016-02-16T22:52:34.190Z",
                    "_id" : "56c3a83290f7150b00064168"
                },
                {
                    "loginId" : "Heath@gct.com",
                    "consent" : "ACCEPTED",
                    "timeStamp" : "2016-02-17T21:06:03.757Z",
                    "_id" : "56c4e0bb90f7150b00066a56"
                },
                {
                    "loginId" : "dev5@dev.com",
                    "consent" : "ACCEPTED",
                    "timeStamp" : "2016-02-22T08:17:15.915Z",
                    "_id" : "56cac40b6ed35d0b00eb9ecb"
                },
                {
                    "loginId" : "faisal@gct.com",
                    "consent" : "ACCEPTED",
                    "timeStamp" : "2016-03-01T23:21:03.689Z",
                    "_id" : "56d623df1ca1e40b0023d3be"
                },
                {
                    "loginId" : "rt1@gct.com",
                    "consent" : "ACCEPTED",
                    "timeStamp" : "2016-04-18T14:18:19.173Z",
                    "_id" : "5714ecabbfdd8a0e0024dc0c"
                },
                {
                    "loginId" : "so@gct.com",
                    "consent" : "ACCEPTED",
                    "timeStamp" : "2016-04-18T14:19:39.451Z",
                    "_id" : "5714ecfbbfdd8a0e0024dc12"
                },
                {
                    "loginId" : "dennis@gct.com",
                    "consent" : "ACCEPTED",
                    "timeStamp" : "2016-04-18T15:39:50.528Z",
                    "_id" : "5714ffc601be880d00a5d705"
                },
                {
                    "loginId" : "pilot@gct.com",
                    "consent" : "ACCEPTED",
                    "timeStamp" : "2016-04-18T15:40:03.075Z",
                    "_id" : "5714ffd301be880d00a5d706"
                }
            ],
            "__v" : 26
        }
    ];

var MongoClient = require('mongodb').MongoClient;
var mongoUrl = "mongodb://" + host + ":" + port + "/test_sproutdb";
var async = require('async');

MongoClient.connect(mongoUrl, function(err, db) {

    if (err) {
        console.log("[Error] " + err);
        process.exit(1);
    }

    if (!db) {
        console.log("[Error] No db Object");
        process.exit(1);
    }

    console.log("[Info] Connected to: " + mongoUrl);

    //db.dropDatabase();

    var accounts = db.collection('accounts');
    var participants = db.collection('participants');
    var fidgets = db.collection('fidgets');
    var clusters = db.collection('clusters');
    var fidgetprofile = db.collection('fidgetprofile');
    var fidgetblobs = db.collection('fidgetblobs');
    var gears = db.collection('gears');
    var instazones = db.collection('instazones');
    var participantgeo = db.collection('participantgeo');
    var safebridge = db.collection('safebridge');
    var schedules = db.collection('schedules');
    var staticcontent = db.collection('staticcontent');

    var arr=[];

    // var callback = function(err, results){
    //     callback(err, results)
    // }

    arr.push( function(callback){accounts.insertMany(accountsdata, function(err, result) {callback(err, result)} )} );
    arr.push( function(callback){participants.insertMany(participantsdata, function(err, result) {callback(err, result)} )} );
    arr.push( function(callback){fidgets.insertMany(fidgetsdata, function(err, result) {callback(err, result)} )} );
    arr.push( function(callback){clusters.insertMany(clustersdata, function(err, result) {callback(err, result)} )} );
    arr.push( function(callback){fidgetprofile.insertMany(fidgetProfilesdata, function(err, result) {callback(err, result)} )} );
    arr.push( function(callback){fidgetblobs.insertMany(fidgetblobsdata, function(err, result) {callback(err, result)} )} );
    arr.push( function(callback){gears.insertMany(gearsdata, function(err, result) {callback(err, result)} )} );
    arr.push( function(callback){instazones.insertMany(instazonesdata, function(err, result) {callback(err, result)} )} );
    arr.push( function(callback){participantgeo.insertMany(participantgeodata, function(err, result) {callback(err, result)} )} );
    arr.push( function(callback){safebridge.insertMany(safebridgedata, function(err, result) {callback(err, result)} )} );
    arr.push( function(callback){schedules.insertMany(schedulesdata, function(err, result) {callback(err, result)} )} );
    arr.push( function(callback){staticcontent.insertMany(staticcontentdata, function(err, result) {callback(err, result)} )} );


    async.parallel(arr, function(err, results){
        if (err) return 'Error: ' + err;

        console.log('Callback complete. Results: ' + results);
        process.exit(0);
    });

    console.log("done");
});

