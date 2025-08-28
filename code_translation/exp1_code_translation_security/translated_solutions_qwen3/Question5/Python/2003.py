def num_to_string(num):
    if num == 0:
        return "0"

    is_neg = num < 0
    if is_neg:
        num = -num

    total_digits = 0
    cur_num = num
    while cur_num > 0:
        cur_num = cur_num // 10
        total_digits += 1

    num_commas = (total_digits - 1) // 3
    str_len = total_digits + num_commas
    if is_neg:
        str_len += 1

    # Initialize a list of characters to hold the result
    chars = [''] * str_len

    cur_num = num
    index = 0
    digits = 0

    while cur_num > 0:
        digit = cur_num % 10
        chars[str_len - index - 1] = str(digit)
        cur_num = cur_num // 10
        index += 1
        digits += 1

        if digits % 3 == 0:
            chars[str_len - index - 1] = ','
            index += 1

    if is_neg:
        chars[0] = '-'

    return ''.join(chars)

# Example usage
print(num_to_string(-5305000))  # Output: -5,305,000