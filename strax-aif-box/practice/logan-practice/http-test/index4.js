//duplicate
const http = require('http')
const port = 3000

var word = 'hello\n'

const requestHandler = (request, response) => {
  //enters here
  console.log(request.url) //localhost:3000
  for(let i = 0; i <100; i++) 
  {
    response.end(word + ' ' + i)
    console.log(word)
  }

}

const server = http.createServer(requestHandler)

server.listen(port, (err) => {
  if (err) {
    return console.log('something bad happened', err)
  }

  console.log(`server is listening on ${port}`)
})