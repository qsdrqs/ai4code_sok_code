def convert(num: int) -> str:
  """
  Converts an integer to its string representation.

  In Python, this is a very simple operation using the built-in str() function.
  There is no need for manual memory allocation or calculating string length beforehand.
  """
  return str(num)

# This block is the standard entry point for a Python script,
# equivalent to the main() function in C.
if __name__ == "__main__":
  ans = convert(700000)
  print(ans)