'use strict';

const supertest = require('supertest');
const apiHost = 'localhost';

module.exports = (apiPort) => {

  let apiUri = `http://${apiHost}:${apiPort}`;

  const api = supertest(apiUri);

  return api;

};
