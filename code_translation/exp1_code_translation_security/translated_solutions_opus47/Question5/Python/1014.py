def strrev(input_str):
    """A function that reverses a string"""
    # Convert to list since strings are immutable in Python
    chars = list(input_str)
    start = 0
    end = len(chars) - 1
    while end > start:
        chars[start], chars[end] = chars[end], chars[start]
        start += 1
        end -= 1
    return ''.join(chars)


def integer_to_string(num):
    """A function that takes a signed integer num and returns a string representation of the integer"""
    result = []
    is_negative = False

    if num < 0:
        is_negative = True
        num = -num

    # do-while loop equivalent (ensures at least one iteration, handles num == 0)
    while True:
        digit = num % 10
        result.append(chr(ord('0') + digit))
        num //= 10
        if num == 0:
            break

    if is_negative:
        result.append('-')

    return strrev(''.join(result))


if __name__ == "__main__":
    num = integer_to_string(12345)
    print(num)

    num = integer_to_string(-12345)
    print(num)