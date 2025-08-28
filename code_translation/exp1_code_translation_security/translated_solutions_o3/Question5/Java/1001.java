public final class Utils {

    /**
     * Converts an integer to its string representation.
     *
     * @param x the integer to convert
     * @return the string form of {@code x}
     */
    public static String intToString(int x) {
        // Java handles memory allocation for strings internally,
        // so we can simply delegate to Integer.toString().
        return Integer.toString(x);
    }

    // Example usage (can be removed if not needed):
    public static void main(String[] args) {
        int number = 12345;
        String str = intToString(number);
        System.out.println(str);  // prints "12345"
    }
}