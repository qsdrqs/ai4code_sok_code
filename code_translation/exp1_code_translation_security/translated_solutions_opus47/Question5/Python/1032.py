def int_to_string(num):
    length = 0
    temp = num
    while temp != 0:
        temp //= 10
        length += 1
    str_list = [''] * length
    for i in range(length - 1, -1, -1):
        str_list[i] = chr((num % 10) + ord('0'))
        num //= 10
    return ''.join(str_list)


def int_to_string_with_commas(num):
    s = int_to_string(num)
    length = len(s)
    comma_count = (length - 1) // 3
    new_str = [''] * (length + comma_count)
    
    j = 0
    k = 0
    for i in range(length + comma_count):
        if (length - j) % 3 == 0 and k != 1 and i != 0:
            new_str[i] = ','
            k = 1
        else:
            new_str[i] = s[j]
            j += 1
            if k == 1:
                k = 0
    
    return ''.join(new_str)


def main():
    num = 7654321
    s = int_to_string_with_commas(num)
    print(s)


if __name__ == "__main__":
    main()