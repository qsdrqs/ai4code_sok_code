# Direct translation of the given C program to Python

MAX_LEN = 14       # equivalent to the C «const int MAX_LEN = 14;»

def stringify(i: int):
    """
    Translates the C routine ‘stringify’ as-is:
       – builds the number right–to–left in a fixed-size buffer,
       – inserts commas every three digits,
       – keeps the sign,
       – finally returns both the length and the resulting string.
    """
    # tmp is the scratch buffer («char tmp[14] = "00000000000000";» in C)
    tmp = ['0'] * MAX_LEN

    j = i if i >= 0 else -i   # absolute value of the number
    k = MAX_LEN - 1           # write index (starts at last char position)
    c = 0                     # digit counter for comma insertion

    # main loop: copy digits and insert commas
    while j > 0 and k >= 0:
        if c != 0 and c % 3 == 0:
            tmp[k] = ','
            k -= 1
            c = 0

        digit = j % 10
        tmp[k] = chr(ord('0') + digit)   # convert digit → char
        j //= 10
        k -= 1
        c += 1

    # prepend sign if needed
    if i < 0 and k >= 0:
        tmp[k] = '-'
        k -= 1

    # copy the useful slice into the “output buffer”
    result = ''.join(tmp[k + 1:])        # equivalent to the for-loop copy in C
    return len(result), result


# ---------------------------------------------------------------------------
# Rough equivalent of the C ‘main’ function
# ---------------------------------------------------------------------------
if __name__ == '__main__':
    input_val = -2147483647
    length, buf = stringify(input_val)
    print(f"{length} :: {buf}")