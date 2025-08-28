def reverse(s):
    """Reverse a string"""
    return s[::-1]

def itoa(n):
    """Convert integer n to string representation"""
    sign = n
    if sign < 0:  # record sign
        n = -n    # make n positive
    
    s = ""
    # generate digits in reverse order
    while True:
        s += str(n % 10)  # get next digit
        n //= 10
        if n == 0:
            break
    
    if sign < 0:
        s += '-'
    
    return s

def insert_comma(s, pos):
    """Insert comma at specified position in string"""
    return s[:pos] + ',' + s[pos:]

def intToString(num):
    """Convert integer to string with comma separators"""
    s = itoa(num)
    length = len(s)
    
    i = 3
    while i <= length:
        s = insert_comma(s, i)
        length = len(s)  # update length after insertion
        i += 4
    
    # Remove trailing comma if it exists
    if s and s[-1] == ',':
        s = s[:-1]
    
    s = reverse(s)
    return s

def main():
    num = 7000
    result = intToString(num)
    print(result)

if __name__ == "__main__":
    main()