'use strict';

var assert = require('assert'),
    request = require('supertest');

module.exports = function() {

    // Variables
    var url = process.env.TESTURL;
    var accountData = {};
    var accountId;
    var updatedAccountData = {};
    var getResponse;
    var updateResponse;
    var deleteResponse;


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
    this.When(/^I sign up for a new user account$/, function (callback) {

        request(url)
            .post('/api/accounts')
            .send(accountData)
            .expect(201)
            .expect('Content-Type', /json/)
            .end(function(err, res) {
                if (err) {
                    callback(new Error('Error: ' + err));
                }

                if (res && res.body && res.body._id) {
                    accountId = res.body._id;
                    callback();
                }

                callback(new Error('Missing Response obj'));
            });

    });

    // Then
    this.Then(/^I should be able to access my account$/, function (callback) {

        request(url)
            .get('/api/accounts/' + accountId)
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

    // And
    this.Then(/^See all of my account information$/, function (callback) {

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
    this.When(/^I update my profile$/, function (callback) {

        request(url)
            .put('/api/accounts/' + accountId)
            .send(updatedAccountData)
            .expect(200)
            .expect('Content-Type', /json/)
            .end(function(err, res) {
                if (err) {
                    callback(new Error('Error: ' + err));
                }

                // Did we get a correctly structured response back
                if (res && res.body) {
                    updateResponse = res.body;
                    callback();
                }

                callback(new Error('Missing Response obj'));
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
    this.Then(/^I should be able to read my information$/, function (callback) {

        request(url)
            .get('/api/accounts/' + accountId)
            .send(updatedAccountData)
            .expect(200)
            .expect('Content-Type', /json/)
            .end(function(err, res) {
                if (err) {
                    callback(new Error('Error: ' + err));
                }

                // Did we get a correctly structured response back
                if (res && res.body && res.body._id) {
                    callback();
                }

                callback(new Error('Missing Response obj. Could not GET profile'));
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
    this.When(/^I mark a user as deleted$/, function (callback) {

        request(url)
            .delete('/api/accounts/' + accountId)
            .send(updatedAccountData)
            .expect(200)
            .expect('Content-Type', /json/)
            .end(function(err, res) {
                if (err) {
                    callback(new Error('Error: ' + err));
                }

                // Did we get a correctly structured response back
                if (res && res.body && res.body.result) {
                    deleteResponse = res.body.result;
                    callback();
                }

                callback(new Error('Missing Response obj. Could not GET profile'));
            });

    });

    // Then
    this.Then(/^The account should be deactivated$/, function (callback) {

        if (deleteResponse == true) {
            callback();
        }

        callback(new Error('Could not delete account'));
    });


};
