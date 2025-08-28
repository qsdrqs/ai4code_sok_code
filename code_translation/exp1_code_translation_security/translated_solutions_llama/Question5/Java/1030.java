import java.text.NumberFormat;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        int num = 7000000; // -703840;
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
        nf.setGroupingUsed(true);

        String snum = nf.format(num);
        System.out.println(snum);
    }
}