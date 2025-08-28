def int_to_string(num):
    """
    Converts an integer to its string representation.
    This is a direct equivalent of the C function `int_to_string`.
    """
    return str(num)

def int_to_string_with_commas(num):
    """
    Converts an integer to a string with commas as thousand separators.
    This is a direct equivalent of the C function `int_to_string_with_commas`.
    """
    s = int_to_string(num)
    len_s = len(s)
    comma_count = (len_s - 1) // 3  # Number of commas needed

    new_str = []
    j = 0  # Index into the original string
    k = 0  # Flag to prevent consecutive commas

    for i in range(len_s + comma_count):
        if (len_s - j) % 3 == 0 and k != 1 and i != 0:
            new_str.append(',')
            k = 1
        else:
            new_str.append(s[j])
            j += 1
            if k == 1:
                k = 0

    return ''.join(new_str)

# Example usage
if __name__ == '__main__':
    num = 7654321
    result = int_to_string_with_commas(num)
    print(result)  # Output: 7,654,321