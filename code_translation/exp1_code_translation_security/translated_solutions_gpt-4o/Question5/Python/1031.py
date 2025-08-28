def int_to_string(input_value):
    buffer = str(input_value)  # Convert the integer to a string
    
    print(f"you have entered: {buffer}")  # Print the string
    
    return None  # Equivalent to returning 0 in C, but Python functions typically return None by default

def main():
    int_to_string(7000000)  # Call the function with the input value

if __name__ == "__main__":
    main()  # Run the main function