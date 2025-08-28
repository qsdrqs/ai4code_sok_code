import java.text.NumberFormat;
import java.util.Locale;

/**
 * This class demonstrates how to format a number according to the default system locale in Java.
 */
public class Main {

    /**
     * The main entry point of the program, equivalent to C's main() function.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        int a = 400;
        convert(a);
    }

    /**
     * Converts and prints an integer with locale-specific formatting.
     * This is the Java equivalent of the C 'convert' function.
     *
     * @param i The integer to format and print.
     * @return An integer status code (0 for success).
     */
    public static int convert(int i) {
        // In C, `setlocale(LC_NUMERIC, "")` sets the numeric formatting rules
        // to the user's default environment locale.
        // The Java equivalent is to get an instance of NumberFormat for the default locale.
        NumberFormat numberFormat = NumberFormat.getInstance();

        // In C, `printf("%'d\n", i)` prints the integer using the locale's
        // grouping separators (like a comma for thousands).
        // In Java, we use the format() method of our NumberFormat instance
        // and then print the resulting string to the console.
        System.out.println(numberFormat.format(i));

        // Return 0 as in the original C function.
        return 0;
    }
}