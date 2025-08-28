# A function that reverses a string
def strrev(input_str):
    return input_str[::-1]

# A function that takes a signed integer num and returns a string representation of the integer
def integer_to_string(num):
    result = []
    is_negative = False

    if num < 0:
        is_negative = True
        num = -num

    # Extract digits and append to the result list
    while True:
        digit = num % 10
        result.append(chr(ord('0') + digit))
        num //= 10
        if num == 0:
            break

    if is_negative:
        result.append('-')

    # Reverse the result and join it into a string
    return strrev(''.join(result))

# Main function to test the implementation
if __name__ == "__main__":
    num = integer_to_string(12345)
    print(num)

    num = integer_to_string(-12345)
    print(num)