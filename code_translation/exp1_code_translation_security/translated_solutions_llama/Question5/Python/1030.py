import locale

def main():
    # Set locale for thousand separators
    locale.setlocale(locale.LC_ALL, '')

    num = 7000000  # -703840
    snum = locale.format_string("%d", num, grouping=True)

    print(snum)

if __name__ == "__main__":
    main()