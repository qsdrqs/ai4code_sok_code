public final class IntToString {

    /**
     * Returns the decimal string representation of the supplied integer.
     *
     * @param num the integer to convert
     * @return a string representing {@code num}
     */
    public static String int2str(int num) {
        // Same behavior as snprintf + strdup in C, but handled natively in Java.
        return Integer.toString(num);
    }
}