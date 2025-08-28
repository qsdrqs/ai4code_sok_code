def get_length(num: int) -> int:
    """
    Replicates the logic of the C getLength function.
    Counts how many base-10 digits are in an integer.
    """
    length = 0
    num_copy = num
    while num_copy > 0:
        num_copy //= 10
        length += 1
    return length


def convert_to_string(num: int) -> str:
    """
    Rough equivalent of the C convertToString function.
    (The incoming num parameter is unused, just like in the C code.)
    """
    result = ""        # start with an empty string
    result += "test"   # concatenate the literal “test”
    return result


def main() -> None:
    num = 7_000_000

    # Print without an automatic newline so the output matches the C program
    print(get_length(num), end="")
    print(convert_to_string(5))


if __name__ == "__main__":
    main()