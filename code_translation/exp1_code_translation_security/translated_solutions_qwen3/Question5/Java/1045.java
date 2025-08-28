public class NumberToStringConverter {

    public static void main(String[] args) {
        int num = -7000000;
        String strNum = numberToString(num);
        System.out.println(strNum);  // Output: -7,000,000
    }

    /**
     * Converts an integer to a string with commas as thousand separators.
     * 
     * @param num The integer to convert.
     * @return A string representation of the number with commas.
     */
    public static String numberToString(int num) {
        int numSize = 0;
        int temp = num;

        // Handle negative numbers
        if (num < 0) {
            temp = -num;
        }

        // Calculate the number of digits
        while (temp > 0) {
            temp /= 10;
            numSize++;
        }

        // Calculate how many commas are needed
        int commaCount = (numSize - 1) / 3;

        // Use StringBuilder to build the result string
        StringBuilder sb = new StringBuilder();

        // Append the negative sign if needed
        if (num < 0) {
            sb.append('-');
            num = -num;
        }

        // Build the string with digits and commas
        for (int j = numSize - 1; j >= 0; j--) {
            int digit = num / power(10, j);
            sb.append((char) ('0' + digit));
            num %= power(10, j);

            // Insert a comma after every 3 digits (except the last group)
            if (j % 3 == 0 && j != 0) {
                sb.append(',');
            }
        }

        return sb.toString();
    }

    /**
     * Computes the power of a base raised to an exponent.
     * 
     * @param base The base.
     * @param exponent The exponent.
     * @return The result of base^exponent.
     */
    private static int power(int base, int exponent) {
        int result = 1;
        while (exponent > 0) {
            result *= base;
            exponent--;
        }
        return result;
    }
}