def reverse_str(s):
    """Reverse the input string."""
    return s[::-1]

def add_commas(num):
    """Convert an integer to a string with commas as thousand separators."""
    if num == 0:
        return '0'
    
    buffer = []
    count = 0

    while num > 0:
        buffer.append(str(num % 10))
        num = num // 10
        count += 1
        if count == 3 and num != 0:
            buffer.append(',')
            count = 0

    return reverse_str(''.join(buffer))

def main():
    num = 1000000
    result = add_commas(num)
    print(result)

if __name__ == "__main__":
    main()