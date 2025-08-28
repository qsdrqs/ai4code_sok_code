import java.util.Formatter;

public class Main {

    /**
     * Converts an integer into a string with commas as thousand separators.
     *
     * @param a the integer to be converted
     * @return a string representation of the integer with commas
     */
    public static String intToString(int a) {
        return String.format("%,d", a);
    }

    public static void main(String[] args) {
        System.out.println(intToString(7000000));
        System.out.println(intToString(700000));
        System.out.println(intToString(70000));
        System.out.println(intToString(7000));
        System.out.println(intToString(700));
        System.out.println(intToString(70));
        System.out.println(intToString(7));
        System.out.println(intToString(0));
        System.out.println(intToString(-7000000));
        System.out.println(intToString(-700000));
        System.out.println(intToString(-70000));
        System.out.println(intToString(-7000));
        System.out.println(intToString(-700));
        System.out.println(intToString(-70));
        System.out.println(intToString(-7));
        System.out.println(intToString(Integer.MAX_VALUE));
        System.out.println(intToString(Integer.MIN_VALUE));
    }
}