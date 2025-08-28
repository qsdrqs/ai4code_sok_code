import math

def string_rep(num):
    """
    Convert an integer to a string representation.

    Args:
        num (int): The number to convert.

    Returns:
        str: The string representation of the number.
    """
    return str(num)

def main():
    leet = 1337
    leet_str = string_rep(leet)
    print(leet_str)

if __name__ == "__main__":
    main()