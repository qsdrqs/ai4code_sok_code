def int_to_string(num):
    # Step 1: Initialize variables
    temp = num
    counter = 0
    length = 0

    # Step 2: Calculate the length of the resulting string (including commas)
    while temp != 0:
        temp = temp // 10
        length += 1
        counter += 1
        if counter == 4:
            length += 1  # Add space for a comma
            counter = 1

    # Step 3: Allocate buffer (list of characters)
    buffer = [''] * (length + 1)  # +1 for null terminator (not used in Python)

    # Step 4: Handle negative sign
    stop = 0
    if num < 0:
        length += 1  # Add space for '-'
        stop = 1
        buffer[0] = '-'

    # Step 5: Reallocate buffer to new length (if negative)
    if num < 0:
        buffer = [''] * (length + 1)  # Reallocate to new length
        buffer[0] = '-'

    # Step 6: Fill the buffer from the end to the start
    count = 0
    for i in range(length - 1, stop - 1, -1):
        count += 1
        if count == 4:
            buffer[i] = ','
            count = 0
        else:
            buffer[i] = str(num % 10)
            num = num // 10

    # Step 7: Join and return the result
    return ''.join(buffer)