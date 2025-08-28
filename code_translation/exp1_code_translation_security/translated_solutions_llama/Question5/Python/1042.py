def int_to_string(num):
    """
    Converts an integer into a string with commas as thousand separators.

    Args:
        num (int): The number to be converted.

    Returns:
        str: The string representation of the number with commas.
    """
    num_str = str(abs(num))
    result = []
    for i, char in enumerate(reversed(num_str)):
        if i % 3 == 0 and i != 0:
            result.append(',')
        result.append(char)
    if num < 0:
        result.append('-')
    return ''.join(reversed(result))


def main():
    print(int_to_string(7000000))


if __name__ == "__main__":
    main()