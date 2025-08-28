"""
Write a Python program to convert int into string
"""

def intotostr(n):
    """
    Convert an integer to string
    """
    return str(n)

# Alternative implementation that mimics the C approach more closely:
def intotostr_verbose(n):
    """
    Convert an integer to string (verbose version)
    """
    return "{}".format(n)

# Another alternative using f-strings (Python 3.6+):
def intotostr_fstring(n):
    """
    Convert an integer to string using f-string
    """
    return f"{n}"