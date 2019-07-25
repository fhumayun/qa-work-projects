# Lesson 8

## Arrays and Homework help

## Arrays

### Creating

#### Declare it prefilled

`var cars = ["Saab", "Volvo", "BMW"];`

#### Declare it empty

`var cars = [];`

### Updating

Functions: Pop and Push

Others: Shift and Unshift

#### Pop

##### Removes the last element

    var cars = ["Saab"]
    cars.pop(); 

  result:

    var cars = []

#### Push

##### Adds to the end of the array

    var cars = [];

    let newCar = { make: "ford", model: "escape" }

    cars.push(newCar); 

result: 

    cars = [{ make: "ford", model:"escape" }]

#### Others

- Shift
  - adds to the beginning of the array
- Unshift
  - removes from the beginning of the array
  
### Looping

#### For Loop

    for(let i = 0; i < cars.length-1;i++)
    {
      //do logic
    }

#### For Each

    cars.forEach((car)=>{
      //do something on variable car
    })

### Arrays can hold objects, functions, and arrays
    cars = []
    cars[0] = function()
    cars[1] = "ford"
    cars[2] = models[] <-- array

### Length property
cars.length returns the size of the array

number of elements

### More Help
https://www.w3schools.com/js/js_arrays.asp
