import math

def int_to_string(x):
    """Converts an integer into a string with commas as thousand separators."""
    return "{:,}".format(x)

def main():
    print(int_to_string(100000))
    print("test")

if __name__ == "__main__":
    main()