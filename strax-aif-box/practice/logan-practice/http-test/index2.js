//hello+hello
const http = require('http')
const port = 3000

var word = 'hello\n'
var count = 0;

const requestHandler = (request, response) => {
  //enters here
  console.log(request.url) //localhost:3000
  if(count !== 0) { //check is count not equal to 0
    word += word; //string manipulation 
    // word = word + word //are same
  }
  response.end(word + ' ' + count)
  count++; //increments count
}

const server = http.createServer(requestHandler)

server.listen(port, (err) => {
  if (err) {
    return console.log('something bad happened', err)
  }

  console.log(`server is listening on ${port}`)
})