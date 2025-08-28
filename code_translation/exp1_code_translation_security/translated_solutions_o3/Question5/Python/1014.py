# A direct, line-for-line style translation of the original C program.

def strrev(s: str) -> str:
    """
    Reverse a string (mimicking the C implementation that swaps characters
    in place).  We do it “manually” instead of using slicing so the logic
    mirrors the C version.
    """
    chars = list(s)                # Strings are immutable in Python
    start, end = 0, len(chars) - 1

    while end > start:
        chars[start], chars[end] = chars[end], chars[start]
        start += 1
        end   -= 1

    return ''.join(chars)


def integerToString(num: int) -> str:
    """
    Re-implements the C algorithm that converts an integer to its
    textual representation without using Python’s built-in str().
    """
    result_chars = []      # Equivalent to the malloc'ed char array
    is_negative  = False

    if num < 0:
        is_negative = True
        num = -num          # Work with the positive value

    # Extract digits (always run once, even for num == 0)
    while True:
        digit = num % 10
        result_chars.append(chr(ord('0') + digit))
        num //= 10
        if num == 0:
            break

    if is_negative:
        result_chars.append('-')

    # We built the string backwards; reverse it
    return strrev(''.join(result_chars))


# “main” section corresponding to the C main()
if __name__ == "__main__":
    print(integerToString(12345))   # -> 12345
    print(integerToString(-12345))  # -> -12345