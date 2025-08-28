# No external dependencies are needed.
# The C code included <stdio.h> for printing and <string.h> for string functions.
# Python's built-in print() function and string formatting capabilities
# cover these functionalities.

def num_to_str(num: int) -> str:
    """
    Converts an integer to a string formatted with thousand separators.

    This function translates the intent of the original C function. The C code
    manually manipulated a character array to insert commas. Python's f-string
    formatting provides a simple, direct, and robust way to achieve the same
    result.

    Args:
        num: The integer to be formatted.

    Returns:
        A string representation of the number with thousand separators.
    """
    # The format specifier ":," tells Python to format the number with a
    # comma as the thousand separator. This single line replaces the entire
    # complex logic of the C function.
    return f"{num:,}"

def main():
    """
    This function serves as the entry point of the script, similar to the
    main() function in the C code.
    """
    num = 7042
    
    # In C, a pre-allocated buffer 'str' was passed to the function to be modified.
    # In Python, it is more common for functions to return a new value.
    formatted_str = num_to_str(num)
    
    # The C `printf` function is replaced by Python's `print` function
    # using an f-string for easy formatting.
    print(f"Output: {formatted_str}")

# This standard Python construct ensures that the main() function is called
# only when the script is executed directly.
if __name__ == "__main__":
    main()