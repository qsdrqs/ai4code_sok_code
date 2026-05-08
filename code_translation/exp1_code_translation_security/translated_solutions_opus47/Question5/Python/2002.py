import locale


def sig_num_to_str(num):
    locale.setlocale(locale.LC_NUMERIC, '')
    text = locale.format_string("%d", num, grouping=True)
    return text