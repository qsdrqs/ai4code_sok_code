import ctypes
import os

# In Python, converting an integer to a string is a built-in operation.
# The complexity in the C code comes from manual memory management, which Python
# handles automatically. The most direct and Pythonic way to achieve the
# same result is using the str() constructor.

def int2str(num: int) -> str:
  """
  Converts an integer to its string representation.

  Args:
    num: The integer to convert.

  Returns:
    The string representation of the integer.
  """
  return str(num)

# The C code uses `strdup` which allocates new memory for the string that the
# caller is responsible for freeing. While `str()` is the correct Python
# equivalent for the *functionality*, a more literal translation that
# mimics the memory allocation behavior of `strdup` could be written using
# ctypes, although this is highly unidiomatic and not recommended for
# practical Python code. It is provided here for completeness.

# Load the C standard library
libc = ctypes.CDLL(ctypes.util.find_library('c'))

# Define the argument and return types for strdup
# char *strdup(const char *);
libc.strdup.argtypes = [ctypes.c_char_p]
libc.strdup.restype = ctypes.c_char_p

def int2str_literal_translation(num: int) -> bytes:
  """
  A more literal, but unidiomatic, translation of the C code.
  This version mimics the C code's memory allocation using ctypes.
  The caller would be responsible for freeing the memory.
  NOTE: This is for educational purposes and should not be used in real code.
  """
  # Create a buffer and format the string into it, similar to snprintf
  # In Python, strings are immutable, so we work with bytes for C interop
  buf = f"{num}".encode('utf-8')

  # Duplicate the string in memory using the C function `strdup`
  # The result is a pointer to a new C string (char*)
  c_string_ptr = libc.strdup(buf)

  # In a real C-interfacing scenario, you would use this pointer and
  # eventually free it with libc.free(c_string_ptr)
  # For this example, we'll just show it can be converted back to a Python object
  py_bytes = ctypes.c_char_p.from_address(c_string_ptr).value

  # IMPORTANT: This memory is now leaked. In a real application, you must free it.
  # libc.free(c_string_ptr)

  return py_bytes


# --- Example Usage ---
if __name__ == '__main__':
  # The Pythonic way
  number = 12345
  string_representation = int2str(number)
  print(f"Pythonic way: int2str({number}) = '{string_representation}' (type: {type(string_representation)})")

  number = -987
  string_representation = int2str(number)
  print(f"Pythonic way: int2str({number}) = '{string_representation}' (type: {type(string_representation)})")

  # The literal (and not recommended) way
  # Note: This example leaks memory as we don't free the pointer from strdup
  literal_result = int2str_literal_translation(54321)
  print(f"Literal way:  int2str_literal_translation(54321) = {literal_result} (type: {type(literal_result)})")