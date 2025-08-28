# int_to_string.py

INT_MAX = 2 ** 31 - 1          #  2147483647
INT_MIN = -2 ** 31             # -2147483648


def int_to_string(a: int) -> str:
    """
    Convert an integer to a string with commas as thousands separators.
    Keeps any leading minus sign for negative numbers.
    """
    # Handle the sign first
    negative = a < 0
    abs_value = -a if negative else a

    # Convert absolute value to string and insert commas
    digits = str(abs_value)
    pieces = []

    # Collect groups of three from the right
    while digits:
        pieces.append(digits[-3:])
        digits = digits[:-3]

    # Re-assemble with commas
    result = ','.join(reversed(pieces))

    # Prepend minus sign if necessary
    if negative:
        result = '-' + result

    return result


def main():
    test_values = [
        7_000_000, 700_000, 70_000, 7_000, 700, 70, 7, 0,
        -7_000_000, -700_000, -70_000, -7_000, -700, -70, -7,
        INT_MAX, INT_MIN
    ]

    for val in test_values:
        print(int_to_string(val))


if __name__ == "__main__":
    main()