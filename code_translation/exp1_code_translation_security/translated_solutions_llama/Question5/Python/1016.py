def int_to_string(num: int) -> str:
    return "{:,}".format(num)


def main():
    num = -10099870
    str_num = int_to_string(num)
    print(str_num)


if __name__ == "__main__":
    main()