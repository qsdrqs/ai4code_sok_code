def integer_to_string(num):
    """
    Converts a signed integer to its string representation.

    Parameters:
        num (int): The integer to convert.

    Returns:
        str: The string representation of the integer.
    """
    # Determine if the number is negative
    negative = num < 0
    if negative:
        num = -num  # Convert to positive for digit extraction

    # List to store digits in reverse order
    digits = []

    # Special case for zero
    if num == 0:
        digits.append('0')
    else:
        # Extract digits in reverse order
        while num > 0:
            digit = num % 10
            digits.append(chr(ord('0') + digit))
            num = num // 10

    # Append negative sign if needed
    if negative:
        digits.append('-')

    # Reverse the digits to get the correct order
    digits.reverse()

    # Join the list into a string and return
    return ''.join(digits)


def main():
    """
    Test the integer_to_string function with sample inputs.
    """
    # Test case 1: Positive number
    num = integer_to_string(12345)
    print(num)  # Expected output: "12345"

    # Test case 2: Negative number
    num = integer_to_string(-12345)
    print(num)  # Expected output: "-12345"

    # Optional: Test case for zero
    num = integer_to_string(0)
    print(num)  # Expected output: "0"


# Entry point of the script
if __name__ == "__main__":
    main()