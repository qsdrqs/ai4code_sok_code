/*
 * A program that converts a signed 32-bit integer to its String
 * representation without using the standard Java conversion routines.
 */

public class IntegerToStringDemo {

    /*
     * Reverses a String (equivalent to the C version of strrev).
     */
    private static String strrev(String input) {
        char[] chars = input.toCharArray();
        int start = 0;
        int end   = chars.length - 1;

        while (end > start) {
            char temp   = chars[start];
            chars[start] = chars[end];
            chars[end]   = temp;
            start++;
            end--;
        }
        return new String(chars);
    }

    /*
     * Converts a signed int to a String.
     */
    public static String integerToString(int num) {
        char[] buffer = new char[100];       // same 100-byte “scratch”
        int    i      = 0;
        boolean isNegative = false;

        /*
         * Use a long to cope safely with Integer.MIN_VALUE (-2147483648),
         * whose absolute value does not fit into an int.
         */
        long n = num;
        if (n < 0) {
            isNegative = true;
            n = -n;
        }

        /* Extract digits, least significant first. */
        do {
            int digit   = (int)(n % 10);
            buffer[i++] = (char) ('0' + digit);
            n /= 10;
        } while (n != 0);

        if (isNegative) {
            buffer[i++] = '-';
        }

        /* Build a String from the filled part of the buffer … */
        String temp = new String(buffer, 0, i);
        /* … and reverse it to obtain the final answer. */
        return strrev(temp);
    }

    /*
     * Demo / test harness – mirrors the original C main().
     */
    public static void main(String[] args) {
        String num = integerToString(12345);
        System.out.println(num);

        num = integerToString(-12345);
        System.out.println(num);
    }
}