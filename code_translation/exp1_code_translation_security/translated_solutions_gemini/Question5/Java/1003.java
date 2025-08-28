/**
 * A utility class to demonstrate the C to Java translation.
 */
public class Converter {

    /**
     * Converts an integer to its string representation.
     *
     * This method is the Java equivalent of the C function `int2str`.
     *
     * @param num The integer to be converted.
     * @return The string representation of the integer.
     */
    public static String int2str(int num) {
        // In Java, converting an integer to a String is a standard, built-in operation.
        // The String.valueOf() method is the most direct and idiomatic way to do this.
        // It handles all integer values, including negative numbers and zero.
        //
        // This one line replaces the entire C implementation:
        // 1. No manual buffer (`char buf[11]`) is needed.
        // 2. The conversion is handled internally, replacing `snprintf`.
        // 3. Java's String is an object, and memory is managed by the Garbage Collector,
        //    so there is no need for `strdup` to copy the string to the heap.
        return String.valueOf(num);

        // An alternative, equally valid, and common method is:
        // return Integer.toString(num);
    }

    /**
     * Main method to demonstrate the usage of the int2str function.
     * This part is provided for testing and is not part of the direct translation.
     */
    public static void main(String[] args) {
        int myNumber = -456;
        String myString = int2str(myNumber);

        System.out.println("The original integer is: " + myNumber);
        System.out.println("The converted string is: " + myString);

        // To prove it's a string, we can concatenate it with another string.
        String newString = myString + " is now a string.";
        System.out.println(newString);
    }
}