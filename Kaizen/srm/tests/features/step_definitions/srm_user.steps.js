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
    var accountData = {};
    var accountId;
    var updatedAccountData = {};
    var postResponse;
    var getResponse;
    var updateResponse;
    var deleteResponse;

    // Make Chai use its own addon for HTTP calls
    chai.use(chaiHttp);

    /*****************************************
     * Scenario: Create
     *****************************************/

    // Given
    this.Given(/^I have all the required information$/, function (callback) {

        accountData = {
            "accountname": "Jshanahan automated test account",
            "email1": "test@test.com",
            "phone": 8675309,
            "website": "ci.strax.co",
            "password": "asdf",
            "status": "SUSPENDED",
            "fidgets": []
        };

        return callback();
    });

    // When
    this.When(/^I sign up for a new user account$/, {timeout: 30000}, function () {

        chai.request(url)
            .post('/api/accounts')
            .send(accountData)
            .end(function(err, res) {
                expect(err).to.be.null;
                expect(res).to.have.status(201);
                expect(res.text).to.be.a('string');

                postResponse = JSON.parse(res.text);
                accountId = postResponse._id;
            });

    });

    // Then
    this.Then(/^I should be able to access my account$/, {timeout: 30000}, function (callback) {

        chai.request(url)
            .get('/api/accounts/' + accountId)
            .end(function(err, res) {
                expect(err).to.be.null;
                expect(res).to.have.status(200);
                expect(res.text).to.be.a('string');

                if (err) {
                    return callback('>>> ' + err);
                } else {
                    getResponse = JSON.parse(res.text);
                    return callback();
                }

            });

    });

    // And
    this.Then(/^See all of my account information$/, function (callback) {

        if (getResponse) {
            return callback();
        } else {
            return callback(new Error('Incorrect data returned from GET api/accounts'))
        }

    });



    /*****************************************
     * Scenario: Update
     *****************************************/

    // Given
    this.Given(/^I need to update my information$/, function (callback) {

        updatedAccountData = {
            "email1": "jshanahan@eagleeyeintelligence.com",
            "status": "ACTIVE"
        };

        return callback();
    });

    // When
    this.When(/^I update my profile$/, {timeout: 30000}, function () {

        chai.request(url)
            .put('/api/accounts/' + accountId)
            .send(updatedAccountData)
            .end(function(err, res) {
                expect(err).to.be.null;
                expect(res).to.have.status(200);
                expect(res.text).to.be.a('string');

                updateResponse = JSON.parse(res.text);

            });

    });

    // Then
    this.Then(/^I will have new information$/, function (callback) {

        // Did the response contain the updated information
        if (updateResponse.email1 == updatedAccountData.email1 &&
            updateResponse.status == updatedAccountData.status) {
            return callback();
        } else {
            return callback(new Error('Account information was not updated'));
        }

    });

    /*****************************************
     * Scenario: Read
     *****************************************/

    // Given
    this.Given(/^I need to access my information$/, function (callback) {

        return callback();

    });

    // When
    this.When(/^I log in to my account$/, function (callback) {

        // Eventually Auth setup will be here for the next step
        return callback();

    });

    // Then
    this.Then(/^I should be able to read my information$/, {timeout: 30000}, function () {

        chai.request(url)
            .get('/api/accounts/' + accountId)
            .send(updatedAccountData)
            .end(function(err, res) {
                expect(err).to.be.null;
                expect(res).to.have.status(200);
                expect(res.text).to.be.a('string');
            });

    });

    /*****************************************
     * Scenario: Delete
     *****************************************/

    // Given
    this.Given(/^I need to delete a user account$/, function (callback) {

        return callback();

    });

    // When
    this.When(/^I mark a user as deleted$/, {timeout: 30000}, function () {

        chai.request(url)
            .delete('/api/accounts/' + accountId)
            .send(updatedAccountData)
            .end(function(err, res) {
                expect(err).to.be.null;
                expect(res).to.have.status(200);
                expect(res.text).to.be.a('string');
            });

    });

    // Then
    this.Then(/^The account should be deactivated$/, function (callback) {

        if (deleteResponse) {
            return callback();
        } else {
            return callback(new Error('Could not delete account'));
        }
    });


};
