def change(input_value):
    count = 0
    tmp = input_value

    # Calculate the number of digits in the input
    while tmp != 0:
        tmp //= 10
        count += 1

    # Calculate the size of the resulting string (including commas)
    num = count + count // 3
    result = [''] * (num + 1)  # Create a list to hold characters
    mid = 0

    print(num)  # Print the calculated size (for debugging, as in the original code)

    # Fill the result list from the end
    for i in range(num - 1, -1, -1):
        if mid == 3:
            mid = 0
            result[i] = ','  # Add a comma
            i -= 1
        result[i] = chr(input_value % 10 + ord('0'))  # Add the current digit
        input_value //= 10
        mid += 1

    return ''.join(result)  # Convert the list to a string


def main():
    snum = change(700000000)
    print(snum)


if __name__ == "__main__":
    main()