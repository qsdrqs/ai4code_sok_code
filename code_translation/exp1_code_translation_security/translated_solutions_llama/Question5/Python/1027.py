import locale

# Set the locale for thousand separators
locale.setlocale(locale.LC_ALL, '')

def convert(i):
    """
    Formats the given integer with thousand separators and prints it.
    
    Args:
        i (int): The number to be formatted.
    """
    # Use locale's format function to add thousand separators
    formatted_number = locale.format_string("%d", i, grouping=True)
    print(formatted_number)

def main():
    a = 400
    convert(a)

if __name__ == "__main__":
    main()