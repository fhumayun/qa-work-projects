# Lesson 9

## Logic

### How to make this true?

if(req.params.id)
{

}

Route needs to pass in a params 
req.params

Route: `todo/id`

### How to make this true?

if(req.body.tod)
{

}

Route needs to have a request body passed in

    body: {
      todo: string
    }

### What about this?

if(req.params.id)
{

}

if(req.body.todo)
{
    
}

Both will run if there is a param id and if there is a todo in the request body.