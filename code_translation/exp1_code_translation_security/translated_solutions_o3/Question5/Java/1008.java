import java.text.NumberFormat;
import java.util.Locale;

public class Main {

    /**
     * Prints the supplied integer using the thousands-separator that
     * belongs to the current default locale (equivalent to
     * `setlocale(LC_NUMERIC, "")` in the C version).
     */
    private static void printNumber(int num) {
        NumberFormat formatter = NumberFormat.getIntegerInstance(Locale.getDefault());
        System.out.println(formatter.format(num));
    }

    public static void main(String[] args) {
        printNumber(7_000_000);      // 7,000,000  (in an en_US locale)
    }
}