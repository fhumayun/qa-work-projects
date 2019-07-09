# Homework 6 - CRUD Routing and Fibonacci

## We will be creating a fibonacci api. 
### Fibonacci sequence
-  1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, ...

## Tasks
- Make sure to read through this whole homework before attempting it.
- The GET will return the current fibonacci number. For our example we'll start it at 1. 
  - Return the fibonacci number.
- The PUT will increment the fibonacci number. 
  - Return the updated fibonacci number.
- The POST will start the fibonacci number at the value inputted. 
  - Remember parameters. 
  - You can assume correct input. For example I will not be passing in 4, an incorrect fibonacci number. 
  - Return the fibonacci number
- The DELETE will reset the fibonacci number back to 1.
  - Return the fibonacci number

## Curriculum

- Able to create a node web server running on port 1337
- Fibonacci Numbers
- Interact with fibonacci numbers

## Helper functions:

### Calculate Previous Fibonacci Number
You will need this:
Take your time to read what this function does

    function calculatePrev(num){
    //goal of this function is to find the previous fibonacci number by calculating the current fibonacci number (num)

    //num - current fibonacci number

    //Declaring variables
    var a = 1; //a - placeholder for the fibonacci number 
    var b = 0; //b - placeholder for the previous fibonacci number
    var temp = 0; //temporary variable
    
    while(num != a){ //loop until the current fibonacci number equals to placeholder for the fibonacci number     
        temp = a;    //save the value of the fibonacci number
        a = a + b;   //modify the fibonacci by adding the previous fibonacci
        b = temp;    //set the placeholder to the temp which is holding the previous fibonacci number
    }

        previousFN = b; //set the server variable (perviousFN) to the placeholder of previous fibonacci number (b)
    }

- You can call this function in your routes: `calculatePrev(num)`
  - Num as in the current fibonacci number
- Think about where this route should go. HINT: POST or PUT?

## Example runs:

### Run 1

#### GET
- request: GET http://localhost:1337/fibonacci
- response: `{
    "fibonacci": 1
}`
#### PUT
- request: PUT http://localhost:1337/fibonacci
- response: `{
    "fibonacci": 1
}`
#### PUT
- request: PUT http://localhost:1337/fibonacci
- response: `{
    "fibonacci": 2
}`
#### PUT
- request: PUT http://localhost:1337/fibonacci
- response: `{
    "fibonacci": 3
}`
#### POST
- request: POST http://localhost:1337/fibonacci/21
- response: `{
    "fibonacci": 21
}`
#### PUT
- request: PUT http://localhost:1337/fibonacci
- response: `{
    "fibonacci": 34
}`
#### DELETE
- request: DELETE http://localhost:1337/fibonacci
- response: `{
    "fibonacci": 1
}`

### Run 2

#### POST
- request: POST http://localhost:1337/fibonacci/13
- response: `{
    "fibonacci": 13
}`

#### PUT
- request: PUT http://localhost:1337/fibonacci
- response: `{
    "fibonacci": 21
}`

### Tips
- Think about variables. Plural. 
  - Create a current fibonacci number (currentFN)
  - Create a previous fibonacci number (previousFN)
- Through the life of the server the variable(s) will hold their values.
  - Life as in however long it runs for.
- Use temp variables to hold data. Previous data. 
  - What is wrong with this? 
    
        previousFN = currentFN;
        currentFN = previousFN + currentFN;

    Answer: You will be doubling the currentFN. WRONG

        var temp = currentFN; //save currentFN
        currentFN = previousFN + currentFN; //do the fibonacci algorithm
        previousFN = temp; //now set the previous fibonacci number
