import math

def convert_to_string(num: int) -> str:
    """
    Converts an integer into a string with commas as thousand separators.

    Args:
        num (int): The number to convert.

    Returns:
        str: The string representation of the number with commas.
    """
    return f"{num:,}"

def main():
    print(convert_to_string(2000))

if __name__ == "__main__":
    main()