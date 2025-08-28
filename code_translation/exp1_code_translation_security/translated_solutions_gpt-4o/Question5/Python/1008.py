import locale

def print_number(num):
    # Set the locale for numeric formatting
    locale.setlocale(locale.LC_NUMERIC, '')
    # Format the number with a thousands separator and print it
    print(locale.format_string("%'d", num, grouping=True))

# Main function
if __name__ == "__main__":
    print_number(7000000)