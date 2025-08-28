import math


def string_rep(num: int) -> str:
    """
    Rough, line-by-line translation of the original C function.
    Any explicit memory handling is unnecessary in Python,
    but the call to math.log10 is left in to mirror the C code.
    """
    # “size” does nothing useful in Python – strings are automatically sized.
    size = int(math.log10(num))          # kept only to stay faithful to the C source
    # Build and return the string representation of the number
    return f"{num}"


def main() -> None:
    leet = 1337
    leet_str = string_rep(leet)
    print(leet_str)


if __name__ == "__main__":
    main()