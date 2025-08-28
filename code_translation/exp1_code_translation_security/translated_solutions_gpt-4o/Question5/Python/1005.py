def stringify(i):
    MAX_LEN = 14
    tmp = ["0"] * MAX_LEN  # Initialize a list of characters with '0'
    j = abs(i)  # Get the absolute value of the input
    k = MAX_LEN - 1
    c = 0

    while j > 0 and k >= 0:
        if c != 0 and c % 3 == 0:
            tmp[k] = ','  # Insert comma
            k -= 1
            c = 0

        # Convert digit to char and add to string
        digit = j % 10
        d_char = chr(digit + ord('0'))  # Convert digit to character
        tmp[k] = d_char

        # Move to next digit
        j //= 10
        k -= 1
        c += 1

    if i < 0:
        tmp[k] = '-'  # Add negative sign
        k -= 1

    # Create the final string from the tmp array
    buf = ''.join(tmp[k + 1:])

    return len(buf), buf


def main():
    input_value = -2147483647
    length, buf = stringify(input_value)
    print(f"{length} :: {buf}")


if __name__ == "__main__":
    main()