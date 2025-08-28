def int_to_string(num):
    length = 0
    temp = num
    while temp != 0:
        temp //= 10
        length += 1
    
    str_list = [''] * (length + 1)
    str_list[length] = '\0'
    
    for i in range(length - 1, -1, -1):
        str_list[i] = chr((num % 10) + ord('0'))
        num //= 10
    
    return ''.join(str_list[:-1])  # Exclude the null terminator

def int_to_string_with_commas(num):
    str_num = int_to_string(num)
    length = len(str_num)
    comma_count = (length - 1) // 3
    new_str = [''] * (length + comma_count + 1)
    
    new_str[length + comma_count] = '\0'
    
    j = 0
    k = 0
    for i in range(length + comma_count):
        if (length - j) % 3 == 0 and k != 1 and i != 0:
            new_str[i] = ','
            k = 1
        else:
            new_str[i] = str_num[j]
            j += 1
            if k == 1:
                k = 0
    
    return ''.join(new_str[:-1])  # Exclude the null terminator

def main():
    num = 7654321
    str_result = int_to_string_with_commas(num)
    print(str_result)

if __name__ == "__main__":
    main()