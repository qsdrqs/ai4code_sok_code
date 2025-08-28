public class Main {

    /**
     * Returns the number of digits in the given integer.
     * 
     * @param num The input integer.
     * @return The number of digits in the integer.
     */
    public static int getLength(int num) {
        if (num == 0) {
            return 1;
        }
        int len = 0;
        int numCopy = Math.abs(num); // Handle negative numbers
        while (numCopy > 0) {
            numCopy = numCopy / 10;
            len = len + 1;
        }
        return len;
    }

    /**
     * Converts an integer to a string.
     * 
     * @param num The input integer.
     * @return The string representation of the integer.
     */
    public static String convertToString(int num) {
        // Directly convert to string using Integer.toString()
        return Integer.toString(num);
    }

    public static void main(String[] args) {
        int num = 7000000;
        System.out.print(getLength(num));
        System.out.print(convertToString(5));
    }
}