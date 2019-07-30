# Exercise 8

## Debugging

Source
https://medium.com/the-node-js-collection/debugging-node-js-with-google-chrome-4965b5f910f4

If `console.log` can't get the job done. Time to turn to the debugging tools. 

To start debugging, run your Node.js application with the `--inspect` flag.

    $ node --inspect <your_file>.js


https://nodejs.org/en/docs/guides/debugging-getting-started/?source=post_page

### Example: 


    'use strict'

    const express = require('express')
    const app = express()

    const PORT = process.env.PORT || 3000

    function capitalize (str) {
      const firstLetter = str.charAt(0) // we can check what's inside here
      return `${firstLetter.toUpperCase()}${str.slice(1)}`
    }

    app.get('/:name?', (req, res) => {
      const name = req.params.name ? capitalize(req.params.name) : 'World'
      res.send(`Hello ${name}!`)
    })

    app.listen(PORT, () => console.log(`App listening on *:${PORT}`))

Let’s run it with the `--inspect` flag and open the dedicated DevTools for Node as explained before.

insert some breakpoints





When all done

Source
https://medium.com/the-node-js-collection/debugging-node-js-with-google-chrome-4965b5f910f4