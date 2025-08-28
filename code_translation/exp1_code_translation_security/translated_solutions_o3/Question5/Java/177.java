public final class Formatter {

    /**
     * Mimics the behaviour of the original C routine:
     *   – prints the (signed) integer in base-10
     *   – inserts a comma ( , ) after every three digits
     *   – returns the newly-created String
     */
    public static String int2str(int num) {

        /* Step 1 : “sprintf” the number so we know its length            */
        String str = String.format("%d", num);     // decimal text of num
        int length = str.length();                 // number of characters

        /* Step 2 : calculate resulting length once commas are inserted   */
        int newl;
        if (length % 3 == 0) {
            newl = length + length / 3 - 1;        // last group needs no comma
        } else {
            newl = length + length / 3;            // every full trio gets one
        }

        /* Step 3 : build the string from right to left                   */
        char[] result = new char[newl];
        int counter = 0;                           // counts copied digits
        int j = newl - 1;                          // write index in result

        for (int i = length - 1; i >= 0; i--) {
            counter++;                             // another digit copied
            result[j] = str.charAt(i);             // copy digit
            if (counter % 3 == 0 && j > 0) {       // need a comma?
                j--;
                result[j] = ',';                   // place comma
            }
            j--;
        }

        return new String(result);
    }

    /* Simple test driver */
    public static void main(String[] args) {
        System.out.println(int2str(1));            // 1
        System.out.println(int2str(123));          // 123
        System.out.println(int2str(1234));         // 1,234
        System.out.println(int2str(123456789));    // 123,456,789
        System.out.println(int2str(-987654));      // -,987,654  (same as C version)
    }
}