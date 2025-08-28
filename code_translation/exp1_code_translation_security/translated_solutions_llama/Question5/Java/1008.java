import java.text.NumberFormat;
import java.util.Locale;

public class Main {

    /**
     * Prints a number with thousand separators based on the default locale.
     * 
     * @param num The number to be printed.
     */
    public static void printNumber(int num) {
        // Set the locale for number formatting
        Locale locale = Locale.getDefault();
        
        // Create a NumberFormat instance for the locale
        NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
        
        // Format the number with thousand separators
        String formattedNumber = numberFormat.format(num);
        
        // Print the formatted number
        System.out.println(formattedNumber);
    }

    public static void main(String[] args) {
        // Test the printNumber method
        printNumber(7000000);
    }
}