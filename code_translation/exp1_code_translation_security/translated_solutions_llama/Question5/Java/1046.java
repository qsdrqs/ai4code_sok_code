public class Main {

    /**
     * Converts an integer to a string and prints it.
     * 
     * @param num The integer to convert.
     * @return The string representation of the integer.
     */
    public static String convert(int num) {
        String str = Integer.toString(num);
        System.out.print(str);
        return str;
    }

    public static void main(String[] args) {
        char c = convert(100).charAt(0);
        System.out.println();
        System.out.println("Character: " + c);
    }
}