import ctypes

def int2str(num: int) -> bytes:
    """
    Converts an integer to a bytes object representing the string.

    Args:
    num (int): The integer to convert.

    Returns:
    bytes: A bytes object containing the string representation of the integer.
    """
    return str(num).encode('utf-8')

# Alternatively, if you want to return a ctypes.c_char_p (similar to char* in C)
import ctypes

def int2str_ctypes(num: int) -> ctypes.c_char_p:
    """
    Converts an integer to a ctypes.c_char_p object representing the string.

    Args:
    num (int): The integer to convert.

    Returns:
    ctypes.c_char_p: A ctypes.c_char_p object containing the string representation of the integer.
    """
    return ctypes.c_char_p(str(num).encode('utf-8'))

# Example usage:
if __name__ == "__main__":
    num = 123
    str_bytes = int2str(num)
    str_ctypes = int2str_ctypes(num)

    print(f"Bytes representation: {str_bytes}")
    print(f"ctypes.c_char_p representation: {str_ctypes.value.decode('utf-8')}")