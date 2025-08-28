public class Main {

    /**
     * Converts an integer into a string.
     * 
     * @param num The integer to be converted.
     * @return A string representation of the integer.
     */
    public static String int2str(int num) {
        return Integer.toString(num);
    }

    public static void main(String[] args) {
        // Example usage:
        int number = 123;
        String str = int2str(number);
        System.out.println(str);  // Outputs: 123
    }
}