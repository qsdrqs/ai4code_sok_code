def add_commas(num):
    """
    Converts an integer into a string with commas.

    Args:
        num (int): The input integer.

    Returns:
        str: The string representation of the integer with commas.
    """
    return f"{num:,}"


# Example usage
if __name__ == "__main__":
    num = 1000000
    print(add_commas(num))