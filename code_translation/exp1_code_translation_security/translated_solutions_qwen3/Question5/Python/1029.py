def convertInt(input_val):
    """
    This function mimics the behavior of the C function `convertInt`, which is incorrect.
    It returns a string of null bytes (`\x00`) because the original C code uses uninitialized memory.
    """
    output = ['\x00'] * 9  # Simulate a C char array of size 9 initialized to null bytes
    for i in range(9):
        if output[i] == '\x00':
            break
        output[i] = chr(input_val % 10 + ord('0'))
        input_val //= 10
    return ''.join(output)

def convertIntAI(input_val):
    """
    This function mimics the behavior of the C function `convertIntAI`, which uses `sprintf` to convert an integer to a string.
    """
    return str(input_val)

def main():
    """
    Main function that calls `convertIntAI` with the value 7000000 and prints the result.
    """
    output = convertIntAI(7000000)
    print(output)

if __name__ == "__main__":
    main()