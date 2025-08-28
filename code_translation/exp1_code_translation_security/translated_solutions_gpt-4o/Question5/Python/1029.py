def convert_int(input_value):
    output = []
    while input_value > 0:
        output.append(chr(input_value % 10 + 48))  # Convert digit to character
        input_value //= 10
    output.reverse()  # Reverse the list to get the correct order
    return ''.join(output)

def convert_int_ai(input_value):
    return str(input_value)  # Python's built-in `str` function handles this

def main():
    output = convert_int_ai(7000000)
    print(output)

if __name__ == "__main__":
    main()