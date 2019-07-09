//Incremental problem

const express = require("express");
const app = express();
const port = 1337;

var myNumber = 1; //let's start it at 1.

//Example output:
//`My number is ${myNumber}`

//write the full route here
//GET ... http://localhost:1337/number
//return my number
app.get("/number", (req, res) => {
  res.status(200).send(`My number is ${myNumber}`);
});

//write the full route here
//POST ... http://localhost:1337/number?number=2
//http://localhost:1337/number?cheese=1
//in order for this route to work you must have query paramter number
app.post("/number", (req, res) => {
  // myNumber = 2 
   //the value of req.query.number is equal to 2;

   
   // IF is followed by the condition about whether something EXISTS
  if(req.query.number) 
  {
    myNumber = req.query.number;
    res.status(200).send(`My number is ${myNumber}`);
  }
  else {
    //should be no change to myNumber
    res.status(400).send(`Invalid parameter. My number is ${myNumber}`);
  }
  
//myNumber
   
// We will now try to set the query parameter to myNumber
// e.g we set nyNumber to a hard coded value of 2 by writing myNumber = 2
// so the trick is to set to a dynamic value by writing 
  // myNumber = req.query.number;

//what goes here? 
//possible error handling? 


// when testing for errors in a condition - we always use "IF" conditionals
// I would want to verify IF (req.query.number) is actually being set to 2
// in the case that it is NOT being set to 2, I would want to print an error about that

// if (req.query.number) != 2 
//    then print "Query parameter is NOT being set to 2!!!" --> response 
// done

});

//write the full route here
//PUT ...http://localhost:1337/number
app.put("/number", (req, res) => {

  //Now we want to increment myNumber by 1

  myNumber += 1
  //-= decrement

  // or I can write this like 

  // myNumber++
//-- decrement
  // or I can write this same thing like

  // myNumber = myNumber + 1
//-1 decrement

  res.status(200).send(`My number is ${myNumber}`);
//what goes here?
});

//app delete
//set it back to 1
//write the full route here
//DELETE ...
app.delete("/number", (req, res) => {
  myNumber = 1;
  res.status(200).send(`Deleting number ${myNumber}`);

//what goes here?
});

app.listen(port, () => console.log(`Example app listening on port ${port}!`));