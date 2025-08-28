/*
 *  Translation of the given C program to Java.
 *  The logic is kept as similar as possible to the original:
 *    – reverseStr   : reverses a String
 *    – strOfInt     : builds a decimal representation of a signed int
 */

public class Main {

    private static final int MAX_STR_LEN = 128;   // kept only for parity with C code

    /**
     * Reverse the characters of a string.
     *
     * @param str the input string
     * @return a new string whose characters are the reverse of {@code str}
     */
    public static String reverseStr(String str) {
        // StringBuilder has a built-in reverse() that returns the same buffer,
        // then toString() produces the final String.
        return new StringBuilder(str).reverse().toString();
    }

    /**
     * Return a string representation of a signed integer (without
     * using the standard library's {@code Integer.toString} on purpose,
     * to mirror the hand-written C routine).
     *
     * @param num the integer to convert
     * @return the decimal text form of {@code num}
     */
    public static String strOfInt(int num) {
        // Handle the zero case early (the loop below would skip it).
        if (num == 0) {
            return "0";
        }

        boolean isNeg = num < 0;

        /*  Use a long so we can safely negate MIN_VALUE
         *  (-2^31 cannot be held as +2^31 in an int).
         */
        long n = isNeg ? -(long) num : num;

        // Build the decimal digits in reverse order.
        StringBuilder tmp = new StringBuilder(MAX_STR_LEN);
        while (n != 0) {
            int digit = (int) (n % 10);
            tmp.append((char) ('0' + digit));
            n /= 10;
        }

        if (isNeg) {
            tmp.append('-');
        }

        // tmp currently holds the number backwards, so reverse it.
        return reverseStr(tmp.toString());
    }

    public static void main(String[] args) {
        System.out.println(strOfInt(7000000));      // should print 7000000
        // Feel free to test further:
        // System.out.println(strOfInt(-42));
        // System.out.println(strOfInt(0));
        // System.out.println(strOfInt(Integer.MIN_VALUE));
    }
}