def int_to_string(num):
    """Write a function that takes an integer and converts it to a string"""
    
    # Handle the case where num is 0
    if num == 0:
        return "0"
    
    # Calculate length needed for the string
    temp = abs(num)  # Use absolute value for length calculation
    length = 0
    counter = 0
    
    while temp != 0:
        temp = temp // 10
        length += 1
        counter += 1
        # There's 1 comma for first 4 digits, then 1 for every 3 after
        if counter == 4:
            length += 1
            counter = 1
    
    # Create a list to build the string (since strings are immutable in Python)
    str_list = [''] * length
    
    stop = 0
    if num < 0:
        length += 1  # +1 for the minus sign character
        stop = 1     # add in the minus sign at start of string
        str_list = [''] * length  # Resize the list
        str_list[0] = '-'
        num = abs(num)  # Work with positive number for digit extraction
    
    count = 0
    i = length - 1
    
    while i >= stop:
        count += 1
        if count == 4:
            str_list[i] = ','
            count = 0
            i -= 1
            continue
        
        str_list[i] = str(num % 10)
        num = num // 10
        i -= 1
    
    return ''.join(str_list)


def main():
    num = -10099870
    result = int_to_string(num)
    print(result)


if __name__ == "__main__":
    main()