import ctypes

# Define the maximum number of digits
MAX_DIGITS = 100

# Function to reverse a string
def reverse(s):
    """Reverses a given string."""
    return s[::-1]

# Function to get the string representation of an integer
def repr_int(n):
    """
    Returns the string representation of a signed integer.

    Args:
        n (int): The integer to be converted.

    Returns:
        str: The string representation of the integer.
    """
    if n == -2**31:
        # When multiplied by -1 this overflows, so handle it separately.
        return "-2,147,483,648"
    if n == 0:
        return '0'

    sign = '-' if n < 0 else ''
    n = abs(n)
    result = ''

    while n > 0:
        result = str(n % 10) + result
        if len(result) % 4 == 0 and n > 10:
            result = ',' + result
        n //= 10

    return sign + result

# Main function
def main():
    n = 0
    print(repr_int(n))

if __name__ == "__main__":
    main()