public class Main {

    /* 10^x --------------------------------------------------------------- */
    private static int e10(int x) {
        int v = 1;
        for (int i = 0; i < x; i++) {
            v *= 10;
        }
        return v;
    }

    /* signum ------------------------------------------------------------- */
    private static int signum(int x) {
        if (x < 0) {
            return -1;
        }
        if (x == 0) {
            return 0;
        }
        return 1;
    }

    /* abs (own implementation, not java.lang.Math.abs) ------------------ */
    private static int abs(int x) {
        return x * signum(x);
    }

    /* -------------------------------------------------------------------- 
       Corresponds to ‘char* int_to_str(int x)’ in the C version.
       The algorithm, index arithmetic, comma insertion and handling of
       negative numbers are kept identical to the original code.
       ------------------------------------------------------------------ */
    private static String intToStr(int x) {

        /* +1 element compared with the C array (index [16] is accessed).   */
        char[] out = new char[17];

        int expOffset = 0;

        for (int i = 0; i < 16; i++) {

            /* real digit position, compensating for commas that were added */
            int digit = i - expOffset;

            /* write the current digit                                     */
            out[16 - i] =
                    (char) (0x30 + abs((x % e10(digit + 1)) / e10(digit)));

            /* are we done? (have we reached the most-significant digit?)   */
            if (x % e10(digit + 1) == x) {

                /* write minus sign if necessary                            */
                if (x < 0) {
                    out[16 - (i + 1)] = '-';
                    i++;                         // keep loop-index logic
                }

                /* build a Java String from the calculated slice            */
                return new String(out, 16 - i, i + 1);
            }

            /* insert thousand separators after 3, 7 and 11 characters      */
            if (i == 2 || i == 6 || i == 10) {
                out[16 - i - 1] = ',';
                i        += 1;                   // skip over the comma
                expOffset += 1;                  // account for the comma
            }
        }
        /* fallback (should not be reached with 32-bit ints)                */
        return new String(out);
    }

    /* ------------------------------------------------------------------ */
    public static void main(String[] args) {

        System.out.println(intToStr(55));            // 55
        System.out.println(intToStr(12345));         // 12,345
        System.out.println(intToStr(-55));           // -55
        System.out.println(intToStr(-123456789));    // -123,456,789
    }
}