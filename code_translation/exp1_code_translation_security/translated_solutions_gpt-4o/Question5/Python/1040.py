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

    s = str(value)
    size = digits + num_commas
    dest = [''] * size
    index = size - 1

    while digits > 0:
        dest[index] = s[digits - 1]
        index -= 1
        if digits % 3 == 0 and digits > 1:
            dest[index] = ','
            index -= 1
        digits -= 1

    result = ''.join(dest)
    if is_negative:
        result = '-' + result

    print(result)
    return result


if __name__ == "__main__":
    int_to_string_with_commas(7000000)