import locale

def sig_num_to_str(num):
    locale.setlocale(locale.LC_NUMERIC, '')
    return locale.format_string("%d", num, grouping=True)

# Example usage
num = 1234567
formatted_str = sig_num_to_str(num)
print(formatted_str)