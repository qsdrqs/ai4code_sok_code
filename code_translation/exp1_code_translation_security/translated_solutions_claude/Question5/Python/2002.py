import locale

def sigNumToStr(num):
    # Set locale for numeric formatting (empty string uses system default)
    locale.setlocale(locale.LC_NUMERIC, "")
    
    # Format the number with thousands separators
    text = locale.format_string("%d", num, grouping=True)
    
    return text