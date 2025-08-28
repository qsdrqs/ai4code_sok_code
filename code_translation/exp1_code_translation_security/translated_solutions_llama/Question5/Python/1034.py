import random
import time

def add_commas(value):
    """Add commas to a given integer for better readability."""
    value_str = str(value)
    result = ""

    # Start from the end of the string and add commas every 3 digits
    for i, char in enumerate(reversed(value_str)):
        if i % 3 == 0 and i != 0:
            result = "," + result
        result = char + result

    return result

def main():
    # Seed the random number generator
    random.seed(int(time.time()))

    # Generate a random long integer
    value = random.randint(0, 2**63 - 1)  # equivalent to long in C

    print(f"Before: \t{value}")

    # Add commas to the integer
    formatted_value = add_commas(value)

    print(f"With commas: \t{formatted_value}")

if __name__ == "__main__":
    main()