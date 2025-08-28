/**
 * All dependencies are included in this single file.
 * No external libraries are needed beyond the Java standard library.
 */
public class IntToStringConverter {

    /**
     * Translates the C function `int2str` to Java.
     * <p>
     * This function converts a signed integer to a string representation
     * with commas as thousands separators.
     *
     * @param num The signed integer to convert.
     * @return A string representation of the number with commas.
     */
    public static String int2str(int num) {
        // In C: snprintf(NULL, 0, "%d", num) and snprintf(str, length + 1, "%d", num)
        // In Java, this is much simpler.
        String numStr = String.valueOf(num);

        // Handle negative numbers by separating the sign and processing the rest.
        boolean isNegative = num < 0;
        if (isNegative) {
            // Remove the '-' sign for processing
            numStr = numStr.substring(1);
        }

        int length = numStr.length();

        // If the number is short, no commas are needed.
        if (length <= 3) {
            // Return the original string version of the number.
            return String.valueOf(num);
        }

        // The C code's calculation for the new length is complex.
        // In Java, a StringBuilder is more efficient and safer for string manipulation.
        StringBuilder result = new StringBuilder();

        // The C code iterates backward. A forward iteration is more natural in Java.
        // First, calculate the length of the first group of digits (1, 2, or 3).
        int firstGroupLength = length % 3;
        if (firstGroupLength == 0) {
            firstGroupLength = 3;
        }

        // Append the first group.
        result.append(numStr.substring(0, firstGroupLength));

        // Now, append the remaining groups, each prefixed with a comma.
        for (int i = firstGroupLength; i < length; i += 3) {
            result.append(',');
            result.append(numStr.substring(i, i + 3));
        }

        // If the number was negative, prepend the '-' sign.
        if (isNegative) {
            result.insert(0, '-');
        }

        // In C: return result; (returning a char*)
        // In Java: convert the StringBuilder to a String.
        return result.toString();
    }

    /**
     * Main method for demonstration purposes.
     */
    public static void main(String[] args) {
        System.out.println("12345 -> " + int2str(12345));         // Expected: 12,345
        System.out.println("1234567 -> " + int2str(1234567));     // Expected: 1,234,567
        System.out.println("-98765 -> " + int2str(-98765));       // Expected: -98,765
        System.out.println("123 -> " + int2str(123));             // Expected: 123
        System.out.println("0 -> " + int2str(0));                 // Expected: 0
        System.out.println("-500 -> " + int2str(-500));           // Expected: -500
        System.out.println("100000 -> " + int2str(100000));       // Expected: 100,000
    }
}