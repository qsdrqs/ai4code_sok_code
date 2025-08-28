def int2str(num):
    # Handle negative numbers
    if num < 0:
        sign = '-'
        s = str(abs(num))
    else:
        sign = ''
        s = str(num)

    length = len(s)

    # Calculate the new length of the result string including commas
    if length % 3 == 0:
        newl = length + (length // 3) - 1
    else:
        newl = length + (length // 3)

    # Account for the sign character if present
    if sign:
        newl += 1

    # Initialize the result list with empty strings
    result = [''] * newl

    counter = 0
    j = newl - 1  # Start filling the result from the end

    # Process each digit in reverse order
    for i in reversed(range(len(s))):
        counter += 1
        result[j] = s[i]
        j -= 1

        # Insert a comma after every 3 digits, but not before the first digit
        if counter % 3 == 0 and i > 0:
            result[j] = ','
            j -= 1

    # Add the sign if the number is negative
    if sign:
        result[0] = sign

    # Join the list into a string and return
    return ''.join(result)