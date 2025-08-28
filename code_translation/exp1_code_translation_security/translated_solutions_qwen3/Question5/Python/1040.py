def num_digits(n):
    digits = 0
    n = abs(n)
    while n != 0:
        n //= 10
        digits += 1
    return digits

def int_to_string_with_commas(value):
    is_negative = value < 0
    if is_negative:
        value = -value

    digits = num_digits(value)
    num_commas = digits // 3
    if digits % 3 == 0:
        num_commas -= 1

    s = str(value)
    size = digits + num_commas + 1
    dest = [''] * size
    index = size - 2  # Start from the second to last index

    current_digits = digits
    while current_digits > 0:
        dest[index] = s[current_digits - 1]
        index -= 1
        current_digits -= 1
        if current_digits % 3 == 0 and current_digits > 0:
            dest[index] = ','
            index -= 1

    dest[-1] = '\0'  # Null-terminate the string
    joined_str = ''.join(dest)
    result = joined_str.split('\0', 1)[0]

    if is_negative:
        result = '-' + result

    return result

# Example usage
if __name__ == '__main__':
    print(int_to_string_with_commas(7000000))  # Expected output: "7,000,000"