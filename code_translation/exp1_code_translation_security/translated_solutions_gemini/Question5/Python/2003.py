def num_to_string(num: int) -> str:
  """
  Converts an integer to a string, with commas as thousands separators.

  This function provides the Pythonic equivalent of the C code's functionality.
  It uses Python's built-in f-string formatting, where the ':, ' specifier
  automatically handles negative signs and adds comma separators.

  Args:
    num: The integer to convert.

  Returns:
    A comma-separated string representation of the number.
  """
  # The format specifier ':, ' tells Python to format the number
  # with a comma for a thousands separator.
  return f"{num:,}"

def main():
  """
  Main function to demonstrate the usage of num_to_string.
  """
  # The C code's main function calls num_to_string with -5305000 and prints the result.
  # Expected output: -5,305,000
  print(num_to_string(-5305000))
  
  # Additional test cases to show correctness
  print(num_to_string(1234567890)) # Expected: 1,234,567,890
  print(num_to_string(123456))     # Expected: 123,456 (Handles the case the C code was buggy on)
  print(num_to_string(0))         # Expected: 0
  print(num_to_string(-999))      # Expected: -999

# Standard Python entry point, equivalent to the C main() function.
if __name__ == "__main__":
  main()