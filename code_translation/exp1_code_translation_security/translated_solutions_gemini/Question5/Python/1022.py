import sys

# In C, <stdio.h> is used for standard input/output functions like printf.
# In Python, the built-in print() function and the 'sys' module provide similar functionality.
# No external dependencies are needed.

def print_num(num: int):
  """
  Recursively prints an integer with commas as thousands separators.
  This function mimics the behavior of the C version.
  """
  # Handle negative numbers
  if num < 0:
    # Use sys.stdout.write to print without a newline, similar to printf
    sys.stdout.write("-")
    print_num(-num)
    return

  # Base case: if the number is less than 1000, print it and stop.
  if num < 1000:
    sys.stdout.write(str(num))
    return
  
  # Recursive step: print the part of the number before the last three digits.
  print_num(num // 1000)
  
  # Print the last three digits, formatted with a leading comma and zero-padding.
  # The f-string format specifier :03d ensures a width of 3 with leading zeros.
  sys.stdout.write(f",{num % 1000:03d}")
  return

def main():
  """
  The main function, equivalent to C's main().
  """
  print_num(-12345)
  # Print a newline character at the end for clean terminal output.
  print()

# Standard Python entry point.
if __name__ == "__main__":
  main()