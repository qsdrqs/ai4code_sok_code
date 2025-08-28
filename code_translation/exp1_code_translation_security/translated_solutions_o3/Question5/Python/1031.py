def int_to_string(value: int) -> str:
    """
    Takes an integer `value`, converts it to a string, prints it, and
    returns the string representation.
    """
    buffer = str(value)                  # Same as sprintf(buffer, "%d", input)
    print(f"you have entered: {buffer}") # Same console output as the C code
    return buffer                        # Return the string (buffer)


if __name__ == "__main__":
    int_to_string(7_000_000)             # Equivalent to int_to_string(7000000) in C