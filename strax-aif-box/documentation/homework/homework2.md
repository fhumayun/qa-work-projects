# Homework 2

[Flexiquiz](https://www.flexiquiz.com)

## HTTP Calls - CRUD


### 1. What is an API? 
<details><summary>Answer</summary>
<p>

A set of functions and procedures allowing the creation of applications that access the features or data of an operating system, application, or other service.

</p>
</details>

### 2. What is a POST?
Multiple Choice

- A) POST is used to send data to a server to create a resource.
- B) POST is used to send data to a server to update a resource.
- C) Both
- D) Neither

<details><summary>Answer</summary>
<p>

C. 
- POST is used to send data to a server to create a resource.
- POST can also be used to update a resource.

</p>
</details>

### 3. What is the parameter in this url route?
Route: `https://github.com/LBorn`
<details><summary>Answer</summary>
<p>

LBorn

</p>
</details>

### 4. What is the query string in this url route? 
Route: `https://api.github.com/repos/vmg/redcarpet/issues?state=closed`

<details><summary>Answer</summary>
<p>

`state=closed`

</p>
</details>

The following 2 questions will refer to this user schema:

`{
  firstName: string,
  lastName: string
}`

Server is running on localhost:1337

### 5. We want to create a user, what route will need to be called? Write a route with the appropriate HTTP Method and the request body. Remember the parameter.

`HTTP Method http://localhost:1337/______`

`body: {
  what goes here?
} `

<details><summary>Answer</summary>
<p>

POST http://localhost:1337/user

`body: {
  firstName: Hedley,
  lastName: Lamarr
} `

</p>
</details>

### 6. We want to update a user, what route will need to be called? Write a route with the appropriate HTTP Method and the request body.

`HTTP Method http://localhost:1337_____`

` body: {
  what goes here?
} `

<details><summary>Answer</summary>
<p>

PUT http://localhost:1337/user/id

`body: {
  firstName: Hedley,
  lastName: Lamarr
}` 

</p>
</details>

