def e10(x: int) -> int:
    """
    Calculate 10 raised to the power of x.

    Args:
    x (int): The exponent.

    Returns:
    int: 10 raised to the power of x.
    """
    return 10 ** x


def signum(x: int) -> int:
    """
    Calculate the signum of x.

    Args:
    x (int): The input number.

    Returns:
    int: -1 if x < 0, 0 if x == 0, 1 if x > 0.
    """
    if x < 0:
        return -1
    elif x == 0:
        return 0
    else:
        return 1


def abs(x: int) -> int:
    """
    Calculate the absolute value of x.

    Args:
    x (int): The input number.

    Returns:
    int: The absolute value of x.
    """
    return x * signum(x)


def int_to_str(x: int) -> str:
    """
    Convert an integer to a string with commas as thousand separators.

    Args:
    x (int): The input integer.

    Returns:
    str: The string representation of x with commas.
    """
    exp_offset = 0
    out = []

    if x < 0:
        out.append('-')
        x = abs(x)

    while x > 0:
        digit = x % 10
        out.append(chr(ord('0') + digit))
        x //= 10

        if len(out) % 3 == 0 and x > 0:
            out.append(',')

    out.reverse()

    return ''.join(out)


def main():
    print(int_to_str(55))
    print(int_to_str(12345))
    print(int_to_str(-55))
    print(int_to_str(-123456789))


if __name__ == "__main__":
    main()