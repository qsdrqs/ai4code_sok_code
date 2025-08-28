def convert(value: int) -> str:
    """
    Converts an integer to a string with commas as thousand separators.
    
    Args:
        value (int): The integer to be formatted.
        
    Returns:
        str: The formatted string with commas.
    """
    s = str(value)
    result = []

    for i, c in enumerate(s):
        result.append(c)
        dist = len(s) - i - 1
        if dist != 0 and dist % 3 == 0:
            result.append(',')

    return ''.join(result)

if __name__ == "__main__":
    test = convert(1000)
    print(test)  # Output: 1,000