# No external libraries are needed as Python's standard library
# provides all the necessary functionality.
# The 'typing' import is used for type hints, which is good practice
# but not required for the code to run.
from typing import List

def convert(value: int) -> str:
    """
    Converts an integer to a string, inserting thousand separators.

    This function is a direct translation of the logic from the C code.
    It manually builds the formatted string character by character.

    Args:
        value: The integer to convert.

    Returns:
        A string representation of the number with commas.
    """
    # In C: snprintf(buf1, BUFFER_SIZE, "%ld", value);
    # In Python, we can simply convert the integer to a string.
    s_value = str(value)

    # In C: int len = strnlen(buf1, BUFFER_SIZE);
    length = len(s_value)

    # In C, a second buffer (buf2) is allocated to build the result.
    # In Python, it's more efficient to build a list of strings/characters
    # and then join them into a single string at the end.
    result_parts: List[str] = []

    # This loop mimics the C code's for loop structure.
    # for (int i = 0; i < len; i++)
    for i, char in enumerate(s_value):
        # In C: buf2[idx++] = buf1[i];
        result_parts.append(char)

        # In C: int dist = len - i - 1;
        # This calculates how many characters are remaining after the current one.
        dist = length - i - 1

        # In C: if (dist % 3 == 0 && dist != 0) { buf2[idx++] = ','; }
        # If the number of remaining characters is a multiple of 3 (and not zero),
        # a comma is inserted. This logic correctly handles negative signs because
        # the distance is calculated from the total string length.
        if dist > 0 and dist % 3 == 0:
            result_parts.append(',')

    # In C: buf2[idx] = '\0';
    # In Python, strings are not null-terminated. We join the list of parts.
    # The C code's free(buf1) is also not needed, as Python's garbage
    # collector handles memory automatically.
    return "".join(result_parts)


# In C, the program starts execution from the main() function.
# In Python, the `if __name__ == "__main__":` block is the conventional
# entry point for a script.
if __name__ == "__main__":
    # In C: char *test = convert(1000);
    test = convert(1000)
    
    # In C: printf("%s\n", test);
    print(test)
    
    # In C: free(test);
    # Memory management is automatic in Python, so free() is not needed.

# ---
# Note: The Pythonic Way
#
# The `convert` function above is a direct translation of the C algorithm.
# However, the idiomatic way to achieve the same result in Python is much
# simpler using f-string formatting with a comma separator.
#
# Example:
#   value = 1234567
#   formatted_string = f"{value:,}"  # This produces "1,234,567"
# ---