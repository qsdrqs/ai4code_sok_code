import math

def change(input_num):
    """
    Format a given integer with commas as thousand separators.

    Args:
        input_num (int): The number to be formatted.

    Returns:
        str: The formatted string with commas.
    """
    # Convert the number to a string to easily manipulate digits
    num_str = str(input_num)
    
    # Initialize an empty string to store the result
    result = ""

    # Initialize a counter to track the number of digits processed
    count = 0

    # Process the digits from right to left
    for i in range(len(num_str) - 1, -1, -1):
        # Add the current digit to the result
        result = num_str[i] + result
        
        # Increment the counter
        count += 1
        
        # If the counter is a multiple of 3, add a comma
        if count % 3 == 0 and i != 0:
            result = "," + result

    return result


def main():
    # Test the change function
    snum = change(700000000)
    print(snum)


if __name__ == "__main__":
    main()