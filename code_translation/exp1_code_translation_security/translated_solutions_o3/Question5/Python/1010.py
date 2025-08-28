def string_it(value: int) -> str:
    """
    Return a string containing the decimal expansion of *value* with
    commas every three digits.  Handles negative numbers as well.
    This is a direct, literal translation of the original C logic.
    """
    # Save the sign and work only with a non-negative value
    negative = value < 0
    if negative:
        value = -value

    # Convert the absolute value to a plain decimal string
    s = str(value)

    # How many commas will be needed?
    # 1 comma for 1,000-9,999; 2 for 1,000,000-9,999,999; etc.
    comma_count = (len(s) - 1) // 3

    end_index = len(s)                      # Acts like the end pointer in C
    while comma_count > 0:
        start_index = end_index - 3         # Move 3 digits to the left
        # Insert a comma at *start_index*
        s = s[:start_index] + ',' + s[start_index:]
        end_index = start_index             # Next “end” is just before the comma
        comma_count -= 1

    # Re-insert the minus sign if the original value was negative
    return ('-' if negative else '') + s


if __name__ == "__main__":
    # Three leading spaces to match the original C program’s output
    print("   " + string_it(-123457))