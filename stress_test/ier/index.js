'use strict';

const IerStressTest = require('./ierStressTest').IerStressTest;

let ierStressTest = new IerStressTest();
ierStressTest.start().then(() => {
  console.log('Finished');
});


