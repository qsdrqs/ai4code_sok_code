def reverse(s):
    """Reverses the input string."""
    return s[::-1]

def itoa(n):
    """Converts an integer to a string."""
    if n < 0:
        sign = "-"
        n = -n
    else:
        sign = ""
    return sign + str(n)

def insert_comma(s, pos):
    """Inserts a comma at the specified position in the string."""
    return s[:pos] + "," + s[pos:]

def int_to_string(num):
    """Converts an integer to a string with commas."""
    s = itoa(num)
    if s[0] == "-":
        s = s[1:]
        sign = "-"
    else:
        sign = ""
    
    # Add commas
    s = s[::-1]  # Reverse the string
    s = ",".join([s[i:i+3] for i in range(0, len(s), 3)])
    s = s[::-1]  # Reverse the string back
    
    return sign + s

def main():
    num = 7000000
    print(int_to_string(num))

if __name__ == "__main__":
    main()