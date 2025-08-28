import java.text.NumberFormat;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        int a = 400;
        convert(a);
    }

    public static int convert(int i) {
        // Set the locale to the default locale (or you can specify a specific locale)
        Locale locale = Locale.getDefault();
        NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);

        // Format the number with grouping separators
        String formattedNumber = numberFormat.format(i);
        System.out.println(formattedNumber);

        return 0;
    }
}