# Exercise 7
Goal of this exercise is to create and run routes in Sails. 

## Route Practice

### Sails App
in practice folder

#### Create controller
command: `sails generate controller user`

Creates a controller in the controller folder.

#### Create functions
inside the controller that you created
create function hello
GET
hello: function (req,res,next) { }
next is optional

have it return a message that says hello

res.status(200).send({message: 'hello'})

### We need a route
inside the route.js file

GET '/hello': UserController.hello

http method GET
'/hello'
UserController is the controller
hello is the function inside the controller


### Another function
back inside the controller
create a new function called goodbye
we want this route to return with string 'goodbye, `<username>`

POST
goodbye: function (req,res,next) {}

res.status(200).send({message: `goodbye, ${req.body.user}`)

### We need a route

POST '/goodbye': UserController.goodbye

http method POST
'/goodbye'
UserController is the controller
goodbye is the function inside the controller

We'll use postman to send the request body


### action2 routes
Use the command `sails generate action eat` to create an action. A file that is a function.
Notice the structure. A lot cleaner and more organized. Good documentation. 


https://sailsjs.com/documentation/concepts/routes/custom-routes#?action-target-syntax

'GET /eat': { action: 'eat' },   // Use the action in api/controllers/eat.js






