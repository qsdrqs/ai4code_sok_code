import locale

def sig_num_to_str(num: int) -> str:
    """
    Format an integer using the current locale’s thousands-separator rules.

    Equivalent to the C version that calls
        setlocale(LC_NUMERIC, "");
        sprintf(text, "%'d", num);
    """
    # Make sure the numeric locale is initialised ("" = user’s default)
    locale.setlocale(locale.LC_NUMERIC, "")

    # Return the number with locale-aware grouping
    # (same effect as "%'d" in C’s printf when LC_NUMERIC is set)
    return locale.format_string("%d", num, grouping=True)