public class Main {

    /**
     * Converts an integer to a string, mimicking manual memory management.
     * 
     * @param x The integer to be converted.
     * @return A string representation of the integer.
     */
    public static String intToString(int x) {
        char[] str = new char[12]; // Allocate a char array
        int length = String.valueOf(x).length();
        String.valueOf(x).getChars(0, length, str, 0); // Copy characters to the array
        return new String(str, 0, length); // Create a string from the array
    }

    public static void main(String[] args) {
        int number = 123;
        String str = intToString(number);
        System.out.println(str); // Output: 123
    }
}