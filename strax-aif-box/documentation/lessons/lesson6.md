# Lesson 6

## Overview of Mocha and Chai

### Installing Mocha
`run npm install -g mocha`

- What does the g mean?

### Installing Chai

`npm install chai`

### Including Chai

`var chai = require('chai')`


### Test Structure

As usual, you should separate your code and your tests. 
Most popular practice with JavaScript code is to have a directory called `test/`.

### To run tests
In the console, run command `mocha`


### API testing

We can use 
[Supertest](https://www.npmjs.com/package/supertest)

This is built off of [Superagent](https://github.com/visionmedia/superagent)

    Small progressive client-side HTTP request library, and Node.js module with the same API, sporting many high-level HTTP client features

We use supertest for the HTTP assertions.

    const request = require('supertest');
    const express = require('express');
    
    const app = express();
    
    app.get('/user', function(req, res) {
      res.status(200).json({ name: 'john' });
    });
    
    request(app)
      .get('/user')
      .expect('Content-Type', /json/)
      .expect('Content-Length', '15')
      .expect(200)
      .end(function(err, res) {
        if (err) throw err;
      });

