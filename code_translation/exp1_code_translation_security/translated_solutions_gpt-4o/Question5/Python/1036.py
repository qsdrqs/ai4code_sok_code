def num_digits(n):
    if n == 0:
        return 1
    cnt = 0
    while n != 0:
        n = n // 10
        cnt += 1
    return cnt

def return_string(num):
    # Convert the number to a string
    str_num = str(num)
    ret = []
    
    # Reverse the string to process from the least significant digit
    reversed_str = str_num[::-1]
    
    for i in range(len(reversed_str)):
        # Add a comma at every third index (except at the end)
        if i > 0 and i % 3 == 0:
            ret.append(',')
        ret.append(reversed_str[i])
    
    # Reverse the result back to the original order
    ret = ''.join(ret[::-1])
    return ret

def main():
    num = 7000000
    str_result = return_string(num)
    print(str_result)

if __name__ == "__main__":
    main()