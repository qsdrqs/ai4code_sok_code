def int_to_string(num):
    """
    Function to convert an integer to a string with commas for formatting.
    """
    # Determine if the number is negative
    is_negative = num < 0
    if is_negative:
        num = -num  # Work with the absolute value of the number

    # Convert the number to a string
    num_str = str(num)

    # Add commas for formatting
    formatted_str = ""
    counter = 0
    for i in range(len(num_str) - 1, -1, -1):
        counter += 1
        formatted_str = num_str[i] + formatted_str
        if counter == 3 and i != 0:
            formatted_str = "," + formatted_str
            counter = 0

    # Add the negative sign back if the number was negative
    if is_negative:
        formatted_str = "-" + formatted_str

    return formatted_str


def main():
    num = -10099870
    str_representation = int_to_string(num)
    print(str_representation)


if __name__ == "__main__":
    main()