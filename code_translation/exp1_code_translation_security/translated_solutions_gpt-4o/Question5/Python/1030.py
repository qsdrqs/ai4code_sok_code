import locale

# Set the locale for numeric formatting
locale.setlocale(locale.LC_NUMERIC, '')

def main():
    num = 7000000  # -703840
    # Format the number with thousands separator
    snum = locale.format_string("%'d", num, grouping=True)
    
    print(snum)

if __name__ == "__main__":
    main()