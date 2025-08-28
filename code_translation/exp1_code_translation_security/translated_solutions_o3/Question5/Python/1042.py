def int_to_string(num: int) -> str:
    """
    Replicates the behaviour of the C version:
    – Works only for non-negative integers (negative handling added for safety).
    – Inserts a comma after every third digit, counting from the right.
    – Returns the result as a Python str.
    """
    # Handle zero (the C version returns an empty string for 0, we’ll be nicer)
    if num == 0:
        return "0"

    # Deal with negatives – not supported in original code, but easy to add
    if num < 0:
        return '-' + int_to_string(-num)

    chars = []          # Will hold the characters in reverse order
    count = 0
    temp  = num

    while temp != 0:
        # Add current least-significant digit
        chars.append(chr(ord('0') + (temp % 10)))
        temp //= 10
        count += 1

        # Every three digits, add a comma if more digits remain
        if count == 3 and temp != 0:
            chars.append(',')
            count = 0

    # Reverse to obtain the correct order and join into a string
    chars.reverse()
    return ''.join(chars)


# “main”
if __name__ == "__main__":
    print(int_to_string(7_000_000))   # -> 7,000,000