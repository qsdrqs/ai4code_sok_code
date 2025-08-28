# No external dependencies are required for this translation.
# The logic is kept as close as possible to the original C code.

BUFFER_SIZE = 1024  # Not strictly needed in Python, but kept for parity


def convert(value: int) -> str:
    """
    Re-implementation of the C function ‘convert’ in Python.
    Inserts a comma every three characters, starting from the right.
    """
    # Equivalent to snprintf() into buf1
    buf1 = f"{value}"                      # The decimal representation
    buf2_chars = []                       # Will collect the output characters

    length = len(buf1)
    for i, ch in enumerate(buf1):
        buf2_chars.append(ch)

        dist = length - i - 1             # Same `dist` computation as in C
        if dist % 3 == 0 and dist != 0:   # Time to insert a comma?
            buf2_chars.append(',')

    # Join the list into the final string (equivalent to null-terminating buf2)
    return ''.join(buf2_chars)


def main() -> None:
    test = convert(1000)
    print(test)  # Expected: 1,000


if __name__ == "__main__":
    main()