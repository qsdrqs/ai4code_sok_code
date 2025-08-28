import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * This class contains the Java translation of the C code.
 * All dependencies are included within this single file.
 */
public class NumberFormatter {

    public static void main(String[] args) {
        // The C code's printf adds leading spaces, which we replicate here.
        System.out.println("   " + stringIt(-123457));
    }

    /**
     * Returns a String containing the expansion of the signed int with comma separators.
     * This is the idiomatic Java way to achieve the C code's goal.
     *
     * @param value The integer to format.
     * @return A comma-separated string representation of the integer.
     */
    public static String stringIt(int value) {
        // Java has built-in formatters that are safe, efficient, and handle
        // all cases (including negative numbers and edge cases) correctly.
        // This one line replaces the entire complex and buggy C implementation.
        return String.format("%,d", value);
    }

    /**
     * An alternative implementation that more closely follows the manual logic
     * of the C code, but implemented safely using Java's StringBuilder.
     * This is still much safer and clearer than the original C.
     *
     * @param value The integer to format.
     * @return A comma-separated string representation of the integer.
     */
    public static String stringItManual(int value) {
        if (value == 0) {
            return "0";
        }

        // StringBuilder is Java's equivalent of a mutable string, perfect for this task.
        StringBuilder sb = new StringBuilder();
        boolean isNegative = value < 0;
        
        // Work with the absolute value, just like the C code.
        // Using long to safely handle Integer.MIN_VALUE
        long longValue = Math.abs((long)value); 

        sb.append(longValue);
        sb.reverse(); // Reverse to make inserting commas easier

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < sb.length(); i++) {
            if (i > 0 && i % 3 == 0) {
                result.append(',');
            }
            result.append(sb.charAt(i));
        }

        result.reverse(); // Reverse back to the correct order

        if (isNegative) {
            result.insert(0, '-');
        }

        return result.toString();
    }
}