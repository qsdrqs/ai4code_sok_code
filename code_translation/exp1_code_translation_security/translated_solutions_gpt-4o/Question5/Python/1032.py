def int_to_string(num):
    """
    Converts an integer to a string representation.
    """
    return str(num)

def int_to_string_with_commas(num):
    """
    Converts an integer to a string representation with commas as thousand separators.
    """
    # Convert the integer to a string
    str_num = int_to_string(num)
    len_str = len(str_num)
    comma_count = (len_str - 1) // 3

    # Create a new list to hold the characters (including commas)
    new_str = [''] * (len_str + comma_count)

    j = 0  # Index for the original string
    k = 0  # Flag to track if a comma was added
    for i in range(len_str + comma_count):
        if (len_str - j) % 3 == 0 and k != 1 and i != 0:
            new_str[i] = ','  # Add a comma
            k = 1
        else:
            new_str[i] = str_num[j]  # Add the digit
            j += 1
            if k == 1:
                k = 0

    # Join the list into a single string
    return ''.join(new_str)

if __name__ == "__main__":
    num = 7654321
    result = int_to_string_with_commas(num)
    print(result)