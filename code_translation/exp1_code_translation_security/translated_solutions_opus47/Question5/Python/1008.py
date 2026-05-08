import locale

def print_number(num):
    locale.setlocale(locale.LC_NUMERIC, '')
    print(locale.format_string("%d", num, grouping=True))


print_number(7000000)