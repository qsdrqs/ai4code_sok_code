/**
 * This class contains a direct translation of the C code's logic.
 */
public class CtoJavaConverter {

    /**
     * Converts a 64-bit integer (long) into a string with thousands separators.
     * This method directly translates the logic from the C implementation.
     *
     * @param value The long integer to convert.
     * @return A new string representing the number with commas.
     */
    public static String convert(long value) {
        // In C: snprintf(buf1, BUFFER_SIZE, "%ld", value);
        // In Java, we can simply convert the long to a String.
        String numberStr = String.valueOf(value);

        // In C: char *buf2 = calloc(BUFFER_SIZE, sizeof(char));
        // In Java, StringBuilder is the efficient way to build a new string.
        StringBuilder resultBuilder = new StringBuilder();

        int len = numberStr.length();
        for (int i = 0; i < len; i++) {
            // Append the current character
            resultBuilder.append(numberStr.charAt(i));

            // Calculate the distance from the end of the string
            int dist = len - i - 1;

            // If the distance is a multiple of 3 and we are not at the very end,
            // add a comma.
            if (dist > 0 && dist % 3 == 0) {
                resultBuilder.append(',');
            }
        }

        // In C, we would return buf2. In Java, we return the final built string.
        // The garbage collector handles memory management (no need for free()).
        return resultBuilder.toString();
    }

    /**
     * The main entry point for the program, equivalent to C's main function.
     */
    public static void main(String[] args) {
        // Test with 1000
        String test1 = convert(1000L);
        System.out.println(test1); // Expected output: 1,000

        // Additional test cases
        String test2 = convert(1234567L);
        System.out.println(test2); // Expected output: 1,234,567

        String test3 = convert(987L);
        System.out.println(test3); // Expected output: 987
        
        String test4 = convert(-54321L);
        System.out.println(test4); // Expected output: -54,321
    }
}