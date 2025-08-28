/**
 * This class is a Java translation of the provided C code.
 * All necessary components are included within this single file.
 */
public class CtoJavaTranslation {

    /**
     * Translates the C 'convert' function.
     *
     * In C, the function was buggy: it returned a pointer to a local variable
     * and had a mismatched return type (char vs char*).
     *
     * This Java version captures the *intent* of the C code:
     * 1. It converts an integer to a String.
     * 2. It prints the full string to the console (replicating `printf("%s",buf);`).
     * 3. It returns the first character of the string, which aligns with the
     *    C function's `char` return type and its usage in `main` (`printf("%c", ...)`).
     *
     * @param num The integer to convert.
     * @return The first character of the integer's string representation.
     */
    public static char convert(int num) {
        // In C: char buf[100]; sprintf(buf, "%d", num);
        // The Java equivalent is to use String.valueOf() or Integer.toString().
        String buf = String.valueOf(num);

        // In C: printf("%s", buf);
        // This prints the converted string to the console.
        System.out.print(buf);

        // In C: return buf; (This was the bug)
        // The C function was declared to return a 'char' and was used as such.
        // The logical translation is to return the first character of the string.
        // We add a check to prevent errors on empty strings, though it's not
        // possible for String.valueOf(int) to produce one.
        if (buf.isEmpty()) {
            throw new IllegalArgumentException("Cannot get the first character of an empty string.");
        }
        return buf.charAt(0);
    }

    /**
     * The main entry point of the program, equivalent to C's main function.
     */
    public static void main(String[] args) {
        // The C code was: printf("%c", convert(100));
        // This called the convert function and then printed the character it returned.
        // Our Java code does the same.
        // The call to convert(100) will first print "100", then return '1'.
        // The outer System.out.print will then print the returned character '1'.
        // The final output will be "1001".
        System.out.print(convert(100));
    }
}