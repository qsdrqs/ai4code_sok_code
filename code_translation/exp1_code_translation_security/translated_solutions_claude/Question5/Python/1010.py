def string_it(value):
    """Return a string containing the expansion of the signed int with comma separators"""
    
    # Handle negative numbers
    if value < 0:
        sign = '-'
        value = -value
    else:
        sign = ''
    
    # Convert to string
    str_num = str(value)
    print(str_num)
    
    length = len(str_num)
    comma_count = (length - 1) // 3
    # print(comma_count)
    
    # Work from right to left, inserting commas
    while comma_count > 0:
        # Find position to insert comma (3 digits from the right end)
        insert_pos = length - (comma_count * 3)
        
        # Get the part after the insertion point
        start_part = str_num[:insert_pos]
        end_part = str_num[insert_pos:]
        
        print(end_part)
        
        # Insert comma
        str_num = start_part + ',' + end_part
        comma_count -= 1
    
    return sign + str_num

def main():
    print("   {}".format(string_it(-123457)))

if __name__ == "__main__":
    main()