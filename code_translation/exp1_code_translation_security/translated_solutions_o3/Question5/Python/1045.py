def power(base: int, exponent: int) -> int:
    """
    Replicates the simple integer power() function from the C code.
    (Built-ins like pow() could be used instead, but we stay close to
     the original implementation.)
    """
    result = 1
    while exponent > 0:
        result *= base
        exponent -= 1
    return result


def number_to_string(num: int) -> str:
    """
    Converts an integer to a string with comma thousand-separators,
    mirroring the behaviour of the C version as closely as possible.
    """
    # -------- Determine the number of digits ----------
    temp = abs(num)
    num_size = 0
    if temp == 0:                     # the C code breaks on 0; we fix it
        num_size = 1
    else:
        while temp > 0:
            temp //= 10
            num_size += 1

    # -------- How many commas are needed? -------------
    comma_count = (num_size - 1) // 3

    chars = []                        # acts like the char array in C

    # -------- Handle the sign -------------------------
    if num < 0:
        chars.append('-')
        num = -num                    # work with the positive magnitude

    # -------- Build the numeric part with commas ------
    for j in range(num_size - 1, -1, -1):          # j = num_size-1 … 0
        digit_char = chr((num // power(10, j)) + ord('0'))
        chars.append(digit_char)
        num %= power(10, j)

        if (j % 3 == 0) and (j != 0):              # insert comma?
            chars.append(',')

    # -------- Return the assembled string -------------
    return ''.join(chars)


# ------------------ Test / main -----------------------
if __name__ == "__main__":
    num = -7_000_000
    str_num = number_to_string(num)
    print(str_num)        # -> -7,000,000