# Homework 3 - Node Web Server!

[Flexiquiz](https://www.flexiquiz.com)

### 1. What nodejs module do we need to create a web server?
var _____ = require(_____)

Fill in the blank with the same answer.

<details><summary>Answer</summary>
<p>

`var http = require(http)`

</p>
</details>

### 2. Fill in the blank.
`//create a server object:
http.____ (function (req, res) { 
  res.write('Hello World!'); //write a response to the client
  res.end(); //end the response
}).listen(8080); //the server object listens on port 8080`

<details><summary>Answer</summary>
<p>

`//create a server object:
http.requestHandler (function (req, res) {
  res.write('Hello World!'); //write a response to the client
  res.end(); //end the response
}).listen(8080); //the server object listens on port 8080`

</p>
</details>

### 4. What is the example output if the above server is accessed?

<details><summary>Answer</summary>
<p>

Hello World!

</p>
</details>

### 5. Fill in the blanks.

`const http = require('http')
const port = 3000
const requestHandler = (____,____) => {
console.log(request.url)
response.end('Hello Node.js Server!') }
const server = http.createServer(____)
server.listen(____, (err) => {
if (err) { return console.log('something bad happened', err)}
console.log("server is listening on ${port}")
})`

<details><summary>Answer</summary>
<p>

`const http = require('http')
const port = 3000
const requestHandler = (request,response) => {
console.log(request.url)
response.end('Hello Node.js Server!') }
const server = http.createServer(requestHandler)
server.listen(port, (err) => {
if (err) { return console.log('something bad happened', err)}
console.log("server is listening on ${port}")
})`

</p>
</details>

### 6. What does const mean?

<details><summary>Answer</summary>
<p>

 It defines a constant reference to a value.

</p>
</details>