def int2str(num):
    # Convert number to string to get its length
    str_num = str(num)
    length = len(str_num)
    
    # Calculate new length with commas
    if length % 3 == 0:
        newl = length + length // 3 - 1
    else:
        newl = length + (length // 3)
    
    # Create result list (easier to work with than strings in Python)
    result = [''] * newl
    counter = 0
    j = newl - 1
    
    # Process digits from right to left
    for i in range(length - 1, -1, -1):
        counter += 1
        result[j] = str_num[i]
        j -= 1
        
        # Add comma every 3 digits (but not at the beginning)
        if counter % 3 == 0 and i > 0:
            result[j] = ','
            j -= 1
    
    # Convert list back to string and return
    return ''.join(result)

# Test the function
if __name__ == "__main__":
    print(int2str(1234567))    # Should output: 1,234,567
    print(int2str(123))        # Should output: 123
    print(int2str(1234))       # Should output: 1,234