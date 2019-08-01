/**
 * TodoController
 *
 * @description :: Server-side actions for handling incoming requests.
 * @help        :: See https://sailsjs.com/docs/concepts/actions
 */

module.exports = {
  homepage: async function(req, res, next) {
    Todo.find({}).then(todos => {
      sails.log("todos", todos);

      res.view("pages/homepage", { todos: todos });
    });
  },

  create: async function(req, res, next) {
    let newTodo = req.body;

    Todo.create(newTodo)
      .then(() => {
        return Todo.find({});
      })
      .then(todos => {
        res.redirect("/")
        // res.view("pages/homepage", { todos: todos });
      });
  }
};
