def insert(string, index):
    """
    A helper function to insert a comma into the string at the specified index.
    """
    # Insert a comma at the specified index
    return string[:index] + ',' + string[index:]


def string_repre(num):
    """
    Function to convert a number into a string representation with commas.
    """
    # Convert the number to a string
    strnum = str(num)
    
    # Calculate the length of the string
    length = len(strnum)
    
    # Calculate the number of commas to be inserted
    num_commas = (length - 1) // 3  # Number of commas needed
    
    # Insert commas into the string
    for i in range(1, num_commas + 1):
        # Calculate the index where the comma should be inserted
        index = length - (3 * i)
        strnum = insert(strnum, index)
    
    return strnum


def main(argc):
    """
    Main function to demonstrate the string representation with commas.
    """
    # Get the string representation with commas
    result = string_repre(argc)
    return result


# Example usage
if __name__ == "__main__":
    # Replace `argc` with any integer value to test
    argc = 123456789
    print(main(argc))