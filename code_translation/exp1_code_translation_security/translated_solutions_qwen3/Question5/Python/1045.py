def number_to_string(num):
    temp = abs(num)
    num_size = 0

    # Calculate the number of digits
    while temp > 0:
        temp //= 10
        num_size += 1

    # Special case for zero
    if num_size == 0:
        return '0'

    # Calculate number of commas needed
    comma_count = (num_size - 1) // 3

    # Initialize the string list
    str_num = []

    # Handle negative sign
    if num < 0:
        str_num.append('-')
        num = -num

    # Process each digit from most significant to least
    for j in range(num_size - 1, -1, -1):
        power_of_ten = 10 ** j
        digit = num // power_of_ten
        str_num.append(str(digit))
        num %= power_of_ten

        # Insert comma after every 3 digits (except at the end)
        if j % 3 == 0 and j != 0:
            str_num.append(',')

    return ''.join(str_num)


def main():
    num = -7000000
    result = number_to_string(num)
    print(result)


if __name__ == "__main__":
    main()