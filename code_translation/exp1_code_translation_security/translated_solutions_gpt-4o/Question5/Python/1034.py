import random
import time

def main():
    # Initialize variables
    value = 0
    c1 = 0
    v1 = 0
    vstring = ""
    commas = []

    # Seed the random number generator
    random.seed(int(time.time()))
    value = random.randint(0, 2**31 - 1)  # Simulating `rand()` in C

    print(f"Before: \t{value}")
    vstring = str(value)  # Convert the number to a string

    length = len(vstring)
    c1 = v1 = 0

    while v1 < len(vstring):
        if length % 3:
            commas.append(vstring[v1])
        else:
            if c1 != 0:
                commas.append(',')
            commas.append(vstring[v1])
        c1 += 1
        v1 += 1
        length -= 1

    # Join the list of characters into a string
    commas_string = ''.join(commas)
    print(f"With: \t{commas_string}")

if __name__ == "__main__":
    main()