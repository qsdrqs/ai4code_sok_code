import java.text.NumberFormat;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        int a = 400;
        convert(a);
    }

    // Mirrors the C function “int convert(int i)”
    // You can keep the int return type (returning 0) or make it void;
    // here we stay faithful to the original signature.
    public static int convert(int i) {
        // Use the default locale (same idea as setlocale in C)
        NumberFormat formatter = NumberFormat.getIntegerInstance(Locale.getDefault());

        // Print the locale-aware, thousands-grouped number
        System.out.println(formatter.format(i));

        return 0;   // same as the C function
    }
}