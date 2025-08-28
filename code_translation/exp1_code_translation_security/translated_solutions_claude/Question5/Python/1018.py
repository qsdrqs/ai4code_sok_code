def change(input_num):
    count = 0
    tmp = input_num
    
    # Count digits
    while True:
        tmp //= 10
        count += 1
        if tmp == 0:
            break
    
    num = count + count // 3
    result = [''] * (num + 2)  # Create list to simulate char array
    mid = 0
    print(num)
    
    i = num - 1
    while i >= 0:
        if mid == 3:
            mid = 0
            result[i] = ','
            i -= 1
        
        if i >= 0:  # Check bounds to avoid index error
            result[i] = str(input_num % 10)
            input_num //= 10
            mid += 1
        
        i -= 1
    
    # Convert list to string and return
    return ''.join(result).rstrip('\x00')  # Remove any null characters

def main():
    snum = change(700000000)
    print(snum)

if __name__ == "__main__":
    main()