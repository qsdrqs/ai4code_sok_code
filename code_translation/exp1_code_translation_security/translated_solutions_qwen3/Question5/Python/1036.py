def num_digits(n):
    """
    Returns the number of digits in the integer n.
    This function is corrected to handle n == 0 properly.
    """
    if n == 0:
        return 1
    cnt = 0
    while n != 0:
        n = n // 10
        cnt += 1
    return cnt

def return_string(num):
    """
    Mimics the flawed logic of the original C function.
    - Converts the number to a string.
    - Inserts commas at every third index (0, 3, 6, ...).
    - Prints the ASCII values of the resulting characters.
    - Returns the resulting string.
    """
    str_num = list(str(num))
    ndigits = num_digits(num)
    ret = []
    i = 0
    while i <= ndigits:  # Mimics the C loop: i != (ndigits + 1)
        if i % 3 == 0:
            ret.append(',')
        else:
            if i < len(str_num):
                ret.append(str_num[i])
        i += 1

    # Print ASCII values of the characters in ret
    for c in ret:
        print(ord(c), end=' ')
    print()

    return ''.join(ret)

if __name__ == '__main__':
    num = 7000000
    s = return_string(num)
    print(s)