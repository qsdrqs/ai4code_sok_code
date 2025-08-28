import sys

def int_to_string(a):
    # Convert integer to string (equivalent to sprintf)
    buf = str(a)
    buf_idx = 0
    
    # Initialize result string as a list (easier to build than string concatenation)
    str_list = []
    
    # Handle negative sign
    actual_buf = buf
    length = len(buf)
    if length > 0 and buf[0] == '-':
        str_list.append(buf[0])
        actual_buf = buf[1:]  # Skip the negative sign
        length -= 1
    
    # Add digits with comma separators
    for buf_idx in range(length):
        if buf_idx != 0 and (length - buf_idx) % 3 == 0:
            str_list.append(',')
        str_list.append(actual_buf[buf_idx])
    
    # Join the list to create the final string
    return ''.join(str_list)

def main():
    # Python's sys.maxsize is similar to INT_MAX, and -sys.maxsize-1 is similar to INT_MIN
    INT_MAX = 2147483647  # Standard 32-bit signed integer max
    INT_MIN = -2147483648  # Standard 32-bit signed integer min
    
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
    print(int_to_string(INT_MAX))
    print(int_to_string(INT_MIN))

if __name__ == "__main__":
    main()