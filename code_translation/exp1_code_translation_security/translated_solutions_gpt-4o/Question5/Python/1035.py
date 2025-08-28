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

def abs_val(x):  # Renamed to avoid conflict with Python's built-in abs()
    return x * signum(x)

def int_to_str(x):
    out = ['\0'] * 16  # Initialize a list of 16 null characters
    exp_offset = 0

    for i in range(16):
        digit = i - exp_offset
        out[15 - i] = chr(0x30 + abs_val((x % e10(digit + 1)) // e10(digit)))

        if x % e10(digit + 1) == x:
            if x < 0:
                out[15 - (i + 1)] = '-'
                i += 1
            return ''.join(out[16 - i:]).strip('\0')

        if i == 2 or i == 6 or i == 10:
            out[15 - i - 1] = ','
            i += 1
            exp_offset += 1

    return ''.join(out).strip('\0')

# Main function equivalent
if __name__ == "__main__":
    print(int_to_str(55))
    print(int_to_str(12345))
    print(int_to_str(-55))
    print(int_to_str(-123456789))