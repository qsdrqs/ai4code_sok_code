/**
 * This class contains the Java translation of the C code for formatting an integer with commas.
 * All original C functions (reverse, itoa, insert_comma, intToString) are consolidated
 * into a single, more idiomatic Java method `intToStringWithCommas`.
 */
public class IntegerFormatter {

    /**
     * Takes a signed integer and returns a string representation with comma separators for thousands.
     * This method faithfully translates the algorithmic approach of the original C code.
     * <p>
     * The C code's logic is as follows:
     * 1. Convert the integer to a reversed string of digits (`itoa`).
     * 2. Insert commas into the reversed string from left to right.
     * 3. Reverse the resulting string to get the final, correct format.
     * 4. Handle the negative sign.
     * <p>
     * This Java implementation uses a `StringBuilder` to efficiently perform these steps.
     *
     * @param num The signed integer to be converted.
     * @return A string representation of the integer with thousand separators (e.g., "7,000,000").
     */
    public static String intToStringWithCommas(int num) {
        // In C, a number like 0 is handled by the do-while loop.
        // In Java, it's cleaner to handle this as a special case.
        if (num == 0) {
            return "0";
        }

        // The C code uses a `char s[]` for mutable string operations.
        // The Java equivalent is `StringBuilder`.
        StringBuilder sb = new StringBuilder();

        // The C `itoa` function first records the sign and then works with the absolute value.
        boolean isNegative = num < 0;
        // We use `long` to safely handle the edge case of Integer.MIN_VALUE,
        // as Math.abs(Integer.MIN_VALUE) would overflow an int.
        long number = Math.abs((long) num);

        // This section mimics the C `itoa` function, which generates digits in reverse order.
        // C code: do { s[i++] = n % 10 + '0'; } while ((n /= 10) > 0);
        do {
            sb.append(number % 10);
        } while ((number /= 10) > 0);

        // At this point, for num = 7,000,000, sb contains "0000007".

        // This section mimics the C `insert_comma` logic. We insert a comma
        // every 3 characters in the reversed string. The C code's loop `i+=4`
        // accounts for the string's length increasing with each comma.
        for (int i = 3; i < sb.length(); i += 4) {
            sb.insert(i, ',');
        }

        // For "0000007", sb is now "000,000,7".

        // This section mimics the C `reverse` function.
        sb.reverse();

        // Finally, we add the negative sign at the beginning if the original number was negative.
        if (isNegative) {
            sb.insert(0, '-');
        }

        return sb.toString();
    }

    /**
     * The main method serves as a driver to test the `intToStringWithCommas` function,
     * similar to the `main` function in the C example.
     */
    public static void main(String[] args) {
        System.out.println("--- Testing Integer to Comma-Separated String Conversion ---");

        // Example from the C code's comment
        int num1 = 7000000;
        String str1 = intToStringWithCommas(num1);
        System.out.printf("Input: %d -> Output: %s%n", num1, str1);

        // Example from the C code's main function
        int num2 = 7000;
        String str2 = intToStringWithCommas(num2);
        System.out.printf("Input: %d -> Output: %s%n", num2, str2);

        // Test with a negative number
        int num3 = -1234567;
        String str3 = intToStringWithCommas(num3);
        System.out.printf("Input: %d -> Output: %s%n", num3, str3);

        // Test with a number whose length is a multiple of 3
        int num4 = 123456;
        String str4 = intToStringWithCommas(num4);
        System.out.printf("Input: %d -> Output: %s%n", num4, str4);

        // Test with a number that doesn't require commas
        int num5 = 999;
        String str5 = intToStringWithCommas(num5);
        System.out.printf("Input: %d -> Output: %s%n", num5, str5);
        
        // Test with the minimum integer value
        int num6 = Integer.MIN_VALUE;
        String str6 = intToStringWithCommas(num6);
        System.out.printf("Input: %d -> Output: %s%n", num6, str6);
    }
}