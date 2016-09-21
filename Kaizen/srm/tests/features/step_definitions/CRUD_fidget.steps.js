'use strict';

var assert = require('assert'),
    request = require('supertest');

module.exports = function() {

    // Variables
    var url = process.env.TESTURL + ":" + process.env.TESTPORT;
    var fidgetData = {};
    var updatedFidgetData = {};
    var fidgetId;
    var updateResponse;
    var getResponse;
    var deleteResponse;


    /*****************************************
     * Scenario: Create
     *****************************************/

    // Given
    this.Given(/^I have all the required fidget information$/, function (callback) {

        fidgetData = {
            "profile" : "5741f57f56d61f0d0074925e",
            "type" : "safetrax - update me",
            "feedId" : "5748ad74870c41330225038c",
            "accountDocId" : "000000000000000000000002",
            "gears" : [],
            "zones" : [],
            "vehicleInfo" : [],
            "photo" : [],
            "__v" : 0
        };

        callback();
    });

    // When
    this.When(/^I create a new fidget$/, function (callback) {

        request(url)
            .post('/api/fidgets')
            .send(fidgetData)
            .expect(201)
            .expect('Content-Type', /json/)
            .end(function(err, res) {
                if (err) {
                    callback(new Error('Not created' + err));
                }

                if (res && res.body && res.body._id) {
                    fidgetId = res.body._id;
                    callback();
                }

                callback(new Error('Missing Response obj'));
            });

    });

    // Then
    this.Then(/^I should get a fidget creation successful response$/, function (callback) {

        if (fidgetId.length > 0) {
            callback();
        }

        callback(new Error('Fidget could not be created'));
    });

    /*****************************************
     * Scenario: Update
     *****************************************/

    // Given
    this.Given(/^I have new Fidget information$/, function (callback) {
        updatedFidgetData = {
            "type": "eagleeye"
        };

        callback();
    });

    // When
    this.When(/^I update the Fidget$/, function (callback) {

        request(url)
            .put('/api/fidgets/' + fidgetId)
            .send(updatedFidgetData)
            .expect(200)
            .expect('Content-Type', /json/)
            .end(function(err, res) {
                if (err) {
                    callback(new Error('Not updated' + err));
                }

                if (res && res.body && res.body.result) {
                    updateResponse = res.body.result;
                    callback();
                }

                callback(new Error('Missing Response obj'));
            });

    });

    // Then
    this.Then(/^I should see the updated Fidget$/, function (callback) {

        if (updateResponse == true) {
            callback();
        }

        callback(new Error('Fidget type was not updated'));
    });

    /*****************************************
     * Scenario: Read
     *****************************************/

    // Given
    this.Given(/^I need to look up a Fidget and have the id$/, function (callback) {

        if (fidgetId) {
            callback();
        }

        callback(new Error('Missing Fidget id. Could not GET.'));
    });

    // When
    this.When(/^I look the Fidget up$/, function (callback) {

        request(url)
            .get('/api/fidgets/' + fidgetId)
            .expect(200)
            .expect('Content-Type', /json/)
            .end(function(err, res) {
                if (err) {
                    callback(new Error('Not created' + err));
                }

                if (res && res.body && res.body) {
                    getResponse = res.body;
                    callback();
                }

                callback(new Error('Missing Response obj'));
            });

    });

    // Then
    this.Then(/^I should get the Fidget profile back$/, function (callback) {

        // Validate fidget data
        if (getResponse[0]._id == fidgetId) {
            callback();
        }

        callback(new Error('Could not GET Fidget profile'));
    });

    /*****************************************
     * Scenario: Delete
     *****************************************/

    // Given
    this.Given(/^I need to delete a decommissioned Fidget and have the id$/, function (callback) {

        if (fidgetId) {
            callback();
        }

        callback(new Error('Missing Fidget id. Could not DELETE.'));
    });

    // When
    this.When(/^I delete the Fidget$/, function (callback) {

        request(url)
            .delete('/api/fidgets/' + fidgetId)
            .expect(200)
            .expect('Content-Type', /json/)
            .end(function(err, res) {
                if (err) {
                    callback(new Error('Not deleted' + err));
                }

                if (res && res.body && res.body) {
                    deleteResponse = res.body;
                    callback();
                }

                callback(new Error('Missing Response obj'));
            });

    });

    // Then
    this.Then(/^I should no longer be able to use the Fidget$/, function (callback) {

        if (deleteResponse.result == true) {
            callback();
        }

        callback(new Error('Could not DELETE Fidget'));
    });

};
