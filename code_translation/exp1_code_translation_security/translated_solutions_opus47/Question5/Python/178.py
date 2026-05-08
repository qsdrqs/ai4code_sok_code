def convert_to_string(num):
    # Equivalent to snprintf: convert number to string
    string = str(num)
    space = len(string)
    num_of_commas = round(len(string) / 3)
    print(num_of_commas)
    # Allocate a char array (list in Python, since strings are immutable)
    new_string = [''] * (space + 1 + num_of_commas)
    for i in range(len(string) + 1 + num_of_commas):
        if i < len(string):
            new_string[i] = string[i]
            print(ord(string[i]), end='')  # %d on a char prints the ASCII value
        if i % 3 == 0 and i != len(string) + 1 + num_of_commas:
            print("s", end='')
            if i + 1 < len(new_string):
                new_string[i + 1] = ","
    return ''.join(new_string)


print(convert_to_string(2000), end='')