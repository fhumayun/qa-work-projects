/**
 *
 */
'use strict';

// System Configuration
var systemConfigs =
    [
        {
            "_id" : ObjectId("000000000000000000000001"),
            "_class" : "com.strax.datamodel.system.SystemConfig",
            "hostname" : "ssl://localhost",
            "port" : 8883,
            "protocolVersion" : "1.0.0",
            "kafkaServer" : "localhost:9092",
            "ntpServer" : "time-d.nist.gov",
            "nodeServer" : "http://beta.strax.co:9900",
            "boldServer" : "https://boldapi.acadianmonitoring.com/Post"
        }
    ];


// Email configuration
var emailConfigs =
    [
        {
            "_id" : ObjectId("000000000000000000000002"),
            "_class" : "com.strax.datamodel.system.EmailConfig",
            "protocol" : "1.0",
            "host" : "smtp.office365.com",
            "port" : 587,
            "auth" : true,
            "tls" : true,
            "from" : "strax@groupcaretech.com",
            "userName" : "strax@groupcaretech.com",
            "password" : "GcT@str@x2016"
        }
    ];


// Email template1
var emailTemplate1 =
    [
        {
            "_id" : ObjectId("000000000000000000000003"),
            "_class" : "com.strax.datamodel.system.EmailTemplate",
            "template" : ['<!DOCTYPE html><html lang="en">',
                '<head>',
                '<meta charset="UTF-8">',
                '</head>',
                '<style>',
                'p.padding {',
                'padding-left: 20px;',
                '}',
                '</style>',
                '<body>',
                '<p>',
                '<img src="http://media.strax.co:8092/images/groupcare-logo.png"/>',
                '</p>',
                '<table>',
                '<tr>',
                '<p>Dear ##name##,</p>',
                '<p class="padding">##message##</p>',
                '<p><i>Your GroupCare Team</i></p>',
                '</tr>',
                '<tr>',
                '<td>',
                '<p>',
                '<img src="https://maps.googleapis.com/maps/api/staticmap?zoom=14&size=600x300&maptype=roadmap',
                '&markers=color:red%7Clabel:X%7C##latitude##,##longitude##',
                '&key=AIzaSyC8V6qIVgWeYDcYor9kQ4ozmGLjZ2XtFjk"/>',
                '</p>',
                '</td>',
                '</tr>',
                '</table>',
                '</body>',
                '</html>'],
            "map" : true
        }
    ];


// Email template2
var emailTemplate2 =
    [
        {
            "_id" : ObjectId("000000000000000000000004"),
            "_class" : "com.strax.datamodel.system.EmailTemplate",
            "template" : ['<!DOCTYPE html><html lang="en">',
                '<head>',
                '<meta charset="UTF-8">',
                '</head>',
                '<style>',
                'p.padding {',
                'padding-left: 20px;',
                '}',
                '</style>',
                '<body>',
                '<p>',
                '<img src="http://media.strax.co:8092/images/groupcare-logo.png"/>',
                '</p>',
                '<table>',
                '<tr>',
                '<p>Dear ##name##,</p>',
                '<p class="padding">##message##</p>',
                '<p><i>Your GroupCare Team</i></p>',
                '</tr>',
                '</table>',
                '</body>',
                '</html>'],
            "map" : false
        }
    ];


// Zoo keeper config
var zooKeepers =
    [
        {
            "_id" : ObjectId("000000000000000000000001"),
            "_class" : "com.strax.datamodel.service.ZooKeeper",
            "sessionTimeout" : "10000",
            "syncTime" : "200",
            "autoCommitInterval" : "1000"
        }
    ];


