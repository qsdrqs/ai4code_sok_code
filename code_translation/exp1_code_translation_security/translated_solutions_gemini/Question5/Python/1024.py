# In Python, functionality from standard libraries like C's <stdio.h>
# is often available through built-in functions, so no imports are needed for this task.

def int_to_str(num: int) -> str:
  """
  Converts an integer into its string representation.

  This function is the Python equivalent of the C code's logic.
  In Python, converting an integer to a string is a built-in operation.

  Args:
    num: The integer to be converted.

  Returns:
    The string representation of the integer.
  """
  return str(num)

# --- Example of how to use the function ---
if __name__ == "__main__":
    my_integer = 42
    my_string = int_to_str(my_integer)

    print(f"The original integer: {my_integer}")
    print(f"The converted string: '{my_string}'")

    # We can verify the type has changed
    print(f"Type of original variable: {type(my_integer)}")
    print(f"Type of converted variable: {type(my_string)}")