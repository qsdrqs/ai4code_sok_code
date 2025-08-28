# No imports are needed for this functionality as it's built into the language.

def num_digits(n: int) -> int:
  """
  Calculates the number of digits in an integer.
  Note: The original C code had a bug where it would fail for n=0. 
  This version handles it correctly.
  """
  if n == 0:
    return 1
  # A simple and Pythonic way to count digits is to convert the number to a string
  # and get its length. We use abs() to handle negative numbers correctly.
  return len(str(abs(n)))

def return_string(num: int) -> str:
  """
  Takes an integer and returns it as a string with comma separators for thousands.
  
  The original C code's logic for this was flawed. Python's f-strings provide
  a simple, powerful, and correct way to achieve this.
  """
  # The format specifier ":," tells Python to format the number with a comma
  # as a thousands separator.
  return f"{num:,}"

# In Python, the main execution block is typically wrapped in this conditional.
# This ensures the code only runs when the script is executed directly.
if __name__ == "__main__":
  num = 7000000
  formatted_str = return_string(num)
  print(formatted_str)