import ctypes

# Define the maximum string length
MAX_STR_LEN = 128

def reverse_str(s: str) -> str:
    """
    Reverses the input string.

    Args:
        s (str): The input string.

    Returns:
        str: The reversed string.
    """
    return s[::-1]


def str_of_int(num: int) -> str:
    """
    Returns a string representation of a signed integer.

    Args:
        num (int): The input number as a signed integer.

    Returns:
        str: The string representation of the signed integer.
    """
    is_neg = False
    if num < 0:
        is_neg = True
        num = -num

    str_num = ''
    while num != 0:
        str_num += chr(num % 10 + ord('0'))
        num //= 10

    if is_neg:
        str_num += '-'

    return reverse_str(str_num)


def main():
    print(str_of_int(7000000))


if __name__ == "__main__":
    main()