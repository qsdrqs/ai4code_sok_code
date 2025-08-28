def print_num(num):
    """Prints a number with commas as thousand separators."""
    if num < 0:
        print("-", end='')
        print_num(-num)
        return
    
    if num < 1000:
        print(num, end='')
        return
    
    print_num(num // 1000)
    print(f",{num % 1000:03d}", end='')

def main():
    print_num(-12345)

if __name__ == "__main__":
    main()