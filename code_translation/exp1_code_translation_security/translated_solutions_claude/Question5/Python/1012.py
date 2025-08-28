def insert(string, index):
    """A helper function to insert comma to the string"""
    if index < 0 or index > len(string):
        return string
    return string[:index] + ',' + string[index:]

def string_repre(num):
    """Convert number to string representation with comma separators"""
    str_num = str(num)
    
    length = len(str_num)  # before the insertion
    co_num = (length - 1) // 3  # number of commas needed
    
    if co_num == 0:
        return str_num
    
    buff = str_num
    
    # Insert commas from right to left
    for i in range(1, co_num + 1):
        # Insert comma at position: length - (3 * i) 
        insert_pos = length - (3 * i) + (i - 1)  # adjust for previously inserted commas
        buff = insert(buff, insert_pos)
    
    return buff

def main(argc, argv=None):
    """Main function"""
    if argv is None:
        argv = []
    
    result = string_repre(argc)
    return result

# Example usage:
if __name__ == "__main__":
    import sys
    argc = len(sys.argv)
    result = main(argc, sys.argv)
    print(result)