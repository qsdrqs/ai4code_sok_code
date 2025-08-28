import java.util.Random;

public class Main {

    public static void main(String[] args) {
        // Generate a random long value
        long value = new Random().nextLong();

        // Convert the long value to a string
        String vstring = Long.toString(value);

        System.out.println("Before: \t" + value);

        // Insert commas as thousand separators
        String commas = insertCommas(vstring);

        System.out.println("With commas: \t" + commas);
    }

    /**
     * Inserts commas as thousand separators into a given string representation of a number.
     *
     * @param numberStr The string representation of a number.
     * @return The string with commas inserted as thousand separators.
     */
    private static String insertCommas(String numberStr) {
        StringBuilder sb = new StringBuilder();
        int len = numberStr.length();
        int count = 0;

        for (int i = len - 1; i >= 0; i--) {
            if (count == 3) {
                sb.insert(0, ',');
                count = 0;
            }
            sb.insert(0, numberStr.charAt(i));
            count++;
        }

        return sb.toString();
    }
}