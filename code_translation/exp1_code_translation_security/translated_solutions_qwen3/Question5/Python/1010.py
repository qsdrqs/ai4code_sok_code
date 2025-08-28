def string_it(value):
    """
    Converts an integer to a string with commas as thousand separators.
    Handles negative numbers by prepending a '-' sign.
    """
    # Handle the sign
    if value < 0:
        sign = '-'
        value = -value
    else:
        sign = ''

    # Convert the number to a string
    s = str(value)

    # Reverse the string to insert commas from the right
    reversed_s = s[::-1]

    # Split the reversed string into chunks of 3 digits
    chunks = []
    for i in range(0, len(reversed_s), 3):
        chunk = reversed_s[i:i+3]
        chunks.append(chunk)

    # Reverse each chunk and the list of chunks to get the correct order
    chunks = [chunk[::-1] for chunk in chunks][::-1]

    # Join the chunks with commas and prepend the sign
    return sign + ','.join(chunks)


def main():
    """
    Main function that prints the formatted string with three leading spaces.
    """
    result = string_it(-123457)
    print(f"   {result}")


# Entry point
if __name__ == "__main__":
    main()