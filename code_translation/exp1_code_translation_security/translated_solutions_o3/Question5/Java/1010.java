import java.util.*;

/**
 * Direct translation of the given C program.
 */
public class Main {

    public static void main(String[] args) {
        System.out.printf("   %s%n", stringIt(-123457));
    }

    /**
     * Return a String containing the decimal representation of the
     * signed int with commas every three digits (just like the C version).
     */
    public static String stringIt(int value) {

        /* In C the code mallocs 30 bytes.  Reserve roughly the same
           amount of room in a StringBuilder so we do the minimum of
           reallocations. */
        StringBuilder result = new StringBuilder(30);

        /* Handle the sign.  We have to be careful with Integer.MIN_VALUE
           (-2^31) because ‑Integer.MIN_VALUE would overflow.  Therefore
           promote to long first. */
        long v = value;
        if (v < 0) {
            result.append('-');
            v = -v;
        }

        /* Convert the absolute value to a digit string, exactly what
           sprintf(str, "%d", value) did in the C code. */
        String digits = Long.toString(v);

        /* How many commas will we need? */
        int length      = digits.length();
        int commaCount  = (length - 1) / 3;

        /* Fast path: no commas needed. */
        if (commaCount == 0) {
            result.append(digits);
            return result.toString();
        }

        /* Build the string with commas from left to right so we do
           not need temporary buffers like the C version did.   */
        int leading = length % 3;
        if (leading == 0) leading = 3;      // First group could be 3 long.

        // First (possibly short) group
        result.append(digits, 0, leading);

        // Remaining full 3-digit groups, each preceded by a comma
        for (int i = leading; i < length; i += 3) {
            result.append(',');
            result.append(digits, i, i + 3);
        }

        return result.toString();
    }
}