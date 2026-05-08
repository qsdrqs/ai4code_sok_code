import locale

# TODO: Verify

def main():
    num = 7000000  # -703840
    
    locale.setlocale(locale.LC_NUMERIC, '')
    snum = locale.format_string("%d", num, grouping=True)
    
    print(snum, end='')

if __name__ == "__main__":
    main()