def e10(x):
    v = 1
    for _ in range(x):
        v *= 10
    return v


def signum(x):
    if x < 0:
        return -1
    if x == 0:
        return 0
    return 1


def my_abs(x):
    return x * signum(x)


def c_div(a, b):
    """C-style integer division (truncates toward zero, unlike Python's floor division)"""
    q = a // b
    if (a % b != 0) and ((a < 0) ^ (b < 0)):
        q += 1
    return q


def c_mod(a, b):
    """C-style modulo (sign follows dividend)"""
    return a - c_div(a, b) * b


def int_to_str(x):
    # Original C declares char out[16], but writes to out[16]; use 17 to hold all writes
    out = [0] * 17
    exp_offset = 0

    i = 0
    while i < 16:
        digit = i - exp_offset
        out[16 - i] = 0x30 + my_abs(c_div(c_mod(x, e10(digit + 1)), e10(digit)))

        if c_mod(x, e10(digit + 1)) == x:
            if x < 0:
                out[16 - (i + 1)] = ord('-')
                i += 1
            # Equivalent of `return &out[16-i];`
            return ''.join(chr(c) for c in out[16 - i:17])

        if i == 2 or i == 6 or i == 10:
            out[16 - i - 1] = ord(',')
            i += 1
            exp_offset += 1

        i += 1

    # Equivalent of `return out;`
    return ''.join(chr(c) for c in out[0:17])


if __name__ == "__main__":
    print(int_to_str(55))
    print(int_to_str(12345))
    print(int_to_str(-55))
    print(int_to_str(-123456789))