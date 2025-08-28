# No imports are needed as all functionality is built-in.

def string_it(value: int) -> str:
    """
    Returns a string containing the comma-separated expansion of a signed integer.
    
    This translation manually replicates the C code's comma-insertion
    logic to be faithful to its behavior, including the side-effect printf call.
    """
    # Handle the sign, similar to the C code
    if value < 0:
        sign = '-'
        # Work with the positive value for formatting
        s = str(-value)
    else:
        sign = ''
        s = str(value)

    length = len(s)
    # If no commas are needed, return the number as is (with sign)
    if length <= 3:
        return sign + s

    # The C code inserts commas from right to left. We can simulate this by
    # building a list of 3-character chunks from the end of the string.
    parts = []
    temp_s = s
    
    while len(temp_s) > 3:
        # Get the last 3 characters, equivalent to `end - 3` in the C code
        start_index = len(temp_s) - 3
        chunk = temp_s[start_index:]
        
        # This print statement replicates the `printf("%s\n", start);` in the C loop
        print(chunk)
        
        parts.append(chunk)
        # Move to the next part of the string
        temp_s = temp_s[:start_index]

    # Add the remaining part at the beginning (which will be 1, 2, or 3 chars)
    parts.append(temp_s)
    
    # The parts were added from right-to-left (e.g., ['457', '123']).
    # We reverse the list and join with commas to get the correct order.
    formatted_string = ",".join(reversed(parts))
    
    # Prepend the sign and return the final string
    return sign + formatted_string

def main():
    """
    Main function to test the string_it function, equivalent to C's main.
    """
    # The C code calls string_it and then prints the result with 3 leading spaces.
    # The call to string_it() will produce its own output first due to the
    # replicated print statement inside the function.
    result = string_it(-123457)
    print(f"   {result}")

# Standard Python entry point to run the main function.
if __name__ == "__main__":
    main()