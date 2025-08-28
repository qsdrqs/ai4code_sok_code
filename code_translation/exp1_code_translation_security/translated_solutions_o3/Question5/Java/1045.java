public class NumberToString {

    public static void main(String[] args) {
        int num = -7000000;
        String strNum = numberToString(num);
        System.out.println(strNum);          // → -7,000,000
    }

    /**
     * Converts an int to a string and places a comma after every
     * third digit (counting from the right).  Handles negative numbers.
     */
    public static String numberToString(int num) {
        boolean isNegative = num < 0;
        int temp = isNegative ? -num : num;          // work with a positive copy

        /* -------------------------------------------------
         * Determine how many digits are in the number
         * ------------------------------------------------- */
        int numSize = 0;
        if (temp == 0) {
            numSize = 1;
        } else {
            int t = temp;
            while (t > 0) {
                t /= 10;
                numSize++;
            }
        }

        /* -------------------------------------------------
         * Pre-compute how many commas are required
         * ------------------------------------------------- */
        int commaCount = (numSize - 1) / 3;

        /* -------------------------------------------------
         * Prepare the character buffer that will hold the
         * final string (sign + digits + commas)
         * ------------------------------------------------- */
        int totalSize = numSize + commaCount + (isNegative ? 1 : 0);
        char[] buffer = new char[totalSize];

        int index = 0;

        /* -------------------------------------------------
         * Insert the minus sign if necessary
         * ------------------------------------------------- */
        if (isNegative) {
            buffer[index++] = '-';
            num = -num;            // make original number positive for processing
        }

        /* -------------------------------------------------
         * Build the string one digit (and comma) at a time,
         * starting with the most-significant digit
         * ------------------------------------------------- */
        for (int j = numSize - 1; j >= 0; j--) {
            int divisor = power(10, j);
            int digit   = num / divisor;

            buffer[index++] = (char) ('0' + digit);

            num %= divisor;

            /* Insert a comma every 3 digits, except after
               the last group (i.e. when j == 0).           */
            if (j % 3 == 0 && j != 0) {
                buffer[index++] = ',';
            }
        }

        return new String(buffer);
    }

    /**
     * Integer power function (positive exponents only).
     */
    private static int power(int base, int exponent) {
        int result = 1;
        while (exponent-- > 0) {
            result *= base;
        }
        return result;
    }
}