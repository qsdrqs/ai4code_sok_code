def repr_int(n):
    """
    Function repr_int
    
    Takes in a signed integer and returns the string representation of that integer.
    """
    MAX_DIGITS = 100
    
    if n == -2147483648:
        # When multiplied by -1 this overflows, so handle it separately.
        return "-2,147,483,648"
    
    if n == 0:
        return "0"
    
    str_chars = []
    sign = 1
    
    if n < 0:
        sign = -1
        n *= -1
    
    while n > 0:
        str_chars.append(str(n % 10))
        if (len(str_chars) + 1) % 4 == 0 and n > 10:
            str_chars.append(',')
        n //= 10
    
    if sign == -1:
        str_chars.append('-')
    
    # Reverse the string
    str_chars.reverse()
    
    return ''.join(str_chars)


def reverse(s):
    """
    Function reverse
    
    Takes in a string and reverses it.
    """
    return s[::-1]


def main():
    n = 0
    result = repr_int(n)
    print(result)


if __name__ == "__main__":
    main()