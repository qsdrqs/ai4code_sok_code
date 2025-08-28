public class Main {

    private static final int MAX_STR_LEN = 128;

    /**
     * Reverses the input string.
     * 
     * @param str The input string to be reversed.
     * @return The reversed string.
     */
    public static String reverseStr(String str) {
        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString();
    }

    /**
     * Returns a string representation of a signed integer.
     * 
     * @param num The input number as a signed integer.
     * @return The string representation of the signed integer.
     */
    public static String strOfInt(int num) {
        StringBuilder sb = new StringBuilder();
        boolean isNeg = false;

        if (num < 0) {
            isNeg = true;
            num = -num;
        }

        do {
            sb.append(num % 10);
            num /= 10;
        } while (num != 0);

        if (isNeg) {
            sb.append('-');
        }

        String str = sb.toString();
        return reverseStr(str);
    }

    public static void main(String[] args) {
        System.out.println(strOfInt(7000000));
    }
}