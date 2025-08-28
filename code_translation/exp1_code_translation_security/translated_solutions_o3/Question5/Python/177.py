def int2str(num: int) -> str:
    """
    Python translation of the provided C function.
    For a signed integer `num` it returns a string in which the
    decimal representation is grouped with commas every three
    digits, e.g. 1234567  →  "1,234,567".
    """

    # Handle the sign first and always work with the absolute value.
    sign = '-' if num < 0 else ''
    digits = str(abs(num))          # decimal representation w/out sign

    # Build the result right-to-left (exactly what the C loop does).
    result_chars = []
    counter = 0                     # how many digits have been copied

    for ch in reversed(digits):
        result_chars.append(ch)
        counter += 1
        # After every 3 digits (except when no more digits remain)
        if counter % 3 == 0 and counter != len(digits):
            result_chars.append(',')

    # We built it backwards, so reverse again.
    result = ''.join(reversed(result_chars))

    return sign + result