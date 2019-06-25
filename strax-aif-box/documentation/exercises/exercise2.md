# Exercise 2
## Goal of this exercise is to review params and query params

### 1. What is the parameter here?
http://localhost:3000/user/15

### 2. What is the parameter here?
http://localhost:3000/toy/gijoe/15

### 3. What is the query parameter here?
http://localhost:3000/user?name=logan

### 4. What is the query parameter here?
http://localhost:3000/student?grade=A

### 5. How do i access the parameter?
http://localhost:3000/user/logan

route: /user/:name
request.param.name

### 6. How do i access this query parameter?
http://localhost:3000/computer?cpu=3

route: /computer
request.query.cpu 