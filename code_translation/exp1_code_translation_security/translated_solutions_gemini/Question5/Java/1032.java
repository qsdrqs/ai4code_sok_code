/**
 * This class contains the Java translation of the provided C code.
 * The logic from the C functions has been preserved.
 * Java's automatic garbage collection replaces the manual memory management (malloc/free) from C.
 */
public class Main {

    /**
     * Translates the C function `int_to_string`.
     * Converts an integer to its string representation without using built-in library functions,
     * mirroring the manual approach in the C code.
     * Note: The original C code does not handle 0 or negative numbers correctly. This
     * translation adds a specific check for 0 and assumes positive integers, like the original.
     *
     * @param num The positive integer to convert.
     * @return The string representation of the number.
     */
    public static String intToString(int num) {
        if (num == 0) {
            return "0";
        }

        // Calculate the number of digits to determine the length of the string.
        int len = 0;
        int temp = num;
        while (temp != 0) {
            temp /= 10;
            len++;
        }

        // Create a character array to build the string.
        char[] strChars = new char[len];
        int n = num;

        // Populate the character array from right to left.
        for (int i = len - 1; i >= 0; i--) {
            strChars[i] = (char) ((n % 10) + '0');
            n /= 10;
        }

        return new String(strChars);
    }

    /**
     * Translates the C function `int_to_string_with_commas`.
     * It takes an integer, converts it to a string, and then inserts commas
     * as thousands separators. The logic is a direct port from the C implementation.
     *
     * @param num The integer to format.
     * @return A string representation of the number with commas.
     */
    public static String intToStringWithCommas(int num) {
        String str = intToString(num);
        int len = str.length();

        // If the number is 3 digits or less, no commas are needed.
        if (len <= 3) {
            return str;
        }

        int commaCount = (len - 1) / 3;
        
        // StringBuilder is the Java equivalent for efficient string building.
        StringBuilder newStr = new StringBuilder();

        int j = 0; // Index for the original string (str)
        int k = 0; // Flag to prevent consecutive commas, mimicking the C logic.

        // Loop to build the new string with commas.
        for (int i = 0; i < len + commaCount; i++) {
            // This condition checks if the number of remaining digits is a multiple of 3.
            // If so, and we are not at the start of the string, a comma is inserted.
            if ((len - j) % 3 == 0 && k != 1 && i != 0) {
                newStr.append(',');
                k = 1; // Set flag indicating a comma was just added.
            } else {
                newStr.append(str.charAt(j));
                j++;
                if (k == 1) {
                    k = 0; // Reset flag after adding a digit.
                }
            }
        }
        
        return newStr.toString();
    }

    /**
     * The main method, equivalent to the C main function.
     * It demonstrates the usage of the `intToStringWithCommas` method.
     */
    public static void main(String[] args) {
        int num = 7654321;
        String str = intToStringWithCommas(num);
        System.out.println(str);
        // In Java, there is no need to manually free memory like in C.
        // The Garbage Collector handles this automatically.
    }
}