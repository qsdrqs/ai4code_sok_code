import random
import time

def main():
  """
  A direct translation of the C code's logic for adding commas to a number string.
  """
  # In C: srand((unsigned)time(NULL));
  # Seeds the random number generator with the current time.
  # random.seed() with no arguments does the same in Python.
  random.seed()

  # In C: value = rand();
  # rand() typically generates a number up to RAND_MAX (e.g., 2^31 - 1).
  # We will simulate this with random.randint().
  value = random.randint(0, 2**31 - 1)

  # In C: printf("Before: \t%ld\n", value);
  print(f"Before: \t{value}")

  # In C: sprintf(vstring, "%ld", value);
  # In Python, we simply convert the integer to a string.
  vstring = str(value)

  # In C: len = strlen(vstring);
  v_len = len(vstring)
  
  # This list will hold the characters for our new string, similar to the 'commas' char array.
  commas_list = []

  # This loop translates the C 'while' loop logic.
  # We iterate through the original number string with an index.
  for index, digit in enumerate(vstring):
    # The C code's logic depends on the number of digits remaining.
    remaining_len = v_len - index

    # The core logic: if the number of remaining digits is a multiple of 3,
    # and we are not at the very beginning of the string, we need a comma.
    if (remaining_len % 3 == 0) and (index > 0):
      commas_list.append(',')
    
    # Append the current digit.
    commas_list.append(digit)

  # In Python, we join the list of characters to form the final string.
  commas = "".join(commas_list)

  # In C: printf("With: \t%s\n", commas);
  # Note: The original C code had a typo here.
  print(f"With: \t\t{commas}")

# Standard Python practice to run the main function.
if __name__ == "__main__":
  main()