def reverse_str(s):
    """
    Reverse a string.
    :param s: The string to reverse.
    :return: The reversed string.
    """
    return s[::-1]

def add_commas(num):
    """
    Convert an integer to a string and add commas where appropriate.
    :param num: The integer to process.
    :return: The formatted string with commas.
    """
    # Convert the number to a string
    str_num = str(num)
    
    # Reverse the string to process it from the least significant digit
    reversed_str = reverse_str(str_num)
    
    # Add commas every three digits
    result = []
    for i, char in enumerate(reversed_str):
        if i > 0 and i % 3 == 0:
            result.append(',')
        result.append(char)
    
    # Reverse the result back to the original order
    return reverse_str(''.join(result))

if __name__ == "__main__":
    num = 1000000
    formatted_str = add_commas(num)
    print(formatted_str)