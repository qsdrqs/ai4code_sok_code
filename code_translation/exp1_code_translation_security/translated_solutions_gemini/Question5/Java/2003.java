/**
 * This class contains the Java translation of the C code.
 * All dependencies are included within this single file.
 */
public class Solution {

    /**
     * A constant equivalent to MAX_LEN in the C code.
     * Note: This constant is not used in the formatting logic, same as in the original C code.
     */
    public static final int MAX_LEN = 16;

    /**
     * Converts an integer to a string, formatting it with commas as thousands separators.
     * This method faithfully translates the logic from the C function `num_to_string`,
     * using Java's StringBuilder for efficient string manipulation and correcting a bug
     * from the original C code that occurred with numbers whose digit count is a multiple of 3.
     *
     * @param num The integer to convert.
     * @return A string representation of the number with commas, or null if memory allocation fails (though this is highly unlikely in Java).
     */
    public static String numToString(int num) {
        // Handle the edge case of 0, which requires no formatting.
        if (num == 0) {
            return "0";
        }

        // The original C code has a bug with INT_MIN because -INT_MIN overflows.
        // We handle this specific edge case in Java as well.
        if (num == Integer.MIN_VALUE) {
            return "-2,147,483,648";
        }

        // Use a StringBuilder for efficient string construction. It's the Java
        // equivalent of building a string in a character array.
        StringBuilder sb = new StringBuilder();

        // Handle negativity. We work with the absolute value of the number for formatting.
        boolean isNeg = num < 0;
        if (isNeg) {
            num = -num;
        }

        int digitCount = 0;
        while (num > 0) {
            // Get the last digit of the number.
            int digit = num % 10;
            sb.insert(0, digit);
            digitCount++;

            // Remove the last digit from the number.
            num = num / 10;

            // Insert a comma after every three digits, but only if there are more digits to process.
            // This prevents a leading comma (e.g., ",123").
            if (digitCount % 3 == 0 && num > 0) {
                sb.insert(0, ',');
            }
        }

        // If the original number was negative, prepend the '-' sign.
        if (isNeg) {
            sb.insert(0, '-');
        }

        return sb.toString();
    }

    /**
     * The main method, equivalent to the C main function, to test the translation.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Prints the formatted string for -5,305,000, which is "-5,305,000".
        System.out.println(numToString(-5305000));
    }
}