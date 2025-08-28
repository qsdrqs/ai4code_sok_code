public class Main {

    // Corresponds to #define MAX_STR_LEN 128
    // In Java, StringBuilder manages its own capacity, so this is not strictly
    // necessary for functionality but is included for faithfulness to the original.
    private static final int MAX_STR_LEN = 128;

    /**
     * Reverses a given string, mimicking the C implementation.
     * @param str The string to be reversed.
     * @return A new string with the characters in reverse order.
     */
    public static String reverseStr(String str) {
        // In C, a new char array is allocated. In Java, we use a StringBuilder
        // for efficient string construction.
        int len = str.length();
        StringBuilder newStr = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            // Append characters from the end of the original string to the start.
            newStr.append(str.charAt(len - i - 1));
        }
        return newStr.toString();
    }

    /*
    Return a string representation of a signed integer

    Args:
        int num: input number as a signed integer
    
    Return:
        string representation of the signed integer
    */
    public static String strOfInt(int num) {
        // In C, a char buffer is allocated with malloc.
        // In Java, a StringBuilder is the idiomatic equivalent.
        StringBuilder str = new StringBuilder(MAX_STR_LEN);
        
        boolean isNeg = false;
        // Note: The original C code has a bug with Integer.MIN_VALUE.
        // -Integer.MIN_VALUE is still Integer.MIN_VALUE due to two's complement
        // overflow, which would cause an infinite loop. This translation
        // faithfully reproduces that behavior.
        if (num < 0) {
            isNeg = true;
            num = -num;
        }

        // The do-while loop correctly handles the case where num is 0.
        do {
            // Get the last digit (num % 10) and convert it to its character
            // representation by adding the ASCII value of '0'.
            str.append((char)((num % 10) + '0'));
            // Remove the last digit.
            num /= 10;
        } while (num != 0);

        if (isNeg) {
            str.append('-');
        }

        // The number is built in reverse order (e.g., 123 becomes "321").
        // We call reverseStr to get the correct order, just like the C code.
        return reverseStr(str.toString());
    }

    public static void main(String[] args) {
        // Corresponds to: printf(strOfInt(7000000));
        System.out.print(strOfInt(7000000));
        // Corresponds to: printf("\n");
        System.out.println();
    }
}