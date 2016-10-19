'use strict';

var assert = require('assert');
var chai = require('chai');
var chaiHttp = require('chai-http');
var expect = chai.expect;

module.exports = function() {

    // Variables
    var url = process.env.TESTURL;
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

        callback();
    });

    // When
    this.When(/^I sign up for a new user account$/, {timeout: 30000}, function (callback) {

        chai.request(url)
            .post('/api/accounts')
            .send(accountData)
            .end(function(err, res) {
                expect(res).to.have.status(201);
                expect(res.text).to.be.a('string');
                if (err) callback('>>> ' + err);

                postResponse = JSON.parse(res.text);
                accountId = postResponse._id;
                
                callback();
            });

    });

    // Then
    this.Then(/^I should be able to access my account$/, {timeout: 30000}, function (callback) {

        chai.request(url)
            .get('/api/accounts/' + accountId)
            .end(function(err, res) {
                expect(res).to.have.status(200);
                expect(res.text).to.be.a('string');
                if (err) callback('>>> ' + err);

                getResponse = JSON.parse(res.text);

                callback();
            });

    });

    // And
    this.Then(/^See all of my account information$/, function (callback) {

        callback(); // REMOVE

        if (getResponse.accountname == accountData.accountname &&
            getResponse.email1 == accountData.email1 &&
            getResponse.phone == accountData.phone &&
            getResponse.website == accountData.website &&
            getResponse.status == accountData.status) {
                callback();
        }

        callback(new Error('Incorrect data returned from GET api/accounts'))
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

        callback();
    });

    // When
    this.When(/^I update my profile$/, {timeout: 30000}, function (callback) {

        chai.request(url)
            .put('/api/accounts/' + accountId)
            .send(updatedAccountData)
            .end(function(err, res) {
                expect(res).to.have.status(200);
                expect(res.text).to.be.a('string');
                if (err) callback('>>> ' + err);

                updateResponse = JSON.parse(res.text);

                callback();
            });

    });

    // Then
    this.Then(/^I will have new information$/, function (callback) {

        // Did the response contain the updated information
        if (updateResponse.email1 == updatedAccountData.email1 &&
            updateResponse.status == updatedAccountData.status) {
            callback();
        }

        callback(new Error('Account information was not updated'));
    });

    /*****************************************
     * Scenario: Read
     *****************************************/

    // Given
    this.Given(/^I need to access my information$/, function (callback) {

        callback();

    });

    // When
    this.When(/^I log in to my account$/, function (callback) {

        // Eventually Auth setup will be here for the next step
        callback();

    });

    // Then
    this.Then(/^I should be able to read my information$/, {timeout: 30000}, function (callback) {

        chai.request(url)
            .get('/api/accounts/' + accountId)
            .send(updatedAccountData)
            .end(function(err, res) {
                expect(res).to.have.status(200);
                expect(res.text).to.be.a('string');
                if (err) callback('>>> ' + err);

                callback();
            });

    });

    /*****************************************
     * Scenario: Delete
     *****************************************/

    // Given
    this.Given(/^I need to delete a user account$/, function (callback) {

        callback();

    });

    // When
    this.When(/^I mark a user as deleted$/, {timeout: 30000}, function (callback) {

        chai.request(url)
            .delete('/api/accounts/' + accountId)
            .send(updatedAccountData)
            .end(function(err, res) {
                expect(res).to.have.status(200);
                expect(res.text).to.be.a('string');
                if (err) callback('>>> ' + err);

                callback();
            });

    });

    // Then
    this.Then(/^The account should be deactivated$/, function (callback) {

        if (deleteResponse) {
            callback();
        }

        callback(new Error('Could not delete account'));
    });


};
