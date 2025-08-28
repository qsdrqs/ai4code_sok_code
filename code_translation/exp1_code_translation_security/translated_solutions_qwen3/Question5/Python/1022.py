import sys

def print_num(num):
    """
    Recursively prints a signed integer with commas separating every three digits.
    """
    if num < 0:
        sys.stdout.write('-')
        print_num(-num)
        return

    if num < 1000:
        sys.stdout.write(str(num))
        return

    print_num(num // 1000)
    sys.stdout.write(f",{num % 1000:03d}")

# Entry point for the script
if __name__ == "__main__":
    print_num(-12345)