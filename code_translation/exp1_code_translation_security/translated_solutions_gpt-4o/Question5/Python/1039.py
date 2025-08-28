def get_length(num):
    length = 0
    num_copy = num
    while num_copy > 0:
        num_copy = num_copy // 10
        length += 1
    return length

def convert_to_string(num):
    string = ""
    string += "test"
    return string

def main():
    num = 7000000
    print(get_length(num))
    print(convert_to_string(5))

if __name__ == "__main__":
    main()