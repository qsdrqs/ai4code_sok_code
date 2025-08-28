def int2str(num: int) -> str:
    # Convert the integer to a string
    str_num = str(num)
    length = len(str_num)
    
    # Calculate the new length for the result string with commas
    if length % 3 == 0:
        new_length = length + length // 3 - 1
    else:
        new_length = length + (length // 3)
    
    # Create a list to hold the result characters
    result = [''] * new_length
    counter = 0
    j = new_length - 1
    
    # Iterate through the original string in reverse
    for i in range(length - 1, -1, -1):
        counter += 1
        result[j] = str_num[i]
        if counter % 3 == 0 and i != 0:  # Add a comma if needed
            j -= 1
            result[j] = ','
        j -= 1
    
    # Join the result list into a string and return
    return ''.join(result)

# Example usage
print(int2str(123456789))  # Output: "123,456,789"