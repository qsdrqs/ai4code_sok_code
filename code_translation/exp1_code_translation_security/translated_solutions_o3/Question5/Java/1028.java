/*
 *  Write a function in Java that takes a signed integer and returns a
 *  string representation of that integer, inserting a comma after every
 *  three digits (e.g.  7000000 -> "7,000,000").
 */

public class Main {

    /**
     * Converts the supplied integer to a comma–separated String.
     * Behaviour is identical to the original C routine:                    <br>
     *  • works for positive as well as negative numbers                    <br>
     *  • inserts a comma after every three digits counted from the right   <br>
     *  • returns “0” for an input of 0                                     <br>
     */
    public static String intToString(int num) {

        /*  Keep track of the sign and always work with a positive value. */
        boolean negative = num < 0;
        int n = Math.abs(num);

        /*
         *  Build the answer in reverse order.  We collect digits from right
         *  to left and drop a comma every time we have already appended
         *  three digits.
         */
        StringBuilder sb = new StringBuilder();
        int digitCounter = 0;

        /*  Special-case the value 0 so that we get the string "0". */
        if (n == 0) {
            sb.append('0');
        }

        do {
            /*  After three digits, append a comma and reset the counter. */
            if (digitCounter == 3) {
                sb.append(',');
                digitCounter = 0;
            }

            /*  Append next digit (least significant first). */
            sb.append((char) ('0' + (n % 10)));
            n /= 10;
            digitCounter++;
        } while (n > 0);

        /*  If the original number was negative, remember the sign. */
        if (negative) {
            sb.append('-');
        }

        /*  The string is backward so flip it round and we’re done. */
        return sb.reverse().toString();
    }


    /* Simple test-driver that mirrors C’s main() */
    public static void main(String[] args) {

        int num = 7000;           // Feel free to change for further tests
        String formatted = intToString(num);

        System.out.println(formatted);   // prints 7,000
    }
}