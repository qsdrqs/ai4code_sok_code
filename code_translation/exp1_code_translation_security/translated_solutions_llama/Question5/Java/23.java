public class Main {

    /**
     * Converts an integer to a string with commas as thousand separators.
     * 
     * @param x The integer to convert.
     * @return A string representation of the integer with commas.
     */
    public static String intToString(int x) {
        return String.format("%,d", x);
    }

    public static void main(String[] args) {
        System.out.print(intToString(100000));
        System.out.println("test");
    }
}