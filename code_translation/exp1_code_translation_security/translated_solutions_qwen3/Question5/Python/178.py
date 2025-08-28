import math

def main():
    def convert_to_string(num):
        # Step 1: Convert the number to a string
        s = str(num)
        space = len(s)

        # Step 2: Calculate number of commas (rounding logic as in C)
        num_of_commas = round(space / 3)

        # Step 3: Simulate the C-style character array with null terminator
        s_with_null = list(s) + ['\x00']

        # Step 4: Allocate new string buffer with space for commas and null
        new_size = space + 1 + num_of_commas
        new_string = ['\x00'] * new_size  # Initialize with null characters

        # Step 5: Copy original string into new buffer
        for i in range(new_size):
            if i < len(s_with_null):
                new_string[i] = s_with_null[i]

        # Step 6: Insert commas at every 3rd character (flawed logic from C)
        for i in range(new_size):
            if i % 3 == 0 and i != new_size - 1:
                if i + 1 < new_size:
                    new_string[i + 1] = ','

        # Step 7: Convert to Python string and truncate at first null
        result = ''.join(new_string).split('\x00', 1)[0]
        return result

    # Call the function and print the result
    print(convert_to_string(2000))

if __name__ == '__main__':
    main()