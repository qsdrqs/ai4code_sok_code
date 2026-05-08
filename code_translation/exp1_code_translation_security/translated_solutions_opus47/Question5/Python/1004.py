MAX_DIGITS = 100


def repr(n):
    """
    Function repr

    Takes in a signed integer and returns the string representation of that integer.
    """
    s = [''] * MAX_DIGITS
    i = 0
    sign = 1
    if n == -2147483648:
        # When multiplied by -1 this overflows, so handle it separately.
        return "-2,147,483,648"
    if n == 0:
        s[0] = '0'
        return s[0]
    if n < 0:
        sign = -1
        n *= -1
    while n > 0:
        s[i] = chr((n % 10) + ord('0'))
        i += 1
        if (i + 1) % 4 == 0 and n > 10:
            s[i] = ','
            i += 1
        n //= 10
    if sign == -1:
        s[i] = '-'
        i += 1
    result = ''.join(s[:i])
    return reverse(result)


def reverse(s):
    """
    Function reverse

    Takes in a string and reverses it.
    """
    chars = list(s)
    i = 0
    j = len(chars) - 1
    while i < j:
        chars[i], chars[j] = chars[j], chars[i]
        i += 1
        j -= 1
    return ''.join(chars)


if __name__ == "__main__":
    n = 0
    s = repr(n)
    print(s, end='')