var fs = require("fs");
var mongoose = require('mongoose');
var tunnel = require('tunnel-ssh');

//===== db connection =====
var config = {
    username:'strax',
    host:'msg',
    agent : process.env.SSH_AUTH_SOCK,
    privateKey:require('fs').readFileSync('qat.pem'),
    port:22,
    dstPort:27017,
    password:'strax'
};

var server = tunnel(config, function (error, server) {
    if(error){
        console.log("SSH connection error: " + error);
    }
    mongoose.connect('mongodb://localhost:27017/mydbname');

    var db = mongoose.connection;
    db.on('error', console.error.bind(console, 'DB connection error:'));
    db.once('open', function() {
        // we're connected!
        console.log("DB connection successful");
    });
});

