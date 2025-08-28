public class Main {

    private static final int MAX_LEN = 14;

    /**
     * Converts an integer into a string representation with commas as thousand separators.
     *
     * @param i   the integer to convert
     * @param buf the buffer to store the string representation
     * @return the length of the string representation
     */
    public static int stringify(int i, char[] buf) {
        char[] tmp = new char[MAX_LEN];
        for (int n = 0; n < MAX_LEN; n++) {
            tmp[n] = '0';
        }
        int j = (i >= 0) ? i : -1 * i;
        int k = MAX_LEN - 1;
        int c = 0;
        while (j > 0 && k >= 0) {
            if (c != 0 && c % 3 == 0) {
                tmp[k] = ',';
                k--;
                c = 0;
            }
            // Insert comma
            int lenWritten = MAX_LEN - 1 - k;
            // Convert digit to char
            int digit = j % 10;
            char dChar = (char) (digit + '0');
            // Add to string
            tmp[k] = dChar;
            // Move to next digit
            j /= 10;
            k--;
            c++;
        }

        if (i < 0) {
            tmp[k] = '-';
            k--;
        }

        for (int n = k; n < MAX_LEN; n++) {
            buf[n - k] = tmp[n];
        }

        return MAX_LEN - 1 - k;
    }

    public static void main(String[] args) {
        char[] buf = new char[MAX_LEN];
        int input = -2147483647;
        int length = stringify(input, buf);
        System.out.println(length + " :: " + new String(buf, 0, length));
    }
}