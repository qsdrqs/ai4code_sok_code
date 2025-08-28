# No extra dependencies are required; everything we need is in the standard library.

def convert(num: int) -> str:
    """
    Return the decimal string representation of an integer.
    (In the original C code, this is what snprintf was used for.)
    """
    # Simply rely on Python's built-in conversion.
    return str(num)


def main() -> None:
    ans = convert(700000)
    print(ans)


if __name__ == "__main__":
    main()