def string_it(value):
    """Return a string containing the expansion of the signed int."""
    # Even if 64 bit int... only about 18 characters.
    # Add room for sign and 6 commas
    # Far less than 30 total.
    returned = ''
    if value < 0:
        returned = '-'
        value = -value
    
    # Equivalent to sprintf(str, "%d", value)
    s = str(value)
    length = len(s)
    comma_count = (length - 1) // 3
    
    # Use a list since Python strings are immutable
    # (equivalent to the mutable char array in C)
    chars = list(s)
    end = length
    
    while comma_count > 0:
        start = end - 3
        # Equivalent to printf("%s\n", start) - prints from start to end of string
        print(''.join(chars[start:]))
        # Insert comma at position start (shifts everything right by 1)
        chars.insert(start, ',')
        comma_count -= 1
        end = start
    
    returned += ''.join(chars)
    return returned


def main():
    print(f"   {string_it(-123457)}")


if __name__ == "__main__":
    main()