import logging

# Configure logging
logging.basicConfig(level=logging.INFO, format='%(message)s')

def int_to_string(input_value: int) -> str:
    """
    Converts an integer to a string and logs the conversion.

    Args:
        input_value (int): The integer to be converted.

    Returns:
        str: The string representation of the input integer.
    """
    # Convert integer to string
    string_buffer = str(input_value)
    
    # Log the conversion
    logging.info(f"You have entered: {string_buffer}")
    
    return string_buffer

def main():
    # Test the function
    int_to_string(7000000)

if __name__ == "__main__":
    main()