def convert(num):
    # Did not add comma because it's too complicated
    
    length = len(str(num))  # equivalent to snprintf(NULL, 0, "%d", num)
    s = str(num)  # equivalent to malloc + snprintf (Python handles memory automatically)
    # if s is None: print("failed")  # Not needed - str() won't fail here
    # print("failed")
    # print(s)
    return s


def main():
    ans = convert(700000)
    print(ans)
    # No need to free() in Python - garbage collection handles memory
    return 0


if __name__ == "__main__":
    main()