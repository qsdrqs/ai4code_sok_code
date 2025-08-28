def int_to_string(num):
    if num == 0:
        return "0"
    
    # Step 1: Handle sign
    sign = '-' if num < 0 else ''
    n = abs(num)
    
    # Step 2: Convert number to reversed digits (as a list of characters)
    reversed_digits = []
    while n > 0:
        reversed_digits.append(str(n % 10))
        n = n // 10
    
    # Step 3: Split into chunks of 3 digits
    chunks = []
    for i in range(0, len(reversed_digits), 3):
        chunk = ''.join(reversed_digits[i:i+3])
        chunks.append(chunk)
    
    # Step 4: Join chunks with commas
    reversed_with_commas = ','.join(chunks)
    
    # Step 5: Reverse the entire string to get the correct order
    result = reversed_with_commas[::-1]
    
    # Step 6: Add sign if necessary
    return sign + result