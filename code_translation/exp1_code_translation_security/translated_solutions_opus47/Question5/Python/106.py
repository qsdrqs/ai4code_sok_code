MAX_STR_LEN = 128


def reverseStr(s):
    length = len(s)
    new_str = [''] * length
    for i in range(length):
        new_str[i] = s[length - i - 1]
    return ''.join(new_str)


def strOfInt(num):
    """
    Return a string representation of a signed integer

    Args:
        num (int): input number as a signed integer

    Return:
        string representation of the signed integer
    """
    s = [''] * MAX_STR_LEN
    i = 0
    isNeg = 0
    if num < 0:
        isNeg = 1
        num = -num
    # do-while loop equivalent
    while True:
        s[i] = chr(num % 10 + ord('0'))
        i += 1
        num //= 10
        if num == 0:
            break
    if isNeg:
        s[i] = '-'
        i += 1
    return reverseStr(''.join(s[:i]))


def main():
    print(strOfInt(7000000))


if __name__ == "__main__":
    main()