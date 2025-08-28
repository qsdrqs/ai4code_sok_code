import java.text.NumberFormat;
import java.util.Locale;

public class Main {

    public static void printNumber(int num) {
        // Set the locale to the default locale
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
        // Format the number with grouping separators
        String formattedNumber = numberFormat.format(num);
        System.out.println(formattedNumber);
    }

    public static void main(String[] args) {
        printNumber(7000000);
    }
}