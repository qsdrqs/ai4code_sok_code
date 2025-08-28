# All dependencies are built-in, so no imports are strictly necessary.

def reverse_str(s: str) -> str:
    """
    Reverses a given string. This is equivalent to the C function `reverse_str`.

    In C, strings are mutable character arrays, so the reversal is done "in-place".
    In Python, strings are immutable, so this function returns a new, reversed string.

    Args:
        s: The string to reverse.

    Returns:
        The reversed string.
    """
    # Python's slice notation [::-1] is a concise and efficient way to reverse a string.
    return s[::-1]

def add_commas(num: int) -> str:
    """
    Converts a non-negative integer into a string, adding commas for thousands separators.
    This is equivalent to the C function `add_commas`.

    Note: The original C code contains a memory allocation bug where it allocates
    memory proportional to the integer's value, not its number of digits.
    Python's automatic memory management and built-in formatting avoid this issue entirely.

    Args:
        num: The non-negative integer to convert.

    Returns:
        A string representation of the number with commas.
    """
    if not isinstance(num, int) or num < 0:
        raise ValueError("Input must be a non-negative integer.")

    # Python's f-strings provide a simple way to format numbers.
    # The ':, ' format specifier adds commas as a thousands separator.
    # This single line replaces the entire manual loop, string reversal,
    # and memory management from the C version.
    return f"{num:,}"

def main():
    """
    Main function to demonstrate the usage of the translated functions,
    equivalent to the main() function in the C code.
    """
    num = 1000000
    formatted_string = add_commas(num)
    print(f"The number {num} formatted with commas is: {formatted_string}")

    # The original C code's `add_commas` function relied on `reverse_str`.
    # While our Pythonic `add_commas` doesn't need it, we can still
    # demonstrate `reverse_str` as requested.
    print("\n--- Demonstrating reverse_str ---")
    original = "hello world"
    reversed_s = reverse_str(original)
    print(f"Original: '{original}'")
    print(f"Reversed: '{reversed_s}'")
    
    # No need to free memory like in C; Python's garbage collector handles it automatically.

# This block ensures that main() is called only when the script is executed directly.
if __name__ == "__main__":
    main()