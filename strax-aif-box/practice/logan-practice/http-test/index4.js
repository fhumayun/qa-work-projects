const express = require('express')
const app = express()
const port = 3000

//spinners

app.get('/', (req, res) => res.send('Hello World!'))

//get
app.get('/spinner',(req, res) => {
  res.send('Here are our fidget spinners') 

})

/*Fidget spinner schema
 * color
 * speed
 */
app.post('/spinner/create',(req, res) => {
    var newSpinner =
    {
      color : "blue",
      speed : "8"
    }

    //another way to create the body
    // var body = {};
    // body.color="blue";
    // body.speed="8"

    var body = {
      color : "blue",
      speed : "8",
      message: 'This the winner'
    }

  res.send(200,body)
  // .send(body)

})
//post

//put

//delete

app.listen(port, () => console.log(`Example app listening on port ${port}!`))