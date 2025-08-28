# ---  num_to_string.py  -------------------------------------------------------

from typing import Optional

MAX_LEN: int = 16          # Keeps the same constant as in the C code


def num_to_string(num: int) -> Optional[str]:
    """Convert an integer to a string with thousands separators (,)
       that behaves exactly like the original C implementation."""

    # Special-case zero
    if num == 0:
        return "0"

    is_neg: bool = False
    if num < 0:
        is_neg = True
        num = -num                      # Work only with the absolute value

    # -----------------------------------------------------------------
    # 1)  Determine how many decimal digits the (positive) number has
    # -----------------------------------------------------------------
    cur_num: int = num
    total_digits: int = 0
    while cur_num > 0:
        cur_num //= 10
        total_digits += 1

    # -----------------------------------------------------------------
    # 2)  Account for the commas and the leading minus sign (if any)
    # -----------------------------------------------------------------
    num_commas: int = (total_digits - 1) // 3            # one comma after every 3 digits
    str_len: int = total_digits + num_commas
    if is_neg:
        str_len += 1

    # -----------------------------------------------------------------
    # 3)  Build the character list from the back to the front
    # -----------------------------------------------------------------
    chars: list[str] = [""] * (str_len + 1)               # +1 for the final '\0' in C
    cur_num = num
    index: int = 0
    digits_grouped: int = 0

    while cur_num > 0:
        # Fetch the rightmost digit, convert to its ASCII char
        cur_char: str = chr(cur_num % 10 + 48)            # 48 == ord('0')
        chars[str_len - index - 1] = cur_char

        cur_num //= 10
        index += 1
        digits_grouped += 1

        # Insert comma after every 3 digits (unless we're done)
        if digits_grouped % 3 == 0 and index < str_len:
            chars[str_len - index - 1] = ','
            index += 1

    # Leading minus sign
    if is_neg:
        chars[0] = '-'

    # Null-terminator is ignored in Python, but we keep symmetry
    chars[str_len] = '\0'

    # Join everything except the artificial null
    return "".join(chars[:-1])


# ---------------------------------------------------------------------------
# A small test – analogous to `main()` in the C file
# ---------------------------------------------------------------------------
if __name__ == "__main__":
    print(num_to_string(-5305000), end="")