def convertInt(input_num):
    output = [''] * 9
    
    i = 0
    while i < len(output) and output[i] == '':
        output[i] = chr(input_num % 10 + 48)
        input_num //= 10
        i += 1
    
    return ''.join(output).rstrip('\x00')

def convertIntAI(input_num):
    output = str(input_num)
    return output

def main():
    output = convertIntAI(7000000)
    print(output, end='')

if __name__ == "__main__":
    main()