/**
 * Test Strax API authentication
 */

// Variables
const request = require('request');
const Oz = require('oz')

var token;
var appTicketExp;
var appTicketApp;
var appTicketScope;
var appTicketKey;
var appTicketAlgorithm;
var appTicketId;
var rsvp;

var appTicket;

const ID_SERVER = "https://id-dev.strax.co/";
const VALIDATE = "oz/validate";
const USERNAME = "jshanahan@eagleeyeintelligence.com";
const PASSWORD = "eei"


// Login request for Auth rsvp/ticket
request({
    method: 'POST',
    uri:'https://api-dev.strax.co/api/participant/doAuthenticate',
    headers: {
        'Content-Type': 'application/json'
    },
    json: {
      "username": USERNAME,
      "password": PASSWORD,
      "fingerprint": "000",
      "fpData": [{"script": "Cuke JS"}],
      "type": "Auto Test",
      "applName": "Cuke JS"
    }
},
    function(error, response, body) {
        if (error) {
            console.log('>>> Error: Could not log in');
            console.log('>>> Error: ' + JSON.stringify(error));
        } else {

          // --------------------------
          appTicket = body.appTicket;

          console.log("AppTicket from Login response ==> ", appTicket);

          // GET Request 1
          authHeader = Oz.client.header(ID_SERVER + VALIDATE, 'POST', appTicket, null).field;
          allOtherRequests = {
            "uri": "https://api-dev.strax.co/api/participant/57e43fa82581a516007a1406",
            "method": "GET",
            "headers": {"Authorization": authHeader}
          }

          request(allOtherRequests, function (error, response, body) {
            console.log("\n\n\n authenticated request body ==> ", body);
          })

          // GET Request 2
          authHeader = Oz.client.header(ID_SERVER + VALIDATE, 'POST', appTicket, null).field;
          allOtherRequests_2 = {
            "uri": "https://api-dev.strax.co/api/participant/57e43fa82581a516007a1406",
            "method": "GET",
            "headers": {"Authorization": authHeader}
          }

          request(allOtherRequests_2, function (error, response, body) {
            console.log("\n\n\n authenticated request body::allOtherRequests_2 ==> ", body);
          })

          return;

            // pull out auth pieces if login successful
            token = body["token"];
            appTicketExp = body["appTicket"]["exp"];
            appTicketApp = body["appTicket"]["app"];
            appTicketScope = body["appTicket"]["scope"];
            appTicketKey = body["appTicket"]["key"];
            appTicketAlgorithm = body["appTicket"]["algorithm"];
            appTicketId = body["appTicket"]["id"];
            rsvp = body["rsvp"];

            console.log('>>> token: ' + JSON.stringify(token));
            console.log('>>> appTicketExp: ' + JSON.stringify(appTicketExp));
            console.log('>>> appTicketApp: ' + JSON.stringify(appTicketApp));
            console.log('>>> appTicketScope: ' + JSON.stringify(appTicketScope));
            console.log('>>> appTicketKey: ' + JSON.stringify(appTicketKey));
            console.log('>>> appTicketAlgorithm: ' + JSON.stringify(appTicketAlgorithm));
            console.log('>>> appTicketId: ' + JSON.stringify(appTicketId));
            console.log('>>> rsvp: ' + JSON.stringify(rsvp));

            console.log('>>> ----------------------- Generating Auth headers.');

            // start client side auth for GET call
            var credentials = {
                id: appTicketId,
                key: appTicketKey,
                algorithm: appTicketAlgorithm
            }

            var requestOptions = {
                uri: 'https://api-dev.strax.co/api/events',
                method: 'GET',
                headers: {}
            }

            var ext = 0; // No idea

            // Generate auth headers
            const header = hawk.client.header('https://api-dev.strax.co/api/events', 'GET', { credentials: credentials, ext: ext });
            requestOptions.headers.Authorization = header.field;

            // Make GET request using newly generated Hawk auth headers
            request(requestOptions, function (error, response, body) {
                const isValid = hawk.client.authenticate(response, credentials, header.artifacts, { payload: body });
                // Output results
                console.log('>>> ' + `${response.statusCode}: ${body}` + (isValid ? ' (valid)' : ' (invalid)'));
            });
        }
    })
