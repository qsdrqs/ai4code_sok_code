#
# The original C code's purpose is described as:
# "Write a function that takes a signed integer num and returns a string 
# representation of that integer. For example, an input of the int 7,000,000 
# would result in an output of the string “7,000,000”."
#

# ==============================================================================
#  Solution 1: The Modern, Pythonic Way (Recommended)
# ==============================================================================
# Python's built-in string formatting makes this task trivial.
# The format specifier `:,` automatically adds the correct thousands separator.
# This is the standard and most efficient way to solve this problem in Python.

def int_to_string_pythonic(num: int) -> str:
    """
    Converts a signed integer to a comma-separated string using Python's
    built-in formatting.
    
    Args:
        num: The integer to convert.
        
    Returns:
        A string representation of the integer with thousands separators.
    """
    return f"{num:,}"

# ==============================================================================
#  Solution 2: A Literal Translation of the C Algorithm
# ==============================================================================
# This version mimics the logic of the original C code step-by-step.
# It is more complex and less efficient than the Pythonic way, but it serves
# as a direct translation of the C algorithm.
#
# The C algorithm's steps are:
# 1. `itoa`: Convert the integer to a string, but in REVERSE order.
#    (e.g., 12345 becomes "54321"). The sign is appended at the end.
# 2. `insert_comma`: Loop through the reversed string and insert commas
#    every three characters from the left (which corresponds to every three
#    digits from the right in the final number).
# 3. `reverse`: Reverse the final string to get the correct order.

def int_to_string_from_c(num: int) -> str:
    """
    Converts a signed integer to a comma-separated string by replicating
    the C code's manual algorithm.
    
    Args:
        num: The integer to convert.
        
    Returns:
        A string representation of the integer with thousands separators.
    """
    if num == 0:
        return "0"

    # Step 1: Mimic `itoa` to generate digits in reverse order.
    # We handle the sign separately and work with the absolute value.
    is_negative = num < 0
    n = abs(num)
    
    reversed_digits = []
    while n > 0:
        reversed_digits.append(str(n % 10))
        n //= 10
    
    # Step 2: Mimic `insert_comma` logic.
    # We build a new list with commas inserted.
    with_commas_reversed = []
    for i, digit in enumerate(reversed_digits):
        # Insert a comma before every 3rd digit, but not at the beginning.
        if i > 0 and i % 3 == 0:
            with_commas_reversed.append(',')
        with_commas_reversed.append(digit)

    # Step 3: Mimic `reverse` to get the final string.
    # Join the list into a string and then reverse it.
    final_string = "".join(with_commas_reversed)[::-1]

    # Add the negative sign back if the original number was negative.
    if is_negative:
        return "-" + final_string
    else:
        return final_string

# ==============================================================================
#  Main execution block to demonstrate the functions
# ==============================================================================
# This part is equivalent to the `main` function in the C code.

if __name__ == "__main__":
    # Test case from the C code's main function
    num1 = 7000
    # Test case from the C code's top-level comment
    num2 = 7000000
    # Additional test cases
    num3 = -1234567
    num4 = 123456
    num5 = 999
    num6 = 0
    
    print("--- Using the modern Pythonic solution ---")
    print(f"Input: {num1}, Output: '{int_to_string_pythonic(num1)}'")
    print(f"Input: {num2}, Output: '{int_to_string_pythonic(num2)}'")
    print(f"Input: {num3}, Output: '{int_to_string_pythonic(num3)}'")
    print(f"Input: {num4}, Output: '{int_to_string_pythonic(num4)}'")
    print(f"Input: {num5}, Output: '{int_to_string_pythonic(num5)}'")
    print(f"Input: {num6}, Output: '{int_to_string_pythonic(num6)}'")

    print("\n--- Using the translated C algorithm ---")
    print(f"Input: {num1}, Output: '{int_to_string_from_c(num1)}'")
    print(f"Input: {num2}, Output: '{int_to_string_from_c(num2)}'")
    print(f"Input: {num3}, Output: '{int_to_string_from_c(num3)}'")
    print(f"Input: {num4}, Output: '{int_to_string_from_c(num4)}'")
    print(f"Input: {num5}, Output: '{int_to_string_from_c(num5)}'")
    print(f"Input: {num6}, Output: '{int_to_string_from_c(num6)}'")