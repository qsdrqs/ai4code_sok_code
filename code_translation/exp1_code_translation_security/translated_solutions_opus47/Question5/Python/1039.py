def getLength(num):
    length = 0
    numCopy = num
    while numCopy > 0:
        numCopy = numCopy // 10
        length = length + 1
    return length


def convertToString(num):
    string = ""
    string += "test"
    return string


def main():
    num = 7000000
    print(getLength(num), end="")
    print(convertToString(5), end="")
    return 0


main()