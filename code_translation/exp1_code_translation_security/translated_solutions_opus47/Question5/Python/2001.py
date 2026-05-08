INT_MAX = 2147483647
INT_MIN = -2147483648


def int_to_string(a):
    result = []
    buf = str(a)
    length = len(buf)
    
    actual_start = 0
    if length > 0 and buf[0] == '-':
        result.append(buf[0])
        actual_start = 1
        length -= 1
    
    for buf_idx in range(length):
        if buf_idx != 0 and (length - buf_idx) % 3 == 0:
            result.append(',')
        result.append(buf[actual_start + buf_idx])
    
    return ''.join(result)


if __name__ == "__main__":
    print(int_to_string(7000000))
    print(int_to_string(700000))
    print(int_to_string(70000))
    print(int_to_string(7000))
    print(int_to_string(700))
    print(int_to_string(70))
    print(int_to_string(7))
    print(int_to_string(0))
    print(int_to_string(-7000000))
    print(int_to_string(-700000))
    print(int_to_string(-70000))
    print(int_to_string(-7000))
    print(int_to_string(-700))
    print(int_to_string(-70))
    print(int_to_string(-7))
    print(int_to_string(INT_MAX))
    print(int_to_string(INT_MIN))