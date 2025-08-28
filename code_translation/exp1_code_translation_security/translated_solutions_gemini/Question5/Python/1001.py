def inttostring(x: int) -> str:
  """
  Converts an integer to its string representation.

  Args:
    x: The integer to convert.

  Returns:
    The string representation of the integer.
  """
  # In Python, you can simply use the built-in str() constructor to convert
  # an integer (or many other types) to a string.
  # Python's strings are dynamically sized, so there's no need to
  # pre-allocate a buffer like in C with malloc. Memory management is
  # handled automatically by Python's garbage collector.
  return str(x)

# Example usage:
my_integer = 12345
my_string = inttostring(my_integer)

print(f"The integer is: {my_integer}")
print(f"The converted string is: '{my_string}'")
print(f"The type of the result is: {type(my_string)}")

# --- Output ---
# The integer is: 12345
# The converted string is: '12345'
# The type of the result is: <class 'str'>