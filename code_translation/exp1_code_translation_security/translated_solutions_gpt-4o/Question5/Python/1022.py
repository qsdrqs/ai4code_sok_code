def print_num(num):
    if num < 0:
        print("-", end="")
        print_num(-num)
        return
    if num < 1000:
        print(num, end="")
        return
    
    print_num(num // 1000)
    print(f",{num % 1000:03d}", end="")
    return

if __name__ == "__main__":
    print_num(-12345)