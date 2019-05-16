# For loop example
#
#for i in range(10):
#    print("i is now {}".format())

# While loop example
# rare to see while loop like this
# i = 0 
# while i < 10:
#     print("i is now {}".format(i))
#     i += 1



# available_exits = ["east", "north east", "south"]
# 
# chose_exit = ""
# while chose_exit not in available_exits:
#     chose_exit = input("Please choose a direction: ")
#     if chose_exit == "quit":
#         print("Game Over")
#         break
# 
# else:
#     print("aren't you glad you got out of there!")

# Below is my attempt at the WhileLoops Challenge (Cleaner Instructor version Below)

# import random
# 
# highest = 10
# answer = random.randint(1, highest)
# 
# print("Please guess a number between 1 and {}: ".format(highest))
# guess = int(input())
# while guess != answer:
#     if guess < answer:
#         print("Please guess higher!")
#     else: # guess must be greater than number
#         print("Please guess lower!")
#     guess = int(input())
#     if guess == answer:
#         print("Well done, you got it!")
#     else:
#         print("Sorry, you have not guessed correctly")
#     if guess == "quit":
#          print("Game Over")
#          break
# else:
#     print("You got it first time!")




# Instructor version of WhileLoop Chaleng

import random

highest = 10
answer = random.randint(1, highest)

print("Please guess a number between 1 and {}: ".format(highest))
guess = 0 # initialize to any number outside of the valid range
while guess != answer:
    guess = int(input())
    if guess < answer:
        print("Please guess higher!")
    elif guess > answer: # Guess must be greater than number
        print("Please guess lower!")
    else: 
        print("Well done you guessed it!")
    guess = (input())     # <-- needed to switch from int to string 
    if guess == "quit":
          print("Game Over")
          break






