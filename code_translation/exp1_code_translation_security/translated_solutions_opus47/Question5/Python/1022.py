import sys

def print_num(num):
    if num < 0:
        sys.stdout.write("-")
        print_num(-num)
        return
    if num < 1000:
        sys.stdout.write(f"{num}")
        return
    
    print_num(num // 1000)
    sys.stdout.write(f",{num % 1000:03d}")
    return

print_num(-12345)