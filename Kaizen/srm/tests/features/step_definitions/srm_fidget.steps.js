'use strict';

var chai = require('chai');
var chaiHttp = require('chai-http');
var sleep = require('sleep');

module.exports = function() {

    // Variables
    var url = process.env.TESTURL;
    var userCredentials = {
        "username": "jshanahan@eagleeyeintelligence.com",
        "password": "eei"
    };
    var fidgetData = {};
    var updatedFidgetData = {};
    var fidgetId;
    var updateResponse;
    var getResponse;
    var deleteResponse;

    // Make Chai use its own addon for HTTP calls
    var expect = chai.expect;
    chai.use(chaiHttp);

    /*****************************************
     * Scenario: Create
     *****************************************/

    // Given
    this.Given(/^I have all the required fidget information$/, function () {

      sleep.usleep(500);

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

    });

    // When
    this.When(/^I create a new fidget$/, {timeout: 30000}, function (done) {

      sleep.sleep(3);

        chai.request(url)
            .post('/api/fidgets')
            .send(fidgetData)
            .then(function(res) {
                expect(res).to.have.status(201);
                var postRes = JSON.parse(res.text);
                fidgetId = JSON.stringify(postRes._id).replace(/\W/g, '');
                return done();
            })
            .catch(function(err) {
                return done(err);
            });

    });

    // Then
    this.Then(/^I should get a fidget creation successful response$/, function () {

      sleep.usleep(500);

        if (!fidgetId)
            throw new Error('Fidget creation unsuccessfull');

    });

    /*****************************************
     * Scenario: Update
     *****************************************/

    // Given
    this.Given(/^I have new Fidget information$/, function () {

      sleep.usleep(500);

        updatedFidgetData = {
            "type": "eagleeye"
        };

    });

    // When
    this.When(/^I update the Fidget$/, {timeout: 30000}, function (done) {

      sleep.sleep(3);

        chai.request(url)
            .put('/api/fidgets/' + fidgetId)
            .send(updatedFidgetData)
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
    this.Then(/^I should see the updated Fidget$/, function () {

      sleep.usleep(500);

        if (!updateResponse)
            throw new Error('Fidget was not updated');

    });

    /*****************************************
     * Scenario: Read
     *****************************************/

    // Given
    this.Given(/^I need to look up a Fidget and have the id$/, function () {

      sleep.usleep(500);

        if (!fidgetId)
            throw new Error('Missing Fidget id. Could not GET.');

    });

    // When
    this.When(/^I look the Fidget up$/, {timeout: 30000}, function (done) {

      sleep.sleep(3);

        chai.request(url)
            .get('/api/fidgets/' + fidgetId)
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
    this.Then(/^I should get the Fidget profile back$/, function () {

      sleep.usleep(500);

        if (getResponse[0]._id != fidgetId)
            throw new Error('Could not GET Fidget profile');

    });

    /*****************************************
     * Scenario: Delete
     *****************************************/

    // Given
    this.Given(/^I need to delete a decommissioned Fidget and have the id$/, function () {

      sleep.usleep(500);

        if (!fidgetId)
            throw new Error('Missing Fidget id. Could not DELETE.');

    });

    // When
    this.When(/^I delete the Fidget$/, {timeout: 30000}, function (done) {

      sleep.sleep(3);

        chai.request(url)
            .del('/api/fidgets/' + fidgetId)
            .then(function(res) {
                expect(res).to.have.status(400);
                expect(res.text).to.be.a('string');
                deleteResponse = JSON.parse(res.text);
                return done();
            })
            .catch(function(err) {
                return done(err);
            });

    });

    // Then
    this.Then(/^I should no longer be able to use the Fidget$/, function () {

      sleep.usleep(500);

        if (!deleteResponse)
            throw new Error('Could not DELETE Fidget');

    });

};
