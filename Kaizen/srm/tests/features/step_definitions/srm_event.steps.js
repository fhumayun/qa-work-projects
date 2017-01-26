'use strict';

var chai = require('chai');
var chaiHttp = require('chai-http');
var sleep = require('sleep');
var Oz = require('oz');

module.exports = function() {

    // Variables
    var url = process.env.TESTURL || "https://uat-api.strax.co";
    var userCredentials = {
        "username": "john@ee.io",
        "password": "eei"
    };
    var newEventInfo = {};
    var accountId;
    var loginToken;
    var clusterData = {};
    var clusterId;
    var updatedResponse;
    var getResponse;
    var deleteResponse;

    // Oz Variables
    var authHeader;
    var token;
    var rsvp;
    var appTicket;
    const ID_SERVER = "https://uat-id.strax.co/";
    const VALIDATE = "oz/validate";
    const USERNAME = "john@ee.io";
    const PASSWORD = "eei";

    // Make Chai use its own addon for HTTP calls
    var expect = chai.expect;
    chai.use(chaiHttp);

    /*****************************************
     * Scenario: Create
     *****************************************/

    // Given
    this.Given(/^The PIC is logged in$/, {timeout: 30000}, function () {

        return chai.request(url)
            .post('/api/participant/doAuthenticate')
            .send(userCredentials)
            .then(function(res) {
                expect(res).to.have.status(200);

                var loginRes = JSON.parse(res.text);
                appTicket = loginRes.appTicket;
            })
            .catch(function(err) {
                throw err;
            });

    });

    // When I assign participants
    this.When(/^The PIC assigns team members$/, function () {

        return clusterData.participants = [
            {
                "participantDocId": "5730f7237364d60e002d0e0f"
            },
            {
                "participantDocId": "5730fc667364d60e002d0e12"
            }
        ];

    });

    // And assign a drone
    this.When(/^The PIC assigns a UAS$/, function () {

        return clusterData.fidgets = [
            [
                "5735d5f5b651fcb00042fc4c"
            ]
        ];

    });

    // And create the cluster/event
    this.When(/^The PIC creates a new event$/, {timeout: 30000}, function () {

        clusterData.referenceId = 'TESTCLUSTER'+(new Date).getTime();
        clusterData.accountDocId = accountId;

        return chai.request(url)
            .post('/api/clusters')
            .set("Authorization", Oz.client.header(ID_SERVER + VALIDATE, 'POST', appTicket, null).field)
            .send(clusterData)
            .then(function(res) {
                expect(res).to.have.status(201);

                var resText = JSON.parse(res.text);
                clusterId = resText._id.replace(/\W/g, '');
            })
            .catch(function(err) {
                throw err;
            });

    });

    // Then I should get a cluster ID showing it was created
    this.Then(/^A new event should be created$/, function () {

        if (!clusterId)
             throw new Error('Missing clusterId');

    });

    /*****************************************
     * Scenario: Update
     *****************************************/

    // Given
    this.Given(/^The PIC has new information$/, function () {

        return newEventInfo = {
            "description": "updated desc"
        };

    });

    // When
    this.When(/^The PIC updates the event$/, {timeout: 30000}, function () {

        return chai.request(url)
            .put('/api/clusters/' + clusterId)
            .set("Authorization", Oz.client.header(ID_SERVER + VALIDATE, 'POST', appTicket, null).field)
            .send(newEventInfo)
            .then(function(res) {
                expect(res).to.have.status(200);
                updatedResponse = JSON.parse(res.text);
            })
            .catch(function(err) {
                throw err;
            });

    });

    // Then
    this.Then(/^The event should reflect the changes$/, function () {

        if (updatedResponse.description != newEventInfo.description)
            throw new Error('Event information not updated');

    });

    /*****************************************
     * Scenario: Read
     *****************************************/

    // Given
    this.Given(/^The PIC needs to read the event data$/, function () {

      // ok

    });

    // When
    this.When(/^The PIC requests the data$/, {timeout: 30000}, function () {

        return chai.request(url)
            .get('/api/clusters/' + clusterId)
            .set("Authorization", Oz.client.header(ID_SERVER + VALIDATE, 'POST', appTicket, null).field)
            .then(function(res) {
                expect(res).to.have.status(200);
                expect(res.text).to.be.text;
                var getResponse = JSON.parse(res.text);
            })
            .catch(function(err) {
                throw err;
            });

    });

    // Then
    this.Then(/^The PIC should receive the event information$/, function () {

      // ok

    });

    /*****************************************
     * Scenario: Delete
     *****************************************/

    // Given
    this.Given(/^The event is over$/, function () {

      // ok

    });

    // When
    this.When(/^The PIC deletes the event$/, {timeout: 30000}, function () {

        return chai.request(url)
            .del('/api/clusters/' + clusterId)
            .set("Authorization", Oz.client.header(ID_SERVER + VALIDATE, 'POST', appTicket, null).field)
            .then(function(res) {
                expect(res).to.have.status(200);
            })
            .catch(function(err){
                throw err;
            })

    });

    // Then
    this.Then(/^It should no longer be considered active$/, function () {

      // ok

    });

};
