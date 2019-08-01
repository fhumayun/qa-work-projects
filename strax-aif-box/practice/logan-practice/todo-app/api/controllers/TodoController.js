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
    newTodo.completed = false;

    sails.log("newTodo", newTodo);

    if (newTodo.todo == "" || newTodo.todo === undefined) {
      res.redirect("/");
    } else {
      Todo.create(newTodo)
        // .then(() => {
        //   return Todo.find({});
        // })
        .then(() => {
          res.redirect("/");
          // res.view("pages/homepage", { todos: todos });
        });
    }
  },

  updateTodo: async function(req, res, next) {
    if (req.query.completed == "true") {
      req.body.completed = true;
    } else if (req.query.completed == "false") {
      req.body.completed = false;
    }

    Todo.update({ id: req.params.id }, req.body).then(() => {
      //this route pulls the todoList
      res.redirect("/");
    });
  },

  deleteTodo: async function(req, res, next) {
    sails.log("destroying", req.params.id);

    Todo.destroy({ id: req.params.id }).then(() => {
      //this route pulls the todoList
      res.redirect("/");
    });
  },

  clearCompleted: async function(req, res, next) {
    Todo.destroy({ completed: true }).then(() => {
      //this route pulls the todoList
      res.redirect("/");
    });
  }
};
