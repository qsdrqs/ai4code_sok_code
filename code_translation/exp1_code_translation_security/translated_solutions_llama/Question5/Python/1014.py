def strrev(input_str):
    """
    Reverses a given string.

    Args:
        input_str (str): The string to be reversed.

    Returns:
        str: The reversed string.
    """
    return input_str[::-1]


def integer_to_string(num):
    """
    Converts a signed integer to its string representation.

    Args:
        num (int): The integer to be converted.

    Returns:
        str: The string representation of the integer.
    """
    if num == 0:
        return '0'

    is_negative = False
    if num < 0:
        is_negative = True
        num = -num

    result = []
    while num != 0:
        digit = num % 10
        result.append(chr(ord('0') + digit))
        num //= 10

    if is_negative:
        result.append('-')

    return strrev(''.join(result))


def main():
    num = integer_to_string(12345)
    print(num)

    num = integer_to_string(-12345)
    print(num)


if __name__ == "__main__":
    main()