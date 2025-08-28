import java.text.NumberFormat;

public class Main {
    public static void main(String[] args) {
        int num = 7000000;

        // Format the number using the system's default locale
        String snum = NumberFormat.getInstance().format(num);

        // Print the formatted number without a newline
        System.out.print(snum);
    }
}