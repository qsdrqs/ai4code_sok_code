# Python translation of the given C program

MAX_STR_LEN = 128  # kept from the original C version for parity

def reverse_str(s: str) -> str:
    """
    Reverse a given string.

    Args:
        s (str): Input string.

    Returns:
        str: Reversed string.
    """
    # Using Python’s slicing to reverse
    return s[::-1]


def str_of_int(num: int) -> str:
    """
    Return a string representation of a signed integer.
    
    Args:
        num (int): Input number as a signed integer.
    
    Returns:
        str: String representation of the signed integer.
    """
    # A Pythonic one-liner would be `return str(num)`, but we mimic the C logic.
    
    # Handle zero explicitly
    if num == 0:
        return "0"
    
    is_neg = False
    if num < 0:
        is_neg = True
        num = -num  # work with positive counterpart
    
    chars = []  # list of single-character strings
    
    # Extract digits (in reverse order)
    while num != 0:
        digit = num % 10
        chars.append(chr(ord('0') + digit))  # convert digit to corresponding character
        num //= 10
    
    if is_neg:
        chars.append('-')  # append negative sign
    
    # `chars` now holds the characters in reverse order,
    # so join & reverse them to get the final string.
    return reverse_str(''.join(chars))


if __name__ == "__main__":
    print(str_of_int(7000000))