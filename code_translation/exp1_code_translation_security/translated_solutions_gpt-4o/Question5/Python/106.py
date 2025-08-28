# Define the maximum string length
MAX_STR_LEN = 128

def reverse_str(s):
    """
    Reverse a given string.

    Args:
        s (str): Input string.

    Returns:
        str: Reversed string.
    """
    return s[::-1]

def str_of_int(num):
    """
    Return a string representation of a signed integer.

    Args:
        num (int): Input number as a signed integer.

    Returns:
        str: String representation of the signed integer.
    """
    is_neg = False
    if num < 0:
        is_neg = True
        num = -num

    result = []
    while True:
        result.append(chr(num % 10 + ord('0')))
        num //= 10
        if num == 0:
            break

    if is_neg:
        result.append('-')

    # Reverse the list of characters and join them into a string
    return reverse_str(''.join(result))

if __name__ == "__main__":
    print(str_of_int(7000000))