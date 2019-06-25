const express = require('express')
const app = express()
const port = 3000

//object
// declaring Object named computer 
//  //these are the attributes
//     computer condists of Ram 
//     computer consists of cpu


var computer = {
    ram: '4' ,
    cpu: '8' ,
}


//layout of a function
// input(optional) - output(optional)
// function (these are your parameters) {
//   //do stuff here with the parameters
//   return data to user(optional)
// }


//homepage
app.get('/', (req, res) => {
  res.send('Hello World!');
})

//app get
app.get('/computer', (req, res) => {

  if(computer.cpu)
  {
    res.send( computer.cpu)
  }
  else {
    res.send('no computer')
  }
})
//app post

//app put
app.put('/computer',(req, res) => {
  //set computer ram to 8
    computer.ram = '8'
    computer.cpu = '16'
    //return the computer
    // res.send(`${computer.cpu} ${computer.ram}`) 
    res.send(computer.cpu + ' ' + computer.ram ) 
})

//app put
app.put('/computer2',(req, res) => {
  //set computer ram to 8
    // computer.ram = '8'
    // computer.cpu = '16'
    //return the computer
    // res.send(`${computer.cpu} ${computer.ram}`) 
    res.send(computer.cpu + ' ' + computer.ram ) 
})


//app delete
app.delete('/computer', (req, res) => {
    // deleting computer
    computer = null
  

    res.send('we deleted the computer')
})


app.listen(port, () => console.log(`Example app listening on port ${port}!`))