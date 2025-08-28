public class Main {

    /**
     * Prints the given integer with thousand separators based on the system's default locale.
     * This is the Java equivalent of the C function `print_number`, which uses `setlocale(LC_NUMERIC, "")`
     * and `printf("%'d\n", num)` to format the number.
     *
     * @param num The integer to be formatted and printed.
     */
    public static void printNumber(int num) {
        // Use Java's printf with the ',' format specifier to format the number with thousand separators.
        // This uses the default locale, which is equivalent to the system's locale in C.
        System.out.printf("%,d\n", num);
    }

    /**
     * The main method of the Java program.
     * It calls the `printNumber` method with the value 7000000, which is the same as the C code.
     */
    public static void main(String[] args) {
        printNumber(7000000);
    }
}