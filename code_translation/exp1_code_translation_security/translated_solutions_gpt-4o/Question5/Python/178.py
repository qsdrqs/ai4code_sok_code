import math

def convert_to_string(num):
    # Convert the number to a string
    string = str(num)
    
    # Calculate the number of commas needed
    num_of_commas = round(len(string) / 3)
    print(num_of_commas)
    
    # Create a new string with commas
    new_string = ""
    for i in range(len(string)):
        new_string += string[i]
        print(ord(string[i]), end="")  # Print ASCII value of the character
        if (i + 1) % 3 == 0 and (i + 1) != len(string):
            print("s", end="")  # Mimicking the "s" print in the original code
            new_string += ","
    
    return new_string

# Call the function and print the result
print(convert_to_string(2000))