def string_it(value):
    # Convert the integer to a string and handle negative values
    if value < 0:
        str_value = '-' + str(-value)
    else:
        str_value = str(value)

    # Calculate the length of the number and the number of commas needed
    length = len(str_value)
    comma_count = (length - 1) // 3

    # Insert commas into the string
    if comma_count > 0:
        # Split the string into the integer and fractional parts
        if str_value[0] == '-':
            integer_part = str_value[1:]
            sign = '-'
        else:
            integer_part = str_value
            sign = ''

        # Reverse the integer part to insert commas every three digits
        reversed_integer = integer_part[::-1]
        parts = [reversed_integer[i:i+3] for i in range(0, len(reversed_integer), 3)]
        reversed_with_commas = ','.join(parts)

        # Reverse back to the original order and add the sign if necessary
        str_value = sign + reversed_with_commas[::-1]

    return str_value

def main():
    print("   {}".format(string_it(-123457)))

if __name__ == "__main__":
    main()