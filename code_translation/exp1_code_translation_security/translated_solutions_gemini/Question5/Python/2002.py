import locale

def sig_num_to_str(num: int) -> str:
  """
  Converts an integer to a string, using the system's locale to add 
  thousands separators.

  This is a Python translation of the C function that uses setlocale() 
  and sprintf() with the "'" format specifier.

  Args:
    num: The integer to be converted.

  Returns:
    A string representation of the number, formatted according to the
    current locale's numeric settings.
  """
  # In C, setlocale(LC_NUMERIC, "") sets the numeric formatting rules
  # to the user's default locale. This is the direct Python equivalent.
  # We wrap it in a try-except block for robustness in environments
  # where the default locale might not be configured.
  try:
    locale.setlocale(locale.LC_NUMERIC, "")
  except locale.Error:
    # Fallback to a standard locale if the system's default is not supported
    locale.setlocale(locale.LC_NUMERIC, "en_US.UTF-8")

  # The C code `sprintf(text, "%'d", num)` formats the number with
  # locale-specific thousands separators.
  # The Python equivalent is locale.format_string() with grouping=True.
  # Note: An f-string like f'{num:,}' would always use a comma and ignore
  # the locale, so locale.format_string is the correct translation.
  return locale.format_string("%d", num, grouping=True)

# --- Example Usage ---
if __name__ == '__main__':
  number_to_format = 1234567890
  formatted_string = sig_num_to_str(number_to_format)
  
  print(f"Original number: {number_to_format}")
  print(f"Formatted string: {formatted_string}")

  negative_number = -98765
  formatted_negative = sig_num_to_str(negative_number)
  print(f"Original number: {negative_number}")
  print(f"Formatted string: {formatted_negative}")