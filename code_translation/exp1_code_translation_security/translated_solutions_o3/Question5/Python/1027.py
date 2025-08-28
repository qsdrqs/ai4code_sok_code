import locale


def convert(i: int) -> int:
    # make the program obey the user’s current numeric locale
    locale.setlocale(locale.LC_NUMERIC, '')
    # print the integer with the locale’s thousands-separator
    # (equivalent to C’s  printf("%'d\n", i); )
    print(locale.format_string("%d", i, grouping=True))
    return 0


def main() -> int:
    a = 400
    convert(a)
    return 0


if __name__ == "__main__":
    main()