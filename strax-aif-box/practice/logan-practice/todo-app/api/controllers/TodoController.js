/**
 * TodoController
 *
 * @description :: Server-side actions for handling incoming requests.
 * @help        :: See https://sailsjs.com/docs/concepts/actions
 */

module.exports = {
  homepage: async function(req, res, next) {
    Todo.find({}).then(todos => {
      res.view("pages/homepage", { todos: todos });
    });
  },

  create: async function(req, res, next) {
    let newTodo = req.body;

    Todo.create(newTodo)
      .then(() => {
        return Todo.find({});
      })
      .then(() => {
        res.redirect("/");
        // res.view("pages/homepage", { todos: todos });
      });
  },
  deleteTodo: async function(req, res, next) {
    sails.log("destroying", req.params.id);

    Todo.destroy({ id: req.params.id })
      .then(() => {
        return Todo.find({});
      })
      .then(() => {
        res.redirect("/");
      });
  },
  updateTodo: async function(req, res, next) {
    res.redirect("/");
  }
};
