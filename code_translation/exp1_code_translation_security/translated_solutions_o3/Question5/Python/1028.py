# integer_to_string.py
# Direct translation of the C code that formats an integer with
# comma separators, preserving the sign.

def reverse(chars: list) -> None:
    """
    In-place reversal of a list of characters.
    Equivalent to the reverse(char s[]) helper in the C version.
    """
    i, j = 0, len(chars) - 1
    while i < j:
        chars[i], chars[j] = chars[j], chars[i]
        i += 1
        j -= 1


def itoa(n: int) -> list:
    """
    Rough equivalent to the C itoa that stores the *reversed*
    character representation of the absolute value of n
    (and appends '-' if n was negative).
    We return a *list* of characters to make mutation easy,
    because Python strings are immutable.
    """
    sign = '-' if n < 0 else ''
    n = abs(n)

    chars = []
    # Generate digits in reverse order (least significant first)
    while True:
        chars.append(chr(ord('0') + n % 10))
        n //= 10
        if n == 0:
            break

    if sign:
        chars.append(sign)          # append '-' to the end (still reversed)
    return chars                    # still reversed!


def insert_comma(chars: list, pos: int) -> None:
    """
    Insert a comma into the list *chars* at index *pos*.
    Matches the behaviour of the C insert_comma.
    """
    chars.insert(pos, ',')


def int_to_string(num: int) -> str:
    """
    Public helper that mimics the behaviour of intToString in C.
    Returns a formatted string with commas every three digits.
    """
    chars = itoa(num)                # reversed order, maybe with '-'
    length = len(chars)

    # Insert commas every 3 digits (working on the reversed list)
    # We need to skip the minus sign if present.
    start = 0
    if chars[-1] == '-':             # last element is the '-' sign
        start = 1                    # leave sign untouched

    for i in range(3 + start, length, 4):  # 3, 7, 11, ... (shifted by 'start')
        insert_comma(chars, i)

    # If the very last char is a comma, drop it (edge-case for multiples of 1000)
    if chars and chars[-1] == ',':
        chars.pop()

    # Reverse back to normal order
    reverse(chars)

    return ''.join(chars)


# ----------------------------------------------------------------------
# A small main() stub exactly like the C program’s behaviour.
def main() -> None:
    num = 7000
    result = int_to_string(num)
    print(result)


if __name__ == "__main__":
    main()