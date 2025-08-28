import sys

def int_to_string(a: int) -> str:
    return f"{a:,}"

def main():
    print(int_to_string(7000000))
    print(int_to_string(700000))
    print(int_to_string(70000))
    print(int_to_string(7000))
    print(int_to_string(700))
    print(int_to_string(70))
    print(int_to_string(7))
    print(int_to_string(0))
    print(int_to_string(-7000000))
    print(int_to_string(-700000))
    print(int_to_string(-70000))
    print(int_to_string(-7000))
    print(int_to_string(-700))
    print(int_to_string(-70))
    print(int_to_string(-7))
    print(int_to_string(sys.maxsize))
    print(int_to_string(-sys.maxsize - 1))

if __name__ == "__main__":
    main()