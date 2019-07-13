/**
 * GameController
 *
 * @description :: Server-side actions for handling incoming requests.
 * @help        :: See https://sailsjs.com/docs/concepts/actions
 */

module.exports = {
  //create the random number and set it to the global number variable
  newGame: function(req, res, next) {
    //debug
    sails.log(`before - `, sails.config.globals.number);

    sails.config.globals.number = Math.floor(
      Math.random() * Math.floor(10) + 1
    );

    //debug
    sails.log(`after - `, sails.config.globals.number);

    res.view("pages/entry");
  },

  checkGuess: function(req, res, next) {
    sails.log("show me the body", req.body);

    let guess = req.body.guess;
    let statement = "Wrong";
    let newGame = false;

    //do the logic
    if (guess == sails.config.globals.number) {
      (statement = "Correct"), (newGame = true);
    } else if (guess > sails.config.globals.number) {
      statement = "Wrong. You guessed too high.";
    } else {
      statement = "Wrong. You guessed too low.";
    }

    res.view("pages/guess", {
      guess: req.body.guess,
      statement: statement,
      newGame: newGame
    });
  },

  welcome: async function(req, res, next) {
    var greeting = await sails.helpers.welcomeMessage.with({ name: "Bubba" });

    sails.log(greeting);

    res.status(200).send({ message: greeting });
  },

  /*Test Functons */
  runTest: async function(req, res, next) {
    const { spawn } = require("child_process");

    spawn("mocha").stdout.pipe(process.stdout);

    res.status(200).send({ message: "complete" });
  }
};
