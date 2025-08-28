import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatter {

    public static String sigNumToStr(int num) {
        // Create a NumberFormat instance for the default locale
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
        
        // Format the number with grouping
        return numberFormat.format(num);
    }

    public static void main(String[] args) {
        int number = 1234567;
        String formattedNumber = sigNumToStr(number);
        System.out.println(formattedNumber);
    }
}