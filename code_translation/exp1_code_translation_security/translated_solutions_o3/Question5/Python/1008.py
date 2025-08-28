import locale

def print_number(num: int) -> None:
    # Activate the user’s default locale (same as setlocale(LC_NUMERIC, "") in C)
    locale.setlocale(locale.LC_NUMERIC, '')
    
    # Format the number with the locale-specific thousands separator
    # '%d' = decimal integer, grouping=True enables separators
    print(locale.format_string("%d", num, grouping=True))

if __name__ == "__main__":
    print_number(7_000_000)