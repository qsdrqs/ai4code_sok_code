/*
 * write this code in Java: define a function that takes in an unsigned integer.
 * convert this integer into a string. then, add commas to it where appropriate.
 * return this new string. define a method called "reverseStr" that takes a
 * String and reverses it.
 */

public class AddCommas {

    public static void main(String[] args) {
        long num = 1_000_000;                 // same test-value as in the C code
        String str = addCommas(num);
        System.out.println(str);              // prints 1,000,000
    }

    /* -------------------------------------------------------------
       Reverses a String and returns the new String.
       ------------------------------------------------------------- */
    public static String reverseStr(String str) {
        // StringBuilder already supplies a reverse() method
        return new StringBuilder(str).reverse().toString();
    }

    /* -------------------------------------------------------------
       Adds thousand-separating commas to the supplied unsigned int
       (represented here as a long, because Java has no native
       unsigned-int primitive).
       ------------------------------------------------------------- */
    public static String addCommas(long num) {
        // Special-case 0 so that we don’t generate an empty String.
        if (num == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        int count = 0;

        // Build the result backwards (least-significant digit first)
        while (num != 0) {
            long digit = num % 10;
            sb.append((char) ('0' + digit));
            num /= 10;
            count++;

            // Insert a comma after every three digits, unless we’ve run out of digits.
            if (count == 3 && num != 0) {
                sb.append(',');
                count = 0;
            }
        }

        // The string we built is backwards, so reverse it and we’re done.
        return reverseStr(sb.toString());
    }
}