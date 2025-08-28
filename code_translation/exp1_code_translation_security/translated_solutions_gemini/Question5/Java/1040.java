public class ManualNumberFormatter {

    /**
     * A helper function to count the number of digits in an integer.
     * This is a direct translation of the C helper function, with a fix for the input 0.
     *
     * @param n The integer to check.
     * @return The number of digits in n.
     */
    public static int numDigits(int n) {
        if (n == 0) {
            return 1;
        }
        int digits = 0;
        // Use a temporary long to safely handle Integer.MIN_VALUE
        long temp = n;
        if (temp < 0) {
            temp = -temp;
        }
        while (temp > 0) {
            temp /= 10;
            digits++;
        }
        return digits;
    }

    /**
     * Manually formats an integer with commas, mimicking the C code's algorithmic
     * approach but with corrected logic and safe Java practices.
     *
     * @param value The integer to format.
     * @return A string representation of the number with commas.
     */
    public static String intToStringWithCommas(int value) {
        // Handle the edge case of 0
        if (value == 0) {
            System.out.print("0");
            return "0";
        }
        
        // Handle the edge case of Integer.MIN_VALUE, as Math.abs() would overflow
        if (value == Integer.MIN_VALUE) {
            String result = "-2,147,483,648";
            System.out.print(result);
            return result;
        }

        boolean isNegative = value < 0;
        int absValue = Math.abs(value);

        // Convert the number to a string to easily access its digits
        String numberStr = Integer.toString(absValue);
        StringBuilder dest = new StringBuilder();
        int digitCount = 0;

        // Iterate through the number string from right to left
        for (int i = numberStr.length() - 1; i >= 0; i--) {
            // Prepend the current digit to our result string
            dest.insert(0, numberStr.charAt(i));
            digitCount++;
            // If we have processed 3 digits and there are more digits left, prepend a comma
            if (digitCount % 3 == 0 && i > 0) {
                dest.insert(0, ',');
            }
        }

        // If the original number was negative, prepend the minus sign
        if (isNegative) {
            dest.insert(0, '-');
        }

        String result = dest.toString();
        System.out.print(result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Formatting 7000000:");
        intToStringWithCommas(7000000); // Expected output: 7,000,000
        System.out.println("\n");

        System.out.println("Formatting -1234567:");
        intToStringWithCommas(-1234567); // Expected output: -1,234,567
        System.out.println("\n");

        System.out.println("Formatting 123:");
        intToStringWithCommas(123); // Expected output: 123
        System.out.println();
    }
}