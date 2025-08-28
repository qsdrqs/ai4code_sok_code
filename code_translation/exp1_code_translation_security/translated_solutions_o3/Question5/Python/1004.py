"""
A very small, dependency–free, line-for-line translation of the original C
example.

Two helpers are kept:

    • repr_int – does the actual conversion (named this way so we
      do not shadow Python’s built-in repr).
    • _reverse  – mirrors the C ‘reverse’ function.

Nothing else is required; just drop the code in a *.py file and run it.
"""

MAX_DIGITS = 100          # preserved only for completeness


def _reverse(char_list: list) -> None:
    """
    In-place reversal of a list of characters.
    (The original C routine worked on a char*; here we work on a list
    because Python strings are immutable.)
    """
    i, j = 0, len(char_list) - 1
    while i < j:
        char_list[i], char_list[j] = char_list[j], char_list[i]
        i += 1
        j -= 1


def repr_int(n: int) -> str:
    """
    Takes in a signed integer and returns its decimal string representation
    with a comma every three digits, exactly like the C version.

    Examples
    --------
    >>> repr_int(0)
    '0'
    >>> repr_int(1234)
    '1,234'
    >>> repr_int(-9876543)
    '-9,876,543'
    """

    # Special-case −2^31 to follow the original C overflow workaround.
    if n == -2_147_483_648:        # 0x8000_0000
        return "-2,147,483,648"

    # Zero is handled explicitly.
    if n == 0:
        return "0"

    sign = ''
    if n < 0:
        sign = '-'
        n = -n                     # make the number positive for processing

    chars = []                     # acts like the char buffer in C
    digit_counter = 0

    while n > 0:
        # append current least-significant digit
        chars.append(chr(n % 10 + ord('0')))
        digit_counter += 1

        # add a comma after every third digit,
        # but never after the left-most group
        if digit_counter % 3 == 0 and n > 9:
            chars.append(',')

        n //= 10                   # integer division, discarding the digit

    # add the sign, if any
    if sign:
        chars.append(sign)

    # the list is in reverse order – flip it back
    _reverse(chars)

    return ''.join(chars)


# --- simple test / demonstration -----------------------------------------
if __name__ == "__main__":
    n = 0
    print(repr_int(n))