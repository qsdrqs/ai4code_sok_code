# No imports are needed for this version.

def int_to_string(num: int) -> str:
    """
    Translates the C algorithm for converting an integer to a string
    with thousand separators.
    """
    # Edge case: The original C code fails for 0. We handle it here.
    if num == 0:
        return "0"

    # In Python, we use a dynamic list of characters instead of a
    # fixed-size buffer from malloc().
    reversed_chars = []
    
    temp = num
    count = 0

    # This loop builds the string in reverse, just like the C code.
    while temp != 0:
        # Get the last digit and append it as a character.
        # str(temp % 10) is the Python equivalent of C's (temp % 10 + '0').
        reversed_chars.append(str(temp % 10))
        
        # Integer division to remove the last digit.
        temp //= 10
        count += 1

        # Add a comma every 3 digits, but not at the very end of the number.
        if count == 3 and temp != 0:
            reversed_chars.append(',')
            count = 0
            
    # The C code reverses the string in-place. In Python, we can
    # reverse the list and then join its elements into a final string.
    # The C code's null terminator ('\0') is not needed in Python.
    final_string = "".join(reversed(reversed_chars))
    
    return final_string


def main():
    """
    This is the equivalent of the C main function.
    """
    # The C code `printf(*int_to_string(7000000))` is a bit unusual.
    # The standard way is `printf("%s", int_to_string(7000000))`.
    # The Python equivalent is simply to print the returned string.
    print(int_to_string(7000000))


# Standard Python entry point
if __name__ == "__main__":
    main()