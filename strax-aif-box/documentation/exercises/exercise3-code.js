//Addition problem

const express = require("express");
const app = express();
const port = 1337;

var myNumber = 1; 

//GET http://localhost:1337/number
//return your number
app.get("/number", (req, res) => {
  res.status(200).send(`My number is ${myNumber}`);
});

//app post
//POST http://localhost:1337/number?number=15
app.post("/number", (req, res) => {
  if(req.query.number)
  {
    myNumber = req.query.number;
    res.status(200).send(`My number is ${myNumber}`);
  }
  else {
    res.status(200).send(`Wrong Input. No change. My number is ${myNumber}`);
  }

});

//app put
//PUT http://localhost:1337/number
app.put("/number", (req, res) => {
  myNumber++;
  res.status(200).send(`My number is ${myNumber}`);
});

//app delete
//set it back to 1
//DELETE http://localhost:1337/number
app.delete("/number", (req, res) => {
  myNumber = 1;
  res.status(200).send(`My number is ${myNumber}`);
});

app.listen(port, () => console.log(`Example app listening on port ${port}!`));