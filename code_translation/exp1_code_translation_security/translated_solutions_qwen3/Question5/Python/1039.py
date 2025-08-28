def getLength(num):
    length = 0
    num_copy = num
    while num_copy > 0:
        num_copy //= 10
        length += 1
    return length


def convertToString(num):
    # In the original C code, this function initializes a local char array,
    # sets it to an empty string, and then appends "test".
    # Despite the function name, it ignores the input and returns "test".
    return "test"


if __name__ == "__main__":
    num = 7000000
    # Print the length of the number without a newline
    print(getLength(num), end='')
    # Print the result of convertToString(5) without a newline
    print(convertToString(5), end='')