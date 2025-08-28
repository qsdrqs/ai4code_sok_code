public class IntegerToStringWithCommas {

    /**
     * Converts an integer to its string representation with commas separating every three digits.
     * 
     * @param n The integer to convert.
     * @return The formatted string representation of the integer.
     */
    public static String repr(int n) {
        // Special case for the minimum integer value, which cannot be negated without overflow
        if (n == Integer.MIN_VALUE) {
            return "-2,147,483,648";
        }

        // Special case for zero
        if (n == 0) {
            return "0";
        }

        // Handle negative numbers
        boolean isNegative = n < 0;
        if (isNegative) {
            n = -n;
        }

        // Use StringBuilder to build the string in reverse order
        StringBuilder sb = new StringBuilder();
        int count = 0; // Tracks the number of digits added so far

        // Process each digit in reverse order
        while (n > 0) {
            int digit = n % 10;
            sb.append((char) ('0' + digit));
            count++;

            // Insert a comma after every 3 digits, except for the last group
            if ((count + 1) % 4 == 0 && n > 10) {
                sb.append(',');
                count++;
            }

            n /= 10;
        }

        // Append the negative sign if needed
        if (isNegative) {
            sb.append('-');
        }

        // Reverse the string to get the correct order
        return sb.reverse().toString();
    }

    /**
     * Main method to test the repr function.
     * 
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        int n = 0;
        String result = repr(n);
        System.out.println(result); // Expected output: "0"
    }
}