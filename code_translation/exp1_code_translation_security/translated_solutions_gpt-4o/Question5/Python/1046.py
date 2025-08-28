def convert(num):
    buf = ""
    buf = str(num)  # Convert the integer to a string
    print(buf)  # Print the string representation of the number
    return buf

def main():
    # num = 7000
    # str = convert(num)
    print(convert(100))  # Call the convert function and print its return value

if __name__ == "__main__":
    main()