def convert(value: int) -> str:
    """
    Converts an integer into a string with commas as thousand separators.

    Args:
        value (int): The integer to be converted.

    Returns:
        str: The string representation of the integer with commas.
    """
    return f"{value:,}"

def main():
    test = convert(1000)
    print(test)

if __name__ == "__main__":
    main()