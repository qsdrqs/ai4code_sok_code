public class Main {

    private static final int MAX_LEN = 16;   // kept from the C code, though unused

    /**
     * Replicates the behaviour of the original C function `num_to_string`.
     * Inserts thousands-separating commas and keeps a leading “-” when the
     * original number was negative.
     */
    public static String numToString(int num) {

        /* Handle the trivial “0” case. */
        if (num == 0) {
            return "0";
        }

        /* Detect and record the sign. */
        boolean isNeg = false;
        if (num < 0) {
            isNeg = true;
            num = -num;
        }

        /* Count digits. */
        int curNum      = num;
        int totalDigits = 0;
        while (curNum > 0) {
            curNum /= 10;
            totalDigits++;
        }

        /* How many commas are needed? */
        int numCommas = (totalDigits - 1) / 3;

        /* Final string length (without the terminating '\0' from C). */
        int strLen = totalDigits + numCommas;
        if (isNeg) {
            strLen += 1;                     // room for '-'
        }

        /* Create the character buffer. */
        char[] str = new char[strLen];

        /* Fill in the digits and commas from the right end. */
        curNum = num;
        int index  = 0;      // distance from the rightmost position
        int digits = 0;      // digits handled since last comma

        while (curNum > 0) {
            char curChar = (char) ((curNum % 10) + 48);   // ‘0’ == 48
            str[strLen - index - 1] = curChar;

            curNum /= 10;
            index++;
            digits++;

            if (digits % 3 == 0) {            // drop a comma every 3 digits
                str[strLen - index - 1] = ',';
                index++;
            }
        }

        /* Prepend the minus sign when required. */
        if (isNeg) {
            str[0] = '-';
        }

        /* Turn the char array into a String and return it. */
        return new String(str);
    }

    public static void main(String[] args) {
        System.out.print(numToString(-5305000));   // expected: -5,305,000
    }
}