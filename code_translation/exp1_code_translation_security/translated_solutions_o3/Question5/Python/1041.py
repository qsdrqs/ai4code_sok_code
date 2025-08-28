"""
This is a direct, literal translation of the given C program into Python.
It keeps the same two-step approach:

  1. `reverse_str` – reverses any supplied string.
  2. `add_commas`  – builds the textual representation of a positive
     integer, inserting commas every three digits (from the right).

A tiny “main” block is included so the module can be run as a script in
the same way the C program’s `main` was used.
"""

def reverse_str(s: str) -> str:
    """
    Reverse the characters in *s*.

    Equivalent to the C version that swapped characters in place, but
    directly uses Python’s slicing.
    """
    return s[::-1]


def add_commas(num: int) -> str:
    """
    Convert *num* (assumed non-negative) to a string with commas inserted
    every three digits from the right, reproducing the explicit logic of
    the C code.
    """
    # Special-case 0, because the C loop would otherwise yield an empty
    # string.
    if num == 0:
        return "0"

    chars = []          # Collect digits (and commas) in reverse order.
    count = 0           # How many digits have been processed since last comma.

    while num:
        digit = num % 10
        chars.append(chr(ord('0') + digit))  # same as 'digit' + '0' in C
        num //= 10
        count += 1

        # Insert a comma after every group of three digits, *except* when
        # we have consumed the entire number.
        if count == 3 and num:
            chars.append(',')
            count = 0

    # We built the string backwards; reverse it to obtain the final form.
    return reverse_str(''.join(chars))


# ------------------------------------------------------------------------
# Optional demonstration (mirrors the C program’s `main`)
# ------------------------------------------------------------------------
if __name__ == "__main__":
    num = 1_000_000                # 1000000 in C source
    formatted = add_commas(num)    # "1,000,000"
    print(formatted)