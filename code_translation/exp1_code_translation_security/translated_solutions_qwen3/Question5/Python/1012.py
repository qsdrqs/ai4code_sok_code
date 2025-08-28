def insert_str(s, index):
    """Inserts a comma at the specified index in the string."""
    return s[:index] + ',' + s[index:]

def stringRepre(num):
    """Converts a number to a string and attempts to insert commas."""
    strnum = str(num)
    l = len(strnum)
    conum = l // 3  # Number of commas to insert
    buff = None

    for i in range(1, conum + 1):
        index = l - 3 + (i - 1)
        buff = insert_str(strnum, index)

    return buff

def main(argc):
    """Simulates the C main function by returning the formatted string."""
    return stringRepre(argc)

if __name__ == "__main__":
    import sys
    # Simulate C's argc as the number of command-line arguments
    result = main(len(sys.argv))
    print(result)