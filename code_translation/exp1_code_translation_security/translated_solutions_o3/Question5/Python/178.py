import math


def convert_to_string(num: int) -> str:
    """
    Rough, line-by-line translation of the C snippet that tries to insert ‘,’
    every three digits when a number is converted to a string.
    """

    # ----  Original C:  char *string = malloc(space + 1); ---------------
    string = str(num)                       # snprintf(…, “%d”, num)
    space = len(string)

    # ----  Original C:  int numOfCommas = round(strlen(string)/3);  -----
    num_of_commas = round(len(string) / 3)
    print(num_of_commas)                    # Keeps the side-effect

    # Allocate a buffer big enough for the digits plus the commas
    new_len = space + num_of_commas
    new_chars = [''] * new_len              # Mimics the second malloc

    # We fill the buffer from the right so that we can drop a comma
    # after every third digit without shifting the already written data.
    write_pos = new_len - 1                 # Index we are writing to
    digit_count = 0                         # How many digits have been copied

    for ch in reversed(string):             # Walk over the digits, right→left
        new_chars[write_pos] = ch
        write_pos -= 1
        digit_count += 1

        # Every three digits (except at the very left end) insert a comma
        if digit_count % 3 == 0 and write_pos >= 0:
            new_chars[write_pos] = ','
            write_pos -= 1

    # Join the character buffer into the final string
    return ''.join(new_chars)


# Roughly equivalent to the final printf in C
if __name__ == "__main__":
    print(convert_to_string(2000))