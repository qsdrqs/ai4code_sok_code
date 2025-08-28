import java.text.NumberFormat;
import java.util.Locale;

public class Main {
    
    public static int convert(int i) {
        // Get the default locale's number format (equivalent to setlocale(LC_NUMERIC, ""))
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        
        // Format and print the number with locale-specific formatting
        System.out.println(numberFormat.format(i));
        
        return 0;
    }
    
    public static void main(String[] args) {
        int a = 400;
        convert(a);
    }
}