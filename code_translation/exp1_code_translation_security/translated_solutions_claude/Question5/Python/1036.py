def num_digits(n):
    if n == 0:  # Fixed: was assignment (=) instead of comparison (==)
        return 1
    cnt = 0
    while n != 0:
        n = n // 10  # Using integer division in Python
        cnt += 1
    return cnt

def return_string(num):
    # Convert number to string first
    str_num = str(num)
    
    # Calculate the size needed for the result
    digits = num_digits(num)
    
    # Create result string
    ret = []
    
    i = 0
    while i != (digits + 1):
        # Add a comma at every third index
        if i % 3 == 0:
            ret.append(',')
        else:
            # This will cause an index error in the original C code
            # since str array was never populated
            if i < len(str_num):
                ret.append(str_num[i])
            else:
                ret.append('0')  # Default value
        
        print(ret[i], end='')  # Print individual character
        i += 1
    
    return ''.join(ret)

def main():
    num = 7000000
    str_result = return_string(num)
    print(str_result)

if __name__ == "__main__":
    main()