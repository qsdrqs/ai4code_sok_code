def string_rep(num):
    # In Python, converting a number to a string is trivial.
    # No need for manual memory allocation (malloc/free), log10 size
    # calculation, or char** pointers — strings are first-class objects
    # and memory is managed automatically by the garbage collector.
    return str(num)


def main():
    leet = 1337
    leet_str = string_rep(leet)
    print(leet_str, end='')


main()