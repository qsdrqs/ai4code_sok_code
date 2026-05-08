MAX_LEN = 14


def stringify(i, buf):
    tmp = list("00000000000000")
    j = i if i >= 0 else -i
    k = MAX_LEN - 1
    c = 0
    while j > 0 and k >= 0:
        if c != 0 and c % 3 == 0:
            tmp[k] = ','
            k -= 1
            c = 0
        # Insert comma
        len_written = MAX_LEN - 1 - k
        # Convert digit to char
        digit = j % 10
        d_char = chr(digit + ord('0'))
        # Add to string
        tmp[k] = d_char
        # Move to next digit
        j //= 10
        k -= 1
        c += 1

    if i < 0:
        tmp[k] = '-'
        k -= 1

    # Copy tmp[k+1..MAX_LEN-1] to buf[0..]
    # (Original C: for (int n = k; n < MAX_LEN; n++) buf[n - k] = tmp[n + 1];
    #  which has off-by-one OOB; adjusted here to avoid it.)
    for n in range(k + 1, MAX_LEN):
        buf[n - k - 1] = tmp[n]

    return MAX_LEN - 1 - k


def main():
    buf = list("00000000000000")
    input_val = -2147483647
    l = stringify(input_val, buf)
    print(f"{l} :: {''.join(buf)}")
    return 0


main()