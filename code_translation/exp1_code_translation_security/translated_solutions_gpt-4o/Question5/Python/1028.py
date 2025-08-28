def reverse(s):
    """Reverse a string."""
    return s[::-1]

def itoa(n):
    """Convert an integer to a string."""
    sign = ''
    if n < 0:
        sign = '-'
        n = -n
    s = ''
    while True:
        s += chr(n % 10 + ord('0'))  # Get the next digit
        n //= 10
        if n == 0:
            break
    if sign:
        s += sign
    return s

def insert_comma(s, pos):
    """Insert a comma at a specific position in a string."""
    return s[:pos] + ',' + s[pos:]

def int_to_string(num):
    """Convert an integer to a string with commas."""
    s = itoa(num)  # Convert the integer to a string
    s = reverse(s)  # Reverse the string
    length = len(s)
    for i in range(3, length, 4):  # Insert commas every 3 digits
        s = insert_comma(s, i)
    if s[-1] == ',':  # Remove trailing comma if it exists
        s = s[:-1]
    s = reverse(s)  # Reverse the string back to the correct order
    return s

# Main function
if __name__ == "__main__":
    num = 7000
    result = int_to_string(num)
    print(result)