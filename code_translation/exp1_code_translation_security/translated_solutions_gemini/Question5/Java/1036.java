import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatter {

    /**
     * Calculates the number of digits in an integer.
     * This is a corrected version of the C num_digits function.
     *
     * @param n The integer to check.
     * @return The number of digits.
     */
    private static int numDigits(int n) {
        // Fix for the bug in C: `if (n = 0)` was an assignment, not a comparison.
        // In Java, we use `==` for comparison.
        if (n == 0) {
            return 1;
        }

        int count = 0;
        // Use a temporary variable to avoid modifying the input parameter.
        // Also, handle negative numbers by taking the absolute value.
        long tempN = Math.abs((long) n); // Use long to safely handle Integer.MIN_VALUE

        while (tempN > 0) {
            tempN = tempN / 10;
            count++;
        }
        return count;
    }

    /**
     * Formats an integer with thousands separators (commas).
     * This function implements the INTENDED logic of the C `return_string` function,
     * fixing its numerous bugs.
     *
     * @param num The number to format.
     * @return A string representation of the number with commas.
     */
    public static String formatNumberWithCommas(int num) {
        // The C code's algorithm for adding commas was flawed.
        // A much safer and more efficient way is to use a StringBuilder.
        String numStr = Integer.toString(num);
        int len = numStr.length();

        if (len <= 3) {
            return numStr; // No commas needed
        }

        StringBuilder result = new StringBuilder();
        int firstGroupLength = len % 3;
        if (firstGroupLength == 0) {
            firstGroupLength = 3;
        }

        // Add the first group of digits (1, 2, or 3 digits)
        result.append(numStr.substring(0, firstGroupLength));

        // Add the remaining groups, each prefixed with a comma
        for (int i = firstGroupLength; i < len; i += 3) {
            result.append(',');
            result.append(numStr.substring(i, i + 3));
        }

        return result.toString();
    }

    /**
     * The idiomatic, or standard, way to format numbers in Java.
     * This is recommended for real-world applications.
     */
    public static String formatNumberIdiomatic(int num) {
        return NumberFormat.getNumberInstance(Locale.US).format(num);
        // Or a simpler way for default locale:
        // return String.format("%,d", num);
    }


    public static void main(String[] args) {
        int num = 7000000;

        System.out.println("Original C code's intended number: " + num);
        
        // Using our translated and corrected algorithm
        String formattedStr = formatNumberWithCommas(num);
        System.out.println("Formatted string (custom algorithm): " + formattedStr);

        // Using the standard Java way
        String formattedStrIdiomatic = formatNumberIdiomatic(num);
        System.out.println("Formatted string (idiomatic Java):  " + formattedStrIdiomatic);
    }
}