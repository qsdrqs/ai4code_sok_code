public class Converter {

    /**
     * Converts an integer to its string representation.
     * This is the Java equivalent of the C function.
     *
     * @param x The integer to be converted.
     * @return The string representation of the integer.
     */
    public String inttostring(int x) {
        // In Java, the String.valueOf() method is the standard and most
        // efficient way to convert a primitive type, like an int, to a String.
        // It handles all memory allocation and formatting internally.
        return String.valueOf(x);
    }

    // An alternative, equally valid implementation:
    // public String inttostring(int x) {
    //     return Integer.toString(x);
    // }
}