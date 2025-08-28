def num_to_str(num):
    # Convert the number to a string
    str_num = str(num)
    length = len(str_num)
    
    if num == 0:
        return str_num  # If the number is 0, return "0" as is
    
    # Determine the number of commas needed
    if num > 0:
        num_commas = (length - 1) // 3
    else:
        num_commas = (length - 2) // 3  # Account for the negative sign
    
    # Create a new string with commas
    result = []
    digits_moved = 0
    if num > 0:
        for i in range(length - 1, -1, -1):
            result.append(str_num[i])
            digits_moved += 1
            if digits_moved == 3 and i != 0:
                result.append(',')
                digits_moved = 0
    else:
        # Handle negative numbers
        result.append('-')  # Add the negative sign first
        for i in range(length - 1, 0, -1):
            result.append(str_num[i])
            digits_moved += 1
            if digits_moved == 3 and i != 1:
                result.append(',')
                digits_moved = 0
    
    # Reverse the result list and join it into a string
    return ''.join(result[::-1])


def main():
    num = 7042
    formatted_str = num_to_str(num)
    print(f"Output: {formatted_str}")


if __name__ == "__main__":
    main()