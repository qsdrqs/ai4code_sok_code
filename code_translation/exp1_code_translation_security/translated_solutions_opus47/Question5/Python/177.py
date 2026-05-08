def int2str(num):
    """
    Formats a signed integer as a string with comma thousands separators.
    e.g., 1234567 -> "1,234,567"
    """
    # Convert the integer to a string (equivalent to snprintf with %d)
    s = str(num)
    length = len(s)
    
    # Calculate the new length including commas
    if length % 3 == 0:
        newl = length + length // 3 - 1
    else:
        newl = length + length // 3
    
    # Build the result with commas inserted every 3 digits from the right
    result = [''] * newl
    counter = 0
    j = newl - 1
    for i in range(length - 1, -1, -1):
        counter += 1
        result[j] = s[i]
        if counter % 3 == 0:
            j -= 1
            if j >= 0:
                result[j] = ','
        j -= 1
    
    return ''.join(result)


if __name__ == "__main__":
    print(int2str(1234567))   # 1,234,567
    print(int2str(123456))    # 123,456
    print(int2str(12345))     # 12,345
    print(int2str(123))       # 123
    print(int2str(-1234567))  # -1,234,567