import java.text.NumberFormat;
import java.util.Locale;

/**
 * This class demonstrates how to print a number formatted according to the
 * user's default locale, similar to the C code's behavior.
 */
public class NumberPrinter {

    /**
     * Prints an integer formatted with locale-specific thousands separators.
     *
     * @param num The integer to print.
     */
    public static void printNumber(int num) {
        // In Java, the printf method can use the comma (`,`) flag in its format
        // specifier to automatically include locale-specific grouping separators.
        // This is the direct equivalent of C's setlocale() + printf("%'d").
        // It uses the system's default locale automatically.
        System.out.printf("%,d\n", num);
    }

    /**
     * The main entry point for the program.
     */
    public static void main(String[] args) {
        printNumber(7000000);
    }
}