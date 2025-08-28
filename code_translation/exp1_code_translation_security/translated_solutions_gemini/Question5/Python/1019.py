# No import is strictly necessary for this logic, but the original C code
# used the math library, so we can import it for completeness, even if unused.
import math 

def string_rep(num: int) -> str:
  """
  Converts an integer to its string representation.

  Args:
    num: The integer to convert.
  
  Returns:
    The string representation of the number.
  """
  # In Python, converting an integer to a string is a simple, built-in operation.
  return str(num)

# The main execution block in Python, equivalent to C's main() function.
if __name__ == "__main__":
  leet = 1337
  leet_str = string_rep(leet)
  print(leet_str)
  # There is no need for `free()` in Python. Memory is managed automatically.