import java.text.NumberFormat;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        int num = 7000000; //-703840;
        
        // Set the locale to the default locale
        Locale locale = Locale.getDefault();
        
        // Create a NumberFormat instance for the default locale
        NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
        
        // Format the number with the locale-specific grouping
        String formattedNumber = numberFormat.format(num);
        
        // Print the formatted number
        System.out.println(formattedNumber);
    }
}