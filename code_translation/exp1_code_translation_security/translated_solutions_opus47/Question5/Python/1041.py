def reverse_str(s):
    """Reverses a string. In Python, strings are immutable, so we work with a list."""
    chars = list(s)
    i = 0
    j = len(chars) - 1
    while i < j:
        temp = chars[i]
        chars[i] = chars[j]
        chars[j] = temp
        i += 1
        j -= 1
    return ''.join(chars)


def add_commas(num):
    """Converts an unsigned integer to a string with commas as thousand separators."""
    chars = []
    count = 0
    while num != 0:
        chars.append(chr(num % 10 + ord('0')))
        num //= 10
        count += 1
        if count == 3 and num != 0:
            chars.append(',')
            count = 0
    return reverse_str(''.join(chars))


def main():
    num = 1000000
    s = add_commas(num)
    print(s)


if __name__ == "__main__":
    main()