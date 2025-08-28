# translate_c_to_python.py

def change(value: int) -> str:
    """
    Replicates the behaviour of the C function `change`.
    It returns a new string in which commas are inserted every three digits
    starting from the right-hand side (just like the C code tried to do).
    """

    # --- 1. count the digits ----------------------------------------------
    count = 0
    tmp = value
    while True:                 # do { ... } while(tmp != 0)  in C
        tmp //= 10
        count += 1
        if tmp == 0:
            break

    # --- 2. compute resulting buffer length -------------------------------
    num = count + count // 3     # same integer division as in the C code

    # (In C they also alloc'd two extra bytes; we do the same.)
    result = ['\0'] * (num + 2)

    # Print the length, exactly as   printf("%d\n", num);   does in C
    print(num)

    # --- 3. fill the buffer from right to left ----------------------------
    mid = 0
    i = num - 1
    input_val = value            # keep a working copy of the argument

    while i >= 0:
        if mid == 3:             # time to insert a comma
            mid = 0
            result[i] = ','
            i -= 1
            if i < 0:
                break

        # store the current least-significant digit
        result[i] = chr(input_val % 10 + ord('0'))
        input_val //= 10
        mid += 1
        i -= 1

    # The C version never wrote an explicit '\0'.  Here we strip any
    # trailing NUL characters we might have kept in the list.
    return ''.join(result).rstrip('\0')


# --------------------------------------------------------------------------
# Equivalent of C's  int main(void)
# --------------------------------------------------------------------------
if __name__ == "__main__":
    snum = change(700000000)
    print(snum)