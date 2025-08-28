import locale

def print_number(num: int):
  """
  Sets the locale to the user's default and prints a number
  formatted with the appropriate thousands separator.
  """
  # Set the locale to the user's default setting.
  # An empty string "" tells it to use the system's default locale.
  # LC_NUMERIC affects how numbers are formatted.
  locale.setlocale(locale.LC_NUMERIC, "")

  # The 'n' format specifier in an f-string formats the number
  # according to the current locale's settings.
  # This is the direct Python equivalent of C's "%'d".
  print(f"{num:n}")


# The standard Python entry point, equivalent to C's main()
if __name__ == "__main__":
  print_number(7000000)

# --- Example Output ---
# On a US English system, this will print: 7,000,000
# On a German system, this will print: 7.000.000