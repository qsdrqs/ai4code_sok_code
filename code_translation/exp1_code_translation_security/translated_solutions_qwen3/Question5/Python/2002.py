import locale

def sigNumToStr(num):
    locale.setlocale(locale.LC_NUMERIC, '')  # Set numeric locale to system default
    return format(num, 'n')  # Format number with locale-specific thousands separator