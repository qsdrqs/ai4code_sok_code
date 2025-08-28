import java.text.NumberFormat;
import java.util.Locale;

public class Main {

    // Function to convert an integer to a formatted string with commas
    public static String intToString(int x) {
        // Use Java's built-in NumberFormat to format the number with commas
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        return numberFormat.format(x);
    }

    public static void main(String[] args) {
        // Test the intToString function
        System.out.println(intToString(100000)); // Output: 100,000
        System.out.println("test");
    }
}