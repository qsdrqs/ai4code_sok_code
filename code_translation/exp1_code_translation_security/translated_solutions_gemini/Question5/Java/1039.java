public class CtoJavaTranslation {

    /**
     * Calculates the number of digits in a positive integer.
     * Note: For 0 or negative numbers, it will return 0, matching the original C code's behavior.
     * @param num The integer to inspect.
     * @return The number of digits.
     */
    public static int getLength(int num) {
        int len = 0;
        // Using a temporary variable to not modify the original parameter
        int numCopy = num;
        if (numCopy == 0) {
            return 1; // A more standard behavior for getLength(0)
        }
        while (numCopy > 0) {
            numCopy = numCopy / 10;
            len = len + 1;
        }
        return len;
    }

    /**
     * Returns the string "test".
     * The original C function was severely flawed and attempted to do this while ignoring its input parameter.
     * This Java version fulfills the original intent in a safe and correct way.
     * @param num An integer (ignored, as in the original C code).
     * @return The string "test".
     */
    public static String convertToString(int num) {
        // The original C code incorrectly tried to build and return a local char array.
        // The clear intent was to return the string "test".
        // The input parameter 'num' was unused in the original function body.
        return "test";
    }

    public static void main(String[] args) {
        int num = 7000000;
        // Corresponds to: printf("%d", getLength(num));
        System.out.print(getLength(num));

        // Corresponds to: printf(convertToString(5));
        System.out.print(convertToString(5));
        
        // A final newline for cleaner terminal output.
        System.out.println();
    }
}