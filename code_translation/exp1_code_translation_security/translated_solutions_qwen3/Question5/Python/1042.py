def int_to_string(num):
    """
    Converts an integer to a string with commas as thousand separators.
    
    Args:
        num (int): The integer to convert.
        
    Returns:
        str: The formatted string with commas.
    """
    if num == 0:
        return "0"
    
    negative = num < 0
    if negative:
        num = -num

    chars = []
    count = 0
    temp = num

    # Build the string in reverse order with commas
    while temp != 0:
        digit = temp % 10
        chars.append(chr(digit + ord('0')))  # Convert digit to character
        temp = temp // 10
        count += 1
        if count == 3 and temp != 0:
            chars.append(',')  # Insert comma after every 3 digits
            count = 0

    # Reverse the list to get the correct order
    chars = chars[::-1]

    # Join the list into a string
    result = ''.join(chars)

    # Prepend a minus sign if the number was negative
    if negative:
        result = '-' + result

    return result