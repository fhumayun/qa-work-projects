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
    var accountData = {};
    var accountId;
    var updatedAccountData = {};
    var updateResponse;

    // Oz Variables
    var appTicket;
    const ID_SERVER = "https://uat-id.strax.co/";
    const VALIDATE = "oz/validate";
    var AUTHORIZATION;

    // Make Chai use its own addon for HTTP calls
    var expect = chai.expect;
    chai.use(chaiHttp);

    /*****************************************
     * Scenario: Create
     *****************************************/

    // Given
    this.Given(/^The Admin is logged in and has all the required user information$/, function () {

        accountData = {
            "accountname": "Jshanahan automated test account",
            "email1": "test@test.com",
            "phone": 8675309,
            "website": "ci.strax.co",
            "password": "asdf",
            "status": "SUSPENDED",
            "fidgets": []
        };

        return chai.request(url)
            .post('/api/participant/doAuthenticate')
            .send(userCredentials)
            .then(function(res) {
                expect(res).to.have.status(200);

                var loginRes = JSON.parse(res.text);
                appTicket = loginRes.appTicket;

                AUTHORIZATION = Oz.client.header(ID_SERVER + VALIDATE, 'POST', appTicket, null).field;
            })
            .catch(function(err) {
                throw err;
            });

    });

    // When
    this.When(/^I create a new user account$/, function () {

        return chai.request(url)
            .post('/api/accounts')
            .set("Authorization", AUTHORIZATION)
            .send(accountData)
            .then(function(res) {
                expect(res).to.have.status(201);
                expect(res.text).to.be.a('string');
                var postRes = JSON.parse(res.text);
                accountId = postRes._id;
            })
            .catch(function(err) {
                throw err;
            })

    });

    // Then
    this.Then(/^I should recieve an Account Created message$/, function () {

        // ok

    });


    /*****************************************
     * Scenario: Update
     *****************************************/

    // Given
    this.Given(/^I need to update my information$/, function () {

        updatedAccountData = {
            "email1": "jshanahan@eagleeyeintelligence.com",
            "status": "ACTIVE"
        };

    });

    // When
    this.When(/^I update my profile$/, function () {

        return chai.request(url)
            .put('/api/accounts/' + accountId)
            .set("Authorization", AUTHORIZATION)
            .send(updatedAccountData)
            .then(function(res) {
                expect(res).to.have.status(200);
                expect(res.text).to.be.a('string');
                updateResponse = JSON.parse(res.text);
            })
            .catch(function(err) {
                throw err;
            })

    });

    // Then
    this.Then(/^I will have new information$/, function () {

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

        // ok

    });

    // When
    this.When(/^I log in to my account$/, function () {

        // ok

    });

    // Then
    this.Then(/^I should be able to read my information$/, function () {

        return chai.request(url)
            .get('/api/accounts/' + accountId)
            .set("Authorization", AUTHORIZATION)
            .send(updatedAccountData)
            .then(function(res) {
                expect(res).to.have.status(200);
                expect(res.text).to.be.a('string');
            })
            .catch(function(err) {
                throw err;
            })

    });

    /*****************************************
     * Scenario: Delete
     *****************************************/

    // Given
    this.Given(/^I need to delete a user account$/, function () {

        // ok

    });

    // When
    this.When(/^I mark a user as deleted$/, function () {

        return chai.request(url)
            .delete('/api/accounts/' + accountId)
            .set("Authorization", AUTHORIZATION)
            .send(updatedAccountData)
            .then(function(res) {
                expect(res).to.have.status(200);
                expect(res.text).to.be.a('string');
            })
            .catch(function(err) {
                throw err;
            })

    });

    // Then
    this.Then(/^The account should be deactivated$/, function () {

        // ok

    });


};
