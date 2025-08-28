def getLength(num):
    length = 0
    numCopy = num
    while numCopy > 0:
        numCopy = numCopy // 10
        length = length + 1
    return length

def convertToString(num):
    str_val = ""
    str_val = str_val + "test"
    return str_val

def main():
    num = 7000000
    print(getLength(num), end="")
    print(convertToString(5), end="")
    return 0

if __name__ == "__main__":
    main()