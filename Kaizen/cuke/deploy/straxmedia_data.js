/**
 *
 */
'use strict';

// Setup
var host = process.env.MONGOHOST;
var port = process.env.MONOGPORT;

if (!host || host == 'localhost') host = '127.0.0.1';
if (!port) port = '27017';

var userdata =
    [
        {
            "_id" : "000000000000000000000001",
            "userName" : "tim@gct.com",
            "password" : "cf919b461386ba3127cbd6bba36be7f3895f2ed0",
            "firstName" : "Timothy",
            "lastName" : "McClure",
            "salt" : "Dd7u+sI1s1B/48ZX1iILdVGOy445sC8/fA5QZONBCwJR6oLMUhi2H/P5hWvBJ9eIEeo+jNb7eqdtUdxm2oKfOwxi9ZincyygfwtN7dVD6MtB0rDrXRULdt5k5z2ITl23k70wMAnmYNSWMrAxGlyeoWCOnXxmtdZOR8pt7EyCVM0=",
            "role" : "admin",
            "__v" : 0.0
        },
        {
            "_id" : "000000000000000000000002",
            "userName" : "jshanahan@eagleeyeintelligence.com",
            "password" : "cf919b461386ba3127cbd6bba36be7f3895f2ed0",
            "firstName" : "John",
            "lastName" : "Shanahan",
            "salt" : "Dd7u+sI1s1B/48ZX1iILdVGOy445sC8/fA5QZONBCwJR6oLMUhi2H/P5hWvBJ9eIEeo+jNb7eqdtUdxm2oKfOwxi9ZincyygfwtN7dVD6MtB0rDrXRULdt5k5z2ITl23k70wMAnmYNSWMrAxGlyeoWCOnXxmtdZOR8pt7EyCVM0=",
            "role" : "admin",
            "__v" : 0.0
        }
    ];

var MongoClient = require('mongodb').MongoClient;
var mongoUrl = "mongodb://" + host + ":" + port + "/test_straxmedia";

MongoClient.connect(mongoUrl, function(err, db) {

    if (err) {
        console.log("[Error] " + err);
        process.exit(1);
    }

    if (!db) {
        console.log("[Error] No db Object ***");
        process.exit(1);
    }

    console.log("[Info] Connected to: " + mongoUrl);

    db.dropDatabase();

    db.collection('accounts').insert( userdata );

    console.log("[Info] Done.");
    process.exit(0);
});
