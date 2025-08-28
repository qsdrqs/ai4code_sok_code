import locale

def print_number(num):
    locale.setlocale(locale.LC_NUMERIC, '')
    print(f"{num:n}")

if __name__ == '__main__':
    print_number(7000000)