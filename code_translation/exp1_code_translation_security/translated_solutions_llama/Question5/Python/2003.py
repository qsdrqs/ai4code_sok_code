def num_to_string(num: int) -> str:
    return f"{num:,}" if num >= 0 else f"-{abs(num):,}"


def main():
    print(num_to_string(-5305000))


if __name__ == "__main__":
    main()