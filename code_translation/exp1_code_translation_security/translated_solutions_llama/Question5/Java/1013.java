public class Main {

    /**
     * Converts an integer to a string with commas.
     * 
     * @param num The number to convert.
     * @return A string representation of the number with commas.
     */
    public static String convert(int num) {
        return String.format("%,d", num);
    }

    public static void main(String[] args) {
        String ans = convert(700000);
        System.out.println(ans);
    }
}