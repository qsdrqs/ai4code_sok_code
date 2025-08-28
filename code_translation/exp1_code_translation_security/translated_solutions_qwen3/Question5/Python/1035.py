def e10(x):
    return 10 ** x

def c_mod(a, b):
    if b == 0:
        return a
    res = abs(a) % abs(b)
    if a < 0:
        res = -res
    return res

def c_div(a, b):
    return int(a / b)

def int_to_str(x):
    out = ['\x00'] * 17  # Simulate C's 16-element buffer with 17 to avoid index overflow
    exp_offset = 0

    for i in range(16):
        digit = i - exp_offset
        ten_power_digit_plus_1 = e10(digit + 1)
        ten_power_digit = e10(digit)
        mod_val = c_mod(x, ten_power_digit_plus_1)
        div_val = c_div(mod_val, ten_power_digit)
        current_digit = abs(div_val)
        index = 16 - i
        out[index] = chr(0x30 + current_digit)

        if mod_val == x:
            if x < 0:
                hyphen_index = 16 - (i + 1)
                out[hyphen_index] = '-'
                i += 1
            start_index = 16 - i
            res = []
            for c in out[start_index:]:
                if c == '\x00':
                    break
                res.append(c)
            return ''.join(res)

        if i == 2 or i == 6 or i == 10:
            comma_index = 16 - i - 1
            out[comma_index] = ','
            i += 1
            exp_offset += 1

    return ''.join(out).rstrip('\x00') or ''