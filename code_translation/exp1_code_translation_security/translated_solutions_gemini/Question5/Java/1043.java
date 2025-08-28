public class NumberConverter {

    /**
     * Converts an integer to its string representation.
     *
     * @param num The integer to convert.
     * @return The string representation of the number.
     */
    public static String numToString(int num) {
        // In Java, the simplest and most direct way to convert an integer
        // to a string is using the static method Integer.toString()
        // or String.valueOf().
        return Integer.toString(num);
    }

    /**
     * The main entry point for the program.
     */
    public static void main(String[] args) {
        // In Java, we use System.out.println() to print a string to the console,
        // followed by a new line. This is the equivalent of the C `printf`.
        System.out.println(numToString(7000000));
    }
}