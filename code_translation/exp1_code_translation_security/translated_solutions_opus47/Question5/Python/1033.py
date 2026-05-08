def num_to_str(num):
    """Convert a number to a string with commas as thousand separators.
    
    Direct translation from the original C function.
    """
    # Equivalent to sprintf(str, "%d", num)
    s = str(num)
    length = len(s)
    
    # Python strings are immutable, so we use a list to simulate char array
    # Initialize with extra null chars to simulate the C buffer (char str[24])
    str_buf = list(s) + ['\0'] * 24
    
    num_commas = 0
    
    if num == 0:
        return s
    elif num > 0:
        num_commas = (length - 1) // 3
        digits_moved = 0
        
        for i in range(length, 0, -1):
            if digits_moved < 3:
                str_buf[i] = str_buf[i - 1]
                digits_moved += 1
            else:
                str_buf[i] = ','
                digits_moved = 0
    else:
        num_commas = (length - 2) // 3
        digits_moved = 0
        
        for i in range(length, 1, -1):
            if digits_moved < 3:
                str_buf[i] = str_buf[i - 1]
                digits_moved += 1
            else:
                str_buf[i] = ','
                digits_moved = 0
    
    # Return string up to the first null terminator (mimics C's printf %s behavior)
    return ''.join(str_buf).split('\0', 1)[0]


if __name__ == "__main__":
    # char * str = numToStr(7000);
    num = 7042
    
    result = num_to_str(num)
    
    print(f"Output: {result}", end='')
    
    # print("Hi")