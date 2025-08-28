/*
 * Direct translation of the given C program to Java.
 * The logic ‑ including buffer sizes, comma insertion and the
 * returned length – is kept 1-to-1 with the original C code.
 */
public class Main {

    private static final int MAX_LEN = 14;   // identical to the C constant

    /**
     * Writes the string representation of {@code value} into {@code buf},
     * inserting a comma after every three digits (counting from the right)
     * and adding a leading minus sign for negative numbers.
     *
     * @param value the integer to stringify
     * @param buf   destination buffer (must be at least {@code MAX_LEN} long)
     * @return      number of characters written to {@code buf}
     */
    public static int stringify(int value, char[] buf) {

        /* temporary buffer that we fill from the back */
        char[] tmp = "00000000000000".toCharArray();   // length == MAX_LEN

        long abs = (value >= 0) ? value : -(long) value; // long avoids overflow
        int k = MAX_LEN - 1;    // index in tmp where we are writing
        int c = 0;              // how many digits have been written since
                                // the last comma

        /* Special-case 0 so the loop is skipped but ‘0’ is still produced. */
        if (abs == 0) {
            tmp[k--] = '0';
        }

        /* Copy digits (and commas) into tmp from right to left */
        while (abs > 0 && k >= 0) {
            if (c != 0 && c % 3 == 0) {      // insert comma every 3 digits
                tmp[k--] = ',';
                c = 0;
            }

            int digit = (int) (abs % 10);
            tmp[k--] = (char) (digit + '0');

            abs /= 10;
            c++;
        }

        /* Add leading minus sign if necessary */
        if (value < 0 && k >= 0) {
            tmp[k--] = '-';
        }

        /*
         * Copy the relevant slice of tmp into buf.
         * In the C code:
         *   for (n = k; n < MAX_LEN; n++)
         *       buf[n - k] = tmp[n + 1];
         * That is, we skip tmp[k] itself and start with tmp[k + 1].
         */
        for (int n = k + 1; n < MAX_LEN; ++n) {
            buf[n - (k + 1)] = tmp[n];
        }

        /* length = how many characters we wrote */
        return MAX_LEN - 1 - k;
    }

    public static void main(String[] args) {

        char[] buf = new char[MAX_LEN];      // destination buffer
        int input = -2147483647;

        int len = stringify(input, buf);

        /* Produce a Java String strictly of the written length for printing */
        String result = new String(buf, 0, len);

        System.out.println(len + " :: " + result);
    }
}