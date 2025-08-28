public class IntegerToStringConverter {

    /**
     * Translates the C function int_to_string.
     *
     * This function converts a non-negative integer into a string representation,
     * inserting commas as thousands separators.
     *
     * @param num The non-negative integer to convert.
     * @return A string representation of the number with commas.
     */
    public static String intToString(int num) {
        // In C, a fixed-size buffer was allocated with malloc.
        // In Java, StringBuilder is a dynamic and safer way to build strings.
        StringBuilder sb = new StringBuilder();

        // The original C code had a bug where it returned an empty string for 0.
        // This is a more correct and robust implementation.
        if (num == 0) {
            return "0";
        }

        // The original C code does not handle negative numbers. This translation
        // maintains that behavior for a direct comparison. For a full implementation,
        // you would handle the sign separately.
        if (num < 0) {
            // Or throw an IllegalArgumentException, etc.
            return "Negative numbers not supported";
        }

        int temp = num;
        int count = 0;

        // This loop builds the string in reverse, just like the C version.
        // e.g., for 7000000, it builds "000,000,7"
        while (temp != 0) {
            // Get the last digit and append it.
            // The C code's `(temp % 10) + '0'` is implicitly handled by
            // StringBuilder when appending an int.
            sb.append(temp % 10);
            temp = temp / 10;
            count++;

            // If we have 3 digits and there are more digits to process, add a comma.
            if (count == 3 && temp != 0) {
                sb.append(',');
                count = 0; // Reset the counter
            }
        }

        // The C code manually reversed the string in a loop.
        // StringBuilder has a much more convenient built-in reverse() method.
        return sb.reverse().toString();
    }

    /**
     * The main method, equivalent to the C main function, to demonstrate the usage.
     */
    public static void main(String[] args) {
        // The C code `printf(*int_to_string(7000000))` is equivalent to
        // printing the string returned by the function.
        System.out.println(intToString(7000000));
        
        // Additional test cases
        System.out.println(intToString(123));
        System.out.println(intToString(12345));
        System.out.println(intToString(987654321));
        System.out.println(intToString(0));
    }
}