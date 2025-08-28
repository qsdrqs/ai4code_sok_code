public class Main {

    /**
     * Returns the number of digits in the given integer.
     * 
     * @param n The input integer.
     * @return The number of digits in the integer.
     */
    public static int numDigits(int n) {
        int digits = 0;
        while (n != 0) {
            n /= 10;
            digits++;
        }
        return digits;
    }

    /**
     * Converts an integer to a string with commas as thousand separators.
     * 
     * @param value The input integer.
     * @return A string representation of the integer with commas.
     */
    public static String intToStringWithCommas(int value) {
        boolean isNegative = value < 0;
        if (isNegative) {
            value *= -1;
        }
        int digits = numDigits(value);
        int numCommas = digits / 3;
        if (digits % 3 == 0) {
            numCommas -= 1;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(value));
        for (int i = sb.length() - 3; i > 0; i -= 3) {
            sb.insert(i, ',');
        }
        if (isNegative) {
            sb.insert(0, '-');
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    public static void main(String[] args) {
        intToStringWithCommas(7000000);
    }
}