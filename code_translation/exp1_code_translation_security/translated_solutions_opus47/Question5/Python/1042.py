def int_to_string(num):
    # Equivalent to: char *str = malloc(sizeof(char) * 100);
    str_buf = ['\0'] * 100
    i = 0
    j = 0
    length = 0
    temp = num
    count = 0
    
    # Count the number of digits
    while temp != 0:
        temp = temp // 10
        length += 1
    
    # Build the string in reverse with commas every 3 digits
    temp = num
    while temp != 0:
        str_buf[i] = chr(temp % 10 + ord('0'))
        temp = temp // 10
        i += 1
        count += 1
        if count == 3 and temp != 0:
            str_buf[i] = ','
            i += 1
            count = 0
    
    str_buf[i] = '\0'
    i -= 1
    
    # Reverse the string in-place
    while j < i:
        temp_char = str_buf[j]
        str_buf[j] = str_buf[i]
        str_buf[i] = temp_char
        j += 1
        i -= 1
    
    # Build the final string up to the null terminator
    end = str_buf.index('\0')
    return ''.join(str_buf[:end])


if __name__ == "__main__":
    print(int_to_string(7000000))