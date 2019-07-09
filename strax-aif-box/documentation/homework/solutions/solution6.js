const express = require("express");
const app = express();
const port = 1337;

var currentFN = 1; //default to 1
var previousFN = 0;

//return the current FN
app.get("/fibonacci", (req, res) => {
  res.status(200).send({ fibonacci: currentFN });
});

//app post
app.post("/fibonacci/:id", (req, res) => {
  //error checking - not a real fibonacci
  currentFN = Number(req.params.id);

  calculatePrev(currentFN);

  res.status(200).send({ fibonacci: currentFN });
});

//app put
app.put("/fibonacci", (req, res) => {
  let temp = currentFN;
  currentFN = previousFN + currentFN;
  previousFN = temp;

  res.status(200).send({ fibonacci: currentFN });
});

//app delete
//set it back to 1
app.delete("/fibonacci", (req, res) => {
  currentFN = 1;
  previousFN = 0;
  res.status(200).send({ fibonacci: currentFN });
});

app.listen(port, () => console.log(`Example app listening on port ${port}!`));

//without comments
// function calculatePrev(num) {
//   var a = 1,
//     b = 0,
//     temp;
//   while (num != a) {
//     temp = a;
//     a = a + b;
//     b = temp;
//   }

//   previousFN = b;
// }

//with comments
function calculatePrev(num) {
  //goal of this function is to find the previous fibonacci number by calculating the current fibonacci number (num)

  //num - current fibonacci number

  //Declaring variables
  var a = 1; //a - placeholder for the fibonacci number
  var b = 0; //b - placeholder for the previous fibonacci number
  var temp = 0; //temporary variable

  while (num != a) {
    //loop until the current fibonacci number equals to placeholder for the fibonacci number
    temp = a; //save the value of the fibonacci number
    a = a + b; //modify the fibonacci by adding the previous fibonacci
    b = temp; //set the placeholder to the temp which is holding the previous fibonacci number
  }

  previousFN = b; //set the server variable (perviousFN) to the placeholder of previous fibonacci number (b)
}
