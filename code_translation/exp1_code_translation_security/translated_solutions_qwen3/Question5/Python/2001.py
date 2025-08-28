def int_to_string(a):
    """
    Converts an integer to a string with commas as thousand separators.
    
    Args:
        a (int): The integer to convert.
        
    Returns:
        str: The formatted string with commas.
    """
    s = str(a)
    neg = s.startswith('-')
    
    # Extract the digits, ignoring the negative sign if present
    if neg:
        digits = s[1:]
    else:
        digits = s

    # Reverse the digits to group from the right
    rev_digits = digits[::-1]
    
    # Split reversed digits into groups of 3
    groups = []
    for i in range(0, len(rev_digits), 3):
        group = rev_digits[i:i+3]
        groups.append(group)
    
    # Reverse each group to restore original digit order, then reverse the list of groups
    # to ensure correct grouping from the left
    groups = [group[::-1] for group in groups][::-1]
    
    # Join the groups with commas
    formatted = ','.join(groups)
    
    # Add the negative sign back if needed
    if neg:
        formatted = '-' + formatted
    
    return formatted


def main():
    """
    Main function to test the int_to_string function with various inputs.
    """
    tests = [
        7000000, 700000, 70000, 7000, 700, 70, 7, 0,
        -7000000, -700000, -70000, -7000, -700, -70, -7,
        (2**31 - 1), -2**31
    ]
    
    for n in tests:
        print(int_to_string(n))


if __name__ == "__main__":
    main()