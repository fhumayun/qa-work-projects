/**
 * GIController
 *
 * @description :: Server-side actions for handling incoming requests.
 * @help        :: See https://sailsjs.com/docs/concepts/actions
 */

var axios = require('axios');

module.exports = {
  //little tester
  foo: async function(req, res, next) {
    sails.log("foo");
    res.status(200).send({ message: true });
  },

  //Ghost Inspector Tests
  getTests: async function(req, res, next) {
    
    axios
      .get("https://api.ghostinspector.com/v1/suites/5ca5025eda06ce63b53e92d4/tests/", {
        params: {
          apiKey : '0427c9a16e2940e9f5e169889bbe5e30a8623c77'
        }
      })
      .then((response)=> {
        //log the response - debug
        sails.log(response.data);


        res.status(200).send(response.data)
      })
      .catch(function(error) {
        //we have an error
        sails.log(error);
      });
  }
};