// Strax msa
var straxMicroServices =
    [
        {
            "_id" : ObjectId("000000000000000000000001"),
            "_class" : "com.strax.datamodel.service.MqttKafkaBridgeService",
            "name" : "mqtt_bridge",
            "url" : "http://localhost:8079",
            "numOfThreads" : 10,

            "zooKeeper" : {
                "$ref" : "zooKeeper",
                "$id" : ObjectId("000000000000000000000001")
            },

            "mqttConnection" : {
                "_id" : ObjectId("000000000000000000000002"),
                "mqttClientId" : "mqttKafkaBrideId",
                "mqttBrokerUri" : "ssl://localhost:8883",
                "connectionTimeout" : 60,
                "keepAliveInterval" : 60,
                "connectionRetry" : 2000
            }
        },
        {
            "_id" : ObjectId("000000000000000000000002"),
            "_class" : "com.strax.datamodel.service.KafkaService",
            "name" : "rest_bridge",
            "url" : "http://localhost:8086",
            "numOfThreads" : 10,
            "zooKeeper" : {
                "$ref" : "zooKeeper",
                "$id" : ObjectId("000000000000000000000001")
            }
        },
        {
            "_id" : ObjectId("000000000000000000000003"),
            "_class" : "com.strax.datamodel.service.KafkaService",
            "name" : "event_processor",
            "url" : "http://localhost:8082",
            "numOfThreads" : 10,
            "zooKeeper" : {
                "$ref" : "zooKeeper",
                "$id" : ObjectId("000000000000000000000001")
            }
        },
        {
            "_id" : ObjectId("000000000000000000000004"),
            "_class" : "com.strax.datamodel.service.NotificationService",
            "name" : "notification",
            "url" : "http://localhost:8084",
            "googleServerKey" : "AIzaSyC8V6qIVgWeYDcYor9kQ4ozmGLjZ2XtFjk",
            "googleProjectId" : "1034283375880",
            "appleCertificate" : "./certificates/Certificates.p12",
            "applePassword" : "CareTeam2015"
        },
        {
            "_id" : ObjectId("000000000000000000000005"),
            "_class" : "com.strax.datamodel.service.StraxMicroService",
            "name" : "ier",
            "url" : "http://localhost:8083"
        },
        {
            "_id" : ObjectId("000000000000000000000006"),
            "_class" : "com.strax.datamodel.service.KafkaService",
            "name" : "device_manager",
            "url" : "http://localhost:8093",
            "numOfThreads" : 10,
            "zooKeeper" : {
                "$ref" : "zooKeeper",
                "$id" : ObjectId("000000000000000000000001")
            }
        },
        {
            "_id" : ObjectId("000000000000000000000007"),
            "_class" : "com.strax.datamodel.service.StraxMicroService",
            "name" : "configuration",
            "url" : "http://localhost:8080"
        },
        {
            "_id" : ObjectId("000000000000000000000008"),
            "_class" : "com.strax.datamodel.service.StraxMicroService",
            "name" : "crm",
            "url" : "http://localhost:8085"
        },
        {
            "_id" : ObjectId("000000000000000000000009"),
            "_class" : "com.strax.datamodel.service.StraxMicroService",
            "name" : "activity_scheduler",
            "url" : "http://localhost:8088"
        },
        {
            "_id" : ObjectId("000000000000000000000010"),
            "_class" : "com.strax.datamodel.service.KafkaService",
            "name" : "lpwan_bridge",
            "url" : "http://localhost:8091",
            "numOfThreads" : 10,
            "zooKeeper" : {
                "$ref" : "zooKeeper",
                "$id" : ObjectId("000000000000000000000001")
            }
        }
    ];


// Participant opt in
var participantOptIns =
    [
        {
            "_id" : ObjectId("000000000000000000000001"),
            "_class" : "com.strax.datamodel.notification.ParticipantOptIn",
            "emailOptIn" : true,
            "email" : "central@cc.com",
            "smsOptIn" : true,
            "phoneOptIn" : true,
            "phoneNumber" : "5619457595",
            "iphoneDeviceId" : "b6e20f21 c01ec0fa f17fc437 a6a30847 a50abd1e c2a4cbb7 9a28fb87 b938732a",
            "fidgetId" : "000000000000000000000001",
            "participantId" : "000000000000000000000001",
            "mobileProvider" : "Sprint",
            "high" : true,
            "medium" : true,
            "low" : true
        }

    ];


// Message Id
var messageIds =
    [
        {
            "_id" : ObjectId("55ae5a6dd4c62bba1586ee33"),
            "_class" : "com.strax.datamodel.device.MessageId",
            "messageId" : 0
        }
    ];


