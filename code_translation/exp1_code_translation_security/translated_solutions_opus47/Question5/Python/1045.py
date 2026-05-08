def power(base, exponent):
    result = 1
    while exponent > 0:
        result *= base
        exponent -= 1
    return result


def number_to_string(num):
    num_size = 0
    i = 0
    
    temp = num
    if num < 0:
        temp *= -1
    while temp > 0:
        temp //= 10
        num_size += 1
    
    comma_count = (num_size - 1) // 3
    # Pre-allocate list to simulate C's malloc'd char array
    # Size is num_size + comma_count + 1 (+1 for null terminator in C)
    str_num = [''] * (num_size + comma_count + 1)
    
    if num < 0:
        str_num[0] = '-'
        num *= -1
        i += 1
    
    for j in range(num_size - 1, -1, -1):
        str_num[i] = str(num // power(10, j))
        num %= power(10, j)
        i += 1
        if (j % 3 == 0) and (j != 0):
            str_num[i] = ','
            i += 1
    
    # In C, str_num[i] = '\0' marks end of string.
    # In Python, we just join characters up to index i.
    return ''.join(str_num[:i])


def main():
    num = -7000000
    str_num = number_to_string(num)
    print(str_num)


if __name__ == "__main__":
    main()