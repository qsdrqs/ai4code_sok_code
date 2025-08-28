def num_to_str(num):
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

    result.reverse()

    if num < 0:
        result.insert(0, '-')

    return ''.join(result)


def main():
    num = 7042
    str_num = num_to_str(num)
    print(f"Output: {str_num}")


if __name__ == "__main__":
    main()