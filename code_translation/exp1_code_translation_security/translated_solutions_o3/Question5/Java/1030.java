import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        int num = 7_000_000;      // -703_840 would work as well

        /*  The C code does:
               setlocale(LC_NUMERIC, "");
               sprintf(snum, "%'d", num);
           
            In Java we can achieve the same thing with
            String.format(Locale.getDefault(), "%,d", num);
            The comma flag (,) inserts the locale-specific grouping
            separator, just like the apostrophe (') flag in glibc's printf.
         */
        String snum = String.format(Locale.getDefault(), "%,d", num);

        System.out.print(snum);
    }
}