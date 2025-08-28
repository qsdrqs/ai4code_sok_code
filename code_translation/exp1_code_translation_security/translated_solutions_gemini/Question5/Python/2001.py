import sys

# The C code uses <limits.h> for INT_MAX and INT_MIN.
# Python's integers have arbitrary precision, so there isn't a fixed limit.
# For the purpose of this translation, we'll define constants that match
# a typical 32-bit signed integer, as used in the C example.
INT_MAX = 2147483647
INT_MIN = -2147483648

def int_to_string(a: int) -> str:
    """
    Converts an integer to a string, adding thousand separators.

    This function is a direct translation of the C algorithm's logic. It
    manually iterates through the string representation of the number to
    insert commas.

    Note: A more "Pythonic" way to achieve this is with a single line:
          return f"{a:,}"
    """
    # In C: char* buf = malloc(...); sprintf(buf, "%d", a);
    # In Python, we simply convert the integer to a string.
    buf = str(a)

    # In C: char* str = malloc(...); int idx = 0;
    # In Python, it's more efficient to build a list of characters and
    # then join them into a final string.
    result_chars = []

    # In C: char* actual_buf = buf; if (len > 0 && buf[0] == '-') { ... }
    # We handle the sign and get the string containing only the digits.
    actual_buf = buf
    if buf.startswith('-'):
      result_chars.append('-')
      actual_buf = buf[1:]

    len_actual = len(actual_buf)

    # In C: for (; buf_idx < len; buf_idx++) { ... }
    # We loop through the string of digits.
    for i, char in enumerate(actual_buf):
      # In C: if (buf_idx != 0 && (len - buf_idx) % 3 == 0) { str[idx++] = ','; }
      # The logic is identical: if we are not at the start and the
      # number of remaining digits is a multiple of 3, we insert a comma.
      if i > 0 and (len_actual - i) % 3 == 0:
        result_chars.append(',')

      # In C: str[idx++] = actual_buf[buf_idx];
      result_chars.append(char)

    # In C: str[idx] = '\0'; return str;
    # In Python, we join the list of characters. Memory management (like the
    # C code's malloc/free) is handled automatically by Python's garbage collector.
    # Note: The original C code has a memory leak, as the string returned by
    # int_to_string is never freed in main. This is not an issue in Python.
    return "".join(result_chars)

def main():
  """
  Replicates the main function from the C code to test the translation.
  """
  print(int_to_string(7000000))
  print(int_to_string(700000))
  print(int_to_string(70000))
  print(int_to_string(7000))
  print(int_to_string(700))
  print(int_to_string(70))
  print(int_to_string(7))
  print(int_to_string(0))
  print(int_to_string(-7000000))
  print(int_to_string(-700000))
  print(int_to_string(-70000))
  print(int_to_string(-7000))
  print(int_to_string(-700))
  print(int_to_string(-70))
  print(int_to_string(-7))
  print(int_to_string(INT_MAX))
  print(int_to_string(INT_MIN))

# Standard Python entry point to run the main function
if __name__ == "__main__":
  main()