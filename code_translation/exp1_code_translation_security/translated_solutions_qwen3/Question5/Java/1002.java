public class Converter {

    /**
     * Converts a long integer into a formatted string with commas as thousand separators.
     * 
     * @param value the long integer to be formatted
     * @return a string representation of the number with commas
     */
    public static String convert(long value) {
        // Convert the long value to its string representation
        String strValue = Long.toString(value);

        // Use a StringBuilder to efficiently build the formatted string
        StringBuilder formatted = new StringBuilder();

        // Iterate through each character in the string
        for (int i = 0; i < strValue.length(); i++) {
            // Append the current character
            formatted.append(strValue.charAt(i));

            // Calculate the number of characters remaining after this one
            int remaining = strValue.length() - i - 1;

            // Insert a comma if the remaining count is divisible by 3 and not zero
            if (remaining % 3 == 0 && remaining != 0) {
                formatted.append(',');
            }
        }

        // Return the final formatted string
        return formatted.toString();
    }

    /**
     * Main method to test the convert function.
     * 
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Test the convert function with the value 1000
        String test = convert(1000);

        // Print the result
        System.out.println(test); // Expected output: 1,000
    }
}