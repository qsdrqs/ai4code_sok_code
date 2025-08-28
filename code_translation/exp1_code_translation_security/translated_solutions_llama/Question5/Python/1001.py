import ctypes

def inttostring(x: int) -> ctypes.c_char_p:
    """
    Converts an integer to a string and returns a ctypes.c_char_p.

    Args:
        x (int): The integer to be converted.

    Returns:
        ctypes.c_char_p: The string representation of the integer as a ctypes.c_char_p.
    """
    str_x = str(x).encode('utf-8')
    buffer = ctypes.create_string_buffer(len(str_x) + 1)
    buffer.value = str_x
    return buffer

# Example usage:
if __name__ == "__main__":
    x = 123
    result = inttostring(x)
    print(result.value.decode('utf-8'))  # Output: 123