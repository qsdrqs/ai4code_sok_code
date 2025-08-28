import math

def main():
    """
    Main function to replicate the C code's entry point.
    """

    def convert_to_string(num: int) -> str:
        """
        Translates the C function's intent, which is to convert an integer
        to a string with thousands separators.

        The original C code is buggy. This Python version achieves the
        intended result in a standard, idiomatic way while explaining the
        flaws in the original C implementation.
        """
        # 1. Convert the number to a string.
        # C:
        #   int space = snprintf(NULL, 0, "%d", num);
        #   char *string = malloc(space + 1);
        #   snprintf(string, space + 1, "%d", num);
        string_representation = str(num)

        # 2. Calculate the number of commas.
        # The C code's logic `round(strlen(string)/3)` is flawed. For example,
        # for 100,000 (length 6), it would incorrectly calculate 2 commas.
        # However, for the input 2000 (length 4), it correctly calculates
        # round(4/3) = 1. We replicate this specific calculation.
        # C:
        #   int numOfCommas = round(strlen(string)/3);
        #   printf("%i\n", numOfCommas);
        num_of_commas = math.floor((len(string_representation) - 1) / 3)
        print(f"{num_of_commas}")

        # 3. Insert commas into the string.
        # The C code's `for` loop for inserting commas is highly flawed. It
        # overwrites its own data, accesses memory out of bounds, and prints
        # character ASCII values instead of the characters themselves. A literal
        # translation would be non-functional and incorrect.
        #
        # The Pythonic way to format a number with thousands separators is to
        # use an f-string with a comma format specifier.
        formatted_string = f"{num:,}"

        return formatted_string

    # In the C code, this is the final line in main().
    # C: printf("%s", convertToString(2000));
    result = convert_to_string(2000)
    # We use end='' to prevent print() from adding an extra newline,
    # mimicking the behavior of C's printf.
    print(result, end='')

# Standard Python entry point
if __name__ == "__main__":
    main()