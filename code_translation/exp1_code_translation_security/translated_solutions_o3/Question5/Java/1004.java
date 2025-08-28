/*
 * Function  repr
 *
 * Takes in a signed integer and returns the string representation of that
 * integer in exactly the same way the original C version does (including
 * thousand-group commas and the special-case for INT_MIN).
 */

public final class Main {

    private static final int MAX_DIGITS = 100;   // Same constant as in the C code

    public static String repr(int n) {
        /*  Special-case for the one value that overflows when negated  */
        if (n == Integer.MIN_VALUE) {
            return "-2,147,483,648";
        }

        /*  Zero is handled up-front  */
        if (n == 0) {
            return "0";
        }

        int sign = 1;
        if (n < 0) {          // remember the sign and make n positive
            sign = -1;
            n *= -1;
        }

        /*
         * We build the result backwards (least-significant digit first) just
         * like the C code, then reverse it at the end.
         */
        StringBuilder sb = new StringBuilder(MAX_DIGITS);
        int i = 0;            // corresponds to the “i” index in the C code

        while (n > 0) {
            sb.append((char) ('0' + (n % 10)));  // add next digit
            i++;

            /* Insert a comma after every third digit exactly as in the C code */
            if (((i + 1) % 4 == 0) && n > 10) {
                sb.append(',');
                i++;
            }
            n /= 10;
        }

        if (sign == -1) {     // prepend the minus sign if necessary
            sb.append('-');
        }

        sb.reverse();         // reverse to obtain the correct order
        return sb.toString();
    }

    public static void main(String[] args) {
        int n = 0;
        String str = repr(n);
        System.out.print(str);
    }
}