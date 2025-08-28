public class IntegerToString {

    /**
     * Converts a signed integer to its string representation.
     * This method mimics the behavior of the original C function `integerToString`.
     *
     * @param num The integer to convert.
     * @return A string representation of the integer.
     */
    public static String integerToString(int num) {
        // Special case for zero
        if (num == 0) {
            return "0";
        }

        boolean isNegative = false;

        // Handle negative numbers
        if (num < 0) {
            isNegative = true;
            num = -num;
        }

        // Use StringBuilder to build the digits in reverse order
        StringBuilder sb = new StringBuilder();

        // Extract digits from the number
        while (num > 0) {
            int digit = num % 10;
            sb.append((char) ('0' + digit));
            num /= 10;
        }

        // Append the negative sign if needed
        if (isNegative) {
            sb.append('-');
        }

        // Reverse the string to get the correct order
        return sb.reverse().toString();
    }

    /**
     * Main method to test the integerToString function.
     * This mirrors the original C `main` function.
     */
    public static void main(String[] args) {
        // Test with a positive number
        String num = integerToString(12345);
        System.out.println(num);  // Expected output: "12345"

        // Test with a negative number
        num = integerToString(-12345);
        System.out.println(num);  // Expected output: "-12345"
    }
}