import java.text.NumberFormat;
import java.util.Locale;

public class Main {

    /**
     * Converts a signed integer to a string with thousand separators.
     * 
     * @param num The number to be converted.
     * @return A string representation of the number with thousand separators.
     */
    public static String sigNumToStr(int num) {
        // Set the locale to the default locale
        Locale locale = Locale.getDefault();
        // Use NumberFormat to format the number with thousand separators
        NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
        return numberFormat.format(num);
    }

    public static void main(String[] args) {
        int num = 1234567;
        System.out.println(sigNumToStr(num));
    }
}