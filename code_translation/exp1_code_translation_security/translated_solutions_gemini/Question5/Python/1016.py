# All necessary functionalities (like print, string manipulation, etc.) are
# built-in to Python, so no explicit imports are needed for this translation.

def int_to_string(num: int) -> str:
    """
    Converts an integer to a string, adding commas as thousands separators.

    This function mimics the logic of the original C code but is written
    using Pythonic constructs. It also corrects bugs from the C version,
    such as the handling of the number 0 and negative values.

    Args:
        num: The integer to be converted.

    Returns:
        A string representation of the integer with commas.
    """
    # The original C code had a bug where it would return an empty string for 0.
    # We handle this as a special case.
    if num == 0:
        return "0"

    # In C, handling negative numbers with the modulo operator (%) can be tricky.
    # In Python, it's simpler to handle the sign separately and work with the
    # absolute value of the number.
    is_negative = num < 0
    if is_negative:
        num = -num

    # In Python, it's easier to build a list of characters and then join them
    # into a string, rather than pre-allocating a buffer like in C.
    # We will build the string in reverse, then reverse it at the end.
    s_reversed = []
    count = 0

    while num > 0:
        # Add a comma every three digits. The check `count > 0` prevents a
        # leading comma on the right side (e.g., ",123").
        if count > 0 and count % 3 == 0:
            s_reversed.append(',')

        # Get the last digit, convert it to a character, and add to our list.
        s_reversed.append(str(num % 10))
        num //= 10  # Use integer division to remove the last digit
        count += 1

    # The list `s_reversed` now contains the number string in reverse.
    # e.g., for 12345, it would be ['5', '4', '3', ',', '2', '1']

    # Add the negative sign to the end if the number was negative.
    if is_negative:
        s_reversed.append('-')

    # Reverse the list and join the characters to form the final string.
    final_str = "".join(reversed(s_reversed))

    return final_str

def main():
    """
    Main function to demonstrate the usage of int_to_string.
    This replicates the behavior of the main function in the C code.
    """
    num = -10099870
    str_val = int_to_string(num)
    print(str_val)

    # In C, you must manually free the memory allocated with malloc().
    # In Python, memory management is automatic (handled by the garbage
    # collector), so there is no need for a `free()` call.

# The standard Python entry point. This ensures `main()` is called only
# when the script is executed directly.
if __name__ == "__main__":
    main()

# ---
# Note on a more "Pythonic" solution:
# Python has powerful built-in formatting options that can achieve the same
# result in a single line of code. While the function above translates the
# C algorithm, the idiomatic Python way would be:
#
# def int_to_string_pythonic(num: int) -> str:
#     return f"{num:,}"
#
# # Example usage:
# print(f"{-10099870:,}")  # Output: -10,099,870
# ---