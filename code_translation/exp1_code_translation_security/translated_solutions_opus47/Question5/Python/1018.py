def change(input_val):
    count = 0
    tmp = input_val
    # do-while loop equivalent
    while True:
        tmp //= 10
        count += 1
        if tmp == 0:
            break
    
    num = count + count // 3
    result = [''] * (num + 2)  # equivalent to malloc(num+2)
    mid = 0
    print(num)
    
    i = num - 1
    while i >= 0:
        if mid == 3:
            mid = 0
            result[i] = ','
            i -= 1
        if i < 0:  # safety check (Python's negative indexing wraps around, unlike C)
            break
        result[i] = str(input_val % 10)
        input_val //= 10
        mid += 1
        i -= 1
    
    return ''.join(result)


def main():
    snum = change(700000000)
    print(snum)


main()