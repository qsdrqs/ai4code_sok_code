public class Main {

    /* same logical role as the C macro */
    private static final int BUFFER_SIZE = 1024;

    /**
     * Inserts thousands–separator commas into the decimal
     * representation of the supplied long value.
     *
     * Behaviour is identical to the original C routine:
     *   - works for positive or negative numbers
     *   - a comma is added every three digits when counting
     *     from the right (most–significant group may have <3 digits)
     */
    public static String convert(long value) {

        /* first “buffer” – just the plain number as text            */
        /* Long.toString() is equivalent to snprintf("%ld", value);  */
        String plain = Long.toString(value);

        /* second “buffer” – where we build the comma-separated form */
        /* StringBuilder is the Java analogue of char[] + index      */
        StringBuilder formatted = new StringBuilder(BUFFER_SIZE);

        int len = plain.length();

        /* replicate the original for-loop logic from the C code */
        for (int i = 0; i < len; i++) {
            formatted.append(plain.charAt(i));

            int dist = len - i - 1;      // distance to the end
            if (dist % 3 == 0 && dist != 0) {
                /* add the thousands separator */
                formatted.append(',');
            }
        }

        return formatted.toString();
    }

    /* demonstration equivalent to the original main() */
    public static void main(String[] args) {
        String test = convert(1000);
        System.out.println(test);   // prints 1,000
    }
}