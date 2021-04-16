def my_function():
    return 6

for i in my_function(): # Noncompliant {{For statement.}}
    print("Test")
    my_function()
    pass



my_function()