# -------------------------------
#  c o n v e r t I n t . p y
# -------------------------------

def convert_int(value: int) -> str:
    """
    Manual conversion of an int to a string,
    similar to the first C function (but fixed).
    """
    # Handle zero explicitly
    if value == 0:
        return "0"

    negative = value < 0
    value    = abs(value)

    digits = []
    while value > 0:
        digit = value % 10
        digits.append(chr(digit + 48))   # 48 == ASCII '0'
        value //= 10

    if negative:
        digits.append('-')

    # We collected digits in reverse order
    return ''.join(reversed(digits))


def convert_int_ai(value: int) -> str:
    """
    Straight conversion using Python's built-in facilities,
    equivalent to sprintf in the original C code.
    """
    return str(value)


def main() -> None:
    result = convert_int_ai(7_000_000)   # 7,000,000
    print(result)                        # → 7000000


if __name__ == "__main__":
    main()