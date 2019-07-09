function help() {
  let message = "I need Help"; // set message
  // return message; //return message
  return "I need help";
}

console.log(help()); //call the function

//take in someone's name - Mack
let passingName = "Mack";
//return version
function someoneName(name) {
  // <-- X What we are going to input into the function
  let message = "Hi " + name; // <-- String manipulation (Will create Hi [whatever name we passed into funciton] )

  return message; // <-- Here we return the input
}
// console.log()

console.log(someoneName(passingName)); //<-- calling function

//variable version
var message; //what is this

function someoneName2(name) {
  message = "Hi " + name;
}

someoneName2(passingName);
console.log(message);
//then sending _____ to console

function calcSquare(number) {
  let square = number * number
  return square
}

for (var i = 0; i < 10; i++) {
  console.log(calcSquare(i)); //i is visible thus is logged in the console as 0,1,2,....,9
}
// console.log(i); //i is visible here too. thus is logged as 10. 