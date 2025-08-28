/**
 * A Java class to demonstrate number formatting with recursion,
 * equivalent to the provided C code.
 */
public class NumberFormatter {

    /**
     * Prints an integer with comma separators for thousands.
     * This method is a direct translation of the C function `print_num`.
     *
     * @param num The integer to be printed.
     */
    public static void printNum(int num) {
        // Handle negative numbers by printing a '-' sign and recursing with the positive value.
        if (num < 0) {
            System.out.print("-");
            printNum(-num);
            return;
        }

        // Base case for the recursion: if the number is less than 1000, print it directly.
        if (num < 1000) {
            System.out.print(num);
            return;
        }

        // Recursive step:
        // 1. First, print the most significant part of the number (e.g., for 12345, this prints 12).
        printNum(num / 1000);

        // 2. After the recursion returns, print the last three digits, zero-padded.
        //    System.out.printf is the Java equivalent of C's printf.
        //    ",%03d" prints a comma, followed by the number padded with leading zeros to a width of 3.
        System.out.printf(",%03d", num % 1000);
    }

    /**
     * The main method, which serves as the entry point for the program.
     * This is equivalent to the C `main` function.
     */
    public static void main(String[] args) {
        // Call the printNum function with the same test value as in the C code.
        printNum(-12345);
        // Expected output: -12,345
    }
}