# Exercise 4
Goal of this exercise is to go over routes and also looping

## Route Practice

### Prelim:
- Create web server on port 1337
  - Initialize the structure

### 1. Write me a get with the following:
- respond back with `Hello World` 3 times

  app.get('/route',(req,res)=>{
    let hello = 'Hello World'
    let output = 'Hello World';
    for(let i = 0;i < 2; i++)
    {
      output += hello + '\n'
    }
    res.send(output)
  })

## Looping
### For Loop
TODO
### While Loop
TODO
### Foreach
TODO