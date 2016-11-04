'use strict';

var chai = require('chai');
var chaiHttp = require('chai-http');
var sleep = require('sleep');
var Oz = require('oz');

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

    // Oz Variables
    var authHeader;
    var token;
    var rsvp;
    var appTicket;
    const ID_SERVER = "https://id-dev.strax.co/";
    const VALIDATE = "oz/validate";
    const USERNAME = "jshanahan@eagleeyeintelligence.com";
    const PASSWORD = "eei"

    // Make Chai use its own addon for HTTP calls
    var expect = chai.expect;
    chai.use(chaiHttp);

    /*****************************************
     * Scenario: Create
     *****************************************/

     // Given
    this.Given(/^The Admin is logged in$/, {timeout:30000}, function (done) {

      sleep.sleep(3);

        chai.request(url)
            .post('/api/participant/doAuthenticate')
            .send(userCredentials)
            .then(function(res) {
                expect(res).to.have.status(200);

                var loginRes = JSON.parse(res.text);
                appTicket = loginRes.appTicket;

                return done();
            })
            .catch(function(err) {
                return done(err);
            });
    });

    // And
    this.Given(/^I have all the required participant information$/, function () {

      sleep.usleep(500);

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

    });

    // When
    this.When(/^I create a new participant$/, {timeout: 30000}, function (done) {

      sleep.sleep(3);

        chai.request(url)
            .post('/api/participant')
            .set("Authorization", Oz.client.header(ID_SERVER + VALIDATE, 'POST', appTicket, null).field)
            .send(participantData)
            .then(function(res) {
                expect(res).to.have.status(201);
                expect(res.text).to.be.a('string');
                postResponse = JSON.parse(res.text);
                participantId = postResponse._id;
                return done();
            })
            .catch(function(err) {
                return done(err);
            });

    });

    // Then
    this.Then(/^I should get a creation successful response$/, function () {

      sleep.usleep(500);

        if (!participantId)
            throw new Error('Invalid participant ID after creation');

    });

    /*****************************************
     * Scenario: Update
     *****************************************/

    // Given
    this.Given(/^I have new participant data$/, function () {

      sleep.usleep(500);

        updatedParticipantData = {
            "phoneType": "iPhone"
        };

    });

    // When
    this.When(/^I update the data$/, {timeout: 30000}, function (done) {

      sleep.sleep(3);

        chai.request(url)
            .put('/api/participant/' + participantId)
            .set("Authorization", Oz.client.header(ID_SERVER + VALIDATE, 'POST', appTicket, null).field)
            .send(updatedParticipantData)
            .then(function(res) {
              expect(res).to.have.status(200);
              expect(res.text).to.be.a('string');

              updateResponse = JSON.parse(res.text);
              return done();
            })
            .catch(function(err) {
                return done(err);
            });

    });

    // Then
    this.Then(/^I should get an update successful response$/, function () {

      sleep.usleep(500);

        if (!updateResponse)
            throw new Error('Did not recieve update successful reponse');

    });

    /*****************************************
     * Scenario: Read
     *****************************************/

    // Given
    this.Given(/^I need to look up a participant$/, function () {

      sleep.usleep(500);

        if (!participantId)
            throw new Error('Could not GET: Missing participant id');

    });

    // When
    this.When(/^I look up a participant by id$/, {timeout: 30000}, function (done) {

      sleep.sleep(3);

        chai.request(url)
            .get('/api/participant/' + participantId)
            .set("Authorization", Oz.client.header(ID_SERVER + VALIDATE, 'POST', appTicket, null).field)
            .then(function(res) {
              expect(res).to.have.status(200);
              expect(res.text).to.be.a('string');

              getResponse = JSON.parse(res.text);
              return done();
            })
            .catch(function(err) {
                return done(err);
            })

    });

    // Then
    this.Then(/^I should get the participant profile back$/, function () {

      sleep.usleep(500);

        if (getResponse[0]._id != participantId)
            throw new Error('Could not GET participant profile');

    });

    /*****************************************
     * Scenario: Delete
     *****************************************/

    // Given
    this.Given(/^I need to delete a participant and I have the id$/, function () {

      sleep.usleep(500);

      if (!participantId)
          throw new Error('Could not DELETE: Missing participant id');

    });

    // When
    this.When(/^I delete a participant$/, {timeout: 30000}, function (done) {

      sleep.sleep(3);

        chai.request(url)
            .delete('/api/participant/' + participantId)
            .set("Authorization", Oz.client.header(ID_SERVER + VALIDATE, 'POST', appTicket, null).field)
            .then(function(res) {
              expect(res).to.have.status(200);
              expect(res.text).to.be.a('string');
              deleteResponse = JSON.parse(res.text);
              return done();
            })
            .catch(function(err) {
                return done(err);
            });

    });

    // Then
    this.Then(/^I should get a deletion successful response$/, function () {

      sleep.usleep(500);

        if (!deleteResponse)
            throw new Error('Could not successfully DELETE participant');

    });

};
