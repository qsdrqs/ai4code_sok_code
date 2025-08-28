import locale

def main():
    num = 7000000  # -703840
    
    # Set locale for numeric formatting (empty string uses system default)
    locale.setlocale(locale.LC_NUMERIC, "")
    
    # Format number with thousands separator
    snum = locale.format_string("%d", num, grouping=True)
    
    print(snum)

if __name__ == "__main__":
    main()