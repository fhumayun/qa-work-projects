'use strict';

var assert = require('assert'),
    request = require('request'),
    chai = require('chai'),
    chaiHttp = require('chai-http');

module.exports = function() {

    // Variables
    var url = process.env.TESTURL;
    var userCredentials = {
        "username": "jshanahan@eagleeyeintelligence.com",
        "password": "eei"
    };
    var updatedEventInfo = {};
    var accountId;
    var loginToken;
    var clusterData = {};
    var clusterId;
    var updateResponse;
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
                if (err) callback('>>> ' + err);

                callback();
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

        // ---------------------- chai-http -ify this call
        chai.request(url)
            .post('/api/clusters')
            .send(clusterData)
            .end(function(err, res) {
                if (err) callback('>>> ' + err);
                var resText = JSON.parse(res.text);
                clusterId = resText._id;
                //console.log('>>> res.status: ' + JSON.stringify(res.status)); // EXPECT
                //console.log('>>> res.text._id: ' + resText._id); // EXPECT

                callback();
            });

    });

    // Then I should get a cluster ID showing it was created
    this.Then(/^A new event should be created$/, function (callback) {

        if (clusterId) {
            callback();
        }

        callback(new Error('Missing clusterId'));
    });

    /*****************************************
     * Scenario: Update
     *****************************************/

    // Given
    this.Given(/^The PIC has new information$/, function (callback) {

        updatedEventInfo = {
            "description": "updated desc"
        };

        callback();
    });

    // When
    this.When(/^The PIC updates the event$/, function (callback) {

        request(url)
            .put('/api/clusters/' + clusterId)
            .send(updatedEventInfo)
            .expect(200)
            .expect('Content-Type', /json/)
            .end(function(err, res) {
                if (err) {
                    callback(new Error('Error: ' + err));
                }

                if (res && res.body && res.body) {
                    updateResponse = res.body;
                    callback();
                }

                callback(new Error('Missing Response obj'));
            });

    });

    // Then
    this.Then(/^The event should reflect the changes$/, function (callback) {

        if (updateResponse.description == updatedEventInfo.description) {
            callback();
        }

        callback(new Error('Event information not updated'));
    });

    /*****************************************
     * Scenario: Read
     *****************************************/

    // Given
    this.Given(/^The PIC needs to read the event data$/, function (callback) {

        callback();

    });

    // When
    this.When(/^The PIC requests the data$/, function (callback) {

        request(url)
            .get('/api/clusters/' + clusterId)
            .expect(200)
            .expect('Content-Type', /json/)
            .end(function(err, res) {
                if (err) {
                    callback(new Error('Error: ' + err));
                }

                if (res && res.body) {
                    getResponse = res.body;
                    callback();
                }

                callback(new Error('Missing Response obj'));
            });

    });

    // Then
    this.Then(/^The PIC should recieve the event information$/, function (callback) {

        if (getResponse[0].description == updatedEventInfo.description) {
                callback();
        }

        callback(new Error('Did not GET event information'));
    });

    /*****************************************
     * Scenario: Delete
     *****************************************/

    // Given
    this.Given(/^The event is over$/, function (callback) {

        callback();

    });

    // When
    this.When(/^The PIC deletes the event$/, function (callback) {

        request(url)
            .delete('/api/clusters/' + clusterId)
            .send(userCredentials)
            .expect(200)
            .expect('Content-Type', /json/)
            .end(function(err, res) {
                if (err) {
                    callback(new Error('Error: ' + err));
                }

                if (res && res.body && res.body.result) {
                    deleteResponse = res.body.result;
                    callback();
                }

                callback(new Error('Missing Response obj'));
            });

    });

    // Then
    this.Then(/^It should no longer be considered active$/, function (callback) {

        // Is there another way to check?
        if (deleteResponse == true) {
            callback();
        }

        callback(new Error('Event was not deleted'));
    });

};
