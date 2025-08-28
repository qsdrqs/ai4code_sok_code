def convert(value):
    buf1 = f"{value}"
    buf2 = []
    len_buf1 = len(buf1)
    idx = 0
    for i in range(len_buf1):
        buf2.append(buf1[i])
        dist = len_buf1 - i - 1
        if dist % 3 == 0 and dist != 0:
            buf2.append(',')
    return ''.join(buf2)

def main():
    test = convert(1000)
    print(test)

if __name__ == "__main__":
    main()