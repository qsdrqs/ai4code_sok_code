# No imports are needed for this functionality in modern Python.

def int_to_string(x: int) -> str:
  """
  Translates the C function's INTENT, which was to format an integer
  with comma separators for thousands.

  The original C code was non-functional due to several bugs. This Python
  version provides the correct, intended behavior.
  """
  # In Python, you can format a number with comma separators directly
  # using an f-string. This is the most common and readable way.
  return f"{x:,}"

def main():
  """
  This function replicates the behavior of the C main function.
  """
  # The C code `printf("%s", ...)` does not print a newline at the end.
  # We replicate this in Python by setting the `end` parameter to an empty string.
  print(int_to_string(100000), end="")

  # The C code `printf("test\n")` prints "test" followed by a newline.
  # This is the default behavior of Python's print() function.
  print("test")

# This is the standard Python entry point, equivalent to `main()` in C.
if __name__ == "__main__":
  main()