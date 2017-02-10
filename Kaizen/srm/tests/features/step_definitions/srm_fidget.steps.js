'use strict';

var chai = require('chai');
var chaiHttp = require('chai-http');
var sleep = require('sleep');
var Oz = require('oz');

module.exports = function() {

    // Variables
    var url = process.env.TESTURL || "https://qa-api.strax.co";
    var userCredentials = {
        "username": "john@ee.io",
        "password": "eei"
    };
    var fidgetData = {};
    var updatedFidgetData = {};
    var fidgetId;
    var updateResponse;
    var getResponse;
    var deleteResponse;

    // Oz Variables
    var appTicket;
    const ID_SERVER = "https://qa-id.strax.co/";
    const VALIDATE = "oz/validate";
    var AUTHORIZATION;

    // Make Chai use its own addon for HTTP calls
    var expect = chai.expect;
    chai.use(chaiHttp);

    /*****************************************
     * Scenario: Create
     *****************************************/


    // Given
    this.Given(/^The Admin is logged in and has the required fidget information$/, function () {

        fidgetData = {
            "profile" : "5741f57f56d61f0d0074925e",
            "type" : "safetrax",
            "feedId" : "5748ad74870c41330225038c",
            "accountDocId" : "000000000000000000000002",
            "gears" : [],
            "zones" : [],
            "vehicleInfo" : [],
            "photo" : [],
            "__v" : 0
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
    this.When(/^I create a new fidget$/, function () {

        return chai.request(url)
            .post('/api/fidgets')
            .set("Authorization", AUTHORIZATION)
            .send(fidgetData)
            .then(function(res) {
                expect(res).to.have.status(201);
                var postRes = JSON.parse(res.text);
                fidgetId = JSON.stringify(postRes._id).replace(/\W/g, '');
            })
            .catch(function(err) {
                throw err;
            });

    });

    // Then
    this.Then(/^I should get a fidget creation successful response$/, function () {

        if (!fidgetId)
            throw new Error('Fidget creation unsuccessfull');

    });

    /*****************************************
     * Scenario: Update
     *****************************************/

    // Given
    this.Given(/^I have new Fidget information$/, function () {

        updatedFidgetData = {
            "type": "eagleeye"
        };

    });

    // When
    this.When(/^I update the Fidget$/, function () {

        return chai.request(url)
            .put('/api/fidgets/' + fidgetId)
            .set("Authorization", AUTHORIZATION)
            .send(updatedFidgetData)
            .then(function(res) {
                expect(res).to.have.status(200);
                expect(res.text).to.be.a('string');
                updateResponse = JSON.parse(res.text);
            })
            .catch(function(err) {
                throw err;
            });

    });

    // Then
    this.Then(/^I should see the updated Fidget$/, function () {

        if (!updateResponse)
            throw new Error('Fidget was not updated');

    });

    /*****************************************
     * Scenario: Read
     *****************************************/

    // Given
    this.Given(/^I need to look up a Fidget and have the id$/, function () {

        if (!fidgetId)
            throw new Error('Missing Fidget id. Could not GET.');

    });

    // When
    this.When(/^I look the Fidget up$/, function () {

        return chai.request(url)
            .get('/api/fidgets/' + fidgetId)
            .set("Authorization", AUTHORIZATION)
            .then(function(res) {
                expect(res).to.have.status(200);
                expect(res.text).to.be.a('string');
                getResponse = JSON.parse(res.text);
            })
            .catch(function(err) {
                throw err;
            })

    });

    // Then
    this.Then(/^I should get the Fidget profile back$/, function () {

        if (getResponse[0]._id != fidgetId)
            throw new Error('Could not GET Fidget profile');

    });

    /*****************************************
     * Scenario: Delete
     *****************************************/

    // Given
    this.Given(/^I need to delete a decommissioned Fidget and have the id$/, function () {

        if (!fidgetId)
            throw new Error('Missing Fidget id. Could not DELETE.');

    });

    // When
    this.When(/^I delete the Fidget$/, function () {

        return chai.request(url)
            .del('/api/fidgets/' + fidgetId)
            .set("Authorization", AUTHORIZATION)
            .then(function(res) {
                expect(res).to.have.status(200);
                expect(res.text).to.be.a('string');
                deleteResponse = JSON.parse(res.text);
            })
            .catch(function(err) {
                throw err;
            });

    });

    // Then
    this.Then(/^I should no longer be able to use the Fidget$/, function () {

        if (!deleteResponse)
            throw new Error('Could not DELETE Fidget');
        //if (deleteResponse)
         //   throw new Error('Could not DELETE Fidget');

    });

};
