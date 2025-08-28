import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatter {
    
    public static String sigNumToStr(int num) {
        NumberFormat formatter = NumberFormat.getNumberInstance();
        return formatter.format(num);
    }
}