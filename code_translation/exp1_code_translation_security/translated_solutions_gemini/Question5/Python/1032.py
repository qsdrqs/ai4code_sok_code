# In C, headers like <stdio.h>, <stdlib.h>, and <string.h> are needed for
# I/O, memory allocation, and string functions. In Python, these
# functionalities are mostly built-in and do not require explicit imports.
#
# Key C to Python Mappings:
# - printf()       -> print()
# - malloc()/free()  -> Handled automatically by Python's garbage collector.
# - strlen()       -> len()
# - char*          -> Python's `str` type.

def int_to_string(num: int) -> str:
    """
    Translates the C `int_to_string` function.
    
    The C function manually calculates the length and builds the string
    character by character. The idiomatic and direct way to do this in
    Python is to use the built-in `str()` constructor. It also correctly
    handles edge cases like 0 and negative numbers, which the original
    C code did not.
    """
    return str(num)

def int_to_string_with_commas(num: int) -> str:
    """
    Translates the C `int_to_string_with_commas` function.

    The original C code contains a complex and buggy loop to insert commas.
    The most effective and "Pythonic" way to achieve the same result is to
    use Python's built-in string formatting capabilities.
    
    The f-string format specifier `:,` automatically adds thousand
    separators, producing a clean, readable, and correct result.
    """
    return f"{num:,}"

def main():
    """
    This function is the equivalent of the C `main` function, which serves
    as the entry point of the program.
    """
    num = 7654321
    
    # In C, a char pointer is returned which must be manually freed using free().
    # In Python, strings are objects, and memory is managed automatically.
    # There is no need to free the string manually.
    s = int_to_string_with_commas(num)
    
    # Print the resulting string to the console.
    print(s)

# This standard Python construct ensures that the `main()` function is called
# only when the script is executed directly, similar to how the C `main`
# function is the designated start of execution.
if __name__ == "__main__":
    main()