/**
 * A direct Java translation of the supplied C snippet.
 * It converts an integer to a string and inserts commas every three digits,
 * starting from the right-hand side (U.S. style thousands separator).
 */
public class CommaFormatter {

    /** ---------------------------------------------------------------
     * Helper that returns a new string where a comma is inserted
     * at the supplied index.
     * -------------------------------------------------------------- */
    private static String insertComma(String s, int index) {
        // substring(0, index)  : characters before the insertion point
        // substring(index)     : characters from the insertion point on
        return s.substring(0, index) + "," + s.substring(index);
    }

    /** ---------------------------------------------------------------
     * Returns the decimal representation of the number with commas.
     * Example: 1234567 -> "1,234,567"
     * -------------------------------------------------------------- */
    private static String stringRepre(int num) {
        // Initial decimal representation (no commas).
        String strNum = String.valueOf(num);

        // Work with a mutable buffer so that repeated insertions are cheap.
        StringBuilder sb = new StringBuilder(strNum);

        // Start three characters from the right, then step leftward in blocks of three.
        // Insert commas until we run out of digits to the left.
        for (int i = sb.length() - 3; i > 0; i -= 3) {
            sb.insert(i, ',');
        }
        return sb.toString();
    }

    /** ---------------------------------------------------------------
     * Entry point.  The C code printed the comma-formatted value of argc,
     * the number of command line arguments.  We mimic that here.
     * -------------------------------------------------------------- */
    public static void main(String[] args) {
        int argc = args.length;          // Number of command-line arguments
        String result = stringRepre(argc);
        System.out.println(result);
    }
}