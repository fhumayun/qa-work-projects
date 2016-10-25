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
    var participantData = {};
    var updatedParticipantData = {};
    var participantId;
    var postResponse;
    var updateResponse;
    var getResponse;
    var deleteResponse;

    // Make Chai use its own addon for HTTP calls
    chai.use(chaiHttp);

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

        return callback();
    });

    // When
    this.When(/^I create a new participant$/, {timeout: 30000}, function (callback) {

        chai.request(url)
            .post('/api/participant')
            .send(participantData)
            .end(function(err, res) {
                expect(res).to.have.status(201);
                expect(res.text).to.be.a('string');

                postResponse = JSON.parse(res.text);
                participantId = postResponse._id;

                if (err) return callback('>>> ' + err);
                else callback();
            });

    });

    // Then
    this.Then(/^I should get a creation successful response$/, function (callback) {

        if (participantId) {
            return callback();
        } else {
            callback(new Error('Invalid participant ID after creation'));
        }

    });

    /*****************************************
     * Scenario: Update
     *****************************************/

    // Given
    this.Given(/^I have new participant data$/, function (callback) {

        updatedParticipantData = {
            "phoneType": "iPhone"
        };

        return callback();
    });

    // When
    this.When(/^I update the data$/, {timeout: 30000}, function (callback) {

        chai.request(url)
            .put('/api/participant/' + participantId)
            .send(updatedParticipantData)
            .end(function(err, res) {
              expect(res).to.have.status(200);
              expect(res.text).to.be.a('string');

              updateResponse = JSON.parse(res.text);

              if (err) return callback('>>> ' + err);
              else callback();
            });

    });

    // Then
    this.Then(/^I should get an update successful response$/, function (callback) {

        if (updateResponse) {
            return callback();
        } else {
            callback(new Error('Did not recieve update successful reponse'));
        }
    });

    /*****************************************
     * Scenario: Read
     *****************************************/

    // Given
    this.Given(/^I need to look up a participant$/, function (callback) {

        if (participantId) {
            return callback();
        } else {
            callback(new Error('Could not GET: Missing participant id'));
        }
    });

    // When
    this.When(/^I look up a participant by id$/, {timeout: 30000}, function (callback) {

        chai.request(url)
            .get('/api/participant/' + participantId)
            .end(function(err, res) {
              expect(res).to.have.status(200);
              expect(res.text).to.be.a('string');

              getResponse = JSON.parse(res.text);

              if (err) return callback('>>> ' + err);
              else callback();
            });

    });

    // Then
    this.Then(/^I should get the participant profile back$/, function (callback) {

        if (getResponse[0]._id == participantId) {
            return callback();
        } else {
            callback(new Error('Could not GET participant profile'));
        }
    });

    /*****************************************
     * Scenario: Delete
     *****************************************/

    // Given
    this.Given(/^I need to delete a participant and I have the id$/, function (callback) {

        if (participantId) {
            return callback();
        } else {
            callback(new Error('Could not DELETE: Missing participant id'));
        }

    });

    // When
    this.When(/^I delete a participant$/, {timeout: 30000}, function (callback) {

        chai.request(url)
            .delete('/api/participant/' + participantId)
            .end(function(err, res) {
              expect(res).to.have.status(200);
              expect(res.text).to.be.a('string');

              deleteResponse = JSON.parse(res.text);

              if (err) return callback('>>> ' + err);
              else callback();
            });

    });

    // Then
    this.Then(/^I should get a deletion successful response$/, function (callback) {

        if (deleteResponse) {
            return callback();
        } else {
            callback(new Error('Could not successfully DELETE participant'));
        }

    });

};
