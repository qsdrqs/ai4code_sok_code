import locale

def sig_num_to_str(num: int) -> str:
    """
    Converts a signed integer to a string with locale-specific thousand separators.

    Args:
        num (int): The number to convert.

    Returns:
        str: The string representation of the number with thousand separators.
    """
    locale.setlocale(locale.LC_ALL, '')
    return f"{num:,}"

# Example usage:
if __name__ == "__main__":
    num = 1234567
    print(sig_num_to_str(num))