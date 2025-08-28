def num_digits(n):
    digits = 0
    
    while n != 0:
        n //= 10
        digits += 1
    
    return digits

def int_to_string_with_commas(value):
    is_negative = value < 0
    if is_negative:
        value *= -1
    
    digits = num_digits(value)
    num_commas = digits // 3
    if digits % 3 == 0:
        num_commas -= 1
    
    s = str(value)
    size = digits + num_commas + 1
    dest = [''] * size
    index = size - 2
    
    while digits > 0:
        dest[index] = s[digits - 1]
        index -= 1
        if digits % 3 == 0 and digits > 0:
            dest[index] = ','
            index -= 1
        digits -= 1
    
    result = ''.join(dest[:-1])  # Exclude the last empty element (null terminator equivalent)
    print(result)
    return result

def main():
    int_to_string_with_commas(7000000)

if __name__ == "__main__":
    main()