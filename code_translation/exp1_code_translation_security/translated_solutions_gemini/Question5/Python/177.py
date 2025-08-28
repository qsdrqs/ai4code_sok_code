# No special imports are needed. The required functionality is built into Python.

def int2str(num: int) -> str:
    """
    Converts a signed integer to a string with thousands separators.

    This function translates the *intent* of the original C code. The C
    code attempted to perform this conversion manually but contained several
    bugs. This Python version achieves the correct result in a clean,
    safe, and idiomatic way using Python's built-in string formatting
    capabilities.

    Args:
        num: The signed integer to be converted.

    Returns:
        A string representation of the number, formatted with commas.
    """
    # Python's f-string formatting provides a direct and robust way to
    # add thousand separators. The ':, ' format specifier handles all the
    # logic for both positive and negative integers automatically.
    return f"{num:,}"

# Example usage to demonstrate the function's behavior.
# This block is equivalent to a 'main' function in C for testing.
if __name__ == '__main__':
    test_cases = [
        123,
        1234,
        123456,
        1234567,
        9876543210,
        0,
        -500,
        -5432,
        -5432109,
    ]

    print("--- Testing Integer to Formatted String Conversion ---")
    for number in test_cases:
        formatted_string = int2str(number)
        # The print statement is formatted for clarity to show the input and output.
        print(f"Input: {number:>12} -> Output: '{formatted_string}'")