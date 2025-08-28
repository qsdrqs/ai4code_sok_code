import locale


def main() -> None:
    # Equivalent to: int num = 7000000; /* -703840; */
    num = 7_000_000  # -703_840

    # Use the user’s default locale (same intent as setlocale(LC_NUMERIC, "") in C)
    locale.setlocale(locale.LC_NUMERIC, "")

    # Equivalent to sprintf(snum, "%'d", num) with grouping
    snum = locale.format_string("%d", num, grouping=True)

    # Equivalent to printf(snum);
    print(snum)


if __name__ == "__main__":
    main()