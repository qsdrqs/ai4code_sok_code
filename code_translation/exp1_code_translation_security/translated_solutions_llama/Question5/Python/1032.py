def int_to_string_with_commas(num: int) -> str:
    """Converts an integer to a string with commas as thousand separators."""
    num_str = str(num)[::-1]
    result = []
    for i, char in enumerate(num_str):
        if i % 3 == 0 and i != 0:
            result.append(',')
        result.append(char)
    return ''.join(result)[::-1]


def main():
    num = 7654321
    str_with_commas = int_to_string_with_commas(num)
    print(str_with_commas)


if __name__ == "__main__":
    main()