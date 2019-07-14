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

### RPG Create Character Task
Using Sails, create a program that will take in input 
and fetch from an outside api.
The app will then decide the optimal items for the character.
Then (save the character) output the character to a database 
and sends the character to another API 
then shows them on a character page.

### Tasks
- Creat a sails app - name it whatever you see relevant to a character creation app
  `sails new CreateCreation`
- Create front page in ejs
  - Input for each character class
- Call Outside API _______________ (logan needs to fill)
  - GET Items
- Store Items in database
- Optimize logic
  - Loops
- Create the Character with the items
- Call Outside API ________________ (logan needs to fill)
  - POST Character
- view the character page
character page: ________________

### Characters
Base character structure
Character {
  name: string,
  class: string
  attributes: {
    health: number,
    attack: number,
    defense: number,
    mana: number,
  },
  armor: {
    head: object,
    chest: object,
    legs: object,
    gauntlets: object,
    rings: object,
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

### Items
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

possible slot values:
head, chest, legs, gauntlets, rings, boots

This project will broken into parts.

### Part 1a Sails
- Create Sails app
  - design routes
- Take Input
  - look up html buttons
    - forms
- going to fetch gear for certain class

### Part 1b Mongo
- Setup Mongo
  - locally
    - donwload from here: https://docs.mongodb.com/manual/installation/

### Part 2 
- Call API (LOGAN)
- Store items retrieved from API <-database
- Write to database 

### Part 3 
- Optimize items logic
  - Each Class
    - Assassin
    - Knight
    - Spellcaster
    - Warrior
- Write the character to the database

### Part 4
- Send data to next api
  - Send the created character
- Shows output on next api (LOGAN)

#### Possible Tips
- Example Number game is a helper.
- Use logging
- Error checking slots
  - Chestpiece cannot go in helmet slot
- Read the writeup multiple times

#### Final Thoughts
This will get you "geared" for SAIF. Make sure you read it over.
