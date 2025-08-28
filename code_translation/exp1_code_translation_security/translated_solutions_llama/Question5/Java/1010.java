import java.util.Formatter;

public class Main {

    public static void main(String[] args) {
        System.out.println(stringIt(-123457));
    }

    /**
     * Returns a string containing the expansion of the signed int with commas as thousand separators.
     *
     * @param value the integer to be converted
     * @return a string representation of the integer with commas
     */
    public static String stringIt(int value) {
        // Use Java's built-in formatting to add commas
        Formatter formatter = new Formatter();
        String formattedValue = formatter.format("%,d", value).toString();
        formatter.close();
        return formattedValue;
    }
}