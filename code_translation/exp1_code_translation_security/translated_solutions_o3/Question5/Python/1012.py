# ----------------------------------------------------------
#  python_port.py
# ----------------------------------------------------------
# A simple re-implementation of the C program given in the
# prompt.  It converts an integer to a decimal string with
# commas inserted every three digits counting from the right.
# ----------------------------------------------------------

import sys


def insert(s: str, index: int) -> str:
    """
    Helper that returns a *new* string with a comma inserted
    at the supplied index.
    Equivalent to the intended behaviour of the `insert`
    function in the C snippet.
    """
    # s[ :index]  -> part before the insertion point
    # ','         -> comma to insert
    # s[index: ]  -> rest of the original string
    return s[:index] + ',' + s[index:]


def string_repre(num: int) -> str:
    """
    Build the comma-separated decimal representation of `num`.
    Mirrors what the original `stringRepre` function was
    supposed to accomplish.
    """
    strnum = str(num)                 # original decimal string
    l = len(strnum)                   # length before insertion
    conum = l // 3                    # how many commas are needed
    buff = strnum                     # working copy, will grow

    # Insert commas from right to left so earlier insertions do
    # not invalidate the indices of positions still to come.
    for i in range(1, conum + 1):
        # After i-1 commas have already been inserted, the next
        # comma should sit 3*i characters from the right *plus*
        # i-1 commas already added.
        idx = len(buff) - (3 * i + (i - 1))
        if idx <= 0:
            break                     # nothing left to do
        buff = insert(buff, idx)

    return buff


def main(argv: list[str] | None = None) -> str:
    """
    Entry point that mimics `char* main(int argc, char* argv[])`.
    It returns the comma-formatted representation of `argc`
    (i.e. the number of command-line arguments).
    """
    if argv is None:
        argv = sys.argv

    argc = len(argv)                  # number of command-line arguments
    return string_repre(argc)


# If executed directly, behave like a C `main` and print the result.
if __name__ == "__main__":
    print(main())