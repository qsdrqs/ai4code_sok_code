import java.util.Locale;

public class Main {
    public static String sigNumToStr(int num) {
        return String.format(Locale.getDefault(), "%,d", num);
    }
}