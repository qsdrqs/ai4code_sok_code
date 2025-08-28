import math

def string_rep(num):
    # Simulate the C function's logic:
    # Calculate the number of digits using log10 (as in the C code)
    size = int(math.log10(num))
    
    # In C, this would be a char** pointing to a buffer of size bytes.
    # In Python, we simulate this with a list containing the string.
    # Note: The size is not used here, as Python strings are immutable and dynamic.
    return [str(num)]

def main():
    leet = 1337
    leet_str = string_rep(leet)
    
    # Dereference the "pointer" (i.e., access the first element of the list)
    print(leet_str[0])

    # In C, free(leet_str) would be called here.
    # In Python, memory is automatically managed, so no need to explicitly free.

if __name__ == '__main__':
    main()