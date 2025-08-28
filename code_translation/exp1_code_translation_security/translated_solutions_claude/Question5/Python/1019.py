import math

def string_rep(num):
    """
    Convert a number to its string representation.
    Returns a list containing the string (to mimic char** behavior).
    """
    # Calculate size similar to C version (though not needed in Python)
    size = int(math.log10(num)) if num > 0 else 1
    
    # Convert number to string
    str_result = str(num)
    
    # Return as a list to mimic char** (pointer to pointer)
    return [str_result]

def main():
    leet = 1337
    leet_str = string_rep(leet)
    print(leet_str[0])  # Access first element like *leet_str in C

if __name__ == "__main__":
    main()