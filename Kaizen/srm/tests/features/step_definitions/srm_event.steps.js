var chai = require('chai');
var chaiHttp = require('chai-http');

module.exports = function() {

    // Variables
    var url = process.env.TESTURL;
    var userCredentials = {
        "username": "jshanahan@eagleeyeintelligence.com",
        "password": "eei"
    };
    var newEventInfo = {};
    var accountId;
    var loginToken;
    var clusterData = {};
    var clusterId;
    var updatedResponse;
    var getResponse;
    var deleteResponse;

    // Make Chai use its own addon for HTTP calls
    var expect = chai.expect;
    chai.use(chaiHttp);

    /*****************************************
     * Scenario: Create
     *****************************************/

    // Given
    this.Given(/^The PIC is logged in$/, {timeout: 30000}, function (done) {

        chai.request(url)
            .post('/api/participant/doAuthenticate')
            .send(userCredentials)
            .then(function(res) {
                expect(res).to.have.status(200);
                return done();
            })
            .catch(function(err) {
              return done(err);
            });

    });

    // When I assign participants
    this.When(/^The PIC assigns team members$/, function () {

        clusterData.participants = [
            {
                "participantDocId": "5730f7237364d60e002d0e0f"
            },
            {
                "participantDocId": "5730fc667364d60e002d0e12"
            }
        ];

    });

    // And assign a drone
    this.When(/^The PIC assigns a UAS$/, function () {

        clusterData.fidgets = [
            [
                "5735d5f5b651fcb00042fc4c"
            ]
        ];

    });

    // And create the cluster/event
    this.When(/^The PIC creates a new event$/, {timeout: 30000}, function (done) {

        clusterData.referenceId = 'TESTCLUSTER'+(new Date).getTime();
        clusterData.accountDocId = accountId;

        chai.request(url)
            .post('/api/clusters')
            .send(clusterData)
            .then(function(res) {
                expect(res).to.have.status(201);

                var resText = JSON.parse(res.text);
                clusterId = resText._id.replace(/\W/g, '');
                return done();
            })
            .catch(function(err) {
                return done(err);
            });

    });

    // Then I should get a cluster ID showing it was created
    this.Then(/^A new event should be created$/, function () {

        if (!clusterId) {
             throw new Error('Missing clusterId');
        }

    });

    /*****************************************
     * Scenario: Update
     *****************************************/

    // Given
    this.Given(/^The PIC has new information$/, function () {

        newEventInfo = {
            "description": "updated desc"
        };

    });

    // When
    this.When(/^The PIC updates the event$/, {timeout: 30000}, function (done) {

        chai.request(url)
            .put('/api/clusters/' + clusterId)
            .send(newEventInfo)
            .then(function(res) {
                expect(res).to.have.status(200);
                updatedResponse = JSON.parse(res.text);
                return done();
            })
            .catch(function(err) {
                return done(err);
            });

    });

    // Then
    this.Then(/^The event should reflect the changes$/, function () {

        if (updatedResponse.description != newEventInfo.description) {
            throw new Error('Event information not updated');
        }

    });

    /*****************************************
     * Scenario: Read
     *****************************************/

    // Given
    this.Given(/^The PIC needs to read the event data$/, function () {
        // ok
    });

    // When
    this.When(/^The PIC requests the data$/, {timeout: 30000}, function (done) {

        chai.request(url)
            .get('/api/clusters/' + clusterId)
            .then(function(res) {
                expect(res).to.have.status(200);
                var getResponse = JSON.parse(res.text);
                return done();
            })
            .catch(function(err) {
                return done(err);
            });

    });

    // Then
    this.Then(/^The PIC should receive the event information$/, function () {
        // ok
    });

    /*****************************************
     * Scenario: Delete
     *****************************************/

    // Given
    this.Given(/^The event is over$/, function () {
        // ok
    });

    // When
    this.When(/^The PIC deletes the event$/, {timeout: 30000}, function (done) {

        chai.request(url)
            .del('/api/clusters/' + clusterId)
            .then(function(err, res) {
                expect(res).to.have.status(200);
                return done();
            })
            .catch(function(err){
                return done(err);
            })

    });

    // Then
    this.Then(/^It should no longer be considered active$/, function () {
        // ok
    });

};
