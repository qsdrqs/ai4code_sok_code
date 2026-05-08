def convert(value: int) -> str:
    buf1 = str(value)
    buf2 = ""
    length = len(buf1)
    for i in range(length):
        buf2 += buf1[i]
        dist = length - i - 1
        if dist % 3 == 0 and dist != 0:
            buf2 += ','
    return buf2


def main():
    test = convert(1000)
    print(test)
    return 0


if __name__ == "__main__":
    main()