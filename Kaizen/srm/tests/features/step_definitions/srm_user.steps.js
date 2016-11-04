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
    var accountData = {};
    var accountId;
    var updatedAccountData = {};
    var getResponse;
    var updateResponse;
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
    this.Given(/^I have all the required information$/, function () {

      sleep.usleep(500);

        accountData = {
            "accountname": "Jshanahan automated test account",
            "email1": "test@test.com",
            "phone": 8675309,
            "website": "ci.strax.co",
            "password": "asdf",
            "status": "SUSPENDED",
            "fidgets": []
        };

    });

    // When
    this.When(/^I sign up for a new user account$/, {timeout: 30000}, function (done) {

      sleep.sleep(3);

        chai.request(url)
            .post('/api/accounts')
            .send(accountData)
            .then(function(res) {
                expect(res).to.have.status(201);
                expect(res.text).to.be.a('string');
                var postRes = JSON.parse(res.text);
                accountId = postRes._id;
                return done();
            })
            .catch(function(err) {
                return done(err);
            })

    });

    // Then
    this.Then(/^I should be able to access my account$/, {timeout: 30000}, function (done) {

      sleep.sleep(3);

        chai.request(url)
            .get('/api/accounts/' + accountId)
            .then(function(res) {
                expect(res).to.have.status(200);
                expect(res.text).to.be.a('string');
                return done();
            })
            .catch(function(err) {
                return done(err);
            })

    });

    // And
    this.Then(/^See all of my account information$/, function () {

      sleep.usleep(500);

        if (!getResponse)
            throw new Error('Incorrect data returned from GET api/accounts');

    });



    /*****************************************
     * Scenario: Update
     *****************************************/

    // Given
    this.Given(/^I need to update my information$/, function () {

      sleep.usleep(500);

        updatedAccountData = {
            "email1": "jshanahan@eagleeyeintelligence.com",
            "status": "ACTIVE"
        };

    });

    // When
    this.When(/^I update my profile$/, {timeout: 30000}, function (done) {

      sleep.sleep(3);

        chai.request(url)
            .put('/api/accounts/' + accountId)
            .send(updatedAccountData)
            .then(function(res) {
                expect(res).to.have.status(200);
                expect(res.text).to.be.a('string');
                updateResponse = JSON.parse(res.text);
                return done();
            })
            .catch(function(err) {
                return done(err);
            })

    });

    // Then
    this.Then(/^I will have new information$/, function () {

      sleep.usleep(500);

        // Did the response contain the updated information
        if (updateResponse.email1 != updatedAccountData.email1 ||
            updateResponse.status != updatedAccountData.status)
            throw new Error('Account information was not updated');

    });

    /*****************************************
     * Scenario: Read
     *****************************************/

    // Given
    this.Given(/^I need to access my information$/, function () {

      sleep.usleep(500);
        // ok

    });

    // When
    this.When(/^I log in to my account$/, function () {

      sleep.usleep(500);
        // Eventually Auth setup will be here for the next step

    });

    // Then
    this.Then(/^I should be able to read my information$/, {timeout: 30000}, function (done) {

      sleep.sleep(3);

        chai.request(url)
            .get('/api/accounts/' + accountId)
            .send(updatedAccountData)
            .then(function(res) {
                expect(res).to.have.status(200);
                expect(res.text).to.be.a('string');
                return done();
            })
            .catch(function(err) {
                return done(err);
            })

    });

    /*****************************************
     * Scenario: Delete
     *****************************************/

    // Given
    this.Given(/^I need to delete a user account$/, function () {

        sleep.usleep(500);
        // ok

    });

    // When
    this.When(/^I mark a user as deleted$/, {timeout: 30000}, function (done) {

      sleep.sleep(3);

        chai.request(url)
            .delete('/api/accounts/' + accountId)
            .send(updatedAccountData)
            .then(function(res) {
                expect(res).to.have.status(200);
                expect(res.text).to.be.a('string');
                return done();
            })
            .catch(function(err) {
                return done(err);
            })

    });

    // Then
    this.Then(/^The account should be deactivated$/, function () {

      sleep.usleep(500);
        if (!deleteResponse)
            throw new Error('Could not delete account');

    });


};
