/**
 * This class provides functionality to format an integer with commas as thousands separators.
 * It is a Java translation of a C implementation with the same logic.
 *
 * The core logic involves:
 * 1. Converting an integer to a string representation in reverse order.
 * 2. Inserting commas every three digits during this process.
 * 3. Reversing the resulting string to get the final, correctly formatted number string.
 */
public class NumberFormatter {

    /**
     * The main entry point for the program.
     * Demonstrates the usage of the addCommas method.
     */
    public static void main(String[] args) {
        int num = 1000000;
        String str = addCommas(num);
        System.out.println(str); // Expected output: 1,000,000
    }

    /**
     * Reverses a given string. This is a helper method for addCommas.
     * In Java, strings are immutable, so this method returns a new, reversed string.
     *
     * @param str The string to be reversed.
     * @return The reversed string.
     */
    public static String reverseStr(String str) {
        // Use StringBuilder for efficient string manipulation and reversal.
        return new StringBuilder(str).reverse().toString();
    }

    /**
     * Converts a non-negative integer into a string, adding commas as thousands separators.
     * It follows the C code's logic of building the string in reverse and then flipping it.
     *
     * @param num The non-negative integer to format.
     * @return A string representation of the number with commas.
     */
    public static String addCommas(int num) {
        // Handle the edge case of 0, which the original C loop does not.
        if (num == 0) {
            return "0";
        }

        // StringBuilder is the Java equivalent of a mutable character array.
        StringBuilder reversedStringWithCommas = new StringBuilder();
        int digitCount = 0;
        int tempNum = num;

        // Build the string in reverse order while processing the number's digits.
        while (tempNum > 0) {
            // Get the last digit and append it to our builder.
            reversedStringWithCommas.append(tempNum % 10);
            tempNum /= 10; // Remove the last digit.
            digitCount++;

            // If we've added 3 digits and there are more digits to come, add a comma.
            if (digitCount == 3 && tempNum > 0) {
                reversedStringWithCommas.append(',');
                digitCount = 0; // Reset the digit counter.
            }
        }

        // The string is currently reversed (e.g., for 1,000,000, it's "000,000,1").
        // Call reverseStr to get the final, correct string.
        return reverseStr(reversedStringWithCommas.toString());
    }
}