import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        int num = 7000000; // -703840;
        String snum;
        
        // In Java, %,d formats an integer with locale-specific grouping separators
        // (equivalent to %'d in C with setlocale(LC_NUMERIC, ""))
        snum = String.format(Locale.getDefault(), "%,d", num);
        
        System.out.print(snum);
    }
}