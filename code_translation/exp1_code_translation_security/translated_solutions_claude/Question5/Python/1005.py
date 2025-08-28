MAX_LEN = 14

def stringify(i):
    tmp = ['0'] * 14  # equivalent to "00000000000000"
    j = i if i >= 0 else -1 * i
    k = MAX_LEN - 1
    c = 0
    
    while j > 0 and k >= 0:
        if c != 0 and c % 3 == 0:
            tmp[k] = ','
            k -= 1
            c = 0
        
        # Insert comma
        len_written = MAX_LEN - 1 - k
        # Convert digit to char
        digit = j % 10
        d_char = str(digit)  # Convert digit to string character
        # Add to string
        tmp[k] = d_char
        # Move to next digit
        j //= 10
        k -= 1
        c += 1
    
    if i < 0:
        tmp[k] = '-'
        k -= 1
    
    # Build the result string
    buf = []
    for n in range(k + 1, MAX_LEN):
        buf.append(tmp[n])
    
    return MAX_LEN - 1 - k, ''.join(buf)

def main():
    input_val = -2147483647
    length, buf = stringify(input_val)
    print(f"{length} :: {buf}")

if __name__ == "__main__":
    main()