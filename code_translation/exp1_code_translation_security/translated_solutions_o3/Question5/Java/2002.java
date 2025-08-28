import java.text.NumberFormat;
import java.util.Locale;

public class NumUtil {

    /**
     * Converts an integer to a locale-aware, “grouped” string
     * (i.e., inserts the thousands separator defined for the
     * current default locale).
     *
     * @param num the number to convert
     * @return the formatted string
     */
    public static String sigNumToStr(int num) {
        // Equivalent to: setlocale(LC_NUMERIC, "");
        Locale locale = Locale.getDefault();   

        // Produce an integer formatter for that locale
        NumberFormat fmt = NumberFormat.getIntegerInstance(locale);
        fmt.setGroupingUsed(true);             // make sure grouping is on

        // Return the formatted text
        return fmt.format(num);
    }

    // Simple test harness
    public static void main(String[] args) {
        int n = 1234567;
        System.out.println(sigNumToStr(n)); // e.g. "1,234,567" in en_US locale
    }
}