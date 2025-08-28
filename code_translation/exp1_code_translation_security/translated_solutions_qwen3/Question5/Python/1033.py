def num_to_str(num):
    """
    Converts an integer to a string with commas as thousand separators.
    Replicates the behavior of the C function `numToStr`.
    """

    def add_commas(s):
        """
        Helper function to insert commas into a string of digits.
        Works by reversing the string, grouping into chunks of 3,
        and then reversing back to get the final formatted string.
        """
        reversed_s = s[::-1]
        groups = []
        for i in range(0, len(reversed_s), 3):
            groups.append(reversed_s[i:i+3])
        joined = ','.join(groups)
        return joined[::-1]

    if num == 0:
        return '0'
    
    s = str(num)
    
    if num < 0:
        # For negative numbers, process the digits after the '-' sign
        return '-' + add_commas(s[1:])
    else:
        # For positive numbers, process the entire string
        return add_commas(s)

# Main function to test the conversion
if __name__ == "__main__":
    num = 7042
    result = num_to_str(num)
    print(f"Output: {result}")