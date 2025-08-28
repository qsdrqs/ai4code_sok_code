import java.util.*;

public class Main {

    public static String convert(int num) {
        // Convert the integer to a string
        String str = Integer.toString(num);
        return str;
    }

    public static void main(String[] args) {
        String ans = convert(700000);
        System.out.println(ans);
    }
}