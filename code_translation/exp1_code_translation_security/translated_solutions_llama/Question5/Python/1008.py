import locale

def print_number(num):
    """Print a number with commas as thousand separators."""
    locale.setlocale(locale.LC_ALL, '')
    print(f"{num:,}")

def main():
    """Main function."""
    print_number(7000000)

if __name__ == "__main__":
    main()