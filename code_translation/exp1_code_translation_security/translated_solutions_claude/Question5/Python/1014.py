def strrev(input_str):
    """
    A function that reverse a string
    """
    return input_str[::-1]

def integerToString(num):
    """
    A function that takes a signed integer num and returns a string representation of the integer
    """
    result = []
    isNegative = False
    
    if num < 0:
        isNegative = True
        num = -num
    
    # Handle the special case of 0
    if num == 0:
        result.append('0')
    else:
        while num != 0:
            digit = num % 10
            result.append(chr(ord('0') + digit))
            num //= 10
    
    if isNegative:
        result.append('-')
    
    return strrev(''.join(result))

def main():
    num = integerToString(12345)
    print(num)
    
    num = integerToString(-12345)
    print(num)

if __name__ == "__main__":
    main()