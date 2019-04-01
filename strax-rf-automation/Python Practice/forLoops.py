# for i in range(1,20):
#     print("i is now {} ".format(i))

# number = "9,223,372,036,854,775,807"
# for i in range(0, len(number)):
#     print(number[i])

# number = "9,223,372,036,854,775,807"
# cleanednumber = ''
# 
# for i in range(0, len(number)):
#     if number[i] in '0123456789':
#         cleanednumber = cleanednumber + number[i]
#        
# newNumber = int(cleanednumber)
# print("The number is {} ".format(newNumber))

# for i in range (0,10):
#     print(" {} ".format(i))

# for i in range(10):
#     print(i)

number = "9,223,372,036,854,775,807"
cleanednumber = ''

for char in number:
    if char in '0123456789':
        cleanednumber = cleanednumber + char
    
newNumber = int(cleanednumber)
print("The nmber is {} ".format(newNumber))

for state in ["not pinin'", "nor more", "a stiff", "bereft of lift"]:
    print("This Parrott is"+state)
#   print("This parrot is {} ".format(state)) 

for i in range(0, 100, 5):
    print("i is {} ".format(i))

for i in range(1,13):
    for j in range(1,13):
        print("{1} times {0} is {2}".format(i, j, i*j))
    print("=====================")





