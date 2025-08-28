def stringify(i):
    MAX_LEN = 14
    tmp = ['0'] * MAX_LEN  # Initialize buffer with '0's
    j = abs(i)             # Work with the absolute value of the input
    k = MAX_LEN - 1        # Start from the end of the buffer
    c = 0                  # Counter for comma insertion

    # Process each digit
    while j > 0 and k >= 0:
        if c != 0 and c % 3 == 0:
            tmp[k] = ','  # Insert comma every 3 digits
            k -= 1
            c = 0
        # Convert current digit to character
        digit = j % 10
        tmp[k] = str(digit)
        j //= 10
        k -= 1
        c += 1

    # Handle negative numbers
    if i < 0:
        tmp[k] = '-'
        k -= 1

    # Extract the formatted string from the buffer
    buf = ''.join(tmp[k+1:MAX_LEN])
    length = (MAX_LEN - 1) - k  # Length of the formatted string

    return buf, length


def main():
    input_val = -2147483647
    buf, l = stringify(input_val)
    print(f"{l} :: {buf}")


if __name__ == "__main__":
    main()