MAX_LEN = 16


def num_to_string(num):
    if num == 0:
        return "0"
    
    is_neg = False
    if num < 0:
        is_neg = True
        num = -num
    
    cur_num = num
    total_digits = 0
    while cur_num > 0:
        cur_num = cur_num // 10
        total_digits += 1
    
    num_commas = (total_digits - 1) // 3
    str_len = total_digits + num_commas
    if is_neg:
        str_len += 1
    
    # Python equivalent of allocating a mutable character buffer
    str_list = [''] * str_len
    
    cur_num = num
    index = 0
    digits = 0
    while cur_num > 0:
        cur_char = chr((cur_num % 10) + 48)
        str_list[str_len - index - 1] = cur_char
        cur_num = cur_num // 10
        index += 1
        digits += 1
        if digits % 3 == 0:
            str_list[str_len - index - 1] = ','
            index += 1
    
    if is_neg:
        str_list[0] = '-'
    
    return ''.join(str_list)


def main():
    print(num_to_string(-5305000), end='')


if __name__ == "__main__":
    main()