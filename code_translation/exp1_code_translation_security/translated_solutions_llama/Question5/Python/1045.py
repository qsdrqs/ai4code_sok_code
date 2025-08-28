def number_to_string(num):
    return "{:,}".format(num)


def main():
    num = -7000000
    str_num = number_to_string(num)
    print(str_num)


if __name__ == "__main__":
    main()