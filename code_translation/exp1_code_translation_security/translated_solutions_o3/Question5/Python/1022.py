import sys

def print_num(num: int) -> None:
    """
    Recursively print an integer with commas every three digits,
    preserving the sign.  (Mimics the C version’s behaviour.)
    """
    if num < 0:
        sys.stdout.write("-")
        print_num(-num)
        return

    if num < 1000:
        sys.stdout.write(str(num))
        return

    print_num(num // 1000)
    sys.stdout.write(",%03d" % (num % 1000))


if __name__ == "__main__":
    print_num(-12345)