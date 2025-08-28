/**
 * A class to demonstrate the translation of a C integer-to-string
 * function to Java.
 */
public class IntegerFormatter {

    /**
     * Translates the C function int_to_string.
     * <p>
     * This method converts an integer to a string, adding thousand separators (commas).
     * The logic directly mirrors the provided C code's algorithm.
     *
     * @param a The integer to convert.
     * @return A string representation of the integer with commas.
     */
    public static String intToString(int a) {
        // In C: char* buf = malloc(...); sprintf(buf, "%d", a);
        // In Java, we can directly convert the integer to a String.
        String buf = Integer.toString(a);
        int len = buf.length();

        // In C: char* str = malloc(...);
        // In Java, StringBuilder is the efficient way to build a string piece by piece.
        StringBuilder strBuilder = new StringBuilder();

        String actualBuf = buf;
        int actualLen = len;

        // This block handles negative numbers, similar to the C code.
        // C: if (len > 0 && buf[0] == '-') { ... }
        if (len > 0 && buf.charAt(0) == '-') {
            strBuilder.append(buf.charAt(0)); // C: str[idx++] = buf[0];
            actualBuf = buf.substring(1);     // C: actual_buf += 1;
            actualLen -= 1;                   // C: len -= 1;
        }

        // This loop iterates through the digits of the number.
        // C: for (; buf_idx < len; buf_idx++) { ... }
        for (int bufIdx = 0; bufIdx < actualLen; bufIdx++) {
            // This condition inserts a comma every three digits from the right.
            // It's a direct translation of the C code's comma-insertion logic.
            // C: if (buf_idx != 0 && (len - buf_idx) % 3 == 0) { ... }
            if (bufIdx != 0 && (actualLen - bufIdx) % 3 == 0) {
                strBuilder.append(','); // C: str[idx++] = ',';
            }
            // Append the current digit.
            // C: str[idx++] = actual_buf[buf_idx];
            strBuilder.append(actualBuf.charAt(bufIdx));
        }

        // In C, the string must be null-terminated: str[idx] = '\0';
        // In Java, StringBuilder.toString() handles the final string creation.
        // The C memory management (free(buf)) is handled automatically by Java's
        // garbage collector.
        return strBuilder.toString();
    }

    /**
     * The main method, equivalent to the C main function, to test the intToString method.
     */
    public static void main(String[] args) {
        System.out.println(intToString(7000000));
        System.out.println(intToString(700000));
        System.out.println(intToString(70000));
        System.out.println(intToString(7000));
        System.out.println(intToString(700));
        System.out.println(intToString(70));
        System.out.println(intToString(7));
        System.out.println(intToString(0));
        System.out.println(intToString(-7000000));
        System.out.println(intToString(-700000));
        System.out.println(intToString(-70000));
        System.out.println(intToString(-7000));
        System.out.println(intToString(-700));
        System.out.println(intToString(-70));
        System.out.println(intToString(-7));
        // In C: INT_MAX from <limits.h>
        System.out.println(intToString(Integer.MAX_VALUE));
        // In C: INT_MIN from <limits.h>
        System.out.println(intToString(Integer.MIN_VALUE));
    }
}