def num_to_str(num: int) -> str:
    """
    Insert thousands-separating commas into an integer’s decimal string
    (faithfully mirroring the intent of the original C routine).
    """
    # Special-case zero – nothing to format
    if num == 0:
        return "0"

    # Keep track of the sign; work with the magnitude only
    negative   = num < 0
    magnitude  = abs(num)
    base_chars = list(str(magnitude))          # ['7','0','4','2'] …

    # We will build the output in reverse (right-to-left)
    out = []
    digits_moved = 0

    # Start from the least-significant digit and walk leftwards
    for i in range(len(base_chars) - 1, -1, -1):
        out.append(base_chars[i])              # copy one digit
        digits_moved += 1

        # After 3 digits (and if we are not at the very beginning)
        if digits_moved == 3 and i != 0:
            out.append(',')                    # insert a comma
            digits_moved = 0                   # reset counter

    # If the original number was negative, prepend the sign
    if negative:
        out.append('-')

    # We constructed the string backwards – flip it around
    out.reverse()
    return ''.join(out)


def main() -> None:
    num = 7042
    formatted = num_to_str(num)
    print(f"Output: {formatted}")


if __name__ == "__main__":
    main()