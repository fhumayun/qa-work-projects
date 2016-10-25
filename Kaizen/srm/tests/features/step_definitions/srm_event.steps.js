'use strict';

var chai = require('chai');
var chaiHttp = require('chai-http');
var expect = chai.expect;

module.exports = function() {

    // Variables
    var url = process.env.TESTURL;
    var userCredentials = {
        "username": "jshanahan@eagleeyeintelligence.com",
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

    // Make Chai use its own addon for HTTP calls
    chai.use(chaiHttp);

    /*****************************************
     * Scenario: Create
     *****************************************/

    // Given
    this.Given(/^The PIC is logged in$/, {timeout: 30000}, function (callback) {

        chai.request(url)
            .post('/api/participant/doAuthenticate')
            .send(userCredentials)
            .end(function(err, res) {
                expect(res).to.have.status(200);
                expect(res.text).to.be.a('string');

                if (err) return callback('>>> ' + err);
                else callback();
            });

    });

    // When I assign participants
    this.When(/^The PIC assigns team members$/, function (callback) {

        clusterData.participants = [
            {
                "participantDocId": "5730f7237364d60e002d0e0f"
            },
            {
                "participantDocId": "5730fc667364d60e002d0e12"
            }
        ];

        callback();
    });

    // And assign a drone
    this.When(/^The PIC assigns a UAS$/, function (callback) {

        clusterData.fidgets = [
            [
                "5735d5f5b651fcb00042fc4c"
            ]
        ];

        callback();
    });

    // And create the cluster/event
    this.When(/^The PIC creates a new event$/, {timeout: 30000}, function (callback) {

        clusterData.referenceId = 'TESTCLUSTER'+(new Date).getTime();
        clusterData.accountDocId = accountId;

        chai.request(url)
            .post('/api/clusters')
            .send(clusterData)
            .end(function(err, res) {
                expect(res).to.have.status(201);
                expect(res.text).to.be.a('string');

                var resText = JSON.parse(res.text);
                clusterId = resText._id.replace(/\W/g, '');

                if (err) return callback('>>> ' + err);
                else callback();
            });

    });

    // Then I should get a cluster ID showing it was created
    this.Then(/^A new event should be created$/, function (callback) {

        if (clusterId) {
            return callback();
        } else {
             return callback(new Error('Missing clusterId'));
        }

    });

    /*****************************************
     * Scenario: Update
     *****************************************/

    // Given
    this.Given(/^The PIC has new information$/, function (callback) {

        newEventInfo = {
            "description": "updated desc"
        };

        callback();
    });

    // When
    this.When(/^The PIC updates the event$/, {timeout: 30000}, function (callback) {

        chai.request(url)
            .put('/api/clusters/' + clusterId)
            .send(newEventInfo)
            .end(function(err, res) {
                expect(res).to.have.status(200);
                expect(res.text).to.be.a('string');

                updatedResponse = JSON.parse(res.text);

                if (err) return callback('>>> ' + err);
                else callback();
            });

    });

    // Then
    this.Then(/^The event should reflect the changes$/, function (callback) {

        if (updatedResponse.description == newEventInfo.description) {
            return callback();
        } else {
            return callback(new Error('Event information not updated'));
        }

    });

    /*****************************************
     * Scenario: Read
     *****************************************/

    // Given
    this.Given(/^The PIC needs to read the event data$/, function (callback) {

        callback();

    });

    // When
    this.When(/^The PIC requests the data$/, {timeout: 30000}, function (callback) {

        chai.request(url)
            .get('/api/clusters/' + clusterId)
            .end(function(err, res) {
                expect(res).to.have.status(200);
                expect(res.text).to.be.a('string');

                var getResponse = JSON.parse(res.text);

                if (err) return callback('>>> ' + err);
                else callback();
            });

    });

    // Then
    this.Then(/^The PIC should receive the event information$/, function (callback) {

        if (getResponse) {
            return callback();
        } else {
            return callback(new Error('Did not GET event information'));
        }

    });

    /*****************************************
     * Scenario: Delete
     *****************************************/

    // Given
    this.Given(/^The event is over$/, function (callback) {

        callback();

    });

    // When
    this.When(/^The PIC deletes the event$/, {timeout: 30000}, function (callback) {

        chai.request(url)
            .del('/api/clusters/' + clusterId)
            .send(userCredentials)
            .end(function(err, res) {
                expect(err).to.be.null;
                expect(res).to.have.status(400);
                expect(res.text).to.be.a('string');

                var deleteResponse = JSON.parse(res.text);
            });
    });

    // Then
    this.Then(/^It should no longer be considered active$/, function (callback) {

        if (deleteResponse) {
            return callback();
        } else {
            return callback(new Error('Event was not deleted'));
        }

    });

};
