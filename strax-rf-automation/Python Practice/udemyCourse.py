s = "Python is fun"
print(s[7:9])


address = ["Flat Floor Street", "18", "New York"] # <-- This is a python list (1)
pins = {"Mike":1234, "Joe":1111, "Jack":2222}     # <-- This is a python dictionary

print(address[0], address[1])
# print(address[0:1]) <-- List Splicing "Flat Floor Street 18"
# print(address[:3])  <-- List Splicing "Flat Floor Stree 18 New York"
pin = int(input("Enter your pin: "))

def find_in_file(f):          # <-- Defining the Function
    myfile = open("sample.txt")
    fruits = myfile.read()
    fruits = fruits.splitlines()
    if f in fruits:
        return "That fruit is in the list."
    else: 
        return "No such fruit found!"
    
if pin in pins.values():
    fruit = input("Enter fruit: ")
    print(find_in_file(fruit))   # <-- Calling the Function 
else:
    print("Incorrect Pin!")
    print("This info can be access only by:")
    for key in pins.keys():
        print(key)



 
#  Here are some more examples of slicing lists if you're still not sure how slicing works.

#  Let's suppose we have the following list in our Python shell:
  
#  >>> days = ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"] 
 
#  Here is how to access the first three items (from first to third):
#  >>> days[0:3] 
 
#  Output:
#  ['Mon', 'Tue', 'Wed'] 
 
#  Access items from first to fourth:
#  >>> days[0:4] 
#  ['Mon', 'Tue', 'Wed', 'Thu'] 
 
#  Exactly the same as above
#  >>> days[:4] 
#  ['Mon', 'Tue', 'Wed', 'Thu'] 
 
#  No boundaries
#  >>> days[:] 
#  ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'] 

#  From first to second-to-last
#  >>> days[0:-1] 
#  ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'] 
 
#  From first to third-to-last
#  >>> days[:-2] 
#  ['Mon', 'Tue', 'Wed', 'Thu', 'Fri'] 
 
#  From third-to-last to second-to-last
#  >>> days[-3:-1] 
#  ['Fri', 'Sat'] 
 
#  From third-to-last to last
#  >>> days[-3:] 
#  ['Fri', 'Sat', 'Sun'] 

### List Example ### (1)
# address = ["Flat Floor Street", "18", "New York"]

### Dictionary 
# pins = {"Mike":1234, "Joe":1111, "Jack":2222}

# Below are some operations you can do with dictionaries

#Let's say we have the following dictionary:

# >>> person97 = {"name":"Jack", "surname":"Smith", "age":"29"} 

# Removing pair "name":"Jack"
# >>> person97.pop("name") 
# 'Jack' 
# >>> person97 
# {'surname': 'Smith', 'age': '29'} 

# Adding new pair "name":"Jack"
# >>> person97["name"] = "Jack" 
# >>> person97 
# {'surname': 'Smith', 'age': '29', 'name': 'Jack'} 
 
# Changing an existing value
# >>> person97["age"] = 30 
# >>> person97 
# {'surname': 'Smith', 'age': 30, 'name': 'Jack'} 

# Mapping two lists to a dictionary:
# >>> keys = ["a", "b", "c"] 
# >>> values = [1, 2, 3] 
# >>> mydict = dict(zip(keys, values)) 
# >>> mydict 
# {'a': 1, 'b': 2, 'c': 3} 

### Custom Functions ###
