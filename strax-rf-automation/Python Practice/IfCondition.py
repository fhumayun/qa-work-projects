# x = int("5")
# y = int("7")
# 
# if (x > y):
#     print("x is greater than y")
# elif (x < y):
#     print("x is smaller than y")
# elif (x == y):
#     print("x is equal to y")

# Lecture 30 Challenge 
name = (input("What is your name? "))
age = int(input("How old are you {0} ? ".format(name))) # <-- {0} and .format(name) calls the user input name

if (age > 17) and (age < 31):
    print("Welcome to the Holiday {0} ! Enjoy!".format(name)) # <-- {0} and .format(name) calls the user input name
else:
    print("Im sorry {0}, you do not qualify for the Holiday.".format(name)) # <-- {0} and .format(name) calls the user input name 
