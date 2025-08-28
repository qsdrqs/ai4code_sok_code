def int_to_string(num):
    # Create a list to simulate the character array (Python strings are immutable)
    str_list = [''] * 100
    i = 0
    j = 0
    k = 0
    len_val = 0
    temp = num
    count = 0
    
    # Calculate length of the number
    while temp != 0:
        temp = temp // 10  # Use integer division
        len_val += 1
    
    temp = num
    
    # Build the string with digits and commas
    while temp != 0:
        str_list[i] = str(temp % 10)  # Convert digit to string
        temp = temp // 10  # Use integer division
        i += 1
        count += 1
        if count == 3 and temp != 0:
            str_list[i] = ','
            i += 1
            count = 0
    
    # No need to add null terminator in Python
    i -= 1
    
    # Reverse the string
    while j < i:
        temp_char = str_list[j]
        str_list[j] = str_list[i]
        str_list[i] = temp_char
        j += 1
        i -= 1
    
    # Convert list back to string and remove empty elements
    result = ''.join(str_list).rstrip('\x00').rstrip('')
    # Remove any trailing empty strings
    result = ''.join(c for c in result if c != '')
    
    return result

def main():
    print(int_to_string(7000000))

if __name__ == "__main__":
    main()