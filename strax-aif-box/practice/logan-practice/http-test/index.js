// content of index.js
const http = require('http')

console.log('http',http)

const port = 3000

// const requestHandler = (request, response) => {
//   console.log(request.url)
//   response.end('Hello Node.js Server!')
// }

// everchanging


const server = http.createServer((request, response) => {     //setting server to a function called createServer with 
  console.log('lets see some skin',request.url)               //parameters request and response
  response.end('Hello Node.js Server!')}).listen(port,(err)=>{
  if (err) {
    return console.log('something bad happened', err)
  }

  console.log(`server is listening on ${port}`)
})

// server.listen(port, (err) => {
//   if (err) {
//     return console.log('something bad happened', err)
//   }

//   console.log(`server is listening on ${port}`)
// })