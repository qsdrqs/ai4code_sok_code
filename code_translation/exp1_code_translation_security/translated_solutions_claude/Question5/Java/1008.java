import java.text.NumberFormat;
import java.util.Locale;

public class Main {
    
    public static void printNumber(int num) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        System.out.println(numberFormat.format(num));
    }
    
    public static void main(String[] args) {
        printNumber(7000000);
    }
}