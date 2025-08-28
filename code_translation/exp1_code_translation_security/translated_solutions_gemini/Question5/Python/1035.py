# This Python code is a translation of the provided C code.
# All original functions and logic are preserved.

# The C header <stdio.h> is not needed in Python, as the print() function
# for console output is a built-in feature.

def e10(x: int) -> int:
  """
  Calculates 10 to the power of x.
  Equivalent to the C e10 function.
  
  C code:
  int v = 1;
  for (int i = 0; i < x; i++){
    v *= 10;
  }
  return v;
  """
  return 10 ** x

def signum(x: int) -> int:
  """
  Returns the sign of a number: -1 for negative, 0 for zero, 1 for positive.
  Direct translation of the C signum function.
  """
  if x < 0:
    return -1
  if x == 0:
    return 0
  return 1

def abs(x: int) -> int:
  """
  Calculates the absolute value of x using the signum function.
  This function shadows Python's built-in abs() within this script's scope,
  mirroring how the C code provided its own `abs` implementation.
  """
  return x * signum(x)

def int_to_str(x: int) -> str:
  """
  Converts an integer to a string, adding commas as thousands separators.
  This function translates the algorithm of the C version, including its
  right-to-left string building approach.
  """
  # Note on C to Python translation choices:
  # 1. `static char out[16];`: The C code uses a static buffer that persists
  #    between calls. The Python version creates a new list for each call,
  #    which is safer and standard Python practice.
  # 2. Buffer Size: The C code has a bug where it can write to `out[16]`,
  #    one element past the end of a 16-char array. We use a list of size 17
  #    to replicate the intended behavior without causing an error.
  # 3. Logic Simplification: The C code's digit extraction and loop termination
  #    conditions are complex. This translation uses mathematically equivalent
  #    but clearer and more Pythonic expressions to achieve the same result.

  # A buffer to build the string from right to left.
  out = [''] * 17
  exp_offset = 0
  
  x_abs = abs(x)

  # The C `for` loop is translated to a `while` loop because the loop
  # counter `i` is manually incremented inside the loop body.
  i = 0
  while i < 16:
    digit_power = i - exp_offset
    
    # The C logic `abs((x % e10(digit+1)) / e10(digit))` is a complex
    # way to get the n-th digit. A simpler, equivalent formula is used here.
    current_digit = (x_abs // e10(digit_power)) % 10
    
    # Build the string from the right end of the buffer.
    out[16 - i] = str(current_digit)
    
    # The C termination condition `x % e10(digit+1) == x` is equivalent to
    # checking if we have processed all digits of the number.
    if x_abs < e10(digit_power + 1):
      if x < 0:
        # Add the negative sign to the left of the number.
        out[16 - (i + 1)] = '-'
        i += 1  # Account for the extra '-' character.
      
      # The C code returns a pointer to the start of the string in the buffer.
      # In Python, we slice the list and join the characters to form the final string.
      start_index = 16 - i
      return "".join(out[start_index:])
      
    # Insert commas after every 3 digits (from the right).
    if i == 2 or i == 6 or i == 10:
      out[16 - i - 1] = ','
      i += 1  # Account for the comma character.
      exp_offset += 1
      
    i += 1
  
  # This fallback is for numbers too large to be processed by the loop.
  # It returns the content of the buffer assembled so far.
  return "".join(out)


def main():
  """
  Equivalent to the C main function. It calls int_to_str with various
  inputs and prints the results to the console.
  """
  print(int_to_str(55))
  print(int_to_str(12345))
  print(int_to_str(-55))
  print(int_to_str(-123456789))

# Standard Python entry point to run the main function.
if __name__ == "__main__":
  main()