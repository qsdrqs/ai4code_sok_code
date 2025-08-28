# Write a function in Python that takes an integer and converts it to a string
# using the same grouping rule as the original C code:
#   – right-most group : 4 digits
#   – every group to the left : 3 digits
# Example: 10099870  ->  1,009,9870

def int_to_string(num: int) -> str:
    # Special-case zero
    if num == 0:
        return "0"

    # Handle the sign
    sign = ""
    if num < 0:
        sign = "-"
        num = -num                      # work with the absolute value

    digits = []                         # will collect characters in reverse
    group_size = 4                      # first (right-most) group length
    count = 0                           # digits written in the current group

    while num > 0:
        # append next least-significant digit
        digits.append(str(num % 10))
        num //= 10
        count += 1

        # when the current group is complete, and there are still digits left,
        # insert a comma and switch to 3-digit groups
        if count == group_size and num > 0:
            digits.append(',')
            count = 0
            group_size = 3              # all subsequent groups are length 3

    # digits are reversed; re-assemble with the optional sign
    return sign + ''.join(reversed(digits))


# ----------------------------------------------------------------------
# Demo (equivalent to the C main function)

if __name__ == "__main__":
    num = -10099870
    converted = int_to_string(num)
    print(converted)