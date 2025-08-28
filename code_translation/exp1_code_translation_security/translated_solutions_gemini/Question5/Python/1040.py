# No imports are needed for this functionality, as it relies on built-in features.

def num_digits(n):
    """
    Translates the C num_digits function.
    This function counts the number of digits in a non-negative integer.
    
    Note: This function is provided to match the structure of the C code.
    In idiomatic Python, one would simply use len(str(n)). The C version's
    logic is replicated here, where it returns 0 for an input of 0.
    """
    if n == 0:
        return 0
    
    # The original C code assumes n is positive. We use abs() to be safe.
    n = abs(n)
    digits = 0
    while n > 0:
        n //= 10  # Use integer division, equivalent to n /= 10 in C for integers
        digits += 1
    return digits

def int_to_string_with_commas(value):
    """
    Translates the C int_to_string_with_commas function.
    
    This Python version corrects several bugs from the C original:
    1.  Handles the edge case of `value = 0`.
    2.  Correctly handles negative numbers by prepending a '-' sign.
    3.  Fixes the flawed comma-insertion logic and the infinite loop.
    4.  Avoids memory errors (the C code returns a pointer to a local variable,
        which is undefined behavior).

    The most idiomatic Python way to do this is with an f-string: f"{value:,}"
    However, this implementation follows the spirit of the original C code's
    algorithm (building the string manually) to provide a more direct translation.
    """
    if not isinstance(value, int):
        raise TypeError("Input must be an integer.")

    if value == 0:
        print("0")
        return "0"

    # Convert the absolute value of the number to a string to work with its digits.
    s = str(abs(value))
    n_digits = len(s)
    
    # If the number has 3 or fewer digits, no commas are needed.
    if n_digits <= 3:
        result = str(value) # str() handles the negative sign correctly.
        print(result)
        return result

    # Build the formatted string from right to left.
    result_parts = []
    for i, digit in enumerate(reversed(s)):
        # After processing a digit, check if a comma is needed.
        # A comma is needed after every third digit, but not at the very end.
        if i > 0 and i % 3 == 0:
            result_parts.append(',')
        result_parts.append(digit)
    
    # The list is built in reverse order (e.g., "7,6,5,4,3,2,1"), so reverse it back.
    formatted_string = "".join(reversed(result_parts))

    # Add the negative sign back if the original number was negative.
    if value < 0:
        formatted_string = "-" + formatted_string
    
    # The original C function prints the result and also returns it.
    # We will replicate this behavior.
    print(formatted_string)
    return formatted_string

# This block is the Python equivalent of the C main() function.
# It serves as the entry point of the script.
if __name__ == "__main__":
    print("Formatting the number 7000000:")
    int_to_string_with_commas(7000000)
    
    print("\n--- Additional Test Cases ---")
    int_to_string_with_commas(123)
    int_to_string_with_commas(12345)
    int_to_string_with_commas(-123456)
    int_to_string_with_commas(1234567890)
    int_to_string_with_commas(0)