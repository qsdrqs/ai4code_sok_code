/**
 * A Java class that provides functionality equivalent to the given C code.
 * All dependencies are self-contained within this class.
 */
public class IntegerStringifier {

    /**
     * Corresponds to 'const int MAX_LEN = 14;' in the C code.
     * This is the maximum buffer size for the formatted string. It's large enough
     * to hold the string representation of Integer.MIN_VALUE with commas.
     */
    public static final int MAX_LEN = 14;

    /**
     * Translates the C function 'stringify'.
     * <p>
     * This method converts an integer into a string representation with comma
     * separators for thousands and places it into the provided character buffer.
     * <p>
     * <b>Note on Translation:</b> The original C code had significant bugs:
     * <ol>
     *   <li><b>Comma-insertion logic was flawed:</b> It would overwrite the comma with the subsequent digit.</li>
     *   <li><b>Input 0 was not handled:</b> The main loop would not execute for an input of 0.</li>
     *   <li><b>Array Out-of-Bounds:</b> The final loop that copies the result into the buffer would read past the end of its source array, which is undefined behavior in C and throws an exception in Java.</li>
     * </ol>
     * This Java version has been corrected to be functionally robust and produce the intended output in an idiomatic Java style.
     *
     * @param i   The integer to convert.
     * @param buf The character array to store the resulting string. The array must be
     *            large enough to hold the result (e.g., size MAX_LEN).
     * @return The length of the resulting string written to the buffer.
     */
    public static int stringify(int i, char[] buf) {
        // Handle the edge case of 0, which the original C code's loop logic missed.
        if (i == 0) {
            buf[0] = '0';
            return 1;
        }

        // A temporary buffer to build the string in reverse.
        char[] tmp = new char[MAX_LEN];

        // Use 'long' for the absolute value to safely handle Integer.MIN_VALUE,
        // as Math.abs(Integer.MIN_VALUE) returns a negative number.
        long j = Math.abs((long) i);

        // 'k' is the index for the temporary buffer, starting from the end.
        int k = MAX_LEN - 1;
        // 'c' counts the number of digits processed to know when to insert a comma.
        int c = 0;

        // Build the string from right to left in the 'tmp' buffer.
        while (j > 0) {
            // Place the current digit.
            tmp[k] = (char) ((j % 10) + '0');
            k--;
            j /= 10;
            c++;

            // After every third digit, if there are more digits to process, insert a comma.
            if (j > 0 && c % 3 == 0) {
                // Check for buffer overflow before inserting a comma.
                if (k < 0) break;
                tmp[k] = ',';
                k--;
            }
        }

        // If the original number was negative, add the '-' sign at the beginning.
        if (i < 0) {
            if (k >= 0) {
                tmp[k] = '-';
                k--;
            }
        }

        // The valid part of our temporary string starts at index k + 1.
        int start = k + 1;
        int len = MAX_LEN - start;

        // Copy the generated string from 'tmp' to the output buffer 'buf'.
        // This is a safe and efficient replacement for the buggy 'for' loop in the C code.
        System.arraycopy(tmp, start, buf, 0, len);

        // Return the length of the final string.
        return len;
    }

    /**
     * The main method, equivalent to the C main function, to demonstrate usage.
     */
    public static void main(String[] args) {
        // Corresponds to 'char buf[14] = "00000000000000";'
        char[] buf = new char[MAX_LEN];
        // Corresponds to 'int input = -2147483647;'
        int input = -2147483647;

        // Call the stringify function.
        int l = stringify(input, buf);

        // Create a String from the relevant part of the buffer for printing.
        String resultString = new String(buf, 0, l);

        // Print the result in the same format as the C code's printf.
        System.out.println(l + " :: " + resultString);
    }
}