def int_to_string(a):
    # Convert the integer to a string
    buf = str(a)
    
    # If the number is negative, start with the negative sign
    if buf[0] == '-':
        str_result = '-'
        buf = buf[1:]  # Remove the negative sign for further processing
    else:
        str_result = ''
    
    # Calculate the length of the number string
    len_buf = len(buf)
    
    # Iterate over the string and insert commas
    for i in range(len_buf):
        if i != 0 and (len_buf - i) % 3 == 0:
            str_result += ','
        str_result += buf[i]
    
    return str_result

def main():
    print(int_to_string(7000000))
    print(int_to_string(700000))
    print(int_to_string(70000))
    print(int_to_string(7000))
    print(int_to_string(700))
    print(int_to_string(70))
    print(int_to_string(7))
    print(int_to_string(0))
    print(int_to_string(-7000000))
    print(int_to_string(-700000))
    print(int_to_string(-70000))
    print(int_to_string(-7000))
    print(int_to_string(-700))
    print(int_to_string(-70))
    print(int_to_string(-7))
    print(int_to_string(2**31 - 1))  # INT_MAX
    print(int_to_string(-2**31))     # INT_MIN

if __name__ == "__main__":
    main()