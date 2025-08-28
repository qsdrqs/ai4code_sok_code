def int_to_string(num):
    # Create an empty list to store characters (similar to a char array in C)
    str_list = []
    count = 0

    # Handle the case where num is 0
    if num == 0:
        return "0"

    # Process the number and build the string in reverse order
    while num != 0:
        str_list.append(chr(num % 10 + ord('0')))  # Convert digit to character
        num = num // 10
        count += 1
        if count == 3 and num != 0:
            str_list.append(',')  # Add a comma after every 3 digits
            count = 0

    # Reverse the list to get the correct order
    str_list.reverse()

    # Join the list into a string and return
    return ''.join(str_list)


# Main function equivalent
if __name__ == "__main__":
    print(int_to_string(7000000))