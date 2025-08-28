def reverse_str(s):
    """
    Takes a string and reverses it in-place (conceptually)
    Returns the reversed string
    """
    return s[::-1]

def add_commas(num):
    """
    Takes an integer, converts it to string, and adds commas where appropriate
    Returns the formatted string with commas
    """
    if num == 0:
        return "0"
    
    str_digits = ""
    count = 0
    
    while num != 0:
        str_digits += str(num % 10)
        num //= 10
        count += 1
        if count == 3 and num != 0:
            str_digits += ','
            count = 0
    
    return reverse_str(str_digits)

def main():
    num = 1000000
    result = add_commas(num)
    print(result)

if __name__ == "__main__":
    main()