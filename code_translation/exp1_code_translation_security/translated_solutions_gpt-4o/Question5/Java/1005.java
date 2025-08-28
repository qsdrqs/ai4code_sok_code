public class Main {
    private static final int MAX_LEN = 14;

    public static int stringify(int i, char[] buf) {
        char[] tmp = new char[MAX_LEN];
        // Initialize tmp with '0'
        for (int idx = 0; idx < MAX_LEN; idx++) {
            tmp[idx] = '0';
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

        // Copy the result to buf
        int bufIndex = 0;
        for (int n = k + 1; n < MAX_LEN; n++) {
            buf[bufIndex++] = tmp[n];
        }

        return MAX_LEN - 1 - k;
    }

    public static void main(String[] args) {
        char[] buf = new char[MAX_LEN];
        // Initialize buf with '0'
        for (int idx = 0; idx < MAX_LEN; idx++) {
            buf[idx] = '0';
        }

        int input = -2147483647;
        int l = stringify(input, buf);

        // Convert char array to String for printing
        String result = new String(buf, 0, l);
        System.out.println(l + " :: " + result);
    }
}