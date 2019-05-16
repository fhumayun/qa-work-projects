# address = ["Flat Floor Street", "18", "New York"]
# pins = {"Mike":1234, "Joe":1111, "Jack":2222}
# 
# print(address[0], address[1])
# #print(address[0:1]) <-- List Splicing "Flat Floor Street 18"
# #print(address[:3])  <-- List Splicing "Flat Floor Stree 18 New York"
# pin = int(input("Enter your pin: "))
# 
# def find_in_file(f):
#     myfile = open("sample.txt")
#     fruits = myfile.read()
#     fruits = fruits.splitlines()
#     if f in fruits:
#         return "That fruit is in the list."
#     else: 
#         return "No such fruit found!"
#     
# if pin in pins.values():
#     fruit = input("Enter fruit: ")
#     print(find_in_file(fruit))
# else:
#     print("Incorrect Pin!")
#     print("This info can be access only by:")
#     for key in pins.keys():
#         print(key)
