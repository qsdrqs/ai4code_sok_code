public class Main {

    /**
     * Reverses the input string.
     * 
     * @param input The string to be reversed.
     * @return The reversed string.
     */
    public static String strrev(String input) {
        StringBuilder sb = new StringBuilder(input);
        return sb.reverse().toString();
    }

    /**
     * Converts a signed integer into its string representation.
     * 
     * @param num The integer to be converted.
     * @return The string representation of the integer.
     */
    public static String integerToString(int num) {
        StringBuilder sb = new StringBuilder();
        boolean isNegative = false;

        if (num < 0) {
            isNegative = true;
            num = -num;
        }

        do {
            int digit = num % 10;
            sb.append((char) ('0' + digit));
            num /= 10;
        } while (num != 0);

        if (isNegative) {
            sb.append('-');
        }

        return strrev(sb.toString());
    }

    public static void main(String[] args) {
        String num = integerToString(12345);
        System.out.println(num);

        num = integerToString(-12345);
        System.out.println(num);
    }
}