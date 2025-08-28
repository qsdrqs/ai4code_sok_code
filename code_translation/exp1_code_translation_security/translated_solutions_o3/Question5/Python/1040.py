def num_digits(n: int) -> int:
    """Return the number of digits in the (positive) integer n."""
    digits = 0
    while n != 0:
        n //= 10
        digits += 1
    return digits


def int_to_string_with_commas(value: int) -> str:
    """
    Convert an integer to a string and insert commas every three digits.
    The algorithm mirrors the given C implementation as closely as possible.
    """
    is_negative = value < 0
    if is_negative:
        value *= -1                         # work with the absolute value

    digits = num_digits(value)
    num_commas = digits // 3
    if digits % 3 == 0:
        num_commas -= 1                     # same adjustment as in the C code

    # Original C code creates two char arrays, here we construct Python lists
    s = str(value)                          # equivalent to sprintf(s, "%d", value)
    size = digits + num_commas              # total characters (no trailing '\0' in Python)
    dest_chars = [''] * size                # placeholder list for characters

    index = size - 1                        # last valid index
    while digits > 0:
        dest_chars[index] = s[digits - 1]   # copy next digit from the end
        index -= 1

        # Insert comma where needed
        if digits % 3 == 0 and digits > 0:
            dest_chars[index] = ','
            index -= 1

        digits -= 1

    dest = ''.join(dest_chars)              # build the final string

    if is_negative:                         # prepend a minus sign if needed
        dest = '-' + dest

    print(dest)                             # mimic the C program's printf
    return dest


if __name__ == "__main__":
    int_to_string_with_commas(7000000)