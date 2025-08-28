def int2str_manual(num: int) -> str:
    """
    Converts an integer into a string with commas as thousand separators (manual implementation).

    Args:
        num (int): The input integer.

    Returns:
        str: The string representation of the integer with commas.
    """
    num_str = str(abs(num))
    result = []
    for i, char in enumerate(reversed(num_str)):
        if i % 3 == 0 and i != 0:
            result.append(',')
        result.append(char)
    result = ''.join(reversed(result))
    if num < 0:
        result = '-' + result
    return result

# Example usage:
if __name__ == "__main__":
    num = -1234567
    result = int2str_manual(num)
    print(result)  # Output: -1,234,567