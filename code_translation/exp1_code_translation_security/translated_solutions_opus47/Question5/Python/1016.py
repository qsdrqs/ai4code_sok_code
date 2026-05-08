def int_to_string(num):
    """Takes an integer and converts it to a string with commas as thousand separators."""
    # Handle the sign (using abs because Python's modulo semantics differ from C for negatives)
    is_negative = num < 0
    abs_num = abs(num)
    
    # Calculate the length needed for the resulting string
    temp = abs_num
    length = 0
    counter = 0
    while temp != 0:
        temp = temp // 10
        length += 1
        counter += 1
        # there's 1 comma for first 4 digits, then 1 for every 3 after
        if counter == 4:
            length += 1
            counter = 1
    
    stop = 0
    if is_negative:
        length += 1   # +1 for the minus sign character
        stop = 1      # add in the minus sign at start of string
    
    # Build the result as a list of characters (Python strings are immutable)
    result = [''] * length
    if is_negative:
        result[0] = '-'
    
    count = 0
    for i in range(length - 1, stop - 1, -1):
        count += 1
        if count == 4:
            result[i] = ','
            count = 0
            continue
        result[i] = chr((abs_num % 10) + ord('0'))
        abs_num = abs_num // 10
    
    return ''.join(result)


def main():
    num = -10099870
    s = int_to_string(num)
    print(s)
    # No free() needed — Python has automatic garbage collection
    return 0


if __name__ == "__main__":
    main()