// Boldnet Config
var boldnetConfigs =
    [
        {
            "_id" : ObjectId("000000000000000000000001"),
            "baseUrl" : "boldapi.acadianmonitoring.com/BoldNet",
            "username" : "GroupCare",
            "password" : "ACADTG911XY!",
            "_class" : "com.strax.datamodel.ier.BoldnetConfig"
        }
    ];


// Even Threshold
var eventThresholds =
    [
        {
            "_id" : ObjectId("000000000000000000000001"),
            "_class" : "com.strax.datamodel.event.EventThreshold",
            "escalationWaitTime" : 60000000,
            "inVehicleSpeed" : 20,
            "speedingVehicleSpeed" : 71,
            "heartBeat" : -1,
            "batteryInfo" : 30,
            "batteryMedium" : 20,
            "batteryHigh" : 10,
            "batteryInfoRemainingTime" : 10,
            "batteryMediumRemainingTime" : 7,
            "batteryHighRemainingTime" : 1,
            "autoEscalation" : false,
            "checkTelemetryZone" : false,
            "deviceTimeout" : 0
        }
    ];


// Incident Messages
var incidentMessages =
    [
        {
            "_id" : ObjectId("000000000000000000000001"),
            "_class" : "com.strax.datamodel.ier.IncidentMessage",
            "message" : "False alarm",
            "state" : "cleared"
        },
        {
            "_id" : ObjectId("000000000000000000000002"),
            "_class" : "com.strax.datamodel.ier.IncidentMessage",
            "message" : "With me",
            "state" : "cleared"
        },
        {
            "_id" : ObjectId("000000000000000000000003"),
            "_class" : "com.strax.datamodel.ier.IncidentMessage",
            "message" : "Incident was not serious",
            "state" : "resolved"
        },
        {
            "_id" : ObjectId("000000000000000000000004"),
            "_class" : "com.strax.datamodel.ier.IncidentMessage",
            "message" : "Incident serious - law enforcement engaged",
            "state" : "resolved"
        }
    ];


// CRM Config
var crmConfigs =
    [
        {
            "_id" : ObjectId("000000000000000000000001"),
            "_class" : "com.strax.datamodel.crm.CrmConfig",
            "userName" : "admin",
            "url" : "http://localhost:8089/straxcrm/webservice.php",
            "accessKey" : "LBXzVIDNNNwpl3P"
        }
    ];


// Topics
var topics =
    [
        {
            "_id" : ObjectId("000000000000000000000001"),
            "_class" : "com.strax.datamodel.service.Topic",
            "name" : "safetrax",
            "value" : "safetrax_test"
        },
        {
            "_id" : ObjectId("000000000000000000000002"),
            "_class" : "com.strax.datamodel.service.Topic",
            "name" : "lpwan",
            "value" : "lpwan_test"
        },
        {
            "_id" : ObjectId("000000000000000000000003"),
            "_class" : "com.strax.datamodel.service.Topic",
            "name" : "kafka",
            "value" : "localhost:9092"
        },
        {
            "_id" : ObjectId("000000000000000000000004"),
            "_class" : "com.strax.datamodel.service.Topic",
            "name" : "zoo",
            "value" : "localhost:2181"
        },
        {
            "_id" : ObjectId("000000000000000000000005"),
            "_class" : "com.strax.datamodel.service.Topic",
            "name" : "eagleeye",
            "value" : "eagleeye_test"
        }
    ];

var mongo = new Mongo();
var db = mongo.getDB("test_strax");
db.dropDatabase();

db.systemConfig.insert( systemConfigs );
db.systemConfig.insert( emailConfigs );
db.systemConfig.insert( emailTemplate1 );
db.systemConfig.insert( emailTemplate2 );
db.zooKeeper.insert( zooKeepers );
db.straxMicroService.insert( straxMicroServices );
db.participantOptIn.insert( participantOptIns );
db.messageId.insert( messageIds );
db.boldnetConfig.insert( boldnetConfigs );
db.eventThreshold.insert( eventThresholds );
db.incidentMessage.drop();
db.incidentMessage.insert( incidentMessages );
db.crmConfig.insert( crmConfigs );
db.topic.insert( topics );