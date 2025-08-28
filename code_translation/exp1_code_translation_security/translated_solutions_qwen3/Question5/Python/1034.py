import random
import time

# Seed the random number generator using the current time
random.seed(int(time.time()))

# Generate a random integer value (assuming 32-bit signed long in C)
value = random.randint(0, 2**31 - 1)

# Print the original value
print(f"Before:\t{value}")

# Convert the integer to a string for processing
vstring = str(value)

# Initialize variables to track positions in the string and the comma-inserted result
cur_len = len(vstring)
c1 = v1 = 0
commas = []

# Process each character in the string to insert commas every 3 digits
while v1 < len(vstring):
    if cur_len % 3 != 0:
        # If not divisible by 3, just add the digit
        commas.append(vstring[v1])
        c1 += 1
        v1 += 1
        cur_len -= 1
    else:
        # If divisible by 3, add a comma first (except at the start)
        if c1 != 0:
            commas.append(',')
            c1 += 1
        # Then add the current digit
        commas.append(vstring[v1])
        c1 += 1
        v1 += 1
        cur_len -= 1

# Join the list of characters into a final formatted string and print
print(f"With:\t{''.join(commas)}")