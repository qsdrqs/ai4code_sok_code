def num_digits(n):
    """Returns the number of digits in a given integer."""
    return len(str(abs(n)))

def int_to_string_with_commas(value):
    """
    Converts an integer into a string with commas as thousand separators.

    Args:
        value (int): The integer to be converted.

    Returns:
        str: The string representation of the integer with commas.
    """
    is_negative = value < 0
    if is_negative:
        value *= -1
    digits = num_digits(value)
    num_commas = digits // 3
    if digits % 3 == 0:
        num_commas -= 1

    # Convert the integer to a string and add commas
    result = "{:,}".format(value)

    # Handle negative numbers
    if is_negative:
        result = "-" + result

    print(result)
    return result

def main():
    int_to_string_with_commas(7000000)

if __name__ == "__main__":
    main()