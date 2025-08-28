import math

def int_to_string(x):
    # Convert the integer to a string
    tempstring = str(x)
    
    # Calculate the length of the string (including commas)
    # Note: In Python, we don't need to manually calculate the length for memory allocation
    len_with_commas = len(tempstring) + (len(tempstring) - 1) // 3  # Add space for commas
    
    # Initialize the result string
    result = ""
    
    # Iterate through the string and add commas
    for i, char in enumerate(tempstring[::-1]):  # Reverse the string for easier grouping
        if i > 0 and i % 3 == 0:  # Add a comma every 3 digits
            result = "," + result
        result = char + result
    
    return result

def main():
    # Test the int_to_string function
    print(int_to_string(100000))
    print("test")

if __name__ == "__main__":
    main()