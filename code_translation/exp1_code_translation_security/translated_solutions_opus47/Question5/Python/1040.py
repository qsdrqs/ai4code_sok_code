import sys


def num_digits(n):
    digits = 0
    while n != 0:
        n //= 10
        digits += 1
    return digits


def int_to_string_with_commas(value):
    is_negative = value < 0
    if is_negative:
        value *= -1
    digits = num_digits(value)
    num_commas = digits // 3
    if digits % 3 == 0:
        num_commas -= 1
    # In C: char s[digits]; sprintf(s, "%d", value);
    s = str(value)
    size = digits + num_commas + 1
    # In C: char dest[size]; (using list since Python strings are immutable)
    dest = [''] * size
    index = size - 2
    while digits > 0:
        dest[index] = s[digits - 1]
        index -= 1
        if digits % 3 == 0 and digits > 0:
            dest[index] = ','
            index -= 1
    dest[size - 1] = '\0'
    result = ''.join(dest)
    sys.stdout.write(result)
    return result


if __name__ == '__main__':
    int_to_string_with_commas(7000000)