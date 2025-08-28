import ctypes

def string_it(value: int) -> str:
    """
    Returns a string containing the expansion of the signed int with commas.

    Args:
    value (int): The input integer.

    Returns:
    str: A string representation of the input integer with commas.
    """

    # Determine if the number is negative and make it positive for processing
    is_negative = value < 0
    value = abs(value)

    # Convert the integer to a string
    str_value = str(value)

    # Add commas to the string
    formatted_str = "{:,}".format(value)

    # Add a negative sign if the original number was negative
    if is_negative:
        formatted_str = '-' + formatted_str

    return formatted_str


def main():
    print(f"   {string_it(-123457)}")


if __name__ == "__main__":
    main()