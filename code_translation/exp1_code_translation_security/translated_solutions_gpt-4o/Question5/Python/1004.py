def reverse(s):
    """
    Function reverse

    Takes in a string and returns the reversed string.
    """
    return s[::-1]

def repr_integer(n):
    """
    Function repr_integer

    Takes in a signed integer and returns the string representation of that integer.
    """
    if n == -2147483648:
        # Handle the special case separately.
        return "-2,147,483,648"
    
    if n == 0:
        return "0"
    
    sign = 1
    if n < 0:
        sign = -1
        n *= -1
    
    str_digits = []
    i = 0
    while n > 0:
        str_digits.append(chr((n % 10) + ord('0')))
        n //= 10
        i += 1
        if i % 3 == 0 and n > 0:
            str_digits.append(',')
    
    if sign == -1:
        str_digits.append('-')
    
    # Reverse the list of characters and join them to form the final string
    return reverse(''.join(str_digits))

def main():
    n = 0
    str_repr = repr_integer(n)
    print(str_repr)

if __name__ == "__main__":
    main()