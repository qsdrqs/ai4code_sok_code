def main():
    """
    This function demonstrates converting an integer to a string in Python.
    """
    # Define the integer you want to convert
    number = -12

    # In Python, you can directly convert an integer to a string using the str() function.
    # There is no need to pre-allocate a character array.
    string_representation = str(number)

    # Print the string. The print() function in Python automatically adds a newline.
    print(string_representation)

# Standard entry point for a Python script, similar to main() in C.
if __name__ == "__main__":
    main()