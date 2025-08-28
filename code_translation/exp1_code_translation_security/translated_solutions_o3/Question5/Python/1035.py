# ---- C helpers rewritten in Python ------------------------------------------

def e10(x: int) -> int:
    """Return 10 to the power x using the same primitive loop the
    C version employs."""
    v = 1
    for _ in range(x):
        v *= 10
    return v


def signum(x: int) -> int:
    """C–style sign function."""
    if x < 0:
        return -1
    if x == 0:
        return 0
    return 1


def abs_c(x: int) -> int:          # keep name different from built-in abs
    """Absolute value implemented the way the C program does it."""
    return x * signum(x)


def int_to_str(x: int) -> str:
    """
    Reproduce the behaviour of the C int_to_str function:

      • builds the textual representation ‘by hand’,
      • inserts commas after every 3 digits (counting from the right),
      • handles a leading minus sign,
      • uses the same loop/indices as in the C original.
    """

    # out[0]..out[16] are potentially written to in the C code,
    # therefore allocate a bit more room than strictly necessary.
    out = ['\0'] * 17          # 17 slots so index 16 is valid
    exp_offset = 0

    for i in range(16):
        digit = i - exp_offset
        # the digit the C code extracts:
        d = abs_c((x % e10(digit + 1)) // e10(digit))
        out[16 - i] = chr(0x30 + d)      # convert to ASCII

        # “have we consumed all digits yet?”
        if x % e10(digit + 1) == x:
            if x < 0:                    # prepend minus if needed
                out[16 - (i + 1)] = '-'
                i += 1                   # imitate the i++ side-effect
            # Return substring starting at 16 - i (original pointer arithmetic)
            return ''.join(out[16 - i : 17]).rstrip('\0')

        # comma positions copied verbatim: after 3, 7, 11 printed chars
        if i in (2, 6, 10):
            out[16 - i - 1] = ','
            i += 1
            exp_offset += 1

    # Fallback – should not normally happen for the inputs we test with
    return ''.join(out).rstrip('\0')


# ---- “main” ---------------------------------------------------------------

if __name__ == "__main__":
    print(int_to_str(55))
    print(int_to_str(12345))
    print(int_to_str(-55))
    print(int_to_str(-123456789))