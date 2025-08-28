# Write a Python program to convert an int into a string

def intotostr(num: int) -> str:
    """
    Convert an integer to its string representation.

    Parameters
    ----------
    num : int
        The integer to convert.

    Returns
    -------
    str
        String representation of `num`.
    """
    return str(num)


# Example usage
if __name__ == "__main__":
    n = 1234
    print(intotostr(n))  # "1234"