# Homework 3 - Routing

[Flexiquiz](https://www.flexiquiz.com)

### 1. What is Express JS?
Fill in the blanks.
Express is a ____ and flexible Node.js ____ ____ framework that provies a robust set of features for web and mobile applications. With a myriad of HTTP utility methods and ____ at your displosal, creating a robust ____ is quick and easy.

<details><summary>Answer</summary>
<p>

Express is a minimal and flexible Node.js web application framework that provies a robust set of features for web and mobile applications. With a myriad of HTTP utility methods and middleware at your displosal, creating a robust API is quick and easy.

</p>
</details>

### 2. True of False. Sails JS is built on Express JS.

<details><summary>Answer</summary>
<p>

T

</p>
</details>

### 3. Routing refers to determining how an application responds to a client request to a particular endpoint, which is a URI (or path) and a specific HTTP request method (GET, POST, and so on). - From the expressjs site

Each route can have one or more handler functions, which are executed when the route is matched.

Route definition takes the following structure:
`app.METHOD(PATH, HANDLER)`

where: 
 - ____ is an instance of express.
 - ____ is an HTTP request method, in lowercase.
 - ____ is a path on the server.
 - ____ is the function executed when the route is matched.

<details><summary>Answer</summary>
<p>

 - app is an instance of express.
 - METHOD is an HTTP request method, in lowercase.
 - PATH is a path on the server.
 - HANDLER is the function executed when the route is matched.

</p>
</details>

### 4. Example route: 

`app.get('/', function (req, res) {
  res.send('Hello South Florida!')
})`

What is the output on the page when I request the homepage?
- Hello World!
- Hello South Florida!
- Hello Mack!
- Hello Faisal!


<details><summary>Answer</summary>
<p>

Hello South Florida!

</p>
</details>

### 5. Example route: 

`app.post('/user', function (req, res) {
  res.send('Got a POST request at /user')
})`

Let's say we have a server running on port 3000 locally. How would I access this route?

- http://localhost:3000/user
- http://localhost:3000/api/user
- http://localhost:3000/
- http://localhost:3000/post/user

<details><summary>Answer</summary>
<p>

http://localhost:3000/user

</p>
</details>

### 6. Example code: 

`const express = require('express')
const app = express()
const port = 3000
app.get('/', (request, response) => {
  response.send('Hello from Express!')
})
app.listen(port, (err) => {
  if (err) {
    return console.log('something bad happened', err)
  }
  console.log("server is listening on ${port}")
})`

We want to edit a user so we are going to add a PUT route to the above code. Fill in the blanks.

`____.____
('____', (request, response) => {
  response.send('Got a PUT ____
 at /user')
})`

<details><summary>Answer</summary>
<p>

`app.put('/user', (request, response) => {
  response.send('Got a PUT request
 at /user')
})`

</p>
</details>