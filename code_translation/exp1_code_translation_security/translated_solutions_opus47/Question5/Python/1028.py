def reverse(s):
    """Reverse the list of characters in place."""
    i, j = 0, len(s) - 1
    while i < j:
        s[i], s[j] = s[j], s[i]
        i += 1
        j -= 1


def itoa(n, s):
    """Convert n to characters in s (digits stored in reverse order)."""
    sign = n  # record sign
    if sign < 0:
        n = -n  # make n positive
    s.clear()
    # generate digits in reverse order
    while True:
        s.append(chr(n % 10 + ord('0')))  # get next digit
        n //= 10  # delete it
        if n <= 0:
            break
    if sign < 0:
        s.append('-')
    # reverse(s)  # commented out in original


def insert_comma(s, pos):
    """Insert a comma at position pos."""
    s.insert(pos, ',')


def intToString(s, num):
    itoa(num, s)
    length = len(s)
    for i in range(3, length + 1, 4):
        insert_comma(s, i)
    if s and s[-1] == ',':
        s.pop()
    reverse(s)


def main():
    s = []
    num = 7000
    intToString(s, num)
    print(''.join(s), end='')
    return 0


if __name__ == '__main__':
    main()