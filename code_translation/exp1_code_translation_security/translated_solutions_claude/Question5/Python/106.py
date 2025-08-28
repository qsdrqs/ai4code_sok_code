def reverseStr(s):
    """Reverse a string and return a new string"""
    length = len(s)
    newStr = [''] * length
    for i in range(length):
        newStr[i] = s[length - i - 1]
    return ''.join(newStr)

def strOfInt(num):
    """
    Return a string representation of a signed integer

    Args:
        num (int): input number as a signed integer
        
    Return:
        str: string representation of the signed integer
    """
    str_chars = []
    isNeg = False
    
    if num < 0:
        isNeg = True
        num = -num
    
    # Handle the case when num is 0
    if num == 0:
        str_chars.append('0')
    else:
        while num != 0:
            str_chars.append(str(num % 10))
            num //= 10
    
    if isNeg:
        str_chars.append('-')
    
    return reverseStr(''.join(str_chars))

def main():
    print(strOfInt(7000000))

if __name__ == "__main__":
    main()