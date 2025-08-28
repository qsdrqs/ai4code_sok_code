# In Python, standard functionalities like I/O and string manipulation are
# built-in, so there's no need for includes like <stdio.h> or <string.h>.
# Memory management is automatic, so <stdlib.h> for malloc/free is not needed.

# The C code's #define MAX_STR_LEN 128 is not necessary in Python due to
# dynamically sized strings, but we can define it as a global constant
# for faithfulness to the original.
MAX_STR_LEN = 128

def reverse_str(s: str) -> str:
    """
    Reverses a given string.
    This is the Python equivalent of the C `reverseStr` function.
    """
    # In C, a new string is allocated and characters are copied in reverse order.
    # Python's string slicing `[::-1]` is a concise and efficient way to do the same.
    return s[::-1]

def str_of_int(num: int) -> str:
    """
    Return a string representation of a signed integer.
    This is a literal translation of the C algorithm.

    Args:
        num (int): input number as a signed integer

    Returns:
        str: string representation of the signed integer
    """
    # NOTE: A more Pythonic way to do this is to simply use the built-in str() function.
    # For example: return str(num)
    # The implementation below is a direct translation of the C algorithm.

    # Handle the edge case of 0, which the loop below would not cover.
    if num == 0:
        return "0"

    # In C, a character array is used. In Python, a list of characters is a
    # good mutable equivalent before joining them into a final string.
    reversed_chars = []
    is_neg = False

    if num < 0:
        is_neg = True
        num = -num

    # This loop mimics the C do-while loop's logic.
    # It extracts digits from the end of the number and builds a reversed list of characters.
    while num > 0:
        # Get the last digit (num % 10) and convert it to a character.
        # ord('0') gives the ASCII/Unicode value of '0'.
        last_digit = num % 10
        reversed_chars.append(chr(last_digit + ord('0')))
        num //= 10  # Integer division

    if is_neg:
        reversed_chars.append('-')

    # Join the list of characters into a string. At this point, for input -123,
    # the list would be ['3', '2', '1', '-'] and the string "321-".
    temp_string = "".join(reversed_chars)

    # Reverse the string to get the correct order (e.g., "-123").
    return reverse_str(temp_string)

def main():
    """
    The main function, equivalent to C's main.
    """
    # The C code prints the result of strOfInt(7000000) followed by a newline.
    # Python's print() function does this in one call, adding a newline by default.
    print(str_of_int(7000000))

# This is the standard entry point for a Python script,
# ensuring that main() is called only when the script is executed directly.
if __name__ == "__main__":
    main()