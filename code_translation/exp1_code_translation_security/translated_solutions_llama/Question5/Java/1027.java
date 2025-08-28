import java.text.NumberFormat;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        int a = 400;
        convert(a);
    }

    public static void convert(int i) {
        // Set locale to default
        Locale locale = Locale.getDefault();
        // Use NumberFormat to format the number
        NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
        numberFormat.setGroupingUsed(true);
        System.out.println(numberFormat.format(i));
    }
}