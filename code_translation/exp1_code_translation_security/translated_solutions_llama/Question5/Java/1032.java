public class Main {

    /**
     * Converts an integer into a string.
     * 
     * @param num The integer to be converted.
     * @return A string representation of the integer.
     */
    public static String intToString(int num) {
        if (num == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        boolean isNegative = false;

        if (num < 0) {
            num = -num;
            isNegative = true;
        }

        while (num > 0) {
            sb.insert(0, num % 10);
            num /= 10;
        }

        if (isNegative) {
            sb.insert(0, '-');
        }

        return sb.toString();
    }

    /**
     * Converts an integer into a string with commas as thousand separators.
     * 
     * @param num The integer to be converted.
     * @return A string representation of the integer with commas.
     */
    public static String intToStringWithCommas(int num) {
        return String.format("%,d", num);
    }

    public static void main(String[] args) {
        int num = 7654321;
        String str = intToStringWithCommas(num);
        System.out.println(str);
    }
}