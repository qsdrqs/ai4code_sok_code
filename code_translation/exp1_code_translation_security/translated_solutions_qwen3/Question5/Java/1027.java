import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        int a = 400;
        convert(a);
    }

    public static int convert(int i) {
        // Equivalent to setlocale(LC_NUMERIC, "") in C
        // Java uses the system's default locale by default
        System.out.printf("%,d%n", i);
        return 0;
    }
}