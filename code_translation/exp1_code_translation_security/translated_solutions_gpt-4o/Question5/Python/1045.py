def number_to_string(num):
    def power(base, exponent):
        result = 1
        while exponent > 0:
            result *= base
            exponent -= 1
        return result

    num_size = 0
    i = 0
    j = 0
    k = 0
    comma_count = 0
    temp = num

    if num < 0:
        temp *= -1

    while temp > 0:
        temp //= 10
        num_size += 1

    comma_count = (num_size - 1) // 3
    str_num = [''] * (num_size + comma_count + 1)

    if num < 0:
        str_num[0] = '-'
        num *= -1
        i += 1

    for j in range(num_size - 1, -1, -1):
        str_num[i] = chr((num // power(10, j)) + ord('0'))
        num %= power(10, j)
        i += 1
        if (j % 3 == 0) and (j != 0):
            str_num[i] = ','
            i += 1

    str_num[i] = '\0'
    return ''.join(str_num).rstrip('\0')

def main():
    num = -7000000
    str_num = number_to_string(num)
    print(str_num)

if __name__ == "__main__":
    main()