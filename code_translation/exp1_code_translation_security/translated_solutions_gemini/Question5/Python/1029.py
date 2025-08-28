# All necessary functionality is built-in; no imports are needed.

def convert_int(input_num: int) -> str:
    """
    A manual, but corrected, translation of the logic in the C `convertInt` function.
    The original C function was severely flawed. This version correctly implements
    the intended logic of converting an integer to a string digit by digit.
    """
    if input_num == 0:
        return "0"
    
    if input_num < 0:
        # Handle negative numbers, which the original C code did not.
        return "-" + convert_int(-input_num)

    reversed_chars = []
    # Loop until the number is 0
    while input_num > 0:
        # Get the last digit
        last_digit = input_num % 10
        # Convert digit (0-9) to its character representation ('0'-'9')
        # ord('0') is the ASCII/Unicode value for the character '0'
        reversed_chars.append(chr(last_digit + ord('0')))
        # Remove the last digit from the number
        input_num //= 10
    
    # The characters are in reverse order, so we join them backwards
    return "".join(reversed(reversed_chars))

def convert_int_ai(input_num: int) -> str:
    """
    This is the direct and idiomatic Python equivalent of the C `convertIntAI` function,
    which used the standard library function `sprintf`. In Python, we simply use the
    built-in str() constructor.
    """
    return str(input_num)

# This block is the Python equivalent of the C `main` function.
# It runs only when the script is executed directly.
if __name__ == "__main__":
    # We use the simple, idiomatic function, just as the C code did.
    output = convert_int_ai(7000000)
    print(output)

    # Example using the manual function for comparison
    # output_manual = convert_int(12345)
    # print(output_manual)