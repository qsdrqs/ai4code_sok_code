def power(base, exponent):
    result = 1
    while exponent > 0:
        result *= base
        exponent -= 1
    return result

def number_to_string(num):
    num_size = 0
    i = 0
    j = 0
    comma_count = 0
    
    temp = num
    if num < 0:
        temp *= -1
    
    # Calculate number of digits
    while temp > 0:
        temp //= 10  # Integer division in Python
        num_size += 1
    
    # Calculate number of commas needed
    comma_count = (num_size - 1) // 3  # Integer division
    
    # Create a list to build the string (equivalent to malloc in C)
    str_num = [''] * (num_size + comma_count + 1)
    
    # Handle negative numbers
    if num < 0:
        str_num[0] = '-'
        num *= -1
        i += 1
    
    # Build the string with commas
    for j in range(num_size - 1, -1, -1):  # j from num_size-1 down to 0
        str_num[i] = str((num // power(10, j)) % 10)  # Convert digit to string
        num %= power(10, j)
        i += 1
        if (j % 3 == 0) and (j != 0):
            str_num[i] = ','
            i += 1
    
    # Join the list into a string and return
    return ''.join(str_num[:i])  # Only join up to index i (equivalent to null terminator)

def main():
    num = -7000000
    str_num = number_to_string(num)
    print(str_num)

if __name__ == "__main__":
    main()