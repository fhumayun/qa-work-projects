# Homework 5 - CRUD Routing

[Flexiquiz](https://www.flexiquiz.com)

## HTTP Calls - CRUD

### 1. What is CRUD?

<details><summary>Answer</summary>
<p>

- Create
- Read
- Update
- Delete

</p>
</details>

### 2. In a REST environment, what are the corresponding HTTP methods? 

<details><summary>Answer</summary>
<p>

- Create - POST
- Read - GET
- Update - PUT
- Delete - Delete

</p>
</details>

### 3. Create a full CRUD for a type `users`. The routes.
Hints: 
- Url: `http://localhost:1337/users`
- Unique Identifier: id

<details><summary>Answer</summary>
<p>

- POST http://localhost:1337/users
- PUT http://localhost:1337/users/id
- GET http://localhost:1337/users/id
- DELETE http://localhost:1337/users/id

</p>
</details>

### 4. Here is some example code:
route.js File: 

var express = require('express');
var router = express.Router();
router.post('/create', function);
____
____
____
module.exports = router;

### Fill in the update, get, and delete. I have given you the create. 


<details><summary>Answer</summary>
<p>

- router.get('/:id', function);
- router.put('/:id', function);
- router.delete('/:id', function);

</p>
</details>

### Example Controller.js

`exports.product_create = function (req, res) {
    var product = new Product(
        {
            name: req.body.name,
            price: req.body.price
        }
    );
    product.save(function (err) {
        if (err) {
            return next(err);
        }
    })
};`

### 5 What route do you think calls this function? 
- GET
- POST
- PUT
- DELETE

<details><summary>Answer</summary>
<p>

POST

</p>
</details>

### 6 What must you pass in as the request body?

<details><summary>Answer</summary>
<p>

{
  name,
  price
}

</p>
</details>

### 7 There is an error in this controller. Something could be missing. Something could be mispelled.

<details><summary>Answer</summary>
<p>

`res.send('Product Created successfully')`

</p>
</details>

