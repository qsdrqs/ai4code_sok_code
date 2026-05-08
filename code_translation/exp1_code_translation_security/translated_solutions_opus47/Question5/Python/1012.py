import sys


def insert(s, index):
    """A helper function to insert a comma into the string at the given index."""
    # use string slicing to copy the original string from the beginning
    # up to `index`, then append the comma and the rest of the string
    return s[:index] + ',' + s[index:]


def string_repre(num):
    """Convert an integer to its string representation with commas as thousand separators."""
    strnum = str(num)

    l = len(strnum)      # length before insertion
    conum = l // 3       # number of commas to insert

    # If the length is exactly divisible by 3, we need one less comma
    # (no comma at the very beginning of the string)
    if l % 3 == 0:
        conum -= 1

    # buff will hold the string with commas inserted
    buff = strnum

    for i in range(1, conum + 1):
        # insert comma from right to left, every 3 digits
        # since we insert to the right, earlier positions are unaffected
        buff = insert(buff, l - 3 * i)

    return buff


def main():
    argc = len(sys.argv)
    result = string_repre(argc)
    return result


if __name__ == "__main__":
    print(main())