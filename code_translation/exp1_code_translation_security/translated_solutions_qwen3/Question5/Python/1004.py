def repr(n):
    """
    Converts a signed integer to its string representation with commas separating every three digits.
    
    Special cases:
    - If n is -2147483648, returns "-2,147,483,648" directly.
    - If n is 0, returns "0".
    """
    if n == -2147483648:
        return "-2,147,483,648"
    if n == 0:
        return "0"

    # Determine if the number is negative
    is_negative = n < 0
    n_abs = abs(n)

    digits = []
    i = 0  # Tracks the current index in the digits list

    while n_abs > 0:
        digit = n_abs % 10
        digits.append(str(digit))
        i += 1

        # Insert a comma every 3 digits (in reverse order), but only if more digits remain
        if (i + 1) % 4 == 0 and n_abs > 10:
            digits.append(',')
            i += 1

        n_abs //= 10  # Move to the next digit

    # Append the negative sign if needed
    if is_negative:
        digits.append('-')

    # Reverse the list to get the correct order and join into a string
    return ''.join(reversed(digits))


def main():
    """
    Main function to test the repr function.
    """
    n = 0
    result = repr(n)
    print(result)


if __name__ == "__main__":
    main()