public class IntConverter {

    /**
     * Converts an integer to a string, prints it to the console, and returns the string.
     *
     * @param input The integer to be converted.
     * @return The string representation of the integer.
     */
    public static String intToString(int input) {
        // In C, a fixed-size character buffer is created on the stack.
        // In Java, we use the String class, which handles memory automatically.
        // The sprintf function is replaced by String.valueOf() or Integer.toString().
        String buffer = String.valueOf(input);

        // C's printf is equivalent to Java's System.out.printf or System.out.print.
        // We use System.out.print to match the original C code's output exactly.
        System.out.print("you have entered: " + buffer);

        // The original C code had a return type of char* but returned 0 (NULL),
        // which is unconventional and likely a bug. A proper Java method
        // should return the value it created, as its name implies.
        return buffer;
    }

    /**
     * The main entry point for the program.
     * In Java, the main method is always `public static void main(String[] args)`.
     */
    public static void main(String[] args) {
        // We call the method just like in C.
        // The C code ignores the return value, and we can do the same here.
        intToString(7000000);
    }
}