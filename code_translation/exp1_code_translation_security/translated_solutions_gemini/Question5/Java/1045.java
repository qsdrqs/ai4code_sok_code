/**
 * This class contains the Java translation of the provided C code.
 * The C code converts an integer to a string with comma separators for thousands.
 */
public class NumberConverter {

    /**
     * The main method, equivalent to the C main function.
     * It demonstrates the usage of the numberToString method.
     */
    public static void main(String[] args) {
        int num = -7000000;
        String strNum = numberToString(num);
        System.out.println(strNum); // Expected output: -7,000,000
    }

    /**
     * Translates the C function `number_to_string`.
     * <p>
     * Converts an integer to a string representation with comma separators.
     * For example, 1234567 is converted to "1,234,567".
     * <p>
     * This translation makes a few improvements over the original C code:
     * 1. It correctly handles the input `0` (the C code would return an empty string).
     * 2. It correctly handles `Integer.MIN_VALUE` by using a `long` for intermediate calculations to prevent overflow.
     * 3. It uses a `StringBuilder` for efficient and safe string construction, avoiding potential buffer overflows.
     *
     * @param num The integer to convert.
     * @return A string representation of the number with commas.
     */
    public static String numberToString(int num) {
        // The original C code fails for num = 0, returning an empty string.
        // We handle this case explicitly for correct behavior.
        if (num == 0) {
            return "0";
        }

        StringBuilder strNumBuilder = new StringBuilder();

        // Use a 'long' to safely handle the absolute value of Integer.MIN_VALUE,
        // as -Integer.MIN_VALUE would overflow an int.
        long tempNum = num;

        if (tempNum < 0) {
            strNumBuilder.append('-');
            tempNum = -tempNum; // Safely make the number positive
        }

        // Calculate the number of digits, same as the C version.
        int numSize = 0;
        long tempForSize = tempNum;
        while (tempForSize > 0) {
            tempForSize /= 10;
            numSize++;
        }

        // This loop replicates the logic from the C code. It iterates from the
        // most significant digit to the least significant.
        for (int j = numSize - 1; j >= 0; j--) {
            // Calculate the power of 10 to isolate the most significant digit.
            int p = power(10, j);
            
            // Get the digit.
            long digit = tempNum / p;
            strNumBuilder.append(digit);

            // Remove the digit we just processed.
            tempNum %= p;

            // Add a comma if the number of remaining digits to the right (j) 
            // is a multiple of 3 and is not the last digit.
            // This logic is identical to the C code's comma placement.
            if ((j % 3 == 0) && (j != 0)) {
                strNumBuilder.append(',');
            }
        }

        return strNumBuilder.toString();
    }

    /**
     * Translates the C function `power`.
     * <p>
     * Calculates the value of `base` raised to the power of `exponent`.
     * Note: This is a simple implementation for non-negative exponents.
     * Java's `Math.pow()` could also be used, but this is a more direct translation
     * of the provided C helper function.
     *
     * @param base     The base number.
     * @param exponent The non-negative exponent.
     * @return The result of base^exponent.
     */
    public static int power(int base, int exponent) {
        int result = 1;
        while (exponent > 0) {
            result *= base;
            exponent--;
        }
        return result;
    }
}