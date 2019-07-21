# Homework 7 - RPG Character Creation Sails Project

## Curriculum: 
- Sails App
  - Ejs frontend
    - input
  - Call an API
    - retrieve data
  - Call another API
    - send data
- Mongo
  - Write to database
  - Retrieve from database
- Logic
  - Optimizing items
  - statistics

## RPG Create Character Task
Using Sails, create a program that will take in input 
and fetch from an outside api.
The app will then decide the optimal items for the character.
Then (save the character) output the character to a database 
and sends the character to another API 
then shows them on a character page.

## Overview Tasks
- Creat a sails app - name it whatever you see relevant to a character creation app
  `sails new CreateCreation`
- Create front page in ejs
  - Input for each character class
- Call Outside API `https://create-character-items.herokuapp.com/items`
  - GET Items 
- Store Items in database
- Optimize logic
  - Loops
  - Arrays
- Create the Character with the items
- Call Outside API ________________ (logan needs to fill)
  - POST Character
- view the character page
character page: ________________ (logan needs to fill)

## Characters

Base character structure

    Character {
      name: string,
      class: string,
      attributes: {
        health: number,
        attack: number,
        defense: number,
        mana: number,
      },
      gear: {
        head: object,
        chest: object,
        legs: object,
        gauntlets: object,
        rings: array of objects,
        boots: object,
      }
    }

Added in data types. 
Attributes are sums of the items the character is wearing. Plus the initial starting values.

### Character classes

#### Assassin
Cloth and Leather wearer.
Maximize Attack.

Starting values include: 

    attributes: {
      health: 10,
      attack: 10,
      defense: 10,
      mana: 10,
    }

#### Knight
Plate wearer.
Maximize Attack and Defense

    attributes: {
      health: 15,
      attack: 10,
      defense: 10,
      mana: 5,
    }

#### Spellcaster
Cloth wearer.
Maximize Mana

    attributes: {
      health: 10,
      attack: 10,
      defense: 5,
      mana: 15,
    }

#### Warrior
Plate wearer.
Maximize Health and Defense

    attributes: {
      health: 15,
      attack: 5,
      defense: 15,
      mana: 5,
    }

## Items

### Structure: 

    {
      name: string,
      slot: string,
      attributes: {
        health: number,
        attack: number,
        defense: number,
        mana: number,
      }
    }

Slot values:
head, chest, legs, gauntlets, rings, boots

This project will broken into parts.

## Tasks

### Part 1 Sails
- Create Sails app
  - Select the raw Sails App - Option 2
  - learn the structure
  - comment out some designs

### Part 2 Sails
Design what you need
- Controllers?
- Models? 
- Routes?
- Pages? (HTML)

Models: Characters, Items

Use the api command

`sails new api characters`

`sails new api items`

when you lift up with the api
![Sails Alter Prompt](../../assets/sailsalter.png)

You can avoid this by uncommenting the line
`migrate: 'alter'` in the models.js file

 - create a controller - Main controller for the app
    - `CreateCharacterController.js`
  - design routes in `routes.js`
  - lookover the ejs in the views folder
    - design some forms
      - don't code them!
        - Design! -->

Example: Homepage
![Homepage](../../assets/homepage.png)

Example: Dashboard
![Dashboard](../../assets/dashboard1.png)
Characters should be shown underneath

### Part 3 Initial Routes
- Create Getting Started route
  - can be a POST
    - similar to the number game start game
  - should lead you to the dashboard - a view of characters
    - don't need dashboard right now
      - doesn't hurt to lead you to another view tho
  - most likely call a controller function
    - get items 
      - created with blueprint if `sails generate api items` was used
    - get characters
      - created with blueprint if `sails generate api characters` was used
    - sails blueprints
      - https://sailsjs.com/documentation/concepts/blueprints
      - https://sailsjs.com/documentation/concepts/blueprints/blueprint-routes
      - 
- Call API (LOGAN)
  - GET route to url: https://create-character-items.herokuapp.com/items
  - test the route by logging out the items
  - hard code this route
    - most likely in the function
- Create `Optimize` route 
  - should have a character parameter
    - like an id
- Create `create character` route
  - On the dashboard to create the character
  - POST
    - create a characer to the database
    - return to the dashboard with the updated character list
  - In Sails if you use the `sails generate api characters` you already have this route
    - POST /characters
      - request body should include the necessary things
        - name
        - charClass 
      - However, you will have to override the character route in order to route back to the dashboard 
        - create a function in the Characters Controller
          - called `create`
- Create `finalize character` route
  - this will be used later
    - POST to the other outside API
    - url: _______

#### Route Path
1. Getting Started on the homepage 
2. Dashboard 
   1. Get Items
   2. Get Characters
3. Create Character
   1. take in input
      1. name
      2. charClass
         1. radio button
   2. In sails, you use the blueprint, 
      1. POST to /characters
4. Optimize Character
   1. take in character input
      1. character should be stored in db
      2. without gear
   2. result should be character geared
5. Send final Character
   1. finalize character route
   2. post the character

### Part 4 Mongo
- Setup Mongo
  - locally
    - download from here: https://docs.mongodb.com/manual/installation/
- Connect Sails to Mongo https://sailsjs.com/documentation/tutorials/using-mongo-db
- Store Items in to mongo

### Part 5 Input
- Take Input for creating the character
  - name
  - charClass
- look up html buttons
  - form for this

### Part 6
- 1st phase would be to grab random items for character
  - ignore specs
  - ignore armor type
- Write the character to the database
  - Create new character collection
  - Call the optimize route

- Optimize items logic
  - Each Class
    - Assassin
    - Knight
    - Spellcaster
    - Warrior
- taking into account specs
- Write the character to the database
  - Create new character collection
  - Call the optimize route

### Part 7
- Send data to next api
  - Send the created character
- Shows output on next api (LOGAN)

### Part 8
- dockerize

## Tips
- Example Number game is a helper.
- Use logging
- Error checking slots
  - Chestpiece cannot go in helmet slot
- Read the writeup multiple times

### Mock up
Old mockup: Left here for design thoughts
![Mockup v1.0](../../assets/charactercreationapp.png)
<!-- ![Mockup v1.1]() -->
Parts 1 and 2
![Mockup v1.1](../../assets/charactercreationapp2.png)

Parts 3
![Mockup v1.2](../../assets/charactercreationapp3.png)

Parts 4



## Final Thoughts
This will get you "geared" for SAIF. Make sure you read it over.


