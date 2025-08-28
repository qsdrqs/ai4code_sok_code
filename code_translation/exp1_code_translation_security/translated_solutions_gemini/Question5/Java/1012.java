import java.text.NumberFormat;
import java.util.Locale;

/**
 * This class provides functionality to translate the given C code to Java.
 * The original C code's goal was to format an integer with comma separators.
 */
public class NumberFormatter {

    /**
     * A manual implementation to represent an integer as a string with comma separators.
     * This mimics the *intent* of the original C code's logic.
     *
     * @param num The integer to format.
     * @return A string representation of the number with commas (e.g., "1,234,567").
     */
    public static String stringRepresentationManual(int num) {
        // In Java, we can easily convert a number to a string.
        String numStr = Integer.toString(num);
        int len = numStr.length();

        // If the number has 3 or fewer digits, no commas are needed.
        if (len <= 3) {
            return numStr;
        }

        // StringBuilder is efficient for building strings in a loop.
        StringBuilder result = new StringBuilder();

        // We iterate backwards through the string, inserting a comma every 3 characters.
        int counter = 0;
        for (int i = len - 1; i >= 0; i--) {
            // Insert the character from the original string at the beginning of our new string.
            result.insert(0, numStr.charAt(i));
            counter++;
            // If we have placed 3 digits and are not at the very beginning of the number,
            // insert a comma.
            if (counter % 3 == 0 && i > 0) {
                result.insert(0, ',');
            }
        }

        return result.toString();
    }

    /**
     * The modern, idiomatic Java way to format a number with commas.
     * This is the recommended approach.
     *
     * @param num The integer to format.
     * @return A string representation of the number with commas.
     */
    public static String stringRepresentationIdiomatic(int num) {
        // Java's built-in NumberFormat class is designed for this.
        // It is also locale-aware (e.g., some countries use '.' as a separator).
        return NumberFormat.getInstance(Locale.US).format(num);
        
        // An even shorter alternative using String.format:
        // return String.format("%,d", num);
    }


    /**
     * The main method serves as the entry point for the program.
     * It demonstrates how to use the formatting functions.
     * This replaces the non-functional main from the C code.
     *
     * @param args Command-line arguments (not used in this example).
     */
    public static void main(String[] args) {
        int number1 = 1234567;
        int number2 = 98765;
        int number3 = 100000000;
        int number4 = 500;

        System.out.println("--- Manual Implementation (like the C code's intent) ---");
        System.out.printf("Original: %d -> Formatted: %s\n", number1, stringRepresentationManual(number1));
        System.out.printf("Original: %d -> Formatted: %s\n", number2, stringRepresentationManual(number2));
        System.out.printf("Original: %d -> Formatted: %s\n", number3, stringRepresentationManual(number3));
        System.out.printf("Original: %d -> Formatted: %s\n", number4, stringRepresentationManual(number4));

        System.out.println("\n--- Idiomatic Java Implementation (Recommended) ---");
        System.out.printf("Original: %d -> Formatted: %s\n", number1, stringRepresentationIdiomatic(number1));
        System.out.printf("Original: %d -> Formatted: %s\n", number2, stringRepresentationIdiomatic(number2));
        System.out.printf("Original: %d -> Formatted: %s\n", number3, stringRepresentationIdiomatic(number3));
        System.out.printf("Original: %d -> Formatted: %s\n", number4, stringRepresentationIdiomatic(number4));
    }
}