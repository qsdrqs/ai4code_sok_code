import sys

# In the C code, a fixed-size buffer is allocated. In Python, strings are
# dynamic, so we don't need a MAX_DIGITS constant.

def repr_int(n: int) -> str:
    """
    Takes in a signed integer and returns its string representation with
    thousands separators. This function mimics the C-style algorithm of
    building the string in reverse and then flipping it at the end.
    """
    # Special case for the minimum 32-bit integer, as in the C code.
    # Note: Python's integers have arbitrary precision, so this check is not
    # technically necessary for correctness in Python but is included to
    # faithfully translate the original logic.
    if n == -2147483648:
        return "-2,147,483,648"

    if n == 0:
        return "0"

    is_negative = n < 0
    if is_negative:
        n = -n

    # We will build a list of characters in reverse order.
    s_parts = []
    digit_count = 0
    while n > 0:
        # Add a comma after every third digit.
        # The original C code's comma logic was buggy. This translation
        # implements the clear intent: adding thousands separators.
        if digit_count > 0 and digit_count % 3 == 0:
            s_parts.append(',')

        # Get the last digit and add it to our list as a string.
        s_parts.append(str(n % 10))
        digit_count += 1
        n //= 10  # Integer division

    if is_negative:
        s_parts.append('-')

    # Join the parts into a single string (e.g., "5,432,1-")
    reversed_str = "".join(s_parts)

    # Reverse the string to get the final result (e.g., "-1,234,5")
    return reverse(reversed_str)


def reverse(s: str) -> str:
    """
    Takes in a string and returns a new reversed string.
    In Python, this is easily done with slicing.
    """
    return s[::-1]


# The main function equivalent in Python.
if __name__ == "__main__":
    # You can test with different numbers by changing the value of n
    # or by passing command-line arguments.
    
    # Example 1: The number from the original main function
    n1 = 0
    str1 = repr_int(n1)
    print(f"Input: {n1}, Output: {str1}")

    # Example 2: A larger positive number
    n2 = 123456789
    str2 = repr_int(n2)
    print(f"Input: {n2}, Output: {str2}")

    # Example 3: A negative number
    n3 = -98765
    str3 = repr_int(n3)
    print(f"Input: {n3}, Output: {str3}")
    
    # Example 4: The special case from the C code
    n4 = -2147483648
    str4 = repr_int(n4)
    print(f"Input: {n4}, Output: {str4}")