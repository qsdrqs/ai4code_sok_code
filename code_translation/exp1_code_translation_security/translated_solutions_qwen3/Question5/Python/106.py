def str_of_int(num):
    """
    Converts a signed integer to its string representation.

    Args:
        num (int): The input number as a signed integer.

    Returns:
        str: String representation of the signed integer.
    """
    # Handle the special case of zero
    if num == 0:
        return '0'

    # Determine if the number is negative
    is_neg = num < 0
    if is_neg:
        num = -num  # Work with the absolute value

    # Collect digits in reverse order (from least significant to most)
    digits = []
    while num > 0:
        digits.append(num % 10)
        num = num // 10

    # Convert digits to strings
    chars = [str(d) for d in digits]

    # Append a minus sign if the number was negative
    if is_neg:
        chars.append('-')

    # Reverse the list to get the correct order
    chars = chars[::-1]

    # Join the characters into a final string
    return ''.join(chars)


# Main function equivalent in Python
if __name__ == "__main__":
    print(str_of_int(7000000))  # Expected output: "7000000"
    print(str_of_int(-12345))   # Expected output: "-12345"
    print(str_of_int(0))        # Expected output: "0"