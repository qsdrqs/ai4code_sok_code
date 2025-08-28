def int_to_string(x):
    """
    Converts an integer to a string with commas inserted every three digits,
    starting from the right.

    Args:
        x (int): The integer to convert.

    Returns:
        str: The formatted string with commas.
    """
    s = str(x)
    result = []
    count = 0

    # Process digits in reverse to insert commas every 3 digits
    for digit in reversed(s):
        if count == 3:
            result.append(',')
            count = 0
        result.append(digit)
        count += 1

    # Reverse the list to get the correct order and join into a string
    return ''.join(reversed(result))


if __name__ == '__main__':
    # Print the formatted string without a newline
    print(int_to_string(100000), end='')
    # Print "test" followed by a newline
    print("test")