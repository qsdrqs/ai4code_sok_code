public class Main {

    /* ---------------------------------------------------------
       Same logic as C version: count decimal digits in |n|.
    --------------------------------------------------------- */
    private static int numDigits(int n) {
        if (n == 0) return 1;          // special–case 0
        int digits = 0;
        while (n != 0) {
            n /= 10;
            digits++;
        }
        return digits;
    }

    /* ---------------------------------------------------------
       Convert an int to a String with commas every 3 digits,
       mimicking the C algorithm as closely as reasonable.
    --------------------------------------------------------- */
    public static String intToStringWithCommas(int value) {

        /* --- deal with sign first -------------------------------- */
        boolean isNegative = value < 0;
        if (isNegative) {
            value = -value;            // work with the magnitude only
        }

        /* --- figure out how many commas we will need ------------- */
        int digits     = numDigits(value);
        int numCommas  = digits / 3;
        if (digits % 3 == 0) {
            numCommas--;               // same rule as in the C code
        }

        /* --- obtain the raw digits as a char array --------------- */
        char[] src = Integer.toString(value).toCharArray();

        /* size of the final digit-only portion (no sign) */
        int size = digits + numCommas;

        char[] dest = new char[size];  // where we will build the result

        /* --- fill dest from the end, inserting commas ------------- */
        int destIdx = size - 1;        // write from right to left
        int srcIdx  = src.length - 1;  // last digit of the number
        int digitCnt = 0;              // how many digits have we copied?

        while (srcIdx >= 0) {
            dest[destIdx--] = src[srcIdx--];   // copy a digit
            digitCnt++;

            /* insert a comma after every third digit, unless done */
            if (digitCnt % 3 == 0 && srcIdx >= 0) {
                dest[destIdx--] = ',';         // place the comma
            }
        }

        /* --- assemble final String (put sign back if needed) ------ */
        String result = (isNegative ? "-" : "") + new String(dest);

        /* mimic the printf in C: print the result */
        System.out.println(result);

        return result;
    }

    /* --------------------------------------------------------- */
    public static void main(String[] args) {
        intToStringWithCommas(7_000_000);   // identical test to the C main()
    }
}