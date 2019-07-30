# Lesson 10

## EJS

Be sure to brush up on your html. Know what the tags are. Not all but a few such as <p>,<div>, <br>

Source
https://scotch.io/tutorials/use-ejs-to-template-your-node-application


### Values

Lets say we have a server like this: 

    // server.js

    // index page 
    app.get('/', function(req, res) {
        var drinks = [
            { name: 'Bloody Mary', drunkness: 3 },
            { name: 'Martini', drunkness: 5 },
            { name: 'Scotch', drunkness: 10 }
        ];
        var tagline = "Any code of your own that you haven't looked at for six or more months might as well have been written by someone else.";

        res.render('pages/index', {
            drinks: drinks,
            tagline: tagline
        });
    });

https://stackoverflow.com/questions/24650564/sailsjs-res-render-vs-res-view

res.view is a sails function

`<%= tagline %>` outputs the tagline

### Looping

To loop over data use a .forEach

    <ul>
        <% drinks.forEach(function(drink) { %>
            <li><%= drink.name %> - <%= drink.drunkness %></li>
        <% }); %>
    </ul>

