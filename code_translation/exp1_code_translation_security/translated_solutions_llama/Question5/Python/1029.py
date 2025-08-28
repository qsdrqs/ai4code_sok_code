import ctypes

def convert_int(input_num: int) -> str:
    """
    Converts an integer into a string manually.

    Args:
    input_num (int): The number to be converted.

    Returns:
    str: The string representation of the input number.
    """
    output = []
    while input_num > 0:
        output.append(chr(input_num % 10 + 48))
        input_num //= 10

    # Reverse the list to get the correct order
    output.reverse()
    return ''.join(output)


def convert_int_ai(input_num: int) -> str:
    """
    Converts an integer into a string using built-in functions.

    Args:
    input_num (int): The number to be converted.

    Returns:
    str: The string representation of the input number.
    """
    return str(input_num)


def main():
    output = convert_int_ai(7000000)
    print(output)


if __name__ == "__main__":
    main()