# All necessary functionalities are built into Python, so no imports are needed
# for this specific logic.

def strrev(input_str: str) -> str:
    """
    A function that reverses a string.
    This is the Python equivalent of the C strrev function.
    In Python, strings are immutable, so this function returns a new reversed string
    instead of modifying it in-place. The `[::-1]` slice is a common and
    efficient way to reverse a sequence.
    """
    return input_str[::-1]

def integer_to_string(num: int) -> str:
    """
    A function that takes a signed integer and returns its string representation,
    mimicking the logic of the C implementation.
    """
    # In C, a fixed-size buffer is allocated with malloc. In Python, we can
    # build a list of characters and join them, which is more flexible.
    result_chars = []
    is_negative = False

    # The C do-while loop runs at least once, correctly handling num = 0.
    # We handle this case explicitly in Python for clarity.
    if num == 0:
        return "0"

    if num < 0:
        is_negative = True
        num = -num  # Work with the absolute value

    # This loop builds the string in reverse, just like the C code.
    # e.g., 123 becomes ['3', '2', '1']
    while num > 0:
        digit = num % 10
        # In C: result[i++] = '0' + digit;
        # In Python, we convert the digit to a string and append it.
        result_chars.append(str(digit))
        # In C: num /= 10; (integer division)
        # In Python, we use // for integer division.
        num //= 10

    if is_negative:
        # In C: result[i++] = '-';
        result_chars.append('-')

    # In C, the string is null-terminated: result[i] = '\0';
    # This is not necessary in Python.

    # Join the characters to form the reversed string (e.g., "321-")
    reversed_result = "".join(result_chars)

    # Reverse the string to get the correct order (e.g., "-123")
    # This is equivalent to calling strrev(result) in the C code.
    return strrev(reversed_result)

# The main() function in C is equivalent to the following block in Python,
# which serves as the entry point for the script.
if __name__ == "__main__":
    # In Python, memory management is automatic (garbage collection).
    # There is no need for malloc() or free().
    num_str = integer_to_string(12345)
    print(num_str)

    num_str = integer_to_string(-12345)
    print(num_str)