'use strict';

var assert = require('assert'),
    request = require('supertest');

module.exports = function() {

    // Variables
    var url = process.env.TESTURL + ":" + process.env.TESTPORT;
    var participantData = {};
    var updatedParticipantData = {};
    var participantId;
    var updateResponse;
    var getResponse;
    var deleteResponse;

    /*****************************************
     * Scenario: Create
     *****************************************/

    // Given
    this.Given(/^I have all the required participant information$/, function (callback) {

        participantData = {
            "loginId" : "qa@qa.qa",
            "firstName" : "Quality",
            "lastName" : "Assurance",
            "title" : "Your Highness",
            "accessLevel" : "3",
            "accountDocId" : "000000000000000000000099",
            "password" : "$2a$13$P7tKirx7bjlCFuYUeLcvO.8JAuJPT/KfcQpXnqPlYzdWHOe/8H.H6",
            "fidgets" : [],
            "status" : true,
            "createdOn" : "2016-05-10T17:31:04.346Z",
            "isLocShared" : true,
            "isOnline" : false,
            "secondaryAddress" : {
                "verified" : false
            },
            "contactAddress" : {
                "verified" : false
            },
            "dutyStatus" : true,
            "vertical" : "safetrax",
            "roles" : [
                "PRIMARY"
            ],
            "mobilePhone" : "5618675309",
            "carrier" : "Tmobile",
            "phoneType" : "Android",
            "phoneId" : "b6e20f21 c01ec0fa f17fc437 a6a30847 a50abd1e c2a4cbb7 9a28fb87 b938732a",
            "color" : "#FF0000",
            "__v" : 0.0
        };

        callback();
    });

    // When
    this.When(/^I create a new participant$/, function (callback) {

        request(url)
            .post('/api/participant')
            .send(participantData)
            .expect(201)
            .expect('Content-Type', /json/)
            .end(function(err, res) {
                if (err) {
                    callback(new Error('Not created - Does this Participant already exist?: ' + err));
                }

                if (res && res.body && res.body._id) {
                    participantId = res.body._id;
                    callback();
                }

                callback(new Error('Missing Response obj'));
            });

    });

    // Then
    this.Then(/^I should get a creation successful response$/, function (callback) {

        if (participantId != null) {
            callback();
        }

        callback(new Error('Invalid participant ID after creation'));
    });

    /*****************************************
     * Scenario: Update
     *****************************************/

    // Given
    this.Given(/^I have new participant data$/, function (callback) {

        updatedParticipantData = {
            "phoneType": "iPhone"
        };

        callback();
    });

    // When
    this.When(/^I update the data$/, function (callback) {

        request(url)
            .put('/api/participant/' + participantId)
            .send(updatedParticipantData)
            .expect(200)
            .expect('Content-Type', /json/)
            .end(function(err, res) {
                if (err) {
                    callback(new Error('Participant not updated: ' + err));
                }

                if (res && res.body && res.body.result) {
                    updateResponse = res.body.result;
                    callback();
                }

                callback(new Error('Missing Response obj'));
            });

    });

    // Then
    this.Then(/^I should get an update successful response$/, function (callback) {

        if (updateResponse == true) {
            callback();
        }

        callback(new Error('Did not recieve update successful reponse'));
    });

    /*****************************************
     * Scenario: Read
     *****************************************/

    // Given
    this.Given(/^I need to look up a participant$/, function (callback) {

        if (participantId) {
            callback();
        }

        callback(new Error('Missing participant id'));
    });

    // When
    this.When(/^I look up a participant by id$/, function (callback) {

        request(url)
            .get('/api/participant/' + participantId)
            .expect(200)
            .expect('Content-Type', /json/)
            .end(function(err, res) {
                if (err) {
                    callback(new Error('Could not GET participant' + err));
                }

                if (res && res.body) {
                    getResponse = res.body;
                    callback();
                }

                callback(new Error('Missing Response obj'));
            });

    });

    // Then
    this.Then(/^I should get the participant profile back$/, function (callback) {

        if (getResponse[0]._id == participantId) {
            callback();
        }

        callback(new Error('Could not GET participant profile'));
    });

    /*****************************************
     * Scenario: Delete
     *****************************************/

    // Given
    this.Given(/^I need to delete a participant and I have the id$/, function (callback) {
        if (participantId) {
            callback();
        }

        callback(new Error('Missing participant id'));
    });

    // When
    this.When(/^I delete a participant$/, function (callback) {

        request(url)
            .delete('/api/participant/' + participantId)
            .expect(200)
            .expect('Content-Type', /json/)
            .end(function(err, res) {
                if (err) {
                    callback(new Error('Could not GET participant' + err));
                }

                if (res && res.body) {
                    deleteResponse = res.body;
                    callback();
                }

                callback(new Error('Missing Response obj'));
            });

    });

    // Then
    this.Then(/^I should get a deletion successful response$/, function (callback) {
        if (deleteResponse.result == true) {
            callback();
        }

        callback(new Error('Could not successfully DELETE participant'));
    });

};
