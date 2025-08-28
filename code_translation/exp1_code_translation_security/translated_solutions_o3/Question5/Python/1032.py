# int_to_string.py

def int_to_string(num: int) -> str:
    """
    Mimics the C version of int_to_string.
    NOTE: Works for non-negative integers (exactly like the original code).
    """
    if num == 0:                        # The C code did not handle 0, we add it
        return "0"

    digits = []
    while num != 0:
        digits.append(chr(ord('0') + (num % 10)))   # (num % 10) + '0'
        num //= 10

    # digits are collected in reverse order → reverse again
    return ''.join(reversed(digits))


def int_to_string_with_commas(num: int) -> str:
    """
    Re-implements the C algorithm that adds the thousands-separator commas.
    """
    s = int_to_string(num)
    length = len(s)
    comma_count = (length - 1) // 3                 # same formula as in C

    result_chars = []                               # will hold the final characters
    j = 0                                           # index in original string
    k = 0                                           # flag used the same way as in C

    for i in range(length + comma_count):
        # insert comma when (remaining digits) % 3 == 0, not already inserted, and not at start
        if (length - j) % 3 == 0 and k != 1 and i != 0:
            result_chars.append(',')
            k = 1
        else:
            result_chars.append(s[j])
            j += 1
            if k == 1:
                k = 0

    return ''.join(result_chars)


def main() -> None:
    num = 7_654_321                  # 7654321 in the original C code
    converted = int_to_string_with_commas(num)
    print(converted)


if __name__ == "__main__":
    main()