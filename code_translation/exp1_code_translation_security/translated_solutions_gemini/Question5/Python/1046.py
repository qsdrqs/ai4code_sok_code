def convert(num: int) -> str:
    """
    Converts an integer to a string, prints it to the console,
    and returns the first character of that string.

    This function mimics the logic of the original C code while correcting
    its memory bug. The C code's return type was `char`, suggesting the
    intent was to return a single character.
    """
    # In C: sprintf(buf, "%d", num);
    # In Python, str() handles the conversion.
    s = str(num)

    # In C: printf("%s", buf);
    # This prints the string to the console without a newline.
    print(s, end='')

    # In C: return buf; (This was a bug, but the return type was char)
    # The most logical interpretation of returning a single `char` is to
    # return the first character of the resulting string.
    if s:
        return s[0]
    else:
        return ""

def main():
    """
    Main function to run the program.
    """
    # In C: printf("%c", convert(100));
    # 1. Call convert(100). It will print "100" and return '1'.
    # 2. Print the returned character '1'.
    # The final output will be "1001".
    result_char = convert(100)
    print(result_char)

# Standard entry point for a Python script
if __name__ == "__main__":
    main()