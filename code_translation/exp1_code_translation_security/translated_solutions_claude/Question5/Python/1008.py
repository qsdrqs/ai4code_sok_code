import locale

def print_number(num):
    locale.setlocale(locale.LC_NUMERIC, "")
    print(f"{num:n}")

def main():
    print_number(7000000)

if __name__ == "__main__":
    main()