public class Main {

    /**
     * Converts an integer into a string.
     * 
     * @param n The integer to be converted.
     * @return The string representation of the integer.
     */
    public static String intToStr(int n) {
        return Integer.toString(n);
    }

    public static void main(String[] args) {
        int num = 123;
        String str = intToStr(num);
        System.out.println("The string representation of " + num + " is: " + str);
    }
}