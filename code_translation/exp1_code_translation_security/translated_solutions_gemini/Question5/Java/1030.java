import java.text.NumberFormat;
import java.util.Locale;

/**
 * This class demonstrates locale-specific number formatting in Java,
 * equivalent to the C code using setlocale() and sprintf with "%'d".
 */
public class NumericFormatExample {

    public static void main(String[] args) {
        int num = 7000000;

        // In C, setlocale(LC_NUMERIC, "") sets the numeric formatting to the user's
        // default locale. The Java equivalent is to get a NumberFormat instance
        // for the default locale. getIntegerInstance() is suitable for formatting integers
        // and will automatically include grouping separators (like thousands separators).
        NumberFormat numberFormat = NumberFormat.getIntegerInstance(Locale.getDefault());

        // In C, sprintf(snum, "%'d", num) formats the integer into a string
        // using the locale's thousands separator. The format() method of
        // NumberFormat does the same thing in Java.
        String snum = numberFormat.format(num);

        // Print the resulting formatted string to the console.
        // For a US locale, this will print "7,000,000".
        // For a German locale, this will print "7.000.000".
        System.out.println(snum);
    }
}