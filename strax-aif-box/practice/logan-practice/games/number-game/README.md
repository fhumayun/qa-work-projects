# Number game
let's design it

I want to create a number guessing game. 
As a user, i want to guess a number and have a response of whether it matches the random number or not. 

I will accept pseudo code or plain english sentences.

added difficulty - bigger range - incremental with correct guess

## Notes

### Routes

Initial GET - which already is handled by sails creation
'/' - the root - shows the view of the homepage
  
    "/": { view: "pages/homepage" },

What does this homepage have on it? 
 - Greeting
 - Ask user if they want to play?
   - button to advance the game

Homepage is the entry into the program. 
`homepage.ejs`

Flow - Homepage leads to the entry page where you make your initial guess. This page just has the guess. No statements yet. 

To get to the entry page, user needs to be routed to the entry page
a simple `/entry` works. However, that won't be good enough for the user. 

What am are we missing? Well, if my game has a different random number to guess each time, user will need to go through a controller function to calculate that random number. 

Create a `/newgame` route, this calls a function that sets the number to be guessed to something random. Then routes the user to the `entry` page

POST `/newGame --> entry.ejs`

    "/entry": { view: "pages/entry" },

On this page, it requires a post route to do the guess logic. Pass data to a controller. Specifically a controller function. Checks whether the guess is too high or too low. Then routes user to another page showing the status. High or Low. And also showing the user to guess again, unless the user got it correct, give the user the option to start a new game.

So entry page needs a POST route.  

This POST route takes in data. 
Going to be using a variable in the request body.  

`request.body.guess`

How do I call this? Well inside the entry.ejs page. Create a form, with method post and input called guess. 
My route for the guess function:
`/guess`

For the guess functionality, the user's guess will be checked and routed to a new page with the statement of whether too high or too low, and an option for newGame on correct guess. This guess will actually call itself. 

POST `/guess` with `request.body.guess`

### All in all
4 Routes
3 Pages
2 Functions
1 Controller

### Routes
- "/": { view: "pages/homepage" },
- "/entry": { view: "pages/entry" },
- "POST /newgame": "GameController.newGame",
- "POST /guess": "GameController.checkGuess"

### Pages
- homepage.ejs
- entry.ejs
  - form for submitting a guess
- guess.ejs
  - form for submitting a guess
  - output statement value
  - if else logic for new game

### Functions
#### newGame
  - sets a random number to the number to be guessed
  - routes user to entry page to form to guess the number
#### checkGuess
  - takes in data (guess) 
    - checks if the guess is high, low, or correct
  - routes user to `guess` page

### Controller
GameController





