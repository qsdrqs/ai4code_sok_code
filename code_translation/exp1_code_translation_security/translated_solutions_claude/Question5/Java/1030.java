import java.text.NumberFormat;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        int num = 7000000; //-703840;
        String snum;
        
        // Get the default locale's number format (equivalent to setlocale(LC_NUMERIC, ""))
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        
        // Format the number with locale-specific formatting (equivalent to sprintf with %'d)
        snum = numberFormat.format(num);
        
        // Print the formatted string
        System.out.print(snum);
    }
}