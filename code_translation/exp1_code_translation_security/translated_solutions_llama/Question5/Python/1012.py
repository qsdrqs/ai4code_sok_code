def insert_commas(num_str):
    """Insert commas into a string representation of a number."""
    # Convert the number to a string
    num_str = str(num_str)
    
    # Reverse the string
    reversed_str = num_str[::-1]
    
    # Insert commas every 3 characters
    formatted_str = ",".join([reversed_str[i:i+3] for i in range(0, len(reversed_str), 3)])
    
    # Reverse the string back
    formatted_str = formatted_str[::-1]
    
    return formatted_str

def main(argc, argv):
    """Main function."""
    if argc > 1:
        try:
            num = int(argv[1])
            return insert_commas(num)
        except ValueError:
            return "Invalid input"
    else:
        return "No input provided"

# Example usage
if __name__ == "__main__":
    import sys
    argc = len(sys.argv)
    argv = sys.argv
    print(main(argc, argv))