import locale

def convert(i):
    locale.setlocale(locale.LC_NUMERIC, "")
    print(f"{i:n}")
    return 0

def main():
    a = 400
    convert(a)
    return 0

if __name__ == "__main__":
    main()