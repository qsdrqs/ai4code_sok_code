def e10(x):
    v = 1
    for i in range(x):
        v *= 10
    return v

def signum(x):
    if x < 0:
        return -1
    if x == 0:
        return 0
    return 1

def abs_func(x):
    return x * signum(x)

def int_to_str(x):
    out = ['\0'] * 17  # 16 + 1 for null terminator equivalent
    exp_offset = 0
    
    for i in range(16):
        digit = i - exp_offset
        out[16 - i] = chr(0x30 + abs_func((x % e10(digit + 1)) // e10(digit)))
        
        if x % e10(digit + 1) == x:
            if x < 0:
                out[16 - (i + 1)] = '-'
                i += 1
            # Return the string starting from the appropriate position
            result = ''.join(out[16 - i:]).rstrip('\0')
            return result
        
        if i == 2 or i == 6 or i == 10:
            out[16 - i - 1] = ','
            i += 1
            exp_offset += 1
    
    return ''.join(out).rstrip('\0')

def main():
    print(int_to_str(55))
    print(int_to_str(12345))
    print(int_to_str(-55))
    print(int_to_str(-123456789))

if __name__ == "__main__":
    main()