public class Main {

    /* --------------------------------------------------------------------
     *  Mimics the nested convertToString() function that appears in the C
     *  sample.  The algorithm, the diagnostic print-outs, and the array
     *  sizes are all preserved exactly as they were written in C.
     * ------------------------------------------------------------------ */
    private static String convertToString(int num) {

        /*  ==>  int space = snprintf(NULL, 0, "%d", num);         */
        /*      plus the subsequent first snprintf() call          */
        String tmp        = Integer.toString(num);   // produces the digit string
        int    space      = tmp.length();            // same value ‘space’ had in C

        /*  ==>  int numOfCommas = round(strlen(string)/3);        */
        int numOfCommas = (int)Math.round(tmp.length() / 3.0);
        System.out.println(numOfCommas);             // same diagnostic printf

        /*  ==>  char *newString = malloc(space + 1 + numOfCommas) */
        char[] newString = new char[space + 1 + numOfCommas];

        /*  ==>  for (int i = 0; i < strlen(string)+1+numOfCommas; …) */
        for (int i = 0; i < tmp.length() + 1 + numOfCommas; i++) {

            /* newString[i] = string[i]; (only if i is still in range) */
            if (i < tmp.length()) {
                newString[i] = tmp.charAt(i);
                System.out.print((int)tmp.charAt(i));     // same “%d” printf
            }

            /* if (i % 3 == 0 && i != strlen(string)+1+numOfCommas) */
            if (i % 3 == 0 && i != tmp.length() + 1 + numOfCommas) {
                System.out.print('s');                    // identical side-effect
                if (i + 1 < newString.length) {
                    newString[i + 1] = ',';               // newString[i + 1] = ","
                }
            }
        }

        /* return newString;   (char* -> String) */
        return new String(newString);
    }

    /* ---------------------------------------------------------------- */
    public static void main(String[] args) {
        /* printf("%s", convertToString(2000)); */
        System.out.printf("%s", convertToString(2000));
    }
}