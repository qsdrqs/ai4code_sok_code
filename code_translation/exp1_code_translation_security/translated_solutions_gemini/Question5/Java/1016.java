/**
 * This class contains the Java translation of the C function that converts an integer to a string.
 * All necessary dependencies are part of the standard Java library.
 */
public class IntegerConverter {

    /**
     * Takes an integer and converts it to a string, adding commas as thousands separators.
     * This is a Java translation of the provided C function, with corrections for bugs
     * and adaptation to idiomatic Java.
     *
     * @param num The integer to convert.
     * @return A string representation of the integer with commas.
     */
    public static String intToString(int num) {
        // Handle the edge case of 0, which the original C code did not.
        if (num == 0) {
            return "0";
        }

        // Use a StringBuilder for efficient, mutable string operations.
        StringBuilder sb = new StringBuilder();

        // Use a long to safely handle Integer.MIN_VALUE, as Math.abs(Integer.MIN_VALUE) overflows.
        long number = num;
        boolean isNegative = number < 0;
        if (isNegative) {
            number = -number; // Work with the absolute value
        }

        int digitCount = 0;
        while (number > 0) {
            // Append the last digit of the number.
            sb.append(number % 10);
            number /= 10;
            digitCount++;

            // Add a comma every three digits, but only if there are more digits to come.
            if (digitCount % 3 == 0 && number > 0) {
                sb.append(',');
            }
        }

        // If the original number was negative, append the minus sign.
        if (isNegative) {
            sb.append('-');
        }

        // The string was built in reverse order (e.g., "078,990,01-"),
        // so we reverse it to get the correct final string.
        return sb.reverse().toString();
    }

    /**
     * The main method to test the intToString function, equivalent to the C main function.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        int num = -10099870;
        String str = intToString(num);

        // In Java, we use System.out.println() instead of printf() for simple line printing.
        System.out.println(str);

        // No need to free memory like in C; Java's garbage collector handles it automatically.
    }
}