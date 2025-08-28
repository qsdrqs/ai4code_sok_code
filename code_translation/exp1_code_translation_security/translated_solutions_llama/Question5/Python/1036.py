def num_digits(n):
    """Return the number of digits in n."""
    if n == 0:
        return 1
    cnt = 0
    while n != 0:
        n = n // 10
        cnt += 1
    return cnt

def return_string(num):
    """Return a string representation of num with commas every three digits."""
    num_str = str(num)[::-1]  # Convert to string and reverse
    result = []
    for i, char in enumerate(num_str):
        if i % 3 == 0 and i != 0:
            result.append(',')
        result.append(char)
    return ''.join(result)[::-1]  # Reverse back and join

def main():
    num = 7000000
    string = return_string(num)
    print(string)

if __name__ == "__main__":
    main()