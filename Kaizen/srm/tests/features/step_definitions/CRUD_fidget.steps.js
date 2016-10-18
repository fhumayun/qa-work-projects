'use strict';

var assert = require('assert')
var request = require('supertest');
var chai = require('chai');
var chaiHttp = require('chai-http');
var expect = chai.expect;

module.exports = function() {

    // Variables
    var url = process.env.TESTURL;
    var fidgetData = {};
    var updatedFidgetData = {};
    var fidgetId;
    var updateResponse;
    var getResponse;
    var deleteResponse;

    // Make Chai use its own addon for HTTP calls
    chai.use(chaiHttp);

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
    this.When(/^I create a new fidget$/, {timeout: 30000}, function (callback) {

        chai.request(url)
            .post('/api/fidgets')
            .send(fidgetData)
            .end(function(err, res) {
                expect(res).to.have.status(201);
                if (err) callback('>>> ' + err);
                var postRes = JSON.parse(res.text);
                fidgetId = JSON.stringify(postRes._id).replace(/\W/g, '');

                callback();
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
    this.When(/^I update the Fidget$/, {timeout: 30000}, function (callback) {

        chai.request(url)
            .put('/api/fidgets/' + fidgetId)
            .send(updatedFidgetData)
            .end(function(err, res) {
                expect(res).to.have.status(200);
                if (err) callback('>>> ' + err);

                callback();
            });

    });

    // Then
    this.Then(/^I should see the updated Fidget$/, function (callback) {

        if (updateResponse.length > 0) {
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
    this.When(/^I look the Fidget up$/, {timeout: 30000}, function (callback) {

        chai.request(url)
            .get('/api/fidgets/' + fidgetId)
            .end(function(err, res) {
                expect(res).to.have.status(200);
                expect(res.text).to.be.a('string');
                getResponse = JSON.parse(res.text);

                if (err) callback('>>> ' + err);

                callback();
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
    this.When(/^I delete the Fidget$/, {timeout: 30000}, function (callback) {

        console.log('>>> DEL url: ' + url + '/api/fidgets/' + fidgetId);
        chai.request(url)
            .del('/api/fidgets/' + fidgetId)
            .end(function(err, res) {
                expect(res).to.have.status(200);
                expect(res.text).to.be.a('string');
                deleteResponse = JSON.parse(res.text);

                if (err) callback('>>> ' + err);

                callback();
            });

    });

    // Then
    this.Then(/^I should no longer be able to use the Fidget$/, function (callback) {

        if (deleteResponse.length > 0) {
            callback();
        }

        callback(new Error('Could not DELETE Fidget'));
    });

};
