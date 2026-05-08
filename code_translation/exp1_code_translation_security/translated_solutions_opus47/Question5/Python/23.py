import math
import sys


def int_to_string(x):
    tempstring = "%d" % x  # sprintf(tempstring, "%d", x)
    length = int((math.ceil(math.log10(x)) + 1))  # *sizeof(char) which is 1
    # the above INCLUDES the terminating chr
    # okay unfortunately the above ALSO needs a better linker for the math.h package
    # (fortunately Python's math.log10 works fine, unlike in the C version)
    result = ""
    # memset(result, 0, len*sizeof(char)) - not needed in Python since strings are immutable
    for i in range(length):
        if i % 3 == 0:  # yes comma
            result += ","
        # result += tempstring[i]
    return result


def main(argc, argv):
    print("%s" % int_to_string(100000), end="")
    print("test")


if __name__ == "__main__":
    main(len(sys.argv), sys.argv)

# print("TEST\n")