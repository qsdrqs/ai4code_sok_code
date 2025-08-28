# All dependencies are included below.
import locale

def convert(i: int) -> int:
  """
  Sets the program's locale to the user's default environment setting
  and then prints an integer formatted with the locale's thousands separator.

  Args:
    i: The integer to be formatted and printed.

  Returns:
    0, mirroring the C function's return value.
  """
  
  # In C: setlocale(LC_NUMERIC, "");
  # This sets the numeric formatting to the user's default locale.
  # The empty string "" tells setlocale to use the locale specified
  # by the user's environment variables.
  locale.setlocale(locale.LC_NUMERIC, "")
  
  # In C: printf("%'d\n", i);
  # The Python f-string with a comma format specifier achieves the same result,
  # printing the integer with thousands separators according to the current locale.
  # We use a larger number than the original to better demonstrate the separator.
  print(f"{i:,}")
  
  return 0

# This block is the standard entry point for a Python script,
# equivalent to the main() function in C.
if __name__ == "__main__":
  # Using a larger number to better demonstrate the thousands separator
  a = 400000
  convert(a)

# Expected output (for a US/UK locale):
# 400,000