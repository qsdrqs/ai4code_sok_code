def get_length(num: int) -> int:
    """
    Calculate the number of digits in a given integer.

    Args:
    num (int): The input integer.

    Returns:
    int: The number of digits in the input integer.
    """
    return len(str(abs(num)))

def convert_to_string(num: int) -> str:
    """
    Convert an integer to a string.

    Args:
    num (int): The input integer.

    Returns:
    str: The string representation of the input integer.
    """
    return str(num)

def main():
    num = 7000000
    print(get_length(num))
    print(convert_to_string(5))

if __name__ == "__main__":
    main()