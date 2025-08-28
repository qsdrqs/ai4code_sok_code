import java.util.Random;

public class CommaInsertionDemo {

    public static void main(String[] args) {

        /* -----------------------------------------------------------
         *  Generate a pseudo-random value – in the original C program
         *  rand() returned an int in the range 0 … RAND_MAX.
         *  We mimic that by asking Random for a positive int and
         *  storing the result in a long so we can use “%d” formatting
         *  exactly as in the C program.
         * ----------------------------------------------------------- */
        Random random = new Random();               // same rôle as srand()/rand()
        long value = random.nextInt(Integer.MAX_VALUE);

        System.out.println("Before:\t" + value);

        /* -----------------------------------------------------------
         *  Step-1: convert the number to a character sequence
         * ----------------------------------------------------------- */
        String vstring = Long.toString(value);
        int len = vstring.length();                 // how many characters left?
                                                   // (same as the C variable len)

        /* -----------------------------------------------------------
         *  Step-2: build up the result with commas
         * ----------------------------------------------------------- */
        StringBuilder commas = new StringBuilder(); // equivalent to the C char array
        int v1 = 0;                                 // index inside vstring

        /* identical logic to the C “while (vstring[v1]) …” loop        */
        while (v1 < vstring.length()) {
            if (len % 3 != 0) {                     // we are *not* at a group-of-3 boundary
                commas.append(vstring.charAt(v1));
            } else {                                // we *are* at a group-of-3 boundary
                if (commas.length() != 0) {         // …but only add a comma if it’s not the first group
                    commas.append(',');
                }
                commas.append(vstring.charAt(v1));
            }
            v1++;                                   // move to next digit
            len--;                                  // one less digit left
        }

        System.out.println("With:\t" + commas.toString());
    }
}