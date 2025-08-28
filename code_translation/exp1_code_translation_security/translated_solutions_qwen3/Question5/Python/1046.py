def convert(num):
    buf = str(num)
    print(buf, end='')  # Mimic C's printf without newline
    return buf[0]        # Return the first character

def main():
    result = convert(100)
    print(result, end='')  # Print the first character

if __name__ == "__main__":
    main()