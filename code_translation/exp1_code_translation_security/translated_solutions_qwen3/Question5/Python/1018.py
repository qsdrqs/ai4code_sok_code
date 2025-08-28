def change(input_val):
    # Count the number of digits in the input
    if input_val == 0:
        count = 1
    else:
        count = 0
        tmp = input_val
        while tmp != 0:
            tmp = tmp // 10
            count += 1

    # Calculate the size of the result buffer
    num = count + (count // 3)

    # Initialize the result list with spaces
    result = [' '] * num

    mid = 0
    i = num - 1  # Start from the end of the buffer

    while i >= 0:
        if mid == 3:
            # Insert a comma
            result[i] = ','
            mid = 0
            i -= 1  # Move to the next position

        # Insert the current digit
        result[i] = str(input_val % 10)
        input_val = input_val // 10
        mid += 1
        i -= 1  # Move to the previous position

    # Join the list into a string and return
    return ''.join(result)

def main():
    snum = change(700000000)
    print(snum)

if __name__ == "__main__":
    main()