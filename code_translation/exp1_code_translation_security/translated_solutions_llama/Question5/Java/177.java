import java.util.Formatter;

public class Main {

    /**
     * Converts an integer into a string with commas as thousand separators.
     * 
     * @param num The integer to be converted.
     * @return A string representation of the integer with commas.
     */
    public static String int2str(int num) {
        // Use a Formatter to add commas as thousand separators
        Formatter formatter = new Formatter();
        String formattedNum = formatter.format("%,d", num).toString();
        formatter.close();
        return formattedNum;
    }

    public static void main(String[] args) {
        // Example usage:
        System.out.println(int2str(1234567));  // Output: 1,234,567
        System.out.println(int2str(1234));     // Output: 1,234
        System.out.println(int2str(123));      // Output: 123
    }
}