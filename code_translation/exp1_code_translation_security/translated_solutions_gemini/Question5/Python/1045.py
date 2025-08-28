# No imports are needed as all functionality is built-in.

def power(base: int, exponent: int) -> int:
    """
    A direct translation of the C power function.
    Calculates `base` raised to the power of `exponent`.
    
    Note: In Python, it's more idiomatic to use the `**` operator
    (e.g., `base ** exponent`) or the built-in `pow(base, exponent)`.
    """
    result = 1
    while exponent > 0:
        result *= base
        exponent -= 1
    return result

def number_to_string(num: int) -> str:
    """
    Translates the C function's purpose to idiomatic Python.
    It converts an integer to a string, formatting it with comma
    separators for thousands.
    
    The complex C implementation (calculating digits, allocating memory, 
    and placing commas manually) is replaced by a single, efficient, and 
    readable line in Python using an f-string with a format specifier.
    """
    # The format specifier ":," tells Python to format the number
    # with a comma as a thousands separator.
    return f"{num:,}"

def main():
    """
    A translation of the C main function.
    """
    num = -7000000
    str_num = number_to_string(num)
    print(str_num)

# In Python, it's standard practice to use this construct to ensure
# the main() function is called only when the script is executed directly.
if __name__ == "__main__":
    main()