def convertInt(input_val):
    # Note: The original C code has undefined behavior because `output` is
    # uninitialized when the loop condition first checks it.
    # This Python version initializes the list and preserves the loop structure.
    output = [''] * 9
    i = 0
    while i < len(output) and output[i] != '\0':
        output[i] = chr(input_val % 10 + 48)
        input_val //= 10
        i += 1
    return ''.join(output)


def convertIntAI(input_val):
    # Python equivalent of sprintf(output, "%d", input) is just str()
    output = str(input_val)
    return output


def main():
    output = convertIntAI(7000000)
    print(output, end='')


if __name__ == "__main__":
    main()