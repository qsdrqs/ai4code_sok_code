/**
 * A Java program to convert an integer into a string.
 */
public class IntToStringConverter {

    /**
     * Converts an integer to its string representation.
     * <p>
     * This method takes an integer and returns its string equivalent.
     * In Java, this is handled safely by built-in library functions.
     *
     * @param num The integer to convert.
     * @return The string representation of the integer.
     */
    public static String intToStr(int num) {
        // The most direct and standard way to convert an int to a String in Java
        // is by using the static method Integer.toString().
        return Integer.toString(num);
    }

    // --- Alternative Ways to Convert (for educational purposes) ---

    /**
     * Alternative method using String.valueOf().
     */
    public static String intToStrAlternative1(int num) {
        return String.valueOf(num);
    }

    /**
     * Alternative method using string concatenation (simple but can be less efficient).
     */
    public static String intToStrAlternative2(int num) {
        return "" + num;
    }


    /**
     * The main method to demonstrate the functionality.
     */
    public static void main(String[] args) {
        int number = -4567;
        String convertedString = intToStr(number);

        System.out.println("Original integer: " + number);
        System.out.println("Converted string: " + convertedString);

        // We can verify that the result is indeed a String object
        if (convertedString instanceof String) {
            System.out.println("The conversion was successful, and the result is a String.");
        }
    }
}