def stringify(i: int) -> str:
    """
    Converts an integer into a string with comma separation.

    Args:
    i (int): The integer to be converted.

    Returns:
    str: The string representation of the integer with comma separation.
    """
    max_len = 14
    tmp = list("0" * max_len)
    j = abs(i)
    k = max_len - 1
    c = 0

    while j > 0 and k >= 0:
        if c != 0 and c % 3 == 0:
            tmp[k] = ','
            k -= 1
            c = 0
        # Convert digit to char
        digit = j % 10
        # Add to string
        tmp[k] = str(digit)
        # Move to next digit
        j //= 10
        k -= 1
        c += 1

    if i < 0:
        tmp[k] = '-'
        k -= 1

    # Remove leading zeros
    start_idx = k + 1
    return "".join(tmp[start_idx:])


def main():
    input_num = -2147483647
    result_str = stringify(input_num)
    print(f"{len(result_str)} :: {result_str}")


if __name__ == "__main__":
    main()