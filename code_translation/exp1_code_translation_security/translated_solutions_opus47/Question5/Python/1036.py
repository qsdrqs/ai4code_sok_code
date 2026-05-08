def num_digits(n):
    # Note: Original C code has `if(n = 0)` which is a bug (assignment instead of comparison).
    # Translated as `if n == 0` which is the intended behavior.
    if n == 0:
        return 1
    cnt = 0
    while n != 0:
        n = n // 10
        cnt += 1
    return cnt


def return_string(num):
    # In Python, we use lists instead of C char arrays.
    # Note: The original C code has a buffer overflow - arrays have size `num_digits(num)`
    # but the loop iterates up to `num_digits(num) + 1`. Extra space allocated here.
    size = num_digits(num)
    str_arr = [''] * (size + 1)  # Equivalent to uninitialized `char str[]` in C
    ret = [''] * (size + 1)      # Equivalent to uninitialized `char ret[]` in C
    
    i = 0
    while i != (num_digits(num) + 1):
        # add a comma at every third index
        if i % 3 == 0:
            ret[i] = ','
        else:
            ret[i] = str_arr[i]
        print(ret[i], end='')
        i += 1
    
    return ''.join(ret)


def main():
    num = 7000000
    s = return_string(num)
    print(s)


if __name__ == '__main__':
    main()