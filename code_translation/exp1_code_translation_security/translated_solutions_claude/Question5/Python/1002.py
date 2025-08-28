def convert(value):
    """
    Convert an integer to a string with comma separators every 3 digits.
    
    Args:
        value (int): The integer value to convert
        
    Returns:
        str: The formatted string with commas
    """
    # Convert the integer to string (equivalent to snprintf)
    buf1 = str(value)
    
    # Create a list to build the result (equivalent to buf2)
    buf2 = []
    
    # Get the length of the string
    length = len(buf1)
    
    # Process each character
    for i in range(length):
        # Add the current character
        buf2.append(buf1[i])
        
        # Calculate distance from the end
        dist = length - i - 1
        
        # Add comma if distance is divisible by 3 and not at the end
        if dist % 3 == 0 and dist != 0:
            buf2.append(',')
    
    # Join the list into a string and return
    return ''.join(buf2)


def main():
    """Main function to test the convert function."""
    test = convert(1000)
    print(test)


if __name__ == "__main__":
    main()