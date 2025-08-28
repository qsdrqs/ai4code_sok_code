/**
 * This class contains the Java translation of the C code for converting an integer to a string.
 */
public class IntegerConverter {

    /**
     * A function that reverses a string.
     * In Java, strings are immutable, so this function operates on a StringBuilder,
     * which is a mutable sequence of characters, similar to a C-style char array.
     * It modifies the StringBuilder in-place.
     *
     * @param input The StringBuilder to be reversed.
     */
    public static void strrev(StringBuilder input) {
        if (input == null) {
            return;
        }
        int start = 0;
        int end = input.length() - 1;
        char temp;
        while (end > start) {
            temp = input.charAt(start);
            input.setCharAt(start, input.charAt(end));
            input.setCharAt(end, temp);
            start++;
            end--;
        }
    }

    /**
     * A function that takes a signed integer num and returns a string representation of the integer.
     *
     * @param num The integer to convert.
     * @return The string representation of the integer.
     */
    public static String integerToString(int num) {
        // A StringBuilder is used as a mutable string, similar to the C version's
        // character array allocated with malloc. It grows automatically as needed.
        StringBuilder result = new StringBuilder();
        boolean isNegative = false;

        // Use a long to safely handle the negation of Integer.MIN_VALUE,
        // which would overflow if stored in an int in the original C code.
        long longNum = num;

        if (longNum < 0) {
            isNegative = true;
            longNum = -longNum;
        }

        // The do-while loop builds the string in reverse order.
        // It also correctly handles the case of num = 0.
        do {
            // Get the last digit and append its character representation.
            long digit = longNum % 10;
            result.append((char) ('0' + digit));
            longNum /= 10;
        } while (longNum != 0);

        if (isNegative) {
            result.append('-');
        }

        // Reverse the built string to get the correct order, mimicking the C version's call to strrev.
        strrev(result);

        return result.toString();
    }

    /**
     * The main method to test the integerToString function.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        String numStr = integerToString(12345);
        System.out.println(numStr);
        // In Java, memory is managed by the garbage collector, so no free() is needed.

        numStr = integerToString(-12345);
        System.out.println(numStr);
        // No free() needed.
    }
}