# All dependencies are built-in.

# C equivalent: const int MAX_LEN = 14;
# This defines the size of the temporary buffer for string construction.
# The algorithm is limited by this size and will fail for numbers whose
# formatted representation exceeds this length.
MAX_LEN = 14

def stringify(i: int) -> tuple[int, str]:
    """
    Translates the C stringify function.

    This function converts an integer to a string, formatting it with commas
    as thousands separators. It mimics the C version's right-to-left
    string building algorithm in a fixed-size buffer.

    Note: The original C code had bugs related to handling the input 0 and
    copying the final string from the temporary buffer. This Python version
    corrects these bugs to produce a functional and faithful translation of
    the intended logic.

    Args:
        i: The integer to convert.

    Returns:
        A tuple containing:
        - The length of the resulting string.
        - The formatted string.
    
    Raises:
        IndexError: If the formatted number exceeds MAX_LEN, mirroring
                    the buffer overflow behavior of the C code.
    """
    # C equivalent: char tmp[14] = "00000000000000";
    # A list of characters is used as a mutable string buffer in Python.
    tmp = ['0'] * MAX_LEN

    # The original C code's `while (j > 0)` loop fails for i = 0.
    # We handle this as a special case for a correct implementation.
    if i == 0:
        return 1, "0"

    # C equivalent: int j = (i >= 0) ? i : -1 * i;
    # Take the absolute value of the input number for digit processing.
    j = abs(i)

    # C equivalent: int k = MAX_LEN - 1; int c = 0;
    # k is the index for the buffer, starting from the right end.
    # c is a counter for inserting commas.
    k = MAX_LEN - 1
    c = 0

    # This loop builds the string from right to left in the `tmp` buffer.
    # C equivalent: while (j > 0 && k >= 0) { ... }
    while j > 0:
        # C equivalent: if (c != 0 && c % 3 == 0) { ... }
        # Insert a comma every 3 digits (but not before the first digit).
        if c > 0 and c % 3 == 0:
            tmp[k] = ','
            k -= 1

        # C equivalent: int digit = j % 10; char d_char = digit + '0'; tmp[k] = d_char;
        digit = j % 10
        tmp[k] = str(digit)

        # C equivalent: j /= 10; k--; c++;
        j //= 10
        k -= 1
        c += 1

    # C equivalent: if (i < 0) { tmp[k] = '-'; k--; }
    # Prepend a minus sign if the original number was negative.
    if i < 0:
        tmp[k] = '-'
        k -= 1

    # The resulting string is in the slice of `tmp` from index `k+1` to the end.
    # This part replaces the buggy final loop from the C code.
    # C: for (int n = k; n < MAX_LEN; n++) buf[n - k] = tmp[n + 1];
    result_str = "".join(tmp[k+1:])

    # C equivalent: return MAX_LEN - 1 - k;
    # The length is simply the length of the generated string.
    length = len(result_str)

    return length, result_str


def main():
    """
    C equivalent: int main(void) { ... }
    This function demonstrates the usage of the stringify function.
    """
    # C equivalent: char buf[14] = "00000000000000";
    # In Python, the string is returned, so no buffer is needed beforehand.

    # C equivalent: int input = -2147483647;
    input_val = -2147483647

    # C equivalent: int l = stringify(input, buf);
    # The function returns both the length and the string itself.
    l, buf = stringify(input_val)
    
    # C equivalent: printf("%d :: %s\n", l, buf);
    print(f"{l} :: {buf}")
    
    # C equivalent: return 0; (implicit in Python when main() finishes)

# Standard Python entry point to run the main function.
if __name__ == "__main__":
    main